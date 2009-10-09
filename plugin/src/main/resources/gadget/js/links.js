var Links = {};
Links.server = 'http://localhost:8080/rest/rest-api/0.1'
Links.deliciousFeedUrl = 'http://feeds.delicious.com/v2/json/';

Links.showSpinner = function () {}
Links.hideSpinner = function () {}
Links.alertError = function() {}

Links.validate = function(url){
	//TODO check that code returned from url is in the 200 range
	return url;
}

Links.saveLink = function (project, link, description) {
	var call = {}
	
	call.type = "POST"
	call.url = Links.server + '/links'
	
	call.data = {}
	call.data.url = Links.validate(link)
	call.data.description = description
	call.data.project = project
	
	call.beforeSend  = Links.showSpinner
	call.complete = Links.hideSpinner
	call.error = Links.alertError
	call.success = Links.linkSaved
	
	$.ajax(call)
}
Links.linkSaved = function (){}

Links.fetchLinks = function (project, callback) {
	var call = {};
	
	call.type = "GET";
	call.url = Links.server + "/links";
	
	call.data = {};
	call.data.project = project;
	
	call.beforeSend  = Links.showSpinner;
	call.complete = Links.hideSpinner;
	call.error = Links.alertError;
	call.success = callback;
	
	$.ajax(call);
}

Links.deleteLink = function (project, url) {
	var call = {}
	
	call.type = "POST"
	call.url = Links.server + "/link/remove"
	
	call.data = {}
	call.data.project = project
	call.data.url = url
	
	call.beforeSend  = Links.showSpinner
	call.complete = Links.hideSpinner
	call.error = Links.alertError
	call.success = Links.linkDeleted
	
	$.ajax(call)
}
Links.linkDeleted = function() {}

Links.fetchProjects = function(callback) {
	var call = {}
	
	call.type = "GET";
	call.url = Links.server + "/projects";
	
	call.beforeSend  = Links.showSpinner;
	call.complete = Links.hideSpinner;
	call.error = Links.alertError;
	call.success = callback;
	
	$.ajax(call);
}

Links.validateUrl = function(url, callback) {
  var params = {};
  params[gadgets.io.RequestParameters.CONTENT_TYPE] = gadgets.io.ContentType.HTML;
  
  var handleResponse = function(data) {
    if (data.rc == 200) {
      data.text.search(/<\s*title\s*>([\w\W]*)<\s*\/title\s*>/i);
      var title = RegExp.$1;
      callback(true, title);
    } else if (data.rc < 400) {
      callback(true, '');
    } else {
      callback(false);
    }
  }
  gadgets.io.makeRequest(url, handleResponse, params);	
}