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
//			this.on("error", function(model, error) {
//				alert("Absence:" + JSON.stringify(model) + " " + JSON.stringify(error))
//			})
		},
        toEvent: function() {
        	return {
	              "id":this.get("id"),
	              "title":this.get("description"),
	              "start":this.get("start")/1000,
	              "end":this.get("end")/1000}
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
//			this.on("error", function(model, error) {
//				alert("User:" + JSON.stringify(model) + " " + JSON.stringify(error))
//			})
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
        toEvents: function() {
        	var events = []
        	this.forEach(function(a) {
        		events.push(a.toEvent())
        	})
        	return events
        },
		model : Absence,
		url : '/absence'
	})

	this.users = new Users()
	this.absences = new Absences()

	var incommingUser = function(user) {
		var existingUser = absenceNS.users.get(user.id)
		if (existingUser) {
			existingUser.set({name: user.name})
		} else {
			absenceNS.users.add(new User(user))
		}
	}
	
	var incommingAbsence = function(absence) {
		var existingAbsence = absenceNS.absences.get(absence.id)
		if (existingAbsence) {
			existingAbsence.set({description: absence.description,
				start: absence.start,
				end: absence.end})
		} else {
			absenceNS.absences.add(new Absence(absence))
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
			absenceNS.users.remove({id: json.userDelete.id})
		} else if (json.absenceDelete) {
			absenceNS.absences.remove({id: json.absenceDelete.id})
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
	this.init = function(sessionId) {
		var source = new EventSource('serverSentEvents/' + sessionId);
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
		var EventView = Backbone.View.extend({
			initialize: function(){
				_.bindAll(this);
			},
		    render: function() {
		        this.$el.dialog({
		            modal: true,
		            title: 'New/Edit Event',
		            buttons: {'Ok': this.save, 'Cancel': this.close},
		        	open: this.open
		        });
		 
		        return this;
		    },
		    open: function() {
		        this.$('#title').val(this.model.get('description'));
		        this.$('#color').val(this.model.get('userId'));
		    },
		    close: function() {
		    	this.$el.dialog('close');
		    },
		    save: function() {
		    	this.model.set({userId: 1, description: this.$('#title').val()}).save();
		    	this.close();
		    }
		});

		var EventsView = Backbone.View.extend({
	        initialize: function(){
	            this.collection.bind('reset', this.addAll, this);
	            this.collection.bind('add', this.addOne, this);
	            this.collection.bind('change', this.change, this);
	        },
	        change: function(absenceEvent) {
	            var fcEvent = this.$el.fullCalendar('clientEvents', absenceEvent.get('id'))[0];
	            fcEvent.title = absenceEvent.get('description');
	            fcEvent.color = absenceEvent.get('userId');
	            fcEvent.start = absenceEvent.get('start') / 1000;
	            fcEvent.end = absenceEvent.get('end') > 0 ? absenceEvent.get('end') / 1000 : 0;
	            this.$el.fullCalendar('updateEvent', fcEvent);
	        },
		    addOne: function(absence) {
		        this.$el.fullCalendar('renderEvent', absence.toEvent());
		    },
	        render: function() {
	            this.$el.fullCalendar({
	                header: {
	                    left: 'prev,next today',
	                    center: 'title',
	                    right: 'month,basicWeek,basicDay',
	                    ignoreTimezone: false
	                },
	                selectable: true,
	                selectHelper: true,
	                editable: true,
	                weekends: false,
	                select: this.select,
	                eventClick: this.eventClick,
	                eventDrop: this.eventDropOrResize,
	                eventResize: this.eventDropOrResize

	            });
	        },
	        eventDropOrResize: function(fcEvent) {
	        	absenceNS.absences.get(fcEvent.id).save(
	        			{
	        				start: fcEvent.start.getTime(),
	        				end: fcEvent.end ? fcEvent.end.getTime() : 0});
	        },
	        eventClick: function(fcEvent) {
	            var absence = absenceNS.absences.get(fcEvent.id);
	            var eventView = new EventView({el: $('#eventDialog'), model: absence});
	            eventView.render();
	        },
	        addAll: function() {
	          this.$el.fullCalendar('addEventSource', this.collection.toEvents());
	        },
	        select: function(startDate, endDate) {
   	            var eventView = new EventView({el: $('#eventDialog'),
   	            	model: new Absence({start: startDate.getTime(), end: endDate.getTime()})});
	            eventView.render();

	        }
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

		$("#newUserRequestButton").click(function() {
			var u = new User({name: $("#newUserRequestName").val()})
			u.save()
			absenceNS.users.add(u)
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
			absenceNS.absences.add(a)
		});
		$("#currentWeekRequestButton").click(function() {
		    var currentWeekRequest = {}
		    var endpoint = "/view/current"
		    sendMessage(endpoint, currentWeekRequest, "GET")
		});
//		$('#calendar').fullCalendar({
//			weekends: false // will hide Saturdays and Sundays
//		})
//    
//		$('#calendar').fullCalendar({
//			// put your options and callbacks here
//		})
    
		new EventsView({el: $("#calendar"), collection: this.absences}).render();
		this.absences.fetch();

	}
}