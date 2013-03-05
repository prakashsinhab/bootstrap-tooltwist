
var ButtonDropDown = function() {
	
	return {
		
		selectItem: function(buttonDropDownId, row) {
			TtPane_layout.hidePropertiesDialog();
			setTimeout(function() {
				return TtPane_layout.loadTheEditPane({
					op: "selectItem",
					index: row,
					w: buttonDropDownId,
					currentState : getCurrentState()
				});
				
			}, 100);
		},
		
		insertItem: function(accordionId) {
			return TtPane_layout.loadTheEditPane({
				op: "insertItem",
				w: accordionId,
				currentState : getCurrentState()
			});
		},
		
		removeItem: function(accordionId) {
			return TtPane_layout.loadTheEditPane({
				op: "removeItem",
				w: accordionId,
				currentState : getCurrentState()
			});
		}
		
	};
}();