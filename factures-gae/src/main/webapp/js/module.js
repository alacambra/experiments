var invoice = {
    price : 0,
    day : 0,
    month : 0,
    year : 0,
    concept : "",
    bg : 0,

    getFormattedDate : function() {
        "use strict";
        return this.day + "/" + this.month + "/" + this.year;
    }
};

function FormViewModel() {"use strict";
    var self = this;
    self.inv = Object.create(invoice);
    self.inv.price = ko.observable();
    self.inv.day = ko.observable();
    self.inv.month = ko.observable();
    self.inv.year = ko.observable();
    self.inv.concept = ko.observable();
    self.inv.bg = ko.observable();

    self.send = function() {
        self.inv.price(self.inv.price() + 2);
        
        var toSend = Object.create(invoice);
        toSend.price   = self.inv.price();
        toSend.day     = self.inv.day();
        toSend.month   = self.inv.month();
        toSend.year    = self.inv.year();
        toSend.concept = self.inv.concept();
        toSend.bg    = self.inv.bg();
        
        self.ajax("localhost", 'PUT', toSend).done(function() {
            self.inv.concept("done!")
        });
    };

    self.tasksURI = 'http://localhost:5000/todo/api/v1.0/tasks';
    self.username = "";
    self.password = "";
    self.tasks = ko.observableArray();

    self.ajax = function(uri, method, data) {
        var request = {
            url : uri,
            type : method,
            contentType : "application/json",
            accepts : "application/json",
            cache : false,
            dataType : 'json',
            data : JSON.stringify(data),
            beforeSend : function(xhr) {
                xhr.setRequestHeader("Authorization", "Basic " + btoa(self.username + ":" + self.password));
            },
            error : function(jqXHR) {
                console.log("ajax error " + jqXHR.status);
            }
        };
        return $.ajax(request);
    };

    self.add = function(task) {
        self.ajax(self.tasksURI, 'POST', "task").done(function(data) {
        });
    };
}

var formViewModel = new FormViewModel();
ko.applyBindings(formViewModel); 