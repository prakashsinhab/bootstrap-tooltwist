
<html>
<head>
<title></title>
</head>
<body>
<%
	String snippetVar_message = "";
%>
<!--START-->
<%@page import="com.dinaa.data.XData"%>

<div class="alert alert-%%type%%" %%idDefinition%%>
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <span id="message">
	 %%message%% 
  </span>
</div>
<!--END-->
</body>
</html>
