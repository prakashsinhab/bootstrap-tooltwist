package tooltwist.bootstrap.widgets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tooltwist.bootstrap.properties.WbdSelectProperty;
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
import tooltwist.wbd.UserPreferences;
import tooltwist.wbd.WbdWidgetController;
import tooltwist.wbd.ZoneWidget;
import tooltwist.wbd.Snippet.SnippetLocation;
import tooltwist.wbd.UserPreferences.ColorScheme;
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
import com.dinaa.data.XData;
import com.dinaa.data.XDataException;
import com.dinaa.data.XNodes;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;
import com.dinaa.ui.UimResult;
import com.dinaa.xpc.XpcSecurity;

/**
 * NavBar Widget
 */
public class HtmlContainerWidget extends ContainerWidget
{
	private static final Logger logger = LoggerFactory.getLogger(HtmlContainerWidget.class);
	
	private static final String DIV_INDEX_PREFIX = "cont-";
	private static final boolean USE_PRODUCTION_HELPER = false;

	private static final String DEFAULT_TAG = "div"; 

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		
//		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
		
		WbdProperty rowProperty = new WbdStringProperty("rows", null, "Rows", "1");
		rowProperty.setDisplayMode(DisplayMode.DEBUG_ONLY);
		rowProperty.setEditable(false);
		instance.defineProperty(rowProperty);
		
//		instance.defineHiddenProperty(new WbdStringProperty("selectedRow", null, "Selected Row", "0"));
//		instance.defineProperty(new WbdRadioTextProperty("brandType", null, "Brand Type", "Label,Image", ""));
//		instance.defineProperty(new WbdStringProperty("titleImagePath", null, "Title / Image Path", ""));
//		instance.defineProperty(new WbdNavPointProperty("brandNavpoint", null, "Navpoint", ""));
//		instance.defineProperty(new WbdRadioTextProperty("verticalPosition", null, "Position", "Fixed Top:top,Fixed Bottom:bottom", ""));
//		instance.defineProperty(new WbdRadioTextProperty("inverted", null, "Inverted Variation", "True:true,False:false", "false"));
		
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
				new StylesheetCodeInserter(generator, instance, "htmlContainer_cssHeader.css"),
				new StylesheetLinkInserter(ToolTwist.getWebapp() + "/bootstrap/css/bootstrap.min.css?v=3.0.0"),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
			};
			codeInserterList.add(arr);

			if (USE_PRODUCTION_HELPER)
			{
				SnippetParam[] productionHelperParams = null;
			}
		}

	}
	
	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "HTML Container";
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
//		rh.append("<img src='/ttsvr/cloudmall/images/qnet/designer/admin-menu.png'></img>");
	}
	
	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		render(generator, instance, ud, rh);
