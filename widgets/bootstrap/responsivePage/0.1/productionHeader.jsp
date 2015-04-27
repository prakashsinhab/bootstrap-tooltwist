<!--START-->
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>%%pageTitle%%</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="description" content="%%descriptionMetatag%%">
    <meta name="keywords" content="%%keywordMetatag%%">
    <meta name="generator" content="ToolTwist" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta property="og:image" content="/ttsvr/images/pundit-logo.png" />
    <meta property="og:image:type" content="image/png" />
    <link href="%%URL(/ttsvr/bootstrap/css/build/pundit.essential.310714.css)%%" rel="stylesheet" media="screen">
    <link href="%%URL(/ttsvr/bootstrap/css/jquery-ui.min.css)%%" rel="stylesheet" media="screen">
    <link href="%%URL(/ttsvr/pundit/css/pundit.css)%%" rel="stylesheet" media="screen">
    <link href="%%URL(/ttsvr/pundit/css/bootstrap-select.css)%%" rel="stylesheet" media="screen">
    <link rel="icon" href="/ttsvr/cropImage/pundit.images.favicon.ico" sizes="16x16" type="image/ico">
    </head>
  <body>
    %%topCode%%
    <div class="container">
      <input type="hidden" name="serverUrl" value="<%=WbdCache.getProperty("server.url")%>">

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

<!--END-->
      <hr>
      <footer>
        <p>&copy; Company 2013</p>
      </footer>
    </div> <!-- /container -->
  </body>
</html>