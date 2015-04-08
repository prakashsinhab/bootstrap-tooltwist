package tooltwist.bootstrap.widgets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.DesignerHelper;
import tooltwist.wbd.DesignerRole;
import tooltwist.wbd.DesignerUIM;
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
import tooltwist.wbd.WbdProperty;
import tooltwist.wbd.WbdProperty.DisplayMode;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdVersionSelector;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WidgetId;

import com.dinaa.DinaaException;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;
import com.dinaa.ui.UimResult;
import com.dinaa.xpc.XpcSecurity;
import com.tooltwist.xdata.XDException;
import com.tooltwist.xdata.XSelector;

/**
 * Accordion Widget
 */
public class AccordionWidget extends ContainerWidget
{
	private static final Logger logger = LoggerFactory.getLogger(AccordionWidget.class);
	
	private static final String ACCORDION_INDEX_PREFIX = "accordion-";
	private static final boolean USE_PRODUCTION_HELPER = false;

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
		
		WbdProperty rowProperty = new WbdStringProperty("rows", null, "Rows", "1");
		rowProperty.setDisplayMode(DisplayMode.DEBUG_ONLY);
		rowProperty.setEditable(false);
		instance.defineProperty(rowProperty);
		
		instance.defineDebugProperty(new WbdStringProperty("selectedRow", null, "Selected Row", "0"));
		
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
				new StylesheetCodeInserter(generator, instance, "accordion_cssHeader.css"),
//					new StylesheetLinkInserter("/ttsvr/bootstrap/css/bootstrap.min.css"),
//					new JavascriptCodeInserter(generator, instance, "accordion_jsHeader.js"),
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
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "accordion_jsHeader.js")),

//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "accordion_cssHeader.css")),
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
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "accordion_jsHeader.js")),
					
//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "accordion_cssHeader.css")),

