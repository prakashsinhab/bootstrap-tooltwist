
var Navs = function() {
	
	return {
		
		init: function() {
			jQuery(".navs-heading").unbind("click");
			
			$('.tabbable a[data-toggle="tab"]').click(function (e) {
				e.preventDefault();
		        $(this).tab('show');
		        var url = window.location.href;
		        var currentUrl = url.split("?");
		        if(currentUrl && currentUrl.length > 1){
		        	var paramString = currentUrl[1].split("#");
		        	if(currentUrl[0].charAt(currentUrl[0].length-1) === "/"){
		        		url = currentUrl[0].slice(0, -1);		
		        	}
		        	if(paramString && paramString.length > 1){
		        		var hash = paramString[1];
//		        		url = url + "#" + hash;
		        	}
		        }
		        window.location.href = url;
		        $(window).scrollTop(0);
		    });

		    // store the currently selected tab in the hash value
		    $("ul.nav-tabs > li > a").on("shown.bs.tab", function (e) {
		        var id = $(e.target).attr("href").substr(1);
		        window.location.hash = id;
		    });

		    // on load of the page: switch to the currently selected tab
		    var hash = window.location.hash;
		    var n = hash.search("webdesign");
		    if(n<=0){
		    	$('.tabbable a[href="' + hash + '"]').tab('show');
		    }
		    
		},
		
		selectItem: function(navsId, row) {
			TtPane_layout.hidePropertiesDialog();
			setTimeout(function() {
				return TtPane_layout.loadTheEditPane({
					op: "selectItem",
					index: row,
					w: navsId,
					currentState : getCurrentState()
				});
				
			}, 100);
		},
		
		insertItem: function(navsId) {
			return TtPane_layout.loadTheEditPane({
				op: "insertItem",
				w: navsId,
				currentState : getCurrentState()
			});
		},
		
		removeItem: function(navsId) {
			return TtPane_layout.loadTheEditPane({
				op: "removeItem",
				w: navsId,
				currentState : getCurrentState()
			});
		},
		
		expandAll: function(accordionId) {
			jQuery(".navs-body.collapse").css({"height": "auto", "display": "block"});
		},
		
		collapseAll: function(accordionId) {
			jQuery(".navs-body.collapse").css({"height": "0px", "display": "none"});
		}
		
	};
}();
jQuery(Navs.init());