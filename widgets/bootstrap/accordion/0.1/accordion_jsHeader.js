var TtAccordion = function(){
	return {
		
		init: function() {
			jQuery(".accordion-heading").unbind("click");
			
		},
		
		selectAccordion: function(accordionId, row) {
			
			return TtPane_layout.loadTheEditPane({
				op: "selectAccordion",
				index: row,
				w: accordionId
			});
		},
		
		insertAccordion: function(accordionId) {
			return TtPane_layout.loadTheEditPane({
				op: "insertAccordion",
				w: accordionId
			});
		},
		
		removeAccordion: function(accordionId) {
			return TtPane_layout.loadTheEditPane({
				op: "removeAccordion",
				w: accordionId
			});
		}
	};
}();

jQuery(TtAccordion.init());