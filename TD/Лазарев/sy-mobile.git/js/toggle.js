$(document).ready(function(){
/* 

	$(".lang-btn, .lang-menu").hover(
		function() {
			$(".lang-menu").addClass("visible");
		}, function() {
			$(".lang-menu").removeClass("visible");
		}
	);
 */
	// popup login
	$(".a-login").click(function(){
		$(".popup-login").addClass("visible");
		$(".popup-blacklayer").addClass("visible");
		setTimeout(function() {
			$(".inp-login-autofocus").focus();
		}, 250);
	});

	// popup close
	$(".popup-cover").click(function(){
		$(".popup-cover").removeClass("visible");
		$(".popup").removeClass("visible");
		$("#user-nav-btn").removeClass("active");
	});

	$(".btn-close-popup").click(function(){
		$(".popup-blacklayer").removeClass("visible");
		$(".popup").removeClass("visible");
	});


	// banner close
	$(".close-ad").click(function(){
		$(this).parent().addClass("hide");
	});


	// flyer btn
	$(".flyer-container").click(function(){
		$(".flyer-fix-container").addClass("visible");
	});

	// flyer close
	$(".flyer-fix-container").click(function(){
		$(this).removeClass("visible");
	});



	// show more video info btn
	$("#show-more-video-info").click(function(){
		$(this).toggleClass("active");
		$(".video-about-addition-block").toggleClass("visible");
	});

	// show more comments btn
	$("#show-more-comments").click(function(){
		$(this).toggleClass("active");
		$(".comments-list").toggleClass("visible");
	});

	// show user nav btn
	$("#user-nav-btn").click(function(){
		$(this).toggleClass("active");
		$(".popup-cover").toggleClass("visible");
		$(".popup-user-nav").toggleClass("visible");
	});

	// sub nav btn
	$("#sub-nav-btn").click(function(){
		$(".popup-cover").toggleClass("visible");
		$(".popup-leftnav").toggleClass("visible");
	});


	// Broadcasts page tabs
	$(".toggle-btn-1").click(function(){
		$(".toggle-group .toggle-item").removeClass("active");
		$(this).addClass("active");
		$(".toggle-content-1").addClass("active");
		$(".toggle-content-2").removeClass("active");
		$(".toggle-content-3").removeClass("active");
	});

	$(".toggle-btn-2").click(function(){
		$(".toggle-group .toggle-item").removeClass("active");
		$(this).addClass("active");
		$(".toggle-content-1").removeClass("active");
		$(".toggle-content-2").addClass("active");
		$(".toggle-content-3").removeClass("active");
	});

	$(".toggle-btn-3").click(function(){
		$(".toggle-group .toggle-item").removeClass("active");
		$(this).addClass("active");
		$(".toggle-content-1").removeClass("active");
		$(".toggle-content-2").removeClass("active");
		$(".toggle-content-3").addClass("active");
	});

	//swipe leftbar from edge of device and swipe between tabs

	/*$("body").on("swiperight",function(e){
		if ( e.swipestart.coords[0] < 50) {
			$(".popup-leftnav").addClass('visible');
			$(".popup-cover").addClass("visible");
		} else {
			var nextpage;
			if ($('.broadcast-page')) {
				nextpage = "videoselfie.html";
			} else if ($('.adultonly-page')) {
				nextpage = "broadcast.html";
			}
			$.mobile.changePage( nextpage, { transition: "slide", changeHash: false, reverse:true });
		}
	});

	$("body").on("swipeleft",function(e){
		if ($(".popup-leftnav").hasClass('visible')) {
			$(".popup-leftnav").removeClass("visible");
			$(".popup-cover").removeClass("visible");
		} else {
			var nextpage;
			if ($('.videoselfie-page')) {
				nextpage = "broadcast.html";
			} else if ($('.broadcast-page')) {
				nextpage = "adultonly.html";
			}
			$.mobile.changePage( nextpage, { transition: "slide", changeHash: false });
		}
	});*/
});


