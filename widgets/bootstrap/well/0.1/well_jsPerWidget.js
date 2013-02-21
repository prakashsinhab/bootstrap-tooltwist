
var Well_[[widgetId]] = function() {
	return {
		myVariable: null,

		init: function() {
			alert("Well_[[widgetId]].init");
			
//			// attach an event to an HTML element
//			var self = this;
//			jQuery(".Well .myElementClass").click(function() {
//				self.myMethod();
//				// do something
//				...
//			});
		},
		
		myMethod: function() {
			alert("Well.myMethod()");
		}
		// no comma after last method
	};
}();

//jQuery(Well_[[widgetId]].init()); // Run after page loads