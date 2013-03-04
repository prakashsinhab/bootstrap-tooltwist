package tooltwist.bootstrap.widgets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import tooltwist.bootstrap.properties.WbdSelectProperty;
import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.DesignerHelper;
import tooltwist.wbd.DesignerRole;
import tooltwist.wbd.DesignerUIM;
import tooltwist.wbd.JavascriptCodeInserter;
import tooltwist.wbd.Snippet;
import tooltwist.wbd.Snippet.SnippetLocation;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.SnippetParamList;
import tooltwist.wbd.StylesheetCodeInserter;
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
 * ButtonDropdownWidget Widget
 */
public class ButtonDropdownWidget extends ContainerWidget
{
	
	Logger logger = Logger.getLogger(ButtonDropdownWidget.class);
	
	private static final String BUTTONDROPDOWN_INDEX_PREFIX = "buttonDropDown-";
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
		
		instance.defineProperty(new WbdStringProperty("label", null, "Label", "My Button"));
		instance.defineProperty(new WbdSelectProperty("type", null, "Type", "primary,danger,warning,success,info,inverse", ""));
		instance.defineProperty(new WbdRadioTextProperty("dropPosition", null, "Orientation", "dropdown,dropup", "dropdown"));
		instance.defineProperty(new WbdSelectProperty("sizes", null, "Sizes", "mini,small,large", ""));
		
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
					new StylesheetCodeInserter(generator, instance, "buttonDropdown_cssHeader.css"),
					new JavascriptCodeInserter(generator, instance, "buttonDropdown_jsHeader.js")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
					new StylesheetCodeInserter(generator, instance, "buttonDropdown_cssHeader.css"),
					new JavascriptCodeInserter(generator, instance, "buttonDropdown_jsHeader.js")
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
		return "Button Dropdown";
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

			String elementId = instance.getFinalProperty(generator, "elementId");
			String rows = instance.getProperty("rows", null);
			String label = instance.getFinalProperty(generator, "label");
			String type = instance.getFinalProperty(generator, "type");
			String dropPosition = instance.getFinalProperty(generator, "dropPosition");
			String sizes = instance.getFinalProperty(generator, "sizes");
			
			if (!elementId.equals("")) {
				elementId = " id='" + elementId + "' ";
			}
			
			if (!type.equals("")) {
				type = "btn-" + type;
			}
			
			String sizeClass = "";
			switch (sizes) {
				case "mini":
					sizeClass = " btn-mini";
					break;
				case "small":
					sizeClass = " btn-small";
					break;
				case "large":
					sizeClass = " btn-large";
					break;
			}
			
			rh.append("<div" + elementId + " class='btn-group " + dropPosition + "'>\n");
			rh.append("<button class='btn " + type + sizeClass + "'>" + label + "</button>\n");
			rh.append(" <button class='btn dropdown-toggle " + type + sizeClass + "' data-toggle='dropdown' href='#'>\n");
			rh.append("   <span class='caret'></span>\n");
			rh.append(" </button>\n");
			rh.append("  <ul class='dropdown-menu'>\n");
					
			for(int row = 0; row < Integer.valueOf(rows); row++) {
				
				WbdChildIndex wbdChildIndex = new WbdChildIndex(BUTTONDROPDOWN_INDEX_PREFIX+row);
				
				String title = instance.getProperty("title", wbdChildIndex);
				String navpointId = instance.getProperty("navpoint", wbdChildIndex);
				
				navpointId = RoutingUIM.navpointUrl(ud, navpointId, AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS);
				
				rh.append("      <li><a href=\""+navpointId+"\">"+title+"</a></li>\n");
				
			}
			
