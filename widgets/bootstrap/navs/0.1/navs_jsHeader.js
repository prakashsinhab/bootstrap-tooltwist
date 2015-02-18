
var Navs = function() {
	
	return {
		
		init: function() {
			jQuery(".navs-heading").unbind("click");
			
			$('.tabbable a').click(function (e) {
		        e.preventDefault();
		        $(this).tab('show');
		        window.location.href = window.location.href.split("/?")[0];
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