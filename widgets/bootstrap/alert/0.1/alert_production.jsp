
<html>
<head>
<title></title>
</head>
<body>
<% String snippetVar_display = ""; %>
<!--START-->
<%
	
	String display = "";
	if (snippetVar_display.equals("none"))
		display = "style=\"display: none;\"";
	
%>

<div class="alert alert-%%type%%" %%idDefinition%% <%=display %> animate="%%animate%%" timeout="%%timeOut%%">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <span id="message">
	 %%message%% 
  </span>
</div>
<!--END-->
</body>
</html>