//		rh.append("<img src='/ttsvr/cloudmall/images/qnet/designer/admin-menu.png'></img>");
	}
	
	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception {
		try {
			
			String rows = instance.getProperty("rows", null);
			for(int row = 0; row < Integer.valueOf(rows); row++) {
				
				WbdChildIndex wbdChildIndex = new WbdChildIndex(DIV_INDEX_PREFIX+row);
				
				renderDivForJsp(generator, instance, ud, rh, wbdChildIndex);
				
			}
			
//			if (type.equals("Link")) {
//				rh.append("        </ul>\n");
//			}
//			
//			rh.append("      </div>\n" +
//				    		"    </div>\n" +
//				    		"  </nav>");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void render(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) {
		
		try {
			
			String rows = instance.getProperty("rows", null);
			
			WidgetId navBarId = new WidgetId(instance);
			navBarId.setPrefix("navBar");
			
			//outer div
			rh.append("  <div id=\""+navBarId.fullPath()+"\">\n");
			
			for(int row = 0; row < Integer.valueOf(rows); row++) {
				
				WbdChildIndex wbdChildIndex = new WbdChildIndex(DIV_INDEX_PREFIX+row);
				
				renderDivForDesigner(generator, instance, ud, rh, wbdChildIndex, navBarId, row+"");
				
			}
			
			//outer div
			rh.append("  </div>");
			
			String js = codeToInsert(generator, instance, SnippetLocation.PRIMITIVE_WIDGET, "htmlContainer_jsHeader.js", null);
			
			rh.append("<script>");
			rh.append(js);
			rh.append("</script>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void renderDivForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh, WbdChildIndex wbdChildIndex, WidgetId navBarId, String row) throws WbdException
	{
		
		//Get Properties
		String tag = instance.getProperty("tag", wbdChildIndex);
		
		// Work out how to draw the line in the drop area
		UserPreferences pref = DesignerHelper.getUserPreferences(ud);
		int depth = instance.getDepth();
		ColorScheme colorScheme = pref.getCurrentColorScheme();
		String lineColor = colorScheme.getLineColor(depth);
		int lineWidth = pref.getGridLineWidth();
		
		// Find the child
		WbdWidget child = instance.findChildByIndex(wbdChildIndex);

		// Work out the ID for the area where we can drop stuff.
		WidgetId dropAreaWidgetId = new WidgetId(instance);
		dropAreaWidgetId.setPrefix("cell");
		dropAreaWidgetId.setIndex(wbdChildIndex);
		String id = "id='" + navBarId + "["+DIV_INDEX_PREFIX+row+"]" + "'";
		
		String style = "";

		// Set widths if specified, and make sure there's enough space to drop
		if (child == null) {
			style += "width: 18px;height:18px;";
		}
		
		// Perhaps show he grid
		if (generator.getShowGrid()) {
			style += "border-style: solid;";
			style += "border-width: " + lineWidth + "px;";
			style += "border-color: " + lineColor + ";";
		}

		// Make it dropable if we're editing
		String cell_styleClass = "designer-properties";
		if (child == null && instance.mayEdit(ud)) {
			cell_styleClass += " designer-droppable";
		}
		
		// Display the cell inside the frame
		rh.append("<div "+id+" style=\"" + style + "\" class=\"" + cell_styleClass + "\" alt=\""+tag+"\">");
		if (child != null) {
			// There is a child in this frame cell
			if (child.getController() instanceof ZoneWidget)
				child.getController().renderForPreview(generator, instance, ud, rh);
			else
				child.renderForDesignerWithWrapper(generator, ud, rh);
		}
		rh.append("</div>");
	}
	
	public void renderDivForJsp(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh, WbdChildIndex wbdChildIndex) throws Exception
	{
    	try {
    		String elementId = instance.getProperty("elementId", wbdChildIndex);
    		String className = instance.getProperty("class", wbdChildIndex);
    		String tag = instance.getProperty("tag", wbdChildIndex);
    		
//    		String navpoint = instance.getProperty("navpoint", null);

//    		String link = "";
//			if ( !navpoint.equals(""))
//				link = generator.navpointUrlWithoutAutomaticParameters(ud, navpoint);


			// Decide which style and style class to use (may be defined in frame.css)
			String clazz = "";
			if ( !className.equals(""))
				clazz += "class=\"" + className + "\"";
			
			String idstr = "";
			if ( !elementId.trim().equals(""))
				idstr = " id=\"" + elementId.trim() + "\"";
			rh.append("<"+tag+" " + idstr + " " +clazz);

			rh.append(">");
			WbdWidget child = instance.findChildByIndex(wbdChildIndex);
			if (child != null) {
				WbdWidgetController controller = child.getController();
				controller.renderForJSP(generator, child, (UimHelper)ud, rh);
			}
			rh.append("</"+tag+">\n");
//			rh.append(endAnchor);
			

    	} catch (Exception e) {
    		WbdException wbdException = new WbdException("Error rendering FrameWidget:\n" + e);
    		wbdException.setStackTrace(e.getStackTrace());
    		throw wbdException;
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
		html += "TtPane_layout.showProperties('navBar!"+instance.fullPath() + "["+DIV_INDEX_PREFIX + selectedRow +"]');";
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
			cells = node.getNodes("./divChild");
		}
		catch (XDataException e)
		{
			throw new WbdException("Error getting cells");
		}
		while (cells.next())
		{
			String indexStr = cells.getText("./index");
			String elementId = cells.getText("./elementId");
			String clazz = cells.getText("./class");
			String tag = cells.getText("./tag");
			
			if (tag == null || tag.equals("")) 
				tag = DEFAULT_TAG;

			WbdChildIndex index = new WbdChildIndex(DIV_INDEX_PREFIX + indexStr);
			widget.defineProperty(new WbdStringProperty("elementId", index, "Element Id", elementId));
			widget.defineProperty(new WbdSelectProperty("tag", index, "Tag", "address,article,div,em,footer,h1,h2,h3,h4,h5,h6,header,hgroup,main,nav,p,section,small,span,strong", tag));
			widget.defineProperty(new WbdStringProperty("class", index, "Class", clazz));
			
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
			WbdChildIndex index = new WbdChildIndex(DIV_INDEX_PREFIX+row);
			
			String elementId = (instance.getProperty("elementId", index) == null) ? "" : instance.getProperty("elementId", index);
			String clss = (instance.getProperty("class", index) == null) ? "" : instance.getProperty("class", index);
			String tag = (instance.getProperty("tag", index) == null) ? DEFAULT_TAG : instance.getProperty("tag", index);
			
			
			pw.println(indentStr(indent) + "<divChild>");
			pw.println(indentStr(indent + 1) + "<index>" + row + "</index>");
			instance.getProperties().writeProperties(pw, indent + 1, index);
			
			
			WbdWidget child = instance.findChildByIndex(index);
			if (child != null)
				child.saveToFile(generator, pw, indent + 1);
			else {
				instance.defineProperty(new WbdStringProperty("elementId", index, "Id", elementId));
				instance.defineProperty(new WbdSelectProperty("tag", index, "Tag", "address,article,div,em,footer,h1,h2,h3,h4,h5,h6,header,hgroup,main,nav,p,section,small,span,strong", tag));
				instance.defineProperty(new WbdStringProperty("class", index, "class", clss));
			}
			pw.println(indentStr(indent) + "</divChild>");
		}

	}
	
	@Override
	public void renderProperties(WbdGenerator generator, UimData ud, WbdRenderHelper rh, WbdWidget instance, WidgetId id, boolean displayOnly) throws WbdException {
		
		WidgetId navBarId = new WidgetId(instance);
		navBarId.setPrefix("navBar");
		
		WbdChildIndex index = id.getIndex();
		System.out.println(index.getIndexStr());
		
		rh.renderPropertiesHeading(generator, ud, instance, id, this.getLabel(instance), -1);
		
		rh.append("<table>");
			
		rh.append("<tr>");
		rh.append("<td id=\"id-designer-properties-navBar3\">");//[elementId, rows, selectedRow, position, inverted, _widgetId, _controller, _linkedWidget]
//		rh.append("<label>NavBar Property</label>");
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
