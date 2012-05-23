var absenceNS = new function() {
	/* Absence Model */
	var Absence = Backbone.Model.extend({
        toEvent: function() {
        	return {
	              "id": this.get("id"),
	              "title": this.get("description"),
	              "start": this.get("start") / 1000,
	              "end": this.get("end") / 1000}
        },
		url: '/absence'
	})
	/* User Model */
	var User = Backbone.Model.extend({
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
			case "create": {
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
			case "update": {
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
		        this.$('#userId').val(this.model.get('userId'));
		    },
		    close: function() {
		    	this.$el.dialog('close');
		    },
		    save: function() {
		    	this.model.set({userId: 1, description: this.$('#title').val()}).save();
		    	this.close();
		    },
		    destroy: function(event) {
		        this.$el.fullCalendar('removeEvents', event.id);
		    }
		});

		var EventsView = Backbone.View.extend({
	        initialize: function(){
	            this.collection.bind('reset', this.addAll, this);
	            this.collection.bind('add', this.addOne, this);
	            this.collection.bind('change', this.change, this);
	            this.collection.bind('destroy', this.destroy, this);
	        },
	        change: function(absenceEvent) {
	            var fcEvent = this.$el.fullCalendar('clientEvents', absenceEvent.get('id'))[0];
	            fcEvent.title = absenceEvent.get('description');
	            // fcEvent.color = "pink"; // TODO
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
	                eventResize: this.eventDropOrResize,
	                events: "http://www.google.com/calendar/feeds/en.swedish%23holiday%40group.v.calendar.google.com/public/basic"

	            });
	        },
			eventDropOrResize : function(fcEvent) {
				absenceNS.absences.get(fcEvent.id).save({
					start : fcEvent.start.getTime(),
					end : fcEvent.end ? fcEvent.end.getTime() : 0
				});
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
    
		new EventsView({el: $("#calendar"), collection: this.absences}).render();
		this.absences.fetch();

	}
}