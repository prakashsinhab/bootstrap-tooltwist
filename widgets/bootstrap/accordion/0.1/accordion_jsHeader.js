var TtAccordion = function(){
	return {
		
		init: function() {
			jQuery(".accordion-heading").unbind("click");
			//jQuery( document ).tooltip();
		},
		
		selectAccordion: function(accordionId, row) {
			TtPane_layout.hidePropertiesDialog();
			setTimeout(function() {
				return TtPane_layout.loadTheEditPane({
					op: "selectAccordion",
					index: row,
					w: accordionId
				});
				
			}, 100);
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
		},
		
		expandAll: function(accordionId) {
			jQuery(".accordion-body.collapse").css({"height": "auto", "display": "block"});
		},
		
		collapseAll: function(accordionId) {
			jQuery(".accordion-body.collapse").css({"height": "0px", "display": "none"});
		}
	};
}();

jQuery(TtAccordion.init());