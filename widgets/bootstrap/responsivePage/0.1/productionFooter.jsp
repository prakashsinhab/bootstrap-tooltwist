<html>
	<head></head>
	
	<body>
		<script src="<%=Cloudfront.getDNS() %>/freemium/script/bootstrap.min.v3.3.1.js.gz"></script> <!-- version used by Simple Machines -->
		<!-- [ myrp_t ] -->
		<script src="<%=Cloudfront.getDNS() %>/freemium/script/allfreemium.js.gz"></script>
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
	
	// for Jay
	session.removeAttribute("myrpErrorMessages");
	session.removeAttribute("freemiumErrorCatch");
	session.removeAttribute("freemiumPageCall");
%>