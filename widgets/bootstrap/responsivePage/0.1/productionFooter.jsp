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
		
	    <script src="/ttsvr/freemium/script/markerwithlabel.js" type="text/javascript"></script>
		
		<!-- [ This resolves the issue upon logging in to Freemium login page. I got this code from "/myrp_t/widgets/myrp_widgets/page_html5/3.1/new_jquery_footer.jsp" ] -->
		<script type="text/javascript">
			<% 
			boolean kissmetricsActive = "on".equals(WbdCache.getProperty("kissmetrics.active"));
			if(kissmetricsActive) {
			%>
				var _kmq = _kmq || [];
				var _kmk = _kmk || '<%=WbdCache.getProperty("kissmetrics.account.id")%>';
				function _kms(u) {
					setTimeout(function() {
						var d = document, f = d.getElementsByTagName('script')[0],
						s = d.createElement('script');
						s.type = 'text/javascript'; s.async = true; s.src = u;
						f.parentNode.insertBefore(s, f);
					}, 1);
				}
				_kms('//i.kissmetrics.com/i.js');
				_kms('//doug1izaerwt3.cloudfront.net/' + _kmk + '.1.js');
			<%
			}
			%>
			
			function recordKissmetricsEvent(eventAction) {
			<% 
				if(kissmetricsActive) {
			%>
					_kmq.push(['record', eventAction]);
			<%
				}
			%>
			}
		</script>
		
	 	%%bottomCode%%
		 	
	</body>
	
</html>

<%
	request.getSession().removeAttribute("propertyDetailSession");
	request.getSession().removeAttribute("propertySearchSession");
	request.getSession().removeAttribute("purchaseHistorySession");
	request.getSession().removeAttribute("relevantAgentSession");
	request.getSession().removeAttribute("demographicsParagraphSession");
	request.getSession().removeAttribute("avmSalesHistoryDetailSession");
	request.getSession().removeAttribute("bsg3PropertyAddressSession");
	request.getSession().removeAttribute("bsg3PropertyDetailsSession");
	request.getSession().removeAttribute("bsg3SalesPropertySearchSession");
	request.getSession().removeAttribute("bsg3ForSaleAdvertisementUrlSession");
	request.getSession().removeAttribute("bsg3SuggestService");
	request.getSession().removeAttribute("avmSession");
	request.getSession().removeAttribute("isOtmSession");
	request.getSession().removeAttribute("requestTimeSession");
	request.getSession().removeAttribute("bsg3StreetSalesPropertySearchSession");
	request.getSession().removeAttribute("bsg3SuburbSalesPropetySearchSession");
	request.getSession().removeAttribute("bsg3ForSaleAdvertisementUrlSession");
	//WidgetUtil.displayBSGLog(request);
	
	//freemium session
	/* request.getSession().removeAttribute("bsg3PropertyAddressSessionFreemium");
	request.getSession().removeAttribute("bsg3PropertyAdvertisementsSessionFreemium");
	request.getSession().removeAttribute("bsg3SalesPropertySearchSessionFreemium");
	request.getSession().removeAttribute("bsg3SuggestServiceFreemium");
	request.getSession().removeAttribute("propertySearchSessionFreemium"); */
	
	// for Juls
	/* request.getSession().removeAttribute("propertyDetailSessionFreemium"); */
%>