//				// Add import statements to the JSP
//				new PageImportCodeInserter(XData.class.getName()),
			};
			codeInserterList.add(arr);

			if (USE_PRODUCTION_HELPER)
			{
				SnippetParam[] productionHelperParams = null;
//				codeInserterList.add(WbdProductionHelper.codeInserter(instance, AccordionProductionHelper.class.getName(), productionHelperParams));
//				codeInserterList.add(new PageImportCodeInserter(AccordionProductionHelper.class.getName()));
			}
		}

	}
	
	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Accordion";
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
			
			rh.append("<div class=\"accordion\" id=\"accordion2\">\n");

			String rows = instance.getProperty("rows", null);
			for (int row = 0; row < Integer.valueOf(rows); row++) {
				
				String indexPrefix = row + ",";
				WbdChildIndex wbdChildIndex = new WbdChildIndex(ACCORDION_INDEX_PREFIX+row);
				String title = instance.getProperty("title", wbdChildIndex);
				
				rh.append("	<div class=\"accordion-group\">\n");
				rh.append("		<div class=\"accordion-heading\">\n");
				rh.append("			<a class=\"accordion-toggle collapsed\" data-toggle=\"collapse\" data-parent=\"#accordion2\" href=\"#collapse-" + row + "\"> " + title + " </a>\n");
				rh.append("		</div>\n");
				rh.append("		<div id=\"collapse-" + row + "\" class=\"accordion-body collapse\" style=\"height: 0px;\">\n");
				rh.append("			<div class=\"accordion-inner\">\n");
				rh.append("				");
				this.flowChildren_renderForJSP(generator, instance, ud, rh, indexPrefix);
				rh.append("\n");
				rh.append("			</div>\n");
				rh.append("		</div>\n");
				rh.append("	</div>\n");
			}
			
			rh.append("</div>\n");

		} catch (Exception e) {
			throw new WbdException("Error finding cell widget: " + e);
		}
	}
	
	private void render(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) {
		
		try {
			
			String rows = instance.getProperty("rows", null);
			Object tmp = instance.getProperty("selectedRow", null);
			int selectedRow = Integer.valueOf(tmp == "" ? "0" : tmp.toString());
			
			if (selectedRow >= Integer.valueOf(rows)) {
				selectedRow = 0;
				instance.setProperty("selectedRow", null, "0");
				instance.setPropertyWhileLoading("selectedRow", null, "0");
			}
			
			String elementId = instance.getProperty("elementId", null);
			
			WidgetId accordionId = new WidgetId(instance);
			accordionId.setPrefix("accordion");
			
			rh.append("<div id=\""+accordionId.fullPath()+"\">");
			rh.append("<div class=\"accordion\" id=\"accordion-"+elementId+"\">");
			
			for(int row = 0; row < Integer.valueOf(rows); row++) {
				
				String display = (selectedRow == row) ? "block" : "none";
				String indexPrefix =  row + ",";
				WbdChildIndex wbdChildIndex = new WbdChildIndex(ACCORDION_INDEX_PREFIX+row);
				String title = instance.getProperty("title", wbdChildIndex);
				
				rh.append("	<div class=\"accordion-group\">");
				rh.append("		<div class=\"accordion-heading designer-properties\" id=\""+accordionId + "["+ACCORDION_INDEX_PREFIX+row+"]"+"\">");
				rh.append("			<a class=\"accordion-toggle collapsed\" data-toggle=\"collapse\" data-parent=\"#accordion-"+elementId+"\" href=\"#collapse-"+elementId+"-"+row+"\" onclick=\"TtAccordion.selectAccordion('"+accordionId.fullPath()+"', '"+row+"')\"> "+title+" </a>"); 
				rh.append("		</div>");
				rh.append("		<div id=\"collapse-"+elementId+"-"+row+"\" class=\"accordion-body collapse\" style=\"height: auto;display: "+display+";\">");
				rh.append("			<div class=\"accordion-inner\">");
				rh.append("				<div class=\"accordion-container\">");
				this.flowChildren_renderForDesigner(generator, instance, ud, rh, indexPrefix);
				rh.append("			   </div>");
				rh.append("			</div>");
				rh.append("		</div>");
				rh.append("	</div>");
			}
			
			rh.append("</div>");
			rh.append("</div>");
			
			String js = codeToInsert(generator, instance, SnippetLocation.PRIMITIVE_WIDGET, "accordion_jsHeader.js", null);
			
			rh.append("<script>");
			rh.append(js);
			rh.append("</script>");
			
		} catch (Exception e) {
			WbdException wbdException = new WbdException(e.toString());
			wbdException.setStackTrace(e.getStackTrace());
		}
		
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
	
	public UimResult op_insertAccordion(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_insertAccordion() start...");
		
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
	
	public UimResult op_removeAccordion(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_insertAccordion() start...");
		
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
	
	public UimResult op_selectAccordion(DesignerUIM designer, UimHelper uh, DesignerHelper helper, WbdWidget instance) throws DinaaException, ServletException, IOException {
		logger.debug("op_selectAccordion() start...");
		
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
//		html += "TtPane_layout.showPropertiesForElement('accordion"+instance.fullPath()  + "', false);";
//		html += "TtPane_layout.hidePropertiesDialog();";
		html += "TtPane_layout.showProperties('accordion!"+instance.fullPath() + "[" + ACCORDION_INDEX_PREFIX + selectedRow +"]');";
		html += "jQuery(\"#id-designer-properties-div\").css({\"opacity\":\"1\"});";
		html += "</script>\n";
//		
		return uh.reply(html);
	}
	
	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XSelector node) throws WbdException
	{
		// Get the cells
		XSelector cells;
		try
		{
			super.loadPropertiesFromXml(generator, widget, node);
			cells = node.select("./accordions");
		}
		catch (XDException e)
		{
			throw new WbdException("Error getting cells");
		}
		while (cells.next())
		{
			try
			{
				String indexStr = cells.getString("./index");
				String title = cells.getString("./title");
	
				WbdChildIndex index = new WbdChildIndex(ACCORDION_INDEX_PREFIX + indexStr);
				widget.defineProperty(new WbdStringProperty("title", index, "Title", title));

			
				XSelector widgetNode = cells.select("./widget");
				if (widgetNode.next())
				{
					WbdWidget child = new WbdWidget(widget, index);
					child.setParent(widget, index);
				}
			}
			catch (XDException e)
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

		for (int r = 0; r < rows; r++) {
			WbdChildIndex index = new WbdChildIndex(ACCORDION_INDEX_PREFIX+r);
			String title = instance.getProperty("title", index);
			title = (title == null) ? "Title goes here..." : title;
			pw.println(indentStr(indent) + "<accordions>");
			pw.println(indentStr(indent + 1) + "<index>" + r + "</index>");
			instance.getProperties().writeProperties(pw, indent + 1, index);
			WbdWidget child = instance.findChildByIndex(index);
			if (child != null)
				child.saveToFile(generator, pw, indent + 1);
			else {
				instance.defineProperty(new WbdStringProperty("title", index, "Title", title));
			}
			pw.println(indentStr(indent) + "</accordions>");
		}
		
		
		this.flowChildren_writeProperties(generator, instance, pw, indent, null);

	}
	
	@Override
	public void renderProperties(WbdGenerator generator, UimData ud, WbdRenderHelper rh, WbdWidget instance, WidgetId id, boolean displayOnly) throws WbdException {
		
		rh.renderPropertiesHeading(generator, ud, instance, id, this.getLabel(instance), -1);

		rh.append("<table><tr>");
		rh.append("<td id=\"id-designer-properties-accordion1\">");
		rh.renderProperties(generator, ud, instance, id, new String[] {});
		rh.append("</td>");
		rh.append("</tr>");
		
		id.setIndex(null);
		
		rh.append("<tr>");
		rh.append("<td id=\"id-designer-properties-accordion3\">");
		String[] ignoredPropertiesForGrid = {"_controller", "_widgetId", "cellDivs", "rows", "selectedRow" };
		rh.renderProperties(generator, ud, instance, id, ignoredPropertiesForGrid);
		rh.append("<br>");
		XpcSecurity credentials = ud.getCredentials();
		boolean canChangeGrid = credentials.hasRole(DesignerRole.CHANGE_GRIDS.getRoleCode());
		String index = id.fullPath();
		
		//Define Buttons on editable mode
		if (instance.mayEdit(ud) && canChangeGrid) {
			
			rh.append("  <span class=\"button-accordion\" style=\"float: right;cursor: pointer;\" onclick=\"TtAccordion.removeAccordion('"+index+"');\" title=\"Decrease row count.\">&nbsp;-&nbsp;</span>");
			rh.append("  <span class=\"button-accordion\" style=\"float: right;cursor: pointer;\" onclick=\"TtAccordion.insertAccordion('"+index+"');\" title=\"Increase row count.\">&nbsp;+&nbsp;</span>");
			
			rh.append("<span style=\"float: right;\" >&nbsp;&nbsp;</span>");
			
		}
		
		rh.append("  <span class=\"button-accordion\" style=\"float: right;cursor: pointer;\" onclick=\"TtAccordion.expandAll('"+index+"');\"  title=\"Expand all rows.\">&nbsp;&#8595;&nbsp;</span>");
		rh.append("  <span class=\"button-accordion\" style=\"float: right;cursor: pointer;\" onclick=\"TtAccordion.collapseAll('"+index+"');\" title=\"Collapse all rows.\">&nbsp;&#8593;&nbsp;</span>");
		
		rh.append("</td>");
		rh.append("</tr></table>");
		rh.append("<script>jQuery(\".button-accordion\").button();</script>");
		
	}
}
