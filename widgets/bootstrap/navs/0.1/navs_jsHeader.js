
var Navs = function() {
	
	return {
		
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
		}
		
	};
}();