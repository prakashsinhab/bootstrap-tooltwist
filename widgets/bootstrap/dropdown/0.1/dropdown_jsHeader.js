
var DropDown = function() {
	
	return {
		
		selectItem: function(dropDownId, row) {
			setTimeout(function() {
				return TtPane_layout.loadTheEditPane({
					op: "selectItem",
					index: row,
					w: dropDownId,
					currentState : getCurrentState()
				});
				
			}, 100);
			
		},
		
		insertItem: function(dropDownId, dropDownIndex) {
			return TtPane_layout.loadTheEditPane({
				op: "insertItem",
				w: dropDownId,
				index: dropDownIndex,
				currentState : getCurrentState()
			});
		},
		
		removeItem: function(dropDownId, dropDownIndex) {
			return TtPane_layout.loadTheEditPane({
				op: "removeItem",
				w: dropDownId,
				index: dropDownIndex,
				currentState : getCurrentState()
			});
		}
		
	};
}();