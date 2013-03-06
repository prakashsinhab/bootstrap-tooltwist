package tooltwist.bootstrap.widgets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

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
 * 
 * @author richarddimalanta
 *@see https://github.com/aravindnaidu/bootstrap-tooltwist/wiki/Dropdown-Menu
 */

public class DropdownWidget extends ContainerWidget
{
	
	Logger logger = Logger.getLogger(DropdownWidget.class);
	
	private static final String DROPDOWN_INDEX_PREFIX = "dropDown-";
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
		
		instance.defineProperty(new WbdRadioTextProperty("visible", null, "Visible", "yes,no", "yes"));
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
					new StylesheetCodeInserter(generator, instance, "dropdown_cssHeader.css"),
					new JavascriptCodeInserter(generator, instance, "dropdown_jsHeader.js")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
					new StylesheetCodeInserter(generator, instance, "dropdown_cssHeader.css"),
					new JavascriptCodeInserter(generator, instance, "dropdown_jsHeader.js")
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
		return "Dropdown";
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
		
		String rows = instance.getProperty("rows", null);
		String visible = instance.getFinalProperty(generator, "visible");
		
		boolean isDisplay = false;
		if (visible.equals("yes")) {
			isDisplay = true;
		}
		
		WidgetId dropDownId = new WidgetId(instance);
		dropDownId.setPrefix("dropDown");
		
