
var Navs = function() {
	
	return {
		
		init: function() {
			jQuery(".navs-heading").unbind("click");
			//jQuery( document ).tooltip();
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