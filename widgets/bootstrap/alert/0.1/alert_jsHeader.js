
var Alert = function() {
	
	var args = arguments;
	var errMsg = "";
	
	var alertElement = null;
	var animate = null;
	var timeout = null;
	
	if (args.length == 1) {
		alertElement = jQuery(".alert");
		
	}
	else if (args.length == 2) {
		var elementId = args[1];
		alertElement = jQuery("#"+elementId);
		
	}
	else {
		errMsg = "Invalid arguments!";
	}
	
	if (alertElement == null || alertElement.length == 0) {
		alert(errMsg);
	}
	else {
		console.log("here2", alertElement);
		var message = args[0];
		jQuery(alertElement).find("#message").html(message);
		animate = jQuery(alertElement).attr("animate");
		timeout = jQuery(alertElement).attr("timeout");
		
		if (animate == 'fade')
			jQuery(alertElement).fadeIn();
		else if (animate == 'slide')
			jQuery(alertElement).slideDown();
		else
			jQuery(alertElement).show();
		
		if (timeout != '' && parseInt(timeout) > 0) {
			setTimeout(function() {
				if (animate == 'fade')
					jQuery(alertElement).fadeOut();
				else if (animate == 'slide')
					jQuery(alertElement).slideUp();
				else
					jQuery(alertElement).hide();
			}, timeout);
			
		}
	}	
	
	
};

document.ready = function() {};