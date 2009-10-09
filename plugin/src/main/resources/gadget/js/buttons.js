function buttonsLoad(){

  $("#new-link").click(function(){
    $("#new-delicious-link-form").fadeOut(function(){
			$('#link-list').fadeOut(function(){
				$("#new-link-form").fadeIn("slow");
			});
    });
  });

  $("#new-delicious-link").click(function(){
    $("#new-link-form").fadeOut(function(){
				$('#link-list').fadeOut(function(){
					$("#new-delicious-link-form").fadeIn("slow");
			});
    });
  });

	$('input.cancel').click(function(){
		$("#new-link-form").fadeOut(function(){
			$("#new-delicious-link-form").fadeOut(function(){
				$('#link-list').fadeIn('fast')
			});
		});
	});
	
	$('#submitLink').click(function(){
		var project = $('#project-selector').val();
		var url = $('#link').val();
		var desc = $('#desc').val();
		Links.saveLink(project,url,desc);
		$($('input.cancel')[0]).click();		
		List.refresh();
	});
	
    $('#link').blur(function() {
      $('em.error').remove();
      $('#desc').attr('disabled', true);
      $('#submitLink').attr('disabled', true);
      addSpinner($('#link'), 'link-spinner');
	  
	  Links.validateUrl(this.value, 
	    function(success, data) {
	      if(success) {
	        $('#desc')
	          .attr('disabled', false)
	          .val(data);
            $('#submitLink').attr('disabled', false);
	      } else {
	        $('<em/>')
	          .attr('class', 'error')
	          .text('Broken Link')
	          .insertAfter('#link');
	      }
	      removeSpinner('link-spinner');
	    });
	});
	
	$('#submitDelicious').click(function(){
		var project = $('#project-selector').val();
		var url = Links.deliciousFeedUrl;
		url = url + $('#user').val() + '/' + $('#tag').val();
		Links.saveLink(project,url,'del.icio.us');
		$($('input.cancel')[0]).click();
		List.refresh()
	});
	
	$('div.link').live('mouseover', function(e){
		$(this).addClass('hover');
		$(this).children('a.trash').fadeIn()
	});
	
	$('div.link').live('mouseout', function(e){
		$(this).removeClass('hover');
		$(this).children('a.trash').fadeOut();
	});
	
	$('a.trash').live('click',function(){
		if(confirm("Are you sure?")){
			Links.deleteLink($('#project-selector').val(),$(this).prev('a').attr('href'))
			setTimeout(List.refresh,100)
		}
	})
}

function addSpinner(element, newId) {
  $('<img/>')
    .attr('id', newId)
    .attr('src','http://github.com/fernandezpablo85/Links/raw/master/plugin/src/main/resources/gadget/img/ajax-loader.gif')
    .attr('border','0')
    .insertAfter(element);
}

function removeSpinner(id) {
  $('#'+id).remove();
}

gadgets.util.registerOnLoadHandler(buttonsLoad);
