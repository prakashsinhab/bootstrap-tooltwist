<!--START-->
<!-- header for responsivePage -->
<%@page import="com.myrp.util.Cloudfront"%>
<%@page import="tooltwist.wbd.Navpoint"%>
<%@page import="com.dinaa.misc.AltLang"%>
<%@page import="tooltwist.wbd.WbdProductionHelper"%>
<%@page import="com.dinaa.data.XData"%>
<%@page import="tooltwist.misc.JspHelper"%>
<%@page import="tooltwist.ecommerce.AutomaticUrlParametersMode"%>
<%@page import="tooltwist.ecommerce.RoutingUIM"%>
<%@page import="tooltwist.wbd.WbdSession"%>

<%@page errorPage="../tooltwist/basic/error.jsp"%>
<%@page import="java.text.DateFormat"%>
<%@page import="tooltwist.misc.DateUtils"%>
<%@page import="tooltwist.misc.JspHelper"%>
<%@page import="tooltwist.ecommerce.RoutingUIM"%>

<%@page import="java.io.FileReader"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Properties"%>

<%@page import="com.dinaa.data.XData"%>
<%@page import="com.dinaa.ui.UimData"%>
<%@page import="com.dinaa.xpc.XpcSecurity"%>

<%@page import="tooltwist.ecommerce.RoutingUIM"%>
<%@page import="tooltwist.wbd.SnippetParam"%>
<%@page import="tooltwist.wbd.WbdException"%>
<%@page import="tooltwist.wbd.WbdRadioTextProperty"%>
<%@page import="tooltwist.wbd.WbdRenderHelper"%>
<%@page import="tooltwist.wbd.WbdSizeInfo"%>
<%@page import="tooltwist.wbd.WbdStringProperty"%>
<%@page import="tooltwist.wbd.WbdSession"%>
<%@page import="tooltwist.wbd.WbdProductionHelper"%>
<%@page import="tooltwist.wbd.WbdCache"%>
<%@page contentType="text/html; charset=UTF-8" %>
	%%importCode%%
	<%
		String jspName = "%%navpointId%%";
		JspHelper jh = JspHelper.getJspHelper(pageContext, jspName);
		%%preFetchCode%%
	%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>%%pageTitle%%</title>
	<meta name="description" content="%%descriptionMetatag%%">
	<meta name="keywords" content="%%keywordMetatag%%">
	<meta name="generator" content="ToolTwist" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!--[ Import Open Sans font ]-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
	
    <!--[ Bootstrap ]-->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" media="screen"> <!-- version used by Simple Machines -->
    <!-- <link href="%%URL(/ttsvr/bootstrap/css/bootstrap.freemium.min.css?v=3.0.0)%%" rel="stylesheet" media="screen"> --> <!-- before -->
    
    <!--[ Font Awesome ]-->
    <link href="%%URL(/ttsvr/bootstrap/css/font-awesome.css)%%" rel="stylesheet" media="screen">
    
    <!-- [ jQuery ] -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
    <!-- [ CSS from cssContainer widget ] -->
	%%headerCode%%
    
    <!-- [ Favicon ] -->
	<link rel="shortcut icon" href="/ttsvr/freemium/images/pv/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/ttsvr/freemium/images/pv/favicon.ico" type="image/x-icon">
	
	<%
	Navpoint navpoint = WbdCache.findNavpointInAnyLoadedProject(jspName, true);
	boolean requiresLogin = navpoint.requiresLogin();
	
	if (!requiresLogin) {
	%>
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		  ga('create', '<%=WbdCache.getProperty("uaCode")%>', '<%=WbdCache.getProperty("uaDomainName")%>');
		  ga('require', 'linkid', 'linkid.js');
		  ga('send', 'pageview');
		</script>	
	<% } %>
  </head>
<body>
%%topCode%%

	<!--  Provides a common fixed-width (and optionally responsive) layout with only <div class="container"> required. -->
	<div class="container">
	
		<!--  Create a fluid-->
		<!-- <div class="container-fluid" -->
		
		<!--END-->
		<hr>
		
		<footer>
		<p>&copy; Company 2013</p>
		</footer>
	
	</div> <!-- /container -->

</body>
</html>
