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

$("document").ready(function(){
  List.addLink('http://www.google.com', 'Some custom added link');
  List.addLink('http://www.sf.net', 'Example link');
  List.addLink('http://mail.google.com', 'Example link 2');
  
  List.getDeliciousData(
      'http://feeds.delicious.com/v2/json/bologna/opensocial',
      8,
      List.addDeliciousLinks);
});

window.onload = List.fixUnloadedFavicons

