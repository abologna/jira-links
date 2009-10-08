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
		List.refresh()
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

gadgets.util.registerOnLoadHandler(buttonsLoad);
