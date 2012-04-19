var absenceNS = new function() {
	/* Absence Model */
	var Absence = Backbone.Model.extend({
		/*validate: function(attrs) {
			if (attrs.start > attrs.end) {
				return {rc: 1001, message: "end must Not be before start"}
			}
			if (!users.get(attrs.userId)) {
				return {rc: 1002, message: "user must exist"}
			}
		},*/
		initialize: function() {
			this.on("error", function(model, error) {
				alert("Absence:" + JSON.stringify(model) + " " + JSON.stringify(error))
			})
		},
		startDate: function() {
			new Date(start)
		},
		endDate: function() {
			new Date(end)
		},
		url: '/absence'
	})
	/* User Model */
	var User = Backbone.Model.extend({
		/*validate: function(attrs) {
			if (attrs.name.length <= 1) {
				return {rc: 1001, message: "Name must be at least two characters"}
			}
		},*/
		initialize: function() {
			this.on("error", function(model, error) {
				alert("User:" + JSON.stringify(model) + " " + JSON.stringify(error))
			})
		},
		url: '/user'
	})
	
	/* List of User models List[User] */
	var Users = Backbone.Collection.extend({
		model : User,
		url : '/user'
	})

	/* List of Absence models List[Absence] */
	var Absences = Backbone.Collection.extend({
		model : Absence,
		url : '/absence'
	})

	this.users = new Users()
	this.absences = new Absences()

	var incommingUser = function(user) {
		var existingUser = users.get(user.id)
		if (existingUser) {
			existingUser.set({name: user.name})
		} else {
			users.add(new User(user))
		}
	}
	
	var incommingAbsence = function(absence) {
		var existingAbsence = absences.get(absence.id)
		if (existingAbsence) {
			existingAbsence.set({description: existingAbsence.name})
		} else {
			absences.add(new Absence(absence))
		}
	}

	var receiveEvent = function(jsonString) {
		$("#log").append("<div>" + jsonString + "</div>")
		var json = JSON.parse(jsonString)
		if (json.user) {
			incommingUser(json.user)
		} else if (json.userList) {
			var i = 0
			for(; i < json.userList.length; i++) {
				incommingUser(json.userList[i])
			}
		} else if (json.absence) {
			incommingAbsence(json.absence)
		} else if (json.absenceList) {
			var i = 0
			for(; i < json.absenceList.length; i++) {
				incommingAbsence(json.absenceList[i])
			}
		} else if (json.userDelete) {
			users.remove({id: json.userDelete.id})
		} else if (json.absenceDelete) {
			absences.remove({id: json.absenceDelete.id})
		}
	}
	var onopen = function() {
		console.log("connected")
		$("#log").append("<div>connected</div>")
	}
	var onclose = function() {
		console.log("closed")
		$("#log").append("<div>closed</div>")
	}	
	var sendMessage = function(endpoint, json) {
		sendMessage(endpoint, json, "POST")
	}
	
	var sendMessage = function(endpoint, json, method) {
		var jsonString = JSON.stringify(json)
		console.log("sending (" + method + ") " + endpoint + " -> " + jsonString)
		var result = $.ajax({
			type : method,
			contentType : "application/json",
			url : endpoint,
			async : false,
			data : jsonString
		})
		console.log("synchronous response: " + result.responseText)
		return result
	}
	
	var cometFallback = function() {
		$("#log").append("<iframe src='/comet/@sessionId'></iframe>");
	}
	this.cometMessage = function(event) {
		receiveEvent(event)
		console.log('Received comet event: ' + event)
	}
	var serverSentEvent = function(e) {
		console.log(e)
		receiveEvent(e.data)
	}
	var replaceAll = function(txt, replace, replacement) {
	  return txt.replace(new RegExp(replace, 'g'), replacement);
	}
	this.init = function() {
		var source = new EventSource('serverSentEvents/@sessionId');
		source.addEventListener('message', function(e) {
			console.log(e)
			// TODO fix crazy unescape, this string should not be escaped in the first place (Chrome only?)
			var json = e.data.substring(1, e.data.length - 1)
			json = replaceAll(json, "\\\\", "")
			console.log(json)
		  	receiveEvent(json)
		}, true);
		
		source.addEventListener('open', function(e) {
		  onopen()
		}, false);
		
		source.addEventListener('error', function(e) {
		  if (e.readyState == EventSource.CLOSED) {
		    onclose()
		  }
		}, false);
		/* User View */
		var UserView = Backbone.View.extend({
			tagName : "li",
			template: _.template($('#user-template').html()),
			events : {
				"click .liUserName"   : "edit",
				"click .liUserDelete" : "delete"
			},
			initialize : function() {
				this.model.bind('change', this.render, this);
				this.model.bind('remove', this.remove, this);
			},
			render : function() {
				this.$el.html(this.template(this.model.toJSON()));
				return this;
			},
			edit : function() {
				var newName = prompt("Please enter new name")
				if (newName) {
					this.model.set({name: newName})
					this.model.save()
				}
			},
			delete: function() {
				this.model.destroy()
			}
		});
		/* Absence View */
		var AbsenceView = Backbone.View.extend({
			tagName : "li",
			template: _.template($('#absence-template').html()),
			events : {
				"click .liAbsenceDescription" : "edit",
				"click .liAbsenceDelete" : "delete"
			},
			initialize : function() {
				this.model.bind('change', this.render, this)
				this.model.bind('remove', this.remove, this)
			},
			render : function() {
				this.$el.html(this.template(this.model.toJSON()));
				return this;
			},
			edit : function() {
				var newDescription = prompt("Please enter new description for Absence")
				if (newDescription) {
					this.model.set({description: newDescription})
					this.model.save()
				}
			},
			delete: function() {
				this.model.destroy()
			}
		});

		Backbone.sync = function(crud, model, options) {

		  var resp, url, json, method

		  switch (crud) {
		    case "create":  {
		    	url = model.url
		    	json = model
		    	method = "POST"
			  resp = sendMessage(url, json, method)
		    	break
		    }
		    case "read": {
		    	url = model.id ? model.url + "/" + model.id : model.url
		    			json = "{}"
		    	method = "GET"
			  resp = sendMessage(url, json, method)
		    	break
		    }
		    case "update":  {
		    	url = model.url
		    	json = model
		    	method = "PUT"                       
			  resp = sendMessage(url, json, method)
		    	break
		    }
		    case "delete": {
		    	url = model.url + "/" + model.id
		    	json = "{}"
		    	method = "DELETE"
			  resp = sendMessage(url, json, method)
		    	break
		    }
		  }

		  
		  if (resp.status == 200) {
		    options.success(JSON.parse(resp.responseText))
		  } else {
		    options.error(resp);
		  }
		  
		}
		$("#newUserRequestButton").click(function() {
			var u = new User({name: $("#newUserRequestName").val()})
			u.save()
			users.add(u)
		});
		$("#allUsersRequestButton").click(function() {
			var allUsersRequest = {}
			var endpoint = "/user"
			sendMessage(endpoint, allUsersRequest, "GET")
		});
		$("#allAbsenceRequestButton").click(function() {
			var allAbsenceRequest = {}
			var endpoint = "/absence"
			sendMessage(endpoint, allAbsenceRequest, "GET")
		});
		$("#newAbsenceRequestButton").click(function() {
			var a = new Absence({
				userId : parseInt($("#newAbsenceRequestUserId").val()),
				description : $("#newAbsenceRequestDescription").val(),
				start : parseInt($("#newAbsenceRequestStart").val()),
				end : parseInt($("#newAbsenceRequestEnd").val())
			})
			a.save()
			absences.add(a)
		});
		$("#currentWeekRequestButton").click(function() {
		    var currentWeekRequest = {}
		    var endpoint = "/view/current"
		    sendMessage(endpoint, currentWeekRequest, "GET")
		});

		this.users.on("add", function(user) {
			var view = new UserView({
				model : user
			});
			$("#user-list").append(view.render().el);
		})

		this.absences.on("add", function(absence) {
			var view = new AbsenceView({
				model : absence
			});
			$("#absence-list").append(view.render().el);
		})
	}
}