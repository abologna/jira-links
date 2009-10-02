$("document").ready(function(){

  $("#new-link").click(function(){
    $("#new-delicious-link-form").fadeOut(function(){
      $("#new-link-form").fadeIn("slow");
    });
  });

  $("#new-delicious-link").click(function(){
    $("#new-link-form").fadeOut(function(){
      $("#new-delicious-link-form").fadeIn("slow");
    });
  });

});
