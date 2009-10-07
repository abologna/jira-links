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
        .text(description))
    );
}

List.addDeliciousLinks = function(links) {
  $.each(links, function(i, link) {
    List.addLink(link.u, link.d);
  })
  gadgets.window.adjustHeight();
}

List.getDeliciousData = function(url, count, callback) {    
  $.getJSON(url + '?count='+count+'&callback=?',
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
      elt.src = List.defaultFavicon
  });
}

List.log = function(msg) {
  console.log(msg);
}

List.clear = function() {
  $('#link-list').empty();
}

List.addFetchedLinks = function(dataString) {
  console.log(dataString);
  
  // TODO: Remove when backend returns links by project. Just for testing.
  var stub = {links:[
    {url:'http://mail.google.com', description:'GMail Inbox', delicious:false},
    {url:'http://www.google.com', description:'Google Search', delicious:false},
    {url:'http://feeds.delicious.com/v2/json/bologna/opensocial', description:'', delicious:true}]};
    
  List.clear();
  
  $.each(stub.links, function() {
    if (this.delicious) {
      List.getDeliciousData(this.url, 10, List.addDeliciousLinks);
    } else {
      List.addLink(this.url, this.description); 
    }
  });
}

List.onProjectChange = function() {
  var id = $(this).val();
  console.log('changed to : ' + id);
  Links.fetchLinks(id, List.addFetchedLinks);
}

List.renderProjectSelector = function(dataString) {
  var data = gadgets.json.parse(dataString);
  
  $("#projects").append(
    $('<select/>')
      .attr('id', 'project-selector')
      .change(List.onProjectChange));
  
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

