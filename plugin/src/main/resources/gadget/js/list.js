var List = {};

List.defaultFavicon = 'http://farm1.static.flickr.com/95/232542416_10f214442c.jpg?v=0';

List.addLink = function(url, description) {
  $('#link-list')
    .append($('<div/>')
      .attr('class', 'link')
      .append($('<img/>')
        .attr('src', List.faviconFromUrl(url))
        .attr('alt', '')
        .attr('class', 'favicon'))
      .append($('<a/>')
        .attr('href', url)
        .attr('class', 'link-description')
				.attr('target', '_blank')
        .text(description))
    );
}

List.addDeliciousLinks = function(links) {
  $.each(links, function(i, link) {
    List.addLink(link.u, link.d);
  })
  gadgets.window.adjustHeight();
  List.fixUnloadedFavicons();
}

List.getDeliciousData = function(url, callback) {    
  $.getJSON(url + '?callback=?',
    function(data) {
      callback(data);
    });
}

List.faviconFromUrl = function(url) {
  return url.split('/').splice(0,3).join('/')+'/favicon.ico';
}

List.fixUnloadedFavicons = function (){
  $(".favicon").each(function(index,elt){
    if(!elt.naturalWidth)
      elt.src = List.defaultFavicon;
  });
}

List.log = function(msg) {
  console.log(msg);
}

List.clear = function() {
  $('#link-list div.link').remove();
}

List.addFetchedLinks = function(dataString) {  
  var isDelicious = function(url) {
    return url.indexOf(Links.deliciousFeedUrl) == 0;
  }; 
  var data = gadgets.json.parse(dataString);

  $.each(data.links, function() {
    if (isDelicious(this.url)) {
      List.getDeliciousData(this.url, List.addDeliciousLinks);
    } else {
      List.addLink(this.url, this.description); 
    }
  });
}

List.refresh = function() {
  List.clear();
  var projectId = $('#project-selector').val();
  Links.fetchLinks(projectId, List.addFetchedLinks);
}

List.renderProjectSelector = function(dataString) {
  var data = gadgets.json.parse(dataString);
  
  $("#projects").append(
    $('<select/>')
      .attr('id', 'project-selector')
      .change(List.refresh));
  
  $.each(data.projects, function() {
    var option = new Option(this.name, this.id);    
    var dropdownList = $('#project-selector')[0];
    dropdownList.add(option, null);
  });

  $('#project-selector').change();  
}

List.load = function() {
  Links.fetchProjects(List.renderProjectSelector);
}

gadgets.util.registerOnLoadHandler(List.load);

