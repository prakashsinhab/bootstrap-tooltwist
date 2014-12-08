package tooltwist.bootstrap.widgets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tooltwist.bootstrap.properties.WbdSelectProperty;
import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.repository.ToolTwist;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.DesignerHelper;
import tooltwist.wbd.DesignerRole;
import tooltwist.wbd.DesignerUIM;
import tooltwist.wbd.Navpoint;
import tooltwist.wbd.Snippet;
import tooltwist.wbd.Snippet.SnippetLocation;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.SnippetParamList;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.StylesheetLinkInserter;
import tooltwist.wbd.WbdCache;
import tooltwist.wbd.WbdChildIndex;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdLibrary;
import tooltwist.wbd.WbdNavPointProperty;
import tooltwist.wbd.WbdProperty;
import tooltwist.wbd.WbdProperty.DisplayMode;
import tooltwist.wbd.WbdRadioTextProperty;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSession;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdVersionSelector;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WidgetId;

import com.dinaa.DinaaException;
import com.dinaa.data.XDataException;
import com.dinaa.data.XNodes;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;
import com.dinaa.ui.UimResult;
import com.dinaa.xpc.XpcSecurity;

/**
 * NavBar Widget
 */
public class NavBarWidget extends ContainerWidget
{
	private static final Logger logger = LoggerFactory.getLogger(NavBarWidget.class);
	
	private static final String NAVBAR_INDEX_PREFIX = "navbar-";
	private static final boolean USE_PRODUCTION_HELPER = false; 

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
		
		WbdProperty rowProperty = new WbdStringProperty("rows", null, "Rows", "1");
		rowProperty.setDisplayMode(DisplayMode.DEBUG_ONLY);
		rowProperty.setEditable(false);
		instance.defineProperty(rowProperty);
		
		instance.defineHiddenProperty(new WbdStringProperty("selectedRow", null, "Selected Row", "0"));
		instance.defineProperty(new WbdRadioTextProperty("brandType", null, "Brand Type", "Label,Image", ""));
		instance.defineProperty(new WbdStringProperty("titleImagePath", null, "Title / Image Path", ""));
		instance.defineProperty(new WbdStringProperty("brandNavpoint", null, "Link Navpoint", ""));
		instance.defineProperty(new WbdRadioTextProperty("verticalPosition", null, "Position", "Fixed Top:top,Fixed Bottom:bottom", ""));
		instance.defineProperty(new WbdRadioTextProperty("inverted", null, "Inverted Variation", "True:true,False:false", "false"));
		
	}
	
	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException
	{
//TODO: Uncomment this as required
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {

//				// Include a CSS snippet
				new StylesheetCodeInserter(generator, instance, "navBar_cssHeader.css"),
				new StylesheetLinkInserter(ToolTwist.getWebapp() + "/bootstrap/css/bootstrap.min.css?v=3.0.0"),
//					new StylesheetLinkInserter("/ttsvr/bootstrap/css/bootstrap.min.css"),
//					new JavascriptCodeInserter(generator, instance, "navBar_jsHeader.js"),
//					new JavascriptLinkInserter("/ttsvr/bootstrap/js/bootstrap.min.js")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
//				// Link to an external Javascript file
//				new JavascriptLinkInserter(jsUrl),

//				// Link to an external stylesheet
//				new StylesheetLinkInserter(cssUrl),

//				// Include a javascript snippet 
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "navBar_jsHeader.js")),

//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "navBar_cssHeader.css")),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
//				// Link to an external Javascript file
//				new JavascriptLinkInserter(jsUrl),
					
//				// Link to an external stylesheet
//				new StylesheetLinkInserter(cssUrl),
					
//				// Include a javascript snippet 
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "navBar_jsHeader.js")),
					
//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "navBar_cssHeader.css")),

