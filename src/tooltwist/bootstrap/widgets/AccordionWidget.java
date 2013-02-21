package tooltwist.bootstrap.widgets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.DesignerHelper;
import tooltwist.wbd.DesignerUIM;
import tooltwist.wbd.JavascriptCodeInserter;
import tooltwist.wbd.JavascriptLinkInserter;
import tooltwist.wbd.Snippet;
import tooltwist.wbd.WbdAlignProperty;
import tooltwist.wbd.WbdLabelProperty;
import tooltwist.wbd.WbdValignProperty;
import tooltwist.wbd.WbdWrapProperty;
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
import com.dinaa.data.XDataException;
import com.dinaa.data.XNodes;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;
import com.dinaa.ui.UimResult;
import com.dinaa.xpc.XpcSecurity;

/**
 * Accordion Widget
 */
public class AccordionWidget extends ContainerWidget
{
	
	Logger logger = Logger.getLogger(AccordionWidget.class);
	
	private static final String SNIPPET_PREVIEW = "accordion_preview.html";
	private static final String SNIPPET_DESIGN = "accordion_design.html";
	private static final String SNIPPET_PRODUCTION = "accordion_production.jsp";
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
		rh.renderSnippetForStaticPage(generator, instance, SNIPPET_PREVIEW, getSnippetParams(generator, instance, ud));
	}
	
	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		//rh.renderSnippetForStaticPage(generator, instance, SNIPPET_DESIGN, getSnippetParams(generator, instance, ud));
		render(generator, instance, ud, rh);
		//JavascriptCodeInserter javascriptCodeInserter = new JavascriptCodeInserter(generator, instance, "accordion_jsHeader.js");
		
	}
	
	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception {
		try {
			
//			rh.beforeProductionCode(generator, instance, null, false);
			
			
			rh.append("<div class=\"accordion\" id=\"accordion2\">");

			String rows = instance.getProperty("rows", null);
			for (int row = 0; row < Integer.valueOf(rows); row++) {
				String indexPrefix = row + ",";
				rh.append("<div class=\"accordion-group\">");
				rh.append("	<div class=\"accordion-heading\">");
				rh.append("		<a class=\"accordion-toggle collapsed\" data-toggle=\"collapse\" data-parent=\"#accordion2\" href=\"#collapse-" + row + "\"> Collapsible Group Item #" + row + " </a>");
				rh.append("	</div>");
				rh.append("	<div id=\"collapse-" + row + "\" class=\"accordion-body collapse\" style=\"height: 0px;\">");
				rh.append(		"<div class=\"accordion-inner\">");
				this.flowChildren_renderForJSP(generator, instance, ud, rh, indexPrefix);
				rh.append("		</div>");
				rh.append("	</div>");
				rh.append("</div>");
			}
//
			rh.append("</div>");
//			rh.renderSnippetForProduction(generator, instance, SNIPPET_PRODUCTION);
//			rh.afterProductionCode(generator, instance);

			// String html = codeToInsert(generator, instance,
			// SnippetLocation.PRIMITIVE_WIDGET, "accordion_production.jsp",
			// null);

			// String js = codeToInsert(generator, instance,
			// SnippetLocation.PRIMITIVE_WIDGET, "accordion_jsHeader.js", null);

			// rh.append(html);
			// rh.append(js);

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
	
	private void render(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) {
		
		try {
			
			String rows = instance.getProperty("rows", null);
			int selectedRow = Integer.valueOf(instance.getProperty("selectedRow", null));
			String elementId = instance.getProperty("elementId", null);
			
			WidgetId accordionId = new WidgetId(instance);
			accordionId.setPrefix("accordion");
			
			rh.append("<div id=\""+accordionId.fullPath()+"\">");
			rh.append("<div class=\"accordion\" id=\"accordion-"+elementId+"\">");
			
			for(int row = 0; row < Integer.valueOf(rows); row++) {
				
				String display = (selectedRow == row) ? "block" : "none";
				String indexPrefix =  row + ",";
				
				rh.append("	<div class=\"accordion-group\">");
				rh.append("		<div class=\"accordion-heading\">");
				rh.append("			<a class=\"accordion-toggle collapsed\" data-toggle=\"collapse\" data-parent=\"#accordion-"+elementId+"\" href=\"#collapse-"+elementId+"-"+row+"\"  onclick=\"TtAccordion.selectAccordion('"+accordionId.fullPath()+"', '"+row+"')\" > Collapsible Group Item #"+row+" </a>");
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
			
			rh.append("  <span class=\"label label-success add-accordion\" style=\"float: right;\" onclick=\"TtAccordion.insertAccordion('"+accordionId.fullPath()+"');\">+</span>");
			rh.append("  <span class=\"label label-important add-accordion\" style=\"float: right;\" onclick=\"TtAccordion.removeAccordion('"+accordionId.fullPath()+"');\">-</span>");
			
			rh.append("</div>");
			rh.append("</div>");
			
			String js = codeToInsert(generator, instance, SnippetLocation.PRIMITIVE_WIDGET, "accordion_jsHeader.js", null);
			
			rh.append("<script>");
			rh.append(js);
			rh.append("</script>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		html += "</script>\n";
		
		return uh.reply(html);
	}
	
	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XNodes node) throws WbdException
	{
		super.loadPropertiesFromXml(generator, widget, node);

//		// Get the cells
//		XNodes cells;
//		try
//		{
//			cells = node.getNodes("./cell");
//		}
//		catch (XDataException e)
//		{
//			throw new WbdException("Error getting cells");
//		}
//		while (cells.next())
//		{
//			String indexStr = cells.getText("./index");
//			String title = cells.getText("./title");
//
//			WbdChildIndex index = new WbdChildIndex(indexStr);
//			widget.defineProperty(new WbdStringProperty("title", index, "Title", title));
//
//			try
//			{
//				XNodes widgetNode = cells.getNodes("./widget");
//				if (widgetNode.next())
//				{
//					WbdWidget child = WbdWidget.loadBasicPropertiesFromXml(generator, widgetNode);
//					child.setParent(widget, index);
//				}
//			}
//			catch (XDataException e)
//			{
//				throw new WbdException("Error finding cell widget: " + e);
//			}
//		}
	
		
		//for children
		this.flowChildren_loadPropertiesFromXml(generator, widget, node, null);

	}

	@Override
	protected void writeProperties(WbdGenerator generator, WbdWidget instance, PrintWriter pw, int indent) throws WbdException
	{
		instance.getProperties().writeProperties(pw, indent, null);
		
		int rows = Integer.valueOf(instance.getProperty("rows", null));

//		for (int r = 0; r < rows; r++) {
//			WbdChildIndex index = new WbdChildIndex(r+"");
//			pw.println(indentStr(indent) + "<cell>");
//			pw.println(indentStr(indent + 1) + "<index>" + index.getIndexStr() + "</index>");
//			pw.println(indentStr(indent + 1) + "<title>" + index.getIndexStr() + "</title>");
//			instance.getProperties().writeProperties(pw, indent + 1, index);
//			WbdWidget child = instance.findChildByIndex(index);
//			if (child != null)
//				child.saveToFile(generator, pw, indent + 1);
//			pw.println(indentStr(indent) + "</cell>");
//		}
		
		
		this.flowChildren_writeProperties(generator, instance, pw, indent, null);

	}
	
	@Override
	public void renderProperties(WbdGenerator generator, UimData ud, WbdRenderHelper rh, WbdWidget instance, WidgetId id, boolean displayOnly) throws WbdException {
		// Render this differently, depending upon whether it's a child or the entire grid (ZIZ can that happen?)
//		super.renderProperties(instance, generator, ud, rh, id);
//		instance.renderProperties(generator, ud, rh, id);
//		buf.renderPropertyWidgetSeparator();
					
//		WbdChildIndex index = id.getIndex();
//		String indexStr = index.toString();
//		if (indexStr.equals("[]")) {
//			rh.append("Internal error 982773");
//			return;
//		}
//		boolean showCellAsWellAsGrid = ! index.getIndexStr().equals("");
//		int c = this.indexToCol(index);
//		int r = this.indexToRow(index);
		

		// Work out how many rows and columns, including the expansion cracks
//		int numCols = getCols(generator, instance);
//		int numRows = getRows(generator, instance);
		
		// See if it is an empty column
//		String emptyColumn = "Y";
//		for (int tmpR = 0; tmpR < numRows; tmpR++)
//		{
//			WbdChildIndex tmpIndex = colRowToIndex(c, tmpR);
//			WbdWidget tmpChild = instance.findChildByIndex(tmpIndex);
//			if (tmpChild != null)
//			{
//				emptyColumn = "N";
//				break;
//			}
//		}

		// See if it is an empty row
//		String emptyRow = "Y";
//		for (int tmpC = 0; tmpC < numCols; tmpC++)
//		{
//			WbdChildIndex tmpIndex = colRowToIndex(tmpC, r);
//			WbdWidget tmpChild = instance.findChildByIndex(tmpIndex);
//			if (tmpChild != null)
//			{
//				emptyRow = "N";
//				break;
//			}
//		}
		
		// See if the grid is empty
//		String emptyGrid = (instance.numChildren() == 0) ? "Y" : "N";

		
		rh.renderPropertiesHeading(generator, ud, instance, id, this.getLabel(instance), -1);

		rh.append("<table><tr>");
//		if (showCellAsWellAsGrid) {
////			// Render the properties for the grid
////			rh.renderPropertiesHeading(generator, ud, instance, id, "Grid", -1);
////			// Now render properties for the grid itself
////			id.setIndex(null);
////			rh.append("<div class=\"designer-properties-grid2\">");
////			//rh.append("<label>Grid</label>");
////			String[] ignoredPropertiesForGrid = { "_controller", "_widgetId", "cellDivs", "cols", "rows" };
////			rh.renderProperties(generator, ud, instance, id, ignoredPropertiesForGrid);
////			rh.append("</div>");
////		} else {
//			// Render the properties for the cell
//			// Add the cell
//			rh.append("<td id=\"id-designer-properties-grid1\">");
//			rh.append("<label>Cell:</label>");
//			String[] ignoredPropertiesForCell = {CELL_PROPERTY_LEFT, CELL_PROPERTY_TOP, "_initialized" };
//			rh.renderProperties(generator, ud, instance, id, ignoredPropertiesForCell);
//			rh.append("</td>");
//		}
			
			// Now render properties for the grid itself
//			id.setIndex(null);
			rh.append("<td id=\"id-designer-properties-accordion2\">");
//			if (showCellAsWellAsGrid)
//				rh.append("<label>Grid:</label>");
			String[] ignoredPropertiesForGrid = {"rows" };
			rh.renderProperties(generator, ud, instance, id, ignoredPropertiesForGrid);
			rh.append("<br/>");
//			rh.append("<button id=\"id-designer-properties-grid-add\" location=\""+id+"\" index=\""+index+"\">+</button>");
			XpcSecurity credentials = ud.getCredentials();
//			boolean canChangeGrid = credentials.hasRole(DesignerRole.CHANGE_GRIDS.getRoleCode());
//			if (instance.mayEdit(ud) && canChangeGrid) {
//				rh.append("<button id=\"id-designer-properties-grid-delete\" "
//						+ "location=\""+id+"\" "
//						+ "index=\""+index+"\" "
//						+ "emptycol=\""+emptyColumn+"\" "
//						+ "emptyrow=\""+emptyRow+"\" "
//						+ "emptygrid=\""+emptyGrid+"\" "
//						+ ">delete...</button>");
//			}
			rh.append("</tr></table>");
//			rh.append("<script>TtGrid.setupPropertyDialogButtons();</script>");
//		}
	}
}
