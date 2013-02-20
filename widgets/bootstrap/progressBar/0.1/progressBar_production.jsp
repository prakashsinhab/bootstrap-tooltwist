<html>
<head>
<title></title>
</head>
<body>
<%
	WbdProductionHelper helper = null;
	JspHelper jh = null;
	String snippetVar_myProperty;
	String snippetVar_thisNavpoint;
%>
<!--START-->
<%@page import="tooltwist.wbd.WbdProductionHelper"%>
<%@page import="com.dinaa.data.XData"%>
<%@page import="tooltwist.misc.JspHelper"%>
<%@page import="tooltwist.ecommerce.AutomaticUrlParametersMode"%>
<%@page import="tooltwist.ecommerce.RoutingUIM"%>

<!-- ********** INSERT HTML HERE ********** -->

	<div id='%%elementId%%' class="progress progress-%%subType%% %%striped%% %%active%%">
	<div class='bar' style='width: %%width%%'>&nbsp;</div>
	</div>

<!--END-->
</body>
</html>
