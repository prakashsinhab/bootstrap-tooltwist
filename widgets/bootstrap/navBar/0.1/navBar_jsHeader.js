
var NavBar = function() {
	return {
		
		init: function() {
			jQuery(".accordion-heading").unbind("click");
//			jQuery( document ).tooltip();
		},
		
		selectNavBar: function(accordionId, row) {
			TtPane_layout.hidePropertiesDialog();
			setTimeout(function() {
				return TtPane_layout.loadTheEditPane({
					op: "selectNavBar",
					index: row,
					w: accordionId
				});
				
			}, 100);
		},
		
		insertNavBar: function(accordionId) {
			return TtPane_layout.loadTheEditPane({
				op: "insertNavBar",
				w: accordionId
			});
		},
		
		removeNavBar: function(accordionId) {
			return TtPane_layout.loadTheEditPane({
				op: "removeNavBar",
				w: accordionId
			});
		},
		
		loadNavpoint: function(accordionId) {
			return TtPane_layout.loadTheEditPane({
				op: "loadNavpoint",
				w: accordionId
			});
		},
		
		expandAll: function(accordionId) {
			jQuery(".accordion-body.collapse").css({"height": "auto", "display": "block"});
		},
		
		collapseAll: function(accordionId) {
			jQuery(".accordion-body.collapse").css({"height": "0px", "display": "none"});
		}
	};
}();

jQuery(NavBar.init()); // Run after page loads