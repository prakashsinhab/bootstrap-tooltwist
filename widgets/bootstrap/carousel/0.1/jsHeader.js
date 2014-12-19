var Carousel = function() {
	return {
		init : function() {
			
			$('.carousel').carousel({
				  interval: 5000
			});
			
		},
	};
}();
jQuery(Carousel.init());
