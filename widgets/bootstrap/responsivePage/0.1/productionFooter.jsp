<html>
	<head></head>
	
	<body>
	
		</div> <!--/ Di ko alam kung ano ginagawa neto. /-->
		
		<!-- [ jQuery ] -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		
		<!-- [ Bootstrap Project ] -->
		<script src="/ttsvr/bootstrap/js/bootstrap.min.js?v=3.0.0"></script>
		
		<!-- [ myrp_t ] -->
		<!-- <script src="/ttsvr/freemium/script/main.js"></script> -->
		
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