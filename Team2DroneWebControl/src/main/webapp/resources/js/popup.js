jQuery(document).ready(function($){
	//open popup
	$('.btn-danger').on('click', function(event){
		event.preventDefault();
		console.log(deleteInfo);
		$('.cd-popup').addClass('is-visible');
	});
	
	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	
	$('.cd-close-btn').on('click', function(event){
			$('.cd-popup').removeClass('is-visible');
	});
	//close popup when clicking the esc keyboard button
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$('.cd-popup').removeClass('is-visible');
	    }
    });
});