//				// Add import statements to the JSP
//				new PageImportCodeInserter(XData.class.getName()),
			};
			codeInserterList.add(arr);

			if (USE_PRODUCTION_HELPER)
			{
				SnippetParam[] productionHelperParams = null;
//				codeInserterList.add(WbdProductionHelper.codeInserter(instance, NavBarProductionHelper.class.getName(), productionHelperParams));
//				codeInserterList.add(new PageImportCodeInserter(NavBarProductionHelper.class.getName()));
			}
		}

	}
	
	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Navigation Bar";
	}
	
	@Override
	public WbdSizeInfo getSizeInfo(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
		return WbdSizeInfo.unknownSizeInfo();
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		render(generator, instance, ud, rh);
	}
	
	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		render(generator, instance, ud, rh);
	}
	
	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception {
		try {
			
			rh.append("<%@page import=\"java.util.Arrays\"%>");
			rh.append("<%@page import=\"java.util.List\"%>");
			rh.append("<%@page import=\"tooltwist.cloudmall.utils.WebUtils\"%>");
			rh.append("<%@page import=\"tooltwist.cloudmall.utils.WebUtils.SESSION_VARIABLE\"%>");
			
			String rows = instance.getProperty("rows", null);
			String brandType = instance.getProperty("brandType", null).toLowerCase();
			String titleImagePath = instance.getProperty("titleImagePath", null);
			String brandNavpoint = instance.getProperty("brandNavpoint", null);
			String elementId = instance.getProperty("elementId", null);
			String verticalPosition = instance.getProperty("verticalPosition", null);
			
			if (verticalPosition != null && !verticalPosition.equals("")) {
				verticalPosition = "navbar-fixed-" + verticalPosition;
			}
			else {
				verticalPosition = "";
			}
			
			String inverted = instance.getProperty("inverted", null);
			String invertedClass = "";
			if (Boolean.valueOf(inverted)) {
				invertedClass = "navbar-inverse";
			} else {
				invertedClass = "navbar-default";
			}
				
			String currentNavpointId = WbdSession.getNavpointId(ud.getCredentials());
			
			rh.append("  <nav class=\"navbar "+verticalPosition+" "+invertedClass+"\" role=\"navigation\" id=\""+elementId+"\">\n"); 
			rh.append("    <div class=\"container\">\n");
			rh.append(	"      <div class=\"navbar-header\">\n");
			
			// ICON-BAR
//			if (Integer.valueOf(rows) > 1) {
//				rh.append("        <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n"); 
//				
//				for(int row = 1; row < Integer.valueOf(rows); row++) {
//					rh.append("          <span class=\"icon-bar\"></span>\n");
//				}
//				
//				rh.append("        </button>\n"); 
//			}
			
			if (Integer.valueOf(rows) > 1) {
				rh.append("        <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n"); 
				
				for(int row = 0; row < 3; row++) {
					rh.append("          <span class=\"icon-bar\"></span>\n");
				}
				
				rh.append("        </button>\n"); 
			}
			
			if ((brandType != null && brandType.equals("")) || brandType.equals("label")) {
				rh.append(	"        <a class=\"navbar-brand\" href=\""+brandNavpoint+"\">"+titleImagePath+"</a>\n"); 
			} else {
				rh.append("        <a class=\"navbar-brand\" href=\""+brandNavpoint+"\"><img src=\""+titleImagePath+"\"/></a>\n"); 
			}
			
			rh.append("      </div>\n");
			rh.append(	"      <div class=\"collapse navbar-collapse\">\n");
			
			String lastType = "";
			String type = "";
			for(int row = 0; row < Integer.valueOf(rows); row++) {
				WbdChildIndex wbdChildIndex = new WbdChildIndex(NAVBAR_INDEX_PREFIX+row);
				type = instance.getProperty("type", wbdChildIndex);
				
				String horizontalPosition = instance.getProperty("horizontalPosition", wbdChildIndex);
				String horizontalPositionClass = "";
				if (!horizontalPosition.equals("")) {
					horizontalPositionClass = "navbar-"+horizontalPosition;
				} else {
					horizontalPositionClass = "";
				}
				
				if (lastType.equals("")) {
					lastType = type;
					
					if (type.equals("Link")) {
						rh.append("        <ul class=\"nav navbar-nav "+horizontalPositionClass+"\">\n");
					}
				}
				
				if (!lastType.equals(type)) {
					lastType = type;
					
					if (type.equals("Link")) {
						rh.append("        <ul class=\"nav navbar-nav "+horizontalPositionClass+"\">\n");
					} else if (type.equals("Button")) {
						rh.append("        </ul>\n");
					}
				}
				
				String title = instance.getProperty("title", wbdChildIndex);
				String linkNavpoint = instance.getProperty("linkNavpoint", wbdChildIndex);
				String navpointId = instance.getProperty("navpoint", wbdChildIndex);
				String parameters = instance.getProperty("parameters", wbdChildIndex);
				boolean isDisplay = instance.getProperty("display", wbdChildIndex) != null && instance.getProperty("display", wbdChildIndex).equalsIgnoreCase("show") ? true : false;
				
				Navpoint navpoint = WbdCache.findNavPoint(navpointId, false);
				
				if (type.equals("Link")) {
					
					rh.append("<% if (\""+ navpoint.getNotes() +"\".contains(WebUtils.getAttributes(request, SESSION_VARIABLE.ROLE_ID, \"\"))) { %>");
					if (isDisplay) {
						String clazz = (currentNavpointId.equals(linkNavpoint)) ? "active" : "";
//						linkNavpoint = RoutingUIM.navpointUrl(ud, instance.getProperty("linkNavpoint", wbdChildIndex), null);
						rh.append("<li class=\""+clazz+"\"><a href=\""+linkNavpoint + parameters+"\">"+title+"</a></li>");
					}
					rh.append("<% } else { %>");
					rh.append("");
					rh.append("<% } %>");
					
				} else if (type.equals("Button")) {
					String buttonType = instance.getProperty("buttonType", wbdChildIndex);
					String buttonTypeClass = "";
					if (buttonType != null && !buttonType.equals("")) {
						buttonTypeClass = "btn-"+buttonType;
					}
					else {
						buttonTypeClass = "btn-default";
					}
					
					String buttonSize = instance.getProperty("buttonSize", wbdChildIndex);
					String buttonSizeClass = "";
					if (buttonSize != null && !buttonSize.equals("")) {
						buttonSizeClass = "btn-"+buttonSize;
					}
					else {
						buttonSizeClass = "";
					}
					
					String buttonGlyphicon = instance.getProperty("buttonGlyphicon", wbdChildIndex);
					String buttonGlyphiconClass = "";
					if (buttonGlyphicon != null && !buttonGlyphicon.equals("")) {
						buttonGlyphiconClass = "glyphicon-"+buttonGlyphicon;
					}
					else {
						buttonGlyphiconClass = "";
					}
					
//					linkNavpoint = RoutingUIM.navpointUrl(ud, instance.getProperty("linkNavpoint", wbdChildIndex), null);
					
					if (isDisplay) {
						rh.append("        <a href="+linkNavpoint + parameters+"><form class=\"navbar-form "+horizontalPositionClass+"\"><button type=\"button\" class=\"btn "+buttonTypeClass+" "+buttonSizeClass+"\"><span class=\"glyphicon "+buttonGlyphiconClass+"\"></span>&nbsp;"+title+"</button></form></a>\n");
					}
				}
			}
			
			if (type.equals("Link")) {
				rh.append("        </ul>\n");
			}
			
			rh.append("      </div>\n" +
				    		"    </div>\n" +
				    		"  </nav>");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void render(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) {
		
		try {
			
			String rows = instance.getProperty("rows", null);
			String brandType = instance.getProperty("brandType", null).toLowerCase();
			String titleImagePath = instance.getProperty("titleImagePath", null);
			String inverted = instance.getProperty("inverted", null);
			Object tmp = instance.getProperty("selectedRow", null);
			int selectedRow = Integer.valueOf(tmp == "" ? "0" : tmp.toString());
			String invertedClass = "";
			if (Boolean.valueOf(inverted)) {
				invertedClass = "navbar-inverse";
			} else {
				invertedClass = "navbar-default";
			}
			
			if (selectedRow >= Integer.valueOf(rows)) {
				selectedRow = 0;
				instance.setProperty("selectedRow", null, "0");
				instance.setPropertyWhileLoading("selectedRow", null, "0");
			}
			
			WidgetId navBarId = new WidgetId(instance);
			navBarId.setPrefix("navBar");
			
			//outer div
			rh.append("  <div id=\""+navBarId.fullPath()+"\">\n");
			
			rh.append("    <nav class=\"navbar "+invertedClass+"\" role=\"navigation\">\n");
			rh.append("      <div class=\"navbar-header\">\n");
			
			// ICON-BAR
//			if (Integer.valueOf(rows) > 1) {
//				rh.append("        <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n"); 
//				
//				for(int row = 1; row < Integer.valueOf(rows); row++) {
//					rh.append("          <span class=\"icon-bar\"></span>\n");
//				}
//				
//				rh.append("        </button>\n"); 
//			}
			
			if (Integer.valueOf(rows) > 1) {
				rh.append("        <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n"); 
				
				for(int row = 0; row < 3; row++) {
					rh.append("          <span class=\"icon-bar\"></span>\n");
				}
				
				rh.append("        </button>\n"); 
			}
			
			if ((brandType != null && brandType.equals("")) || brandType.equals("label")) {
				rh.append("        <a class=\"navbar-brand\" href=\"javascript:void(0);\">"+titleImagePath+"</a>\n"); 
			} else {
				rh.append("        <a class=\"navbar-brand\" href=\"javascript:void(0);\"><img src=\""+titleImagePath+"\"/></a>\n"); 
			}
			
			rh.append("      </div>\n");
			rh.append("      <div class=\"collapse navbar-collapse\">\n");
			rh.append("        <ul class=\"nav\">\n");

			for(int row = 0; row < Integer.valueOf(rows); row++) {
				
				String clazz = (selectedRow == row) ? "active" : "";
				WbdChildIndex wbdChildIndex = new WbdChildIndex(NAVBAR_INDEX_PREFIX+row);
				String title = instance.getProperty("title", wbdChildIndex);
				
				rh.append("          <li class=\""+clazz+" designer-properties\"  id=\""+navBarId + "["+NAVBAR_INDEX_PREFIX+row+"]" +"\" onclick=\"NavBar.selectNavBar('"+navBarId.fullPath()+"','"+row+"')\"><a href=\"javascript:void(0);\">"+title+"</a></li>\n");
				
			}
			
			rh.append("        </ul>\n" + 
				      "      </div>\n" + 
					  "    </nav>\n");
			
			//outer div
			rh.append("  </div>");
			
			String js = codeToInsert(generator, instance, SnippetLocation.PRIMITIVE_WIDGET, "navBar_jsHeader.js", null);
			
			rh.append("<script>");
			rh.append(js);
			rh.append("</script>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private SnippetParam[] getSnippetParams(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
//		String myProperty = instance.getProperty("myProperty", null);
//		String myNavpoint = instance.getProperty("myNavpoint", null);
		SnippetParam[] params = {
//			new SnippetParam("myProperty", myProperty),
//			new SnippetParam("myNavpoint", myNavpoint)
		};
		return params;
	}
	
	private String codeToInsert(WbdGenerator generator, WbdWidget instance, SnippetLocation location, String templateName, SnippetParam[] params) throws WbdException {
		Snippet snippet = new Snippet(generator, instance, location, templateName);		
		
		// Process includes %%INC(templateName)%%
		snippet.substituteIncludes(generator);

		// Convert URLs
		String descriptionForReportingErrors = instance.findPrimitiveWidget(generator).fullPath() + "/" + templateName;
		snippet.replaceUrls(generator, descriptionForReportingErrors);
		
		// Replace any "incomplete markers"
		snippet.replaceSpecialMarkers();

		// Replace the parameters
		if (params != null)
		{
			SnippetParamList paramList = new SnippetParamList(params);
			snippet.replaceParameters(generator, paramList);
		}

		// Get the output
		return snippet.getContent();
	}
	
	public UimResult op_insertNavBar(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_insertNavBar() start...");
		
		WbdVersionSelector vs = DesignerUIM.getUserVersionSelector(uh);
		WbdLibrary project = helper.getProject(uh);
		WbdGenerator generator = new WbdGenerator(vs, GenerationMode.DESIGN, project);
		
		WbdWidget root = instance.getRoot();
		root.setDirty();
		
		String rows = instance.getProperty("rows",null);
		rows = (Integer.valueOf(rows) + 1) + "";
		instance.setProperty("rows",null, rows);
		
		helper.saveAsRequired(uh);
		
		String html = helper.htmlForLayoutEditorPane(generator, uh, root);

		// Add additional processing to run after the pane is loaded
		html += "<script>\n";
		html += helper.javascriptToSetUpLayoutEditorPane(generator, uh, root);
		html += "</script>\n";
		
		return uh.reply(html);
	}
	
	public UimResult op_removeNavBar(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_insertNavBar() start...");
		
		WbdVersionSelector vs = DesignerUIM.getUserVersionSelector(uh);
		WbdLibrary project = helper.getProject(uh);
		WbdGenerator generator = new WbdGenerator(vs, GenerationMode.DESIGN, project);
		
		WbdWidget root = instance.getRoot();
		root.setDirty();
		
		String rows = instance.getProperty("rows",null);
		rows = (Integer.valueOf(rows) - 1) + "";
		if (Integer.valueOf(rows) > 0)
			instance.setProperty("rows",null, rows);
		
		helper.saveAsRequired(uh);
		
		String html = helper.htmlForLayoutEditorPane(generator, uh, root);

		// Add additional processing to run after the pane is loaded
		html += "<script>\n";
		html += helper.javascriptToSetUpLayoutEditorPane(generator, uh, root);
		html += "</script>\n";
		
		return uh.reply(html);
	}
	
	public UimResult op_selectNavBar(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_selectNavBar() start...");
		
		WbdVersionSelector vs = DesignerUIM.getUserVersionSelector(uh);
		WbdLibrary project = helper.getProject(uh);
		WbdGenerator generator = new WbdGenerator(vs, GenerationMode.DESIGN, project);
		
		WbdWidget root = instance.getRoot();
		root.setDirty();
		
		helper.saveAsRequired(uh);
		
		//set selected row
		String selectedRow = uh.getRequestValue("index");
		instance.setProperty("selectedRow", null, selectedRow);
		
		String html = helper.htmlForLayoutEditorPane(generator, uh, root);

		// Add additional processing to run after the pane is loaded
		html += "<script>\n";
		html += helper.javascriptToSetUpLayoutEditorPane(generator, uh, root);
		html += "TtPane_layout.showProperties('navBar!"+instance.fullPath() + "["+NAVBAR_INDEX_PREFIX + selectedRow +"]');";
		html += "jQuery(\"#id-designer-properties-div\").css({\"opacity\":\"1\"});";
		html += "</script>\n";
//		
		return uh.reply(html);
	}
	
	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XNodes node) throws WbdException
	{
		super.loadPropertiesFromXml(generator, widget, node);

		// Get the cells
		XNodes cells;
		try
		{
			cells = node.getNodes("./navBars");
		}
		catch (XDataException e)
		{
			throw new WbdException("Error getting cells");
		}
		while (cells.next())
		{
			String indexStr = cells.getText("./index");
			String display = cells.getText("./display");
			String type = cells.getText("./type");
			String title = cells.getText("./title");
			String linkNavpoint = cells.getText("./linkNavpoint");
			String navpoint = cells.getText("./navpoint");
			String parameters = cells.getText("./parameters");
			String horizontalPosition = cells.getText("./horizontalPosition");
			String buttonType = cells.getText("./buttonType");
			String buttonSize = cells.getText("./buttonSize");
			String buttonGlyphicon = cells.getText("./buttonGlyphicon");

			WbdChildIndex index = new WbdChildIndex(NAVBAR_INDEX_PREFIX + indexStr);
			widget.defineProperty(new WbdRadioTextProperty("display", index, "Display", "Show,Hide", display));
			widget.defineProperty(new WbdRadioTextProperty("type", index, "Type", "Link,Button", type));
			widget.defineProperty(new WbdStringProperty("title", index, "Title", title));
			widget.defineProperty(new WbdRadioTextProperty("horizontalPosition", index, "Position", "left,right", horizontalPosition));
			widget.defineProperty(new WbdStringProperty("linkNavpoint", index, "Link Navpoint", linkNavpoint));
			widget.defineProperty(new WbdNavPointProperty("navpoint", index, "Navpoint", navpoint));
			widget.defineProperty(new WbdStringProperty("parameters", index, "Parameters", parameters));
			widget.defineProperty(new WbdSelectProperty("buttonType", index, "Button Type", "primary,success,info,warning,danger", buttonType));
			widget.defineProperty(new WbdRadioTextProperty("buttonSize", index, "Button Size", "Large:lg,Small:sm,Extra Small:xs", buttonSize));
			widget.defineProperty(new WbdStringProperty("buttonGlyphicon", index, "Button Glyphicon", buttonGlyphicon));
			
			try
			{
				XNodes widgetNode = cells.getNodes("./widget");
				if (widgetNode.next())
				{
					WbdWidget child = WbdWidget.loadBasicPropertiesFromXml(generator, widgetNode);
					child.setParent(widget, index);
				}
			}
			catch (XDataException e)
			{
				throw new WbdException("Error finding cell widget: " + e);
			}
		}
	
		
		//for children
		this.flowChildren_loadPropertiesFromXml(generator, widget, node, null);

	}

	@Override
	protected void writeProperties(WbdGenerator generator, WbdWidget instance, PrintWriter pw, int indent) throws WbdException
	{
		instance.getProperties().writeProperties(pw, indent, null);
		
		int rows = Integer.valueOf(instance.getProperty("rows", null));

		for (int row = 0; row < rows; row++) {
			WbdChildIndex index = new WbdChildIndex(NAVBAR_INDEX_PREFIX+row);
			String type = instance.getProperty("type", index);
			String title = instance.getProperty("title", index);
			if (type == null) {
				type = "Link";
			}
			
			if ((type != null && type.equals("")) || type.equals("Link")) {
				title = (title == null) ? "Link" : title;
			} else if ((type != null && type.equals("")) || type.equals("Button")) {
				title = (title == null) ? "Button" : title;
			}
			
			String display = (instance.getProperty("display", index) == null) ? "" : instance.getProperty("display", index);
			String linkNavpoint = (instance.getProperty("linkNavpoint", index) == null) ? "" : instance.getProperty("linkNavpoint", index);
			String navpoint = (instance.getProperty("navpoint", index) == null) ? "" : instance.getProperty("navpoint", index);
			String parameters = (instance.getProperty("parameters", index) == null) ? "" : instance.getProperty("parameters", index);
			String horizontalPosition = (instance.getProperty("horizontalPosition", index) == null) ? "" : instance.getProperty("horizontalPosition", index);
			String buttonType = (instance.getProperty("buttonType", index) == null) ? "" : instance.getProperty("buttonType", index);
			String buttonSize = (instance.getProperty("buttonSize", index) == null) ? "" : instance.getProperty("buttonSize", index);
			String buttonGlyphicon = (instance.getProperty("buttonGlyphicon", index) == null) ? "" : instance.getProperty("buttonGlyphicon", index);
			title = (title == null) ? "" : title;
			
			pw.println(indentStr(indent) + "<navBars>");
			pw.println(indentStr(indent + 1) + "<index>" + row + "</index>");
			instance.getProperties().writeProperties(pw, indent + 1, index);
			pw.println(indentStr(indent) + "</navBars>");
			
			WbdWidget child = instance.findChildByIndex(index);
			if (child != null)
				child.saveToFile(generator, pw, indent + 1);
			else {
				instance.defineProperty(new WbdRadioTextProperty("display", index, "Display", "Show,Hide", display));
				instance.defineProperty(new WbdRadioTextProperty("type", index, "Type", "Link,Button", type));
				instance.defineProperty(new WbdStringProperty("title", index, "Title", title));
				instance.defineProperty(new WbdRadioTextProperty("horizontalPosition", index, "Position", "left,right", horizontalPosition));
				instance.defineProperty(new WbdStringProperty("linkNavpoint", index, "Link Navpoint", linkNavpoint));
				instance.defineProperty(new WbdNavPointProperty("navpoint", index, "Navpoint", navpoint));
				instance.defineProperty(new WbdStringProperty("parameters", index, "Parameters", parameters));
				instance.defineProperty(new WbdSelectProperty("buttonType", index, "Button Type", "primary,success,info,warning,danger", buttonType));
				instance.defineProperty(new WbdRadioTextProperty("buttonSize", index, "Button Size", "Large:lg,Small:sm,Extra Small:xs", buttonSize));
				instance.defineProperty(new WbdStringProperty("buttonGlyphicon", index, "Button Glyphicon", buttonGlyphicon));
			}
		}

	}
	
	@Override
	public void renderProperties(WbdGenerator generator, UimData ud, WbdRenderHelper rh, WbdWidget instance, WidgetId id, boolean displayOnly) throws WbdException {
		
		WidgetId navBarId = new WidgetId(instance);
		navBarId.setPrefix("navBar");
		
		WbdChildIndex index = id.getIndex();
		
		rh.renderPropertiesHeading(generator, ud, instance, id, this.getLabel(instance), -1);
		
		rh.append("<table>");
			
		rh.append("<tr>");
		rh.append("<td id=\"id-designer-properties-navBar3\">");//[elementId, rows, selectedRow, position, inverted, _widgetId, _controller, _linkedWidget]
		rh.append("<label>NavBar Property</label>");
		String[] ignoredPropertiesForGrid = {"_controller", "_widgetId", "cellDivs", "rows", "selectedRow", "_linkedWidget"};
		rh.renderProperties(generator, ud, instance, navBarId, ignoredPropertiesForGrid);
		rh.append("</td>");
		rh.append("</tr>");
		
		if (!id.getIndex().getIndexStr().equals("")) {
			rh.append("<tr>");
			rh.append("<td id=\"id-designer-properties-navBar1\">");
			rh.append("<br>");
			rh.append("<label>Row Property</label>");
			rh.renderProperties(generator, ud, instance, id, new String[] {});
			rh.append("</td>");
			rh.append("</tr>");
		}
		
		rh.append("<tr>");
		rh.append("<td id=\"id-designer-properties-navBar3\">");//[elementId, rows, selectedRow, position, inverted, _widgetId, _controller, _linkedWidget]
		String[] _linkedWidgetOnly = {"elementId", "rows", "selectedRow", "brandType", "titleImagePath", "brandNavpoint", "verticalPosition", "inverted", "_widgetId", "_controller", "_controller", "_widgetId", "cellDivs", "rows", "selectedRow", "title"};
		
		rh.renderProperties(generator, ud, instance, navBarId, _linkedWidgetOnly);
		
		rh.append("<br>");
		XpcSecurity credentials = ud.getCredentials();
		boolean canChangeGrid = credentials.hasRole(DesignerRole.CHANGE_GRIDS.getRoleCode());
		if (instance.mayEdit(ud) && canChangeGrid) {
			
			rh.append("  <span class=\"button-navBar\" style=\"float: right;cursor: pointer;\" onclick=\"NavBar.removeNavBar('"+id.fullPath()+"');\" title=\"Decrease row count.\">&nbsp;-&nbsp;</span>");
			rh.append("  <span class=\"button-navBar\" style=\"float: right;cursor: pointer;\" onclick=\"NavBar.insertNavBar('"+id.fullPath()+"');\" title=\"Increase row count.\">&nbsp;+&nbsp;</span>");
		}
		
		rh.append("</td>");
		rh.append("</tr>");
		
		rh.append("</table>");
		rh.append("<script>jQuery(\".button-navBar\").button();</script>");
//		}
	}
	
}