		renderDropDownJSP(instance, rh, ud, dropDownId, rows, "", isDisplay);
	}
	
	private void render(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException {
		
		String rows = instance.getProperty("rows", null);
		String visible = instance.getFinalProperty(generator, "visible");
		
		boolean isDisplay = false;
		if (visible.equals("yes")) {
			isDisplay = true;
		}
		
		WidgetId dropDownId = new WidgetId(instance);
		dropDownId.setPrefix("dropDown");
		rh.append("<div>\n");
		rh.append("<span class='widgetProperty'>Dropdown Properties</span>\n");
		
		renderDropDown(instance, rh, dropDownId, rows, "", isDisplay);
		
		rh.append("</div>\n");
	    
	    String js = codeToInsert(generator, instance, SnippetLocation.PRIMITIVE_WIDGET, "dropdown_jsHeader.js", null);
	    rh.append("<script>");
	    rh.append(js);
	    rh.append("</script>");
		
	}
	
	private void renderDropDown(WbdWidget instance, WbdRenderHelper rh, WidgetId dropDownId, String rows, String indexPrefix, boolean initialDisplay) throws WbdException {
		
		String display = "";
		if (initialDisplay) {
			display = "style='display:block; position: static; margin-bottom: 5px'";
		}
	
		rh.append("<ul id='"+ dropDownId.fullPath() + "' class='dropdown-menu' role='menu' aria-labelledby='dLabel' " + display + ">\n");
		
		for(int row = 0; row < Integer.valueOf(rows); row++) {
			
			String submenuPrefix = indexPrefix + (indexPrefix.equals("") ? "" : ",") + row;
			WbdChildIndex wbdChildIndex = new WbdChildIndex( DROPDOWN_INDEX_PREFIX + submenuPrefix );
			String title = instance.getProperty("title", wbdChildIndex);
			String subRows = instance.getProperty("rows", wbdChildIndex);

			//add a submenu class if a sub-item was added.
			String withSubClass = "";
			if (subRows != null) {
				if (Integer.valueOf(subRows) >= 1) {
					withSubClass = "dropdown-submenu";
				}
			}
			
			rh.append("<li class='designer-properties " + withSubClass + "' id='"+dropDownId + "["+DROPDOWN_INDEX_PREFIX+submenuPrefix+"]'>\n");
			rh.append("  <a tabindex='-1' href='javascript:void(0);' onclick=\"DropDown.selectItem('"+dropDownId.fullPath()+"','"+row+"');\">"+title+"</a>\n");
			
			if (subRows != null && !subRows.equals("") && Integer.valueOf(subRows) > 0) {
				renderDropDown(instance, rh, dropDownId, subRows, submenuPrefix, false);
			}
			rh.append(" </li>\n");
		}
		
		rh.append("</ul>\n");
	}
	
	private void renderDropDownJSP(WbdWidget instance, WbdRenderHelper rh, UimHelper ud, WidgetId dropDownId, String rows, String indexPrefix, boolean initialDisplay) throws WbdException {
		
		String display = "";
		if (initialDisplay) {
			display = "style='display:block; position: static; margin-bottom: 5px'";
		}
		
		rh.append("<ul id='"+ dropDownId.fullPath() + "' class='dropdown-menu' role='menu' aria-labelledby='dLabel' " + display + ">\n");
		
		for(int row = 0; row < Integer.valueOf(rows); row++) {
			
			String submenuPrefix = indexPrefix + (indexPrefix.equals("") ? "" : ",") + row;
			WbdChildIndex wbdChildIndex = new WbdChildIndex( DROPDOWN_INDEX_PREFIX + submenuPrefix );
			String title = instance.getProperty("title", wbdChildIndex);
			String subRows = instance.getProperty("rows", wbdChildIndex);
			
			//add a submenu class if a sub-item was added.
			String withSubClass = "";
			if (subRows != null) {
				if (Integer.valueOf(subRows) >= 1) {
					withSubClass = "dropdown-submenu";
				}
			}
			
			String navpointId = instance.getProperty("navpoint", wbdChildIndex);
			navpointId = RoutingUIM.navpointUrl(ud, navpointId, AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS);
			
			rh.append("<li class='designer-properties " + withSubClass + "'>\n");
			rh.append("  <a tabindex='-1' href='" + navpointId + "'>" + title + "</a>\n");
			
			if (subRows != null && !subRows.equals("") && Integer.valueOf(subRows) > 0) {
				renderDropDownJSP(instance, rh, ud, dropDownId, subRows, submenuPrefix, false);
			}
			
			rh.append(" </li>\n");
			
		}
	    
		rh.append("</ul>\n");
		
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
		
		String idx = uh.getRequestValue("index");
		WbdChildIndex index = new WbdChildIndex(idx);
		
		String rows = instance.getProperty("rows",index);
		rows = (Integer.valueOf(rows == null  || rows.equals("")? "0" : rows) + 1) + "";
		instance.setProperty("rows",index, rows);
		
		UimResult reply =  saveAndRedrawDesigner(designer, uh, helper, instance);
		return reply;
	}
	
	public UimResult op_removeItem(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_removeItem() start...");
		
		String idx = uh.getRequestValue("index");
		WbdChildIndex index = new WbdChildIndex(idx);
		
		String rowsStr = instance.getProperty("rows",index);
		
		if (rowsStr == null || rowsStr.equals("")) {
			rowsStr = "0";
		}
		int rows = Integer.parseInt(rowsStr);
		
		if (rows > 0) {	
			rows = rows - 1;
			instance.setProperty("rows",index, Integer.toString(rows));
		}
		
		UimResult reply =  saveAndRedrawDesigner(designer, uh, helper, instance);
		
		return reply;
	}
	
	private UimResult saveAndRedrawDesigner(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("saveAndRedrawDesigner()");
		
		WbdVersionSelector vs = DesignerUIM.getUserVersionSelector(uh);
		WbdLibrary project = helper.getProject(uh);
		WbdGenerator generator = new WbdGenerator(vs, GenerationMode.DESIGN, project);
		
		WbdWidget root = instance.getRoot();
		root.setDirty();
		
		helper.saveAsRequired(uh);
		
		String html = helper.htmlForLayoutEditorPane(generator, uh, root);

		// Add additional processing to run after the pane is loaded
		html += "<script>\n";
		html += "try {\n";
		html += helper.javascriptToSetUpLayoutEditorPane(generator, uh, root);
		html += "} catch (e) { wbdLogException(e); }\n";
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
		
		//WbdWidget instance = helper.getCurrentWidget().getRoot();
		instance.setProperty("selectedRow", null, selectedRow);
	
		// Redisplay the editor part of the page
		String html = helper.htmlForLayoutEditorPane(generator, uh, root);
		
		// Add additional processing to run after the pane is loaded
		html += "<script>\n";
		html += "try {\n";
		html += helper.javascriptToSetUpLayoutEditorPane(generator, uh, root);
		html += "} catch (e) { wbdLogException(e); }\n";
		html += "</script>\n";

		return uh.reply(html);
	}
	
	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XNodes node) throws WbdException
	{
		logger.debug("loadPropertiesFromXml() start...");
		super.loadPropertiesFromXml(generator, widget, node);

		// Get the cells
		XNodes cells;
		try
		{
			cells = node.getNodes("./dropDowns");
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
			String row = cells.getText("./rows");

			WbdChildIndex index = new WbdChildIndex(DROPDOWN_INDEX_PREFIX + indexStr);
			widget.defineProperty(new WbdStringProperty("title", index, "Title", title == null ? "Link" : title));
			widget.defineProperty(new WbdNavPointProperty("navpoint", index, "Navpoint", navpoint));
			widget.defineHiddenProperty(new WbdStringProperty("rows", index, "Row", row));
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

		//recursion
		writeDropdownProperties(generator, instance, pw, indent, rows, "");

	}
	
	private void writeDropdownProperties(WbdGenerator generator, WbdWidget instance, PrintWriter pw, int indent, int rows, String indexPrefix) throws WbdException {
		
		for (int row = 0; row < rows; row++) {
			String menuIndex = indexPrefix + (indexPrefix.equals("") ? "" : ",") + row;
			WbdChildIndex index = new WbdChildIndex(DROPDOWN_INDEX_PREFIX + menuIndex);
			
			String title = instance.getProperty("title", index);
			title = (title == null) ? "Link" : title;
			
			String navpoint = instance.getProperty("navpoint", index);
			String subRows = instance.getProperty("rows", index);
			
			pw.println(indentStr(indent) + "<dropDowns>");
			pw.println(indentStr(indent + 1) + "<index>" + menuIndex + "</index>");
			instance.getProperties().writeProperties(pw, indent + 1, index);
			pw.println(indentStr(indent) + "</dropDowns>");
			
			
			WbdWidget child = instance.findChildByIndex(index);
			if (child != null) {
				child.saveToFile(generator, pw, indent + 1);
			} else {
				instance.defineProperty(new WbdStringProperty("title", index, "Title", title));
				instance.defineProperty(new WbdNavPointProperty("navpoint", index, "Navpoint", navpoint));
				instance.defineHiddenProperty(new WbdNavPointProperty("rows", index, "Rows", subRows));
			}
			
			if (subRows != null && !subRows.equals("") && Integer.valueOf(subRows) > 0) {
				writeDropdownProperties(generator, instance, pw, indent, Integer.valueOf(subRows), menuIndex);
			}
		}
		
	}
	
	@Override
	public void renderProperties(WbdGenerator generator, UimData ud, WbdRenderHelper rh, WbdWidget instance, WidgetId id, boolean displayOnly) throws WbdException {
		
		WbdChildIndex index = id.getIndex();
		
		rh.renderPropertiesHeading(generator, ud, instance, id, this.getLabel(instance), -1);
		
		rh.append("<table>");
		
		rh.append("<tr>");
		rh.append("<td id=\"id-designer-properties-dropDown3\">");//[elementId, rows, selectedRow, position, inverted, _widgetId, _controller, _linkedWidget]
		String[] ignoredPropertiesForGrid = {"_controller", "_widgetId", "cellDivs", "rows", "selectedRow", "_linkedWidget"};
		rh.renderProperties(generator, ud, instance, id, ignoredPropertiesForGrid);
		rh.append("</td>");
		rh.append("</tr>");
		
		rh.append("<tr>");
		rh.append("<td id=\"id-designer-properties-dropDown3\">");//[elementId, rows, selectedRow, position, inverted, _widgetId, _controller, _linkedWidget]
		rh.append("<br>");
		
		XpcSecurity credentials = ud.getCredentials();
		boolean canChangeGrid = credentials.hasRole(DesignerRole.CHANGE_GRIDS.getRoleCode());
		if (instance.mayEdit(ud) && canChangeGrid) {
			
			rh.append("  <span class=\"button-DropDown\" style=\"float: right;cursor: pointer;\" onclick=\"DropDown.removeItem('"+id.fullPath()+"','"+index.getIndexStr()+"');\" title=\"Remove Item.\">&nbsp;-&nbsp;</span>");
			rh.append("  <span class=\"button-DropDown\" style=\"float: right;cursor: pointer;\" onclick=\"DropDown.insertItem('"+id.fullPath()+"','"+index.getIndexStr()+"');\" title=\"Insert item.\">&nbsp;+&nbsp;</span>");
		}
		
		rh.append("</td>");
		rh.append("</tr>");
	
		rh.append("</table>");
		
		rh.append("<script>jQuery(\".button-DropDown\").button();</script>");
		
	}
}
