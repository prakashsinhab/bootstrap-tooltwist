
var Alert = function() {
	return {
		myVariable: null,

		init: function() {
			alert("Alert.init()");

//			// attach an event to an HTML element
//			var self = this;
//			jQuery(".Alert .myElementClass").click(function() {
//				self.myMethod();
//				// do something
//				...
//			});
		},
		
		myMethod: function() {
			alert("Alert.myMethod()");
		}
		// no comma after last method
	};
}();

//jQuery(Alert.init()); // Run after page loads