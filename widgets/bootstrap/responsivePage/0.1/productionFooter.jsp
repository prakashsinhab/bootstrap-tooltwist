<html>
	<head></head>
	
	<body>
	
		<!-- [ Jquery Cookie ] -->
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
		
		<!-- [ Bootstrap Project ] -->
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> <!-- version used by Simple Machines -->
		<!-- <script src="/ttsvr/bootstrap/js/bootstrap.min.js?v=3.0.0"></script> --> <!-- before -->
		
		<!-- [ myrp_t ] -->
		<script src="/ttsvr/freemium/script/main.js"></script>
		
		<script src='/ttsvr/freemium/script/jquery.transit.min.js'></script>
		<script src='/ttsvr/freemium/script/jquery.easing.min.js'></script>
		
		<script src='/ttsvr/freemium/script/nav.js'></script>
		<script src='/ttsvr/freemium/script/select.js'></script>
		<script src='/ttsvr/freemium/script/suburb.js'></script>
		<script src='/ttsvr/freemium/script/property.js'></script>
		<script src="/ttsvr/freemium/script/signup.js"></script>
		
		<script src='/ttsvr/freemium/script/jquery.peity.min.js'></script>
		
		<script src='/ttsvr/freemium/script/bootstrap-datepicker.js'></script>
		<script src="/ttsvr/freemium/script/search-suggestions.js"></script>
		<script src='/ttsvr/freemium/script/watchlist-functions.js'></script>
		
		<script src='/ttsvr/freemium/script/allfreemium.js'></script>
		<script type="text/javascript">
		function recordKissmetricsEvent (eventAction) {
			try {	_kmq.push(['record', eventAction]); } catch (e) {
				console.log(e);
			}
		}
		</script>
		%%bottomCode%%
		<script type="text/javascript">
		$(document).ready(function() { 
			setTimeout(function() { 
				try { _kmq.push(['identify', '<%=LoginUser.getData(request).getEmail() %>']); } catch (e) {
					console.log(e);
				} 
			}, 1000); 
		});
		</script>
		 <%if (FreemiumErrorCatchUtil.isPageError(request)=="true"){ %>
		<script type="text/javascript">
		   jQuery('#errorModal').modal();
		</script>
		<%}%>
	</body>
	
</html>

<%
	session.removeAttribute("propertyDetailSession");
	session.removeAttribute("propertySearchSession");
	session.removeAttribute("purchaseHistorySession");
	session.removeAttribute("relevantAgentSession");
	session.removeAttribute("demographicsParagraphSession");
	session.removeAttribute("avmSalesHistoryDetailSession");
	session.removeAttribute("bsg3PropertyAddressSession");
	session.removeAttribute("bsg3PropertyDetailsSession");
	session.removeAttribute("bsg3SalesPropertySearchSession");
	session.removeAttribute("bsg3ForSaleAdvertisementUrlSession");
	session.removeAttribute("bsg3SuggestService");
	session.removeAttribute("avmSession");
	session.removeAttribute("isOtmSession");
	session.removeAttribute("requestTimeSession");
	session.removeAttribute("bsg3StreetSalesPropertySearchSession");
	session.removeAttribute("bsg3SuburbSalesPropetySearchSession");
	session.removeAttribute("bsg3ForSaleAdvertisementUrlSession");
	//WidgetUtil.displayBSGLog(request);
	
	//freemium session
	/* session.removeAttribute("bsg3PropertyAddressSessionFreemium");
	session.removeAttribute("bsg3PropertyAdvertisementsSessionFreemium");
	session.removeAttribute("bsg3SalesPropertySearchSessionFreemium");
	session.removeAttribute("bsg3SuggestServiceFreemium");
	session.removeAttribute("propertySearchSessionFreemium"); */
	
	// for Juls
	/* session.removeAttribute("propertyDetailSessionFreemium"); */
	
	// for Jay
	session.removeAttribute("myrpErrorMessages");
	session.removeAttribute("freemiumErrorCatch");
	session.removeAttribute("freemiumPageCall");
%>