			rh.append(" </ul>\n");
			rh.append("</div>\n");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void render(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException {
		
		String rows = instance.getProperty("rows", null);
		String buttonLabel = instance.getFinalProperty(generator, "label");
		
		WidgetId buttonDropDownId = new WidgetId(instance);
		buttonDropDownId.setPrefix("buttonDropDown");
		
		rh.append("	<div id='"+ buttonDropDownId.fullPath() + "' class='btn-group'>\n");
		rh.append("<button class='btn'>" + buttonLabel + "</button>\n");
		rh.append(" <button class='btn dropdown-toggle' data-toggle='dropdown' href='#'>\n");
		rh.append("   <span class='caret'></span>\n");
		rh.append(" </button>\n");
		rh.append("  <ul class='dropdown-menu'>\n");
		
		for(int row = 0; row < Integer.valueOf(rows); row++) {
			
			WbdChildIndex wbdChildIndex = new WbdChildIndex(BUTTONDROPDOWN_INDEX_PREFIX+row);
			String title = instance.getProperty("title", wbdChildIndex);
			
			rh.append("      <li class=\"designer-properties\"  id=\""+buttonDropDownId + "["+BUTTONDROPDOWN_INDEX_PREFIX+row+"]" + 
										"\" onclick=\"ButtonDropDown.selectItem('"+buttonDropDownId.fullPath()+"','"+row+"')\"><a href=\"#\">"+title+"</a></li>\n");
			
		}
	    
		rh.append(" </ul>\n");
		rh.append("</div>\n");
	    
	    String js = codeToInsert(generator, instance, SnippetLocation.PRIMITIVE_WIDGET, "buttonDropdown_jsHeader.js", null);
	    rh.append("<script>");
	    rh.append(js);
	    rh.append("</script>");
		
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
	
	public UimResult op_insertItem(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_insertItem() start...");
		
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
	
	public UimResult op_removeItem(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_removeItem() start...");
		
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
	
	//redraw the page
	public UimResult op_selectItem(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_selectItem() start...");
		
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
		html += "TtPane_layout.showProperties('buttonDropDown!"+instance.fullPath() + "["+BUTTONDROPDOWN_INDEX_PREFIX + selectedRow +"]');";
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
			cells = node.getNodes("./buttonDropDowns");
		}
		catch (XDataException e)
		{
			throw new WbdException("Error getting cells");
		}
		while (cells.next())
		{
			String indexStr = cells.getText("./index");
			String title = cells.getText("./title");
			String navpoint = cells.getText("./navpoint");

			WbdChildIndex index = new WbdChildIndex(BUTTONDROPDOWN_INDEX_PREFIX + indexStr);
			widget.defineProperty(new WbdStringProperty("title", index, "Title", title));
			widget.defineProperty(new WbdNavPointProperty("navpoint", index, "Navpoint", navpoint));
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
			WbdChildIndex index = new WbdChildIndex(BUTTONDROPDOWN_INDEX_PREFIX+row);
			String title = instance.getProperty("title", index);
			title = (title == null) ? "Link" : title;
			
			String navpoint = instance.getProperty("navpoint", index);
			title = (title == null) ? "" : title;
			
			pw.println(indentStr(indent) + "<buttonDropDowns>");
			pw.println(indentStr(indent + 1) + "<index>" + row + "</index>");
			instance.getProperties().writeProperties(pw, indent + 1, index);
			pw.println(indentStr(indent) + "</buttonDropDowns>");
			
			
			WbdWidget child = instance.findChildByIndex(index);
			if (child != null)
				child.saveToFile(generator, pw, indent + 1);
			else {
				instance.defineProperty(new WbdStringProperty("title", index, "Title", title));
				instance.defineProperty(new WbdNavPointProperty("navpoint", index, "Navpoint", navpoint));
			}
		}

	}
	
	@Override
	public void renderProperties(WbdGenerator generator, UimData ud, WbdRenderHelper rh, WbdWidget instance, WidgetId id, boolean displayOnly) throws WbdException {
		
		WidgetId buttonDropDownId = new WidgetId(instance);
		buttonDropDownId.setPrefix("buttonDropDown");
		
		WbdChildIndex index = id.getIndex();
		System.out.println(index.getIndexStr());
		
		rh.renderPropertiesHeading(generator, ud, instance, id, this.getLabel(instance), -1);
		
		rh.append("<table>");
		
		// Click the actual widget
		if (id.getIndex().getIndexStr().equals("")) {
		
			rh.append("<tr>");
			rh.append("<td id=\"id-designer-properties-buttonDropDown3\">");//[elementId, rows, selectedRow, position, inverted, _widgetId, _controller, _linkedWidget]
			String[] ignoredPropertiesForGrid = {"_controller", "_widgetId", "cellDivs", "rows", "selectedRow", "_linkedWidget"};
			rh.renderProperties(generator, ud, instance, buttonDropDownId, ignoredPropertiesForGrid);
			rh.append("</td>");
			rh.append("</tr>");
			
			rh.append("<tr>");
			rh.append("<td id=\"id-designer-properties-buttonDropDown3\">");//[elementId, rows, selectedRow, position, inverted, _widgetId, _controller, _linkedWidget]
			rh.append("<br>");
			XpcSecurity credentials = ud.getCredentials();
			boolean canChangeGrid = credentials.hasRole(DesignerRole.CHANGE_GRIDS.getRoleCode());
			if (instance.mayEdit(ud) && canChangeGrid) {
				
				rh.append("  <span class=\"button-ButtonDropDown\" style=\"float: right;cursor: pointer;\" onclick=\"ButtonDropDown.removeItem('"+id.fullPath()+"');\" title=\"Remove Item.\">&nbsp;-&nbsp;</span>");
				rh.append("  <span class=\"button-ButtonDropDown\" style=\"float: right;cursor: pointer;\" onclick=\"ButtonDropDown.insertItem('"+id.fullPath()+"');\" title=\"Insert item.\">&nbsp;+&nbsp;</span>");
			}
			
			rh.append("</td>");
			rh.append("</tr>");
			
		}else {
			
			//custom property in dialog  when clicking an item.
			rh.append("<tr>");
			rh.append("<td id=\"id-designer-properties-buttonDropDown1\">");
			rh.append("<br>");
			rh.append("<label>Link Property</label>");
			rh.renderProperties(generator, ud, instance, id, new String[] {});
			rh.append("</td>");
			rh.append("</tr>");
		}
		
		rh.append("</table>");
		rh.append("<script>jQuery(\".button-ButtonDropDown\").button();</script>");
		
	}
}
