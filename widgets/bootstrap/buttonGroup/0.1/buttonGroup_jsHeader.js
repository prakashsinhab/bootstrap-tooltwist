
var ButtonGroup = function() {
	
	return {
		
		selectItem: function(buttonGroupId, row) {
			TtPane_layout.hidePropertiesDialog();
			setTimeout(function() {
				return TtPane_layout.loadTheEditPane({
					op: "selectItem",
					index: row,
					w: buttonGroupId,
					currentState : getCurrentState()
				});
				
			}, 100);
		},
		
		insertItem: function(buttonGroupId) {
			return TtPane_layout.loadTheEditPane({
				op: "insertItem",
				w: buttonGroupId,
				currentState : getCurrentState()
			});
		},
		
		removeItem: function(buttonGroupId) {
			return TtPane_layout.loadTheEditPane({
				op: "removeItem",
				w: buttonGroupId,
				currentState : getCurrentState()
			});
		}
		
	};
}();