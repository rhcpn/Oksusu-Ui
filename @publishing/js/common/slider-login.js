/* ---------------------------------------
	Filename: slider-login.js
	Description :T-CORE
	Author	: mobigen
	date	: 2017-08-02
	비고 : 통합로그인 visual
   --------------------------------------- */ 
jQuery(function(){	
	var $slist = $("ul.slider-list");
	var size = $slist.children().outerWidth();
	var len =  $slist.children().length;
	var speed = 3000;
	var timer = null;
	var auto = true;
	var cnt = 1;

	$slist.css("width",len*size);

	if(auto) timer = setInterval(autoSlide, speed);

	$(".slider-number").children().bind({
		"mouseover": function(){
			var idx = $(".slider-number").children().index(this);
			cnt = idx;
			autoSlide();
			if(!auto) return false;
			clearInterval(timer);
			auto = false;
		},
		"mouseleave": function(){
			timer = setInterval(autoSlide, speed);
			auto = true;
		}
	});		


	function autoSlide(){
		if(cnt>len-1){
			cnt = 0;
		}

		$slist.animate({'left': -(cnt*size)+'px' },100);
		$(".slider-number li a").removeClass("on");
		$(".slider-number li a").eq(cnt).addClass("on");

		cnt++;
	}
});