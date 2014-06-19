<!--START-->
<!-- header for responsivePage -->
<%@page import="tooltwist.wbd.Navpoint"%>
<%@page import="com.dinaa.misc.AltLang"%>
<%@page import="tooltwist.cloudmall.utils.WebUtils"%>
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

	AltLang lang=WebUtils.getAltLang(jh);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>%%pageTitle%%</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="description" content="%%descriptionMetatag%%">
	<meta name="keywords" content="%%keywordMetatag%%">
	<meta name="generator" content="ToolTwist" />

%%headerCode%%
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="%%URL(/ttsvr/bootstrap/css/bootstrap.min.css?v=3.0.0)%%" rel="stylesheet" media="screen">
    <link href="%%URL(/ttsvr/bootstrap/css/font-awesome.min.css)%%" rel="stylesheet" media="screen">
    <link rel="icon" href="/ttsvr/cropImage/<%=WbdCache.getProperty("store.name") %>.images.favicon.ico" type="image/x-icon"> 
	<link rel="shortcut icon" href="/ttsvr/cropImage/<%=WbdCache.getProperty("store.name") %>.images.favicon.ico" type="image/x-icon"> 
	<!-- <link href="%%URL(/ttsvr/bootstrap/css/bootstrap-responsive.css)%%" rel="stylesheet"> -->
	<%
	Navpoint navpoint = WbdCache.findNavPoint(jspName, true);
	boolean requiresLogin = navpoint.requiresLogin();
	%>
	
	<% if (!navpoint.getParent().getId().equals("noah-176")) { %>
		  	<% if (navpoint.getLabel().equalsIgnoreCase("home")) { %>
			  	<meta name="google-site-verification" content="AhLcdFsg980gnZh8k4dzSkYUiLzwgnDapP8c80WM_-A" />
			  	<meta name="msvalidate.01" content="4017B989E703CFA096F76AF5F42A2293" />
		  	<% } %>
		  	
	  	<script>
			(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
			m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
			ga('create', '<%=WbdCache.getProperty("uaCode")%>', '<%=WbdCache.getProperty("uaDomainName")%>');
			ga('require', 'linkid', 'linkid.js');
			ga('require', 'displayfeatures');
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
