
/* ---------------------------------------
	Filename: common.js
	Description : T-CORE
	Author	: mobigen
	date	: 2018-06-18
   --------------------------------------- */ 

//UI테스트용도
$( function() {


	//Gnb
	$(".gnb-menu li > ul").hide();
	$(".gnb-menu li a").click(function(){
		$(this).parent().find(">ul").slideDown();
		$(this).parent().addClass("active");
		$(this).parent().siblings().find("ul").slideUp();
		$(this).parent().siblings().removeClass("active").find(".active").removeClass("active");
	});
	
	//slim type	
	var $wrapper = $(".wrapper");
	var $gnbtoggle = $(".toolbar__side-icon");

	$gnbtoggle.on("click", function() {
		$wrapper.toggleClass('slim-type');
		return false;
	});


});


