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
<button id="%%elementId%%" class="close" onclick="Close.hide();">&times;</button>

<script>
	var Close = function() {
		return {
			
			hide: function() {
				var type = '%%elementType%%';
				if (type == 'class') {
					var className = document.getElementsByClassName('%%elementName%%');
	                
					for(var i=0; i < className.length; i++)
	                {
	                    className.style.display = 'none';
	                }
					
				} else {
					document.getElementById('%%elementName%%').style.display = 'none';
				}
				document.getElementById('%%elementId%%').style.display = 'none';
			}
		};
	}();
</script>


<!--END-->
</body>
</html>
