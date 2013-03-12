
var Carousel = function() {
	
	return {
		
		selectItem: function(carouselId, row) {
			TtPane_layout.hidePropertiesDialog();
			setTimeout(function() {
				return TtPane_layout.loadTheEditPane({
					op: "selectItem",
					index: row,
					w: carouselId,
					currentState : getCurrentState()
				});
				
			}, 100);
		},
		
		insertItem: function(carouselId) {
			return TtPane_layout.loadTheEditPane({
				op: "insertItem",
				w: carouselId,
				currentState : getCurrentState()
			});
		},
		
		removeItem: function(carouselId) {
			return TtPane_layout.loadTheEditPane({
				op: "removeItem",
				w: carouselId,
				currentState : getCurrentState()
			});
		}
		
	};
}();