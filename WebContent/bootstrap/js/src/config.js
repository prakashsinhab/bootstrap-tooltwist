/*
 * 
 * Global Javascript Configuration
 *
 */

var config = (function() {
  // variables
  //var abc = 123;

  // object
  return {
    init: function() {
      
      config.animateNav();
      config.fitBannerToScreenHeight();
      
    },
    
    fitBannerToScreenHeight : function () {
      jQuery(window).on("resize load", function() {
        var winHeight = jQuery(window.top).height() - jQuery(".navbar").height();
        var winWidth = jQuery(window).width();
        var bannerHeight = jQuery(".carousel-inner>.item.active img").height();
        if ( winWidth < 1290 ) {
          jQuery(".carousel-inner").height(bannerHeight);
        } else {
          jQuery(".carousel-inner").height(winHeight);
        }
      });
    },
    
    animateNav : function () {
      
      jQuery(window).on("scroll", function() {
    	  
        if ( jQuery(this).scrollTop() > 0 ) {
          jQuery(".navbar-brand img, .navbar-nav>li>a, .navbar-toggle, .sub-nav").addClass("animate");
        } else {
          jQuery(".navbar-brand img, .navbar-nav>li>a, .navbar-toggle, .sub-nav").removeClass("animate");
        }
        
      });
      
    },

    //--------
    nocomma: null
  };
}());

// Init after the page has loaded
jQuery(config.init);