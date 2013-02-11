package tooltwist.bootstrap.widgets;

import java.io.PrintWriter;
import java.util.ArrayList;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.DesignerHelper;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.UserPreferences;
import tooltwist.wbd.UserPreferences.ColorScheme;
import tooltwist.wbd.WbdChildIndex;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;
import tooltwist.wbd.WidgetId;
import tooltwist.wbd.ZoneWidget;

import com.dinaa.data.XData;
import com.dinaa.data.XDataException;
import com.dinaa.data.XNodes;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

/**
 * Hero Unit
 */
public class HeroUnitWidget extends ContainerWidget
{
	private static final String INDEX_OF_CHILD = "child";

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
		instance.defineProperty(new WbdStringProperty("class", null, "class", ""));
		instance.defineProperty(new WbdStringProperty("label", null, "Label", ""));
		instance.defineProperty(new WbdStringProperty("width", null, "Width", ""));
		instance.defineProperty(new WbdStringProperty("height", null, "Height", ""));
//		instance.defineProperty(new WbdNavPointProperty("navpoint", null, "Navpoint", ""));
	}
	
	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException
	{
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {

//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "div_cssHeader.css")),
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
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "div_jsHeader.js")),

				// Include a CSS snippet
//				new StylesheetCodeInserter(generator, instance, "div_cssHeader.css"),
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
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "div_jsHeader.js")),
					
				// Include a CSS snippet
//				new StylesheetCodeInserter(generator, instance, "div_cssHeader.css"),

//				// Add import statements to the JSP
//				new PageImportCodeInserter(XData.class.getName()),
			};
			codeInserterList.add(arr);
		}

	}
	
	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Hero Unit";
	}
	
	@Override
	public WbdSizeInfo getSizeInfo(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
		return WbdSizeInfo.unknownSizeInfo();
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		renderForDesigner(generator, instance, ud, rh);
	}
	
	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		// Get the properties
//		String className = instance.getProperty("class", null);
		String label = instance.getProperty("label", null);
		String width = instance.getProperty("width", null);
		String height = instance.getProperty("height", null);

		// Work out how to draw the line in the drop area
		UserPreferences pref = DesignerHelper.getUserPreferences(ud);
		int depth = instance.getDepth();
		ColorScheme colorScheme = pref.getCurrentColorScheme();
		String lineColor = colorScheme.getLineColor(depth);
		int lineWidth = pref.getGridLineWidth();
		
		// Check the indexes of the children, in case they've been changed by an insert, re-order or delete.
		this.tidyChildren(generator, instance);
		
		// Display the children, starting with index 0.
		// The even entries will be left open, for inserting.
		// The odd entries will contain children.
		// 
		// Keep displaying children, until we find an odd positions that is empty.
		for (int i = 0; ; i++) {
			WbdChildIndex index = new WbdChildIndex("" + i);
			
			if ((i % 2) == 0) {
				// Even position - an insertion point

				// Work out the ID for the area where we can drop stuff.
				WidgetId dropAreaWidgetId = new WidgetId(instance);
				dropAreaWidgetId.setPrefix("cell");
				dropAreaWidgetId.setIndex(index);
				String id = "id='" + dropAreaWidgetId.fullPath() + "'";
				rh.append("<div "+id+" class=\"ttdesigner-insert-row  designer-droppable\">INSERT HERE</div>");
			} else {
				// Odd position - should contain a child.
				WbdWidget child = instance.findChildByIndex(index);
				if (child == null)
					break;
				
				// Perhaps show the grid
				if (generator.getShowGrid()) {
					String style = "";
					style += "border-style: solid;";
					style += "border-width: " + lineWidth + "px;";
					style += "border-color: " + lineColor + ";";
					rh.append("<div style=\"" + style + "\">" );
				}

				
				if (child.getController() instanceof ZoneWidget)
					child.getController().renderForPreview(generator, instance, ud, rh);
				else
					child.renderForDesignerWithWrapper(generator, ud, rh);

				if (generator.getShowGrid()) {
					rh.append("</div>");
				}
			}
			
		}

//		// Work out the ID for the area where we can drop stuff.
//		WidgetId dropAreaWidgetId = new WidgetId(instance);
//		dropAreaWidgetId.setPrefix("cell");
//		dropAreaWidgetId.setIndex(index);
//		String id = "id='" + dropAreaWidgetId.fullPath() + "'";
		
//		String style = "";
//
//		// Set widths if specified, and make sure there's enough space to drop
//		if ( !width.equals(""))
//			style += " width:" + width + ";";
//		else if (child == null && label.equals(""))
//			style += "width: 12px;";
//		if ( !height.equals(""))
//			style += " height:" + height + ";";
//		else if (child == null && label.equals(""))
//			style += "height: 12px;";
//		
//		// Perhaps show the grid
//		if (generator.getShowGrid()) {
//			style += "border-style: solid;";
//			style += "border-width: " + lineWidth + "px;";
//			style += "border-color: " + lineColor + ";";
//		}
//
//		// Make it dropable if we're editing
//		String cell_styleClass = "";
//		if (child == null && instance.mayEdit(ud)) {
//			cell_styleClass += " designer-droppable";
//		}
//		
//		// Display the cell inside the frame
//		rh.append("<div "+id+" style=\"" + style + "\" class=\"" + cell_styleClass + "\">");
//		rh.append(XData.htmlString(label));
//		if (child != null) {
//			// There is a child in this frame cell
//			if (child.getController() instanceof ZoneWidget)
//				child.getController().renderForPreview(generator, instance, ud, rh);
//			else
//				child.renderForDesignerWithWrapper(generator, ud, rh);
//		}
//		rh.append("</div>");
	}
	
	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception
	{
    	try {
    		String className = instance.getProperty("class", null);
    		String label = instance.getProperty("label", null);
    		String width = instance.getProperty("width", null);
    		String height = instance.getProperty("height", null);
//    		String navpoint = instance.getProperty("navpoint", null);

//    		String link = "";
//			if ( !navpoint.equals(""))
//				link = generator.navpointUrlWithoutAutomaticParameters(ud, navpoint);


			// Decide which style and style class to use (may be defined in frame.css)
			String styleClass = "DivWidget";
			if ( !className.equals(""))
				styleClass += " " + className;
//			if ( !link.equals(""))
//				styleClass += " DivWidget-link";
			
			String style = "";
			if ( !width.equals(""))
				style += " width:" + width + ";";
			if ( !height.equals(""))
				style += " height:" + height + ";";
			
//			String startAnchor = WidgetHtmlHelper.getHLinkStart(link, null, null, null, null, null);
//			String endAnchor = WidgetHtmlHelper.getHLinkEnd();

//			rh.append(startAnchor);
			rh.append("<div class='hero-unit "+styleClass+"' style=\""+style+"\"");
//			if ( !link.equals(""))
//				rh.append(" onclick=\"alert('ok');\"");
			rh.append(">");
			
			for (int oddNumber = 1; ; oddNumber += 2) {			
				WbdChildIndex index = new WbdChildIndex("" + oddNumber);
				WbdWidget child = instance.findChildByIndex(index);
				if (child == null)
					break;
				WbdWidgetController controller = child.getController();
				controller.renderForJSP(generator, child, (UimHelper)ud, rh);
			}
			rh.append("</div>\n");
//			rh.append(endAnchor);
			

    	} catch (Exception e) {
    		WbdException wbdException = new WbdException("Error rendering FrameWidget:\n" + e);
    		wbdException.setStackTrace(e.getStackTrace());
    		throw wbdException;
    	}
	}

	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XNodes node) throws WbdException
	{
		// Read the properties of this widget
		super.loadPropertiesFromXml(generator, widget, node);

		// Get the child, if there is one.
		try
		{
			XNodes children = node.getNodes("./child");
			if (children.next()) {
				String indexStr = children.getText("./index");
				XNodes widgetNode = children.getNodes("./widget");
				if (widgetNode.next()) {
					WbdWidget child = WbdWidget.loadBasicPropertiesFromXml(generator, widgetNode);
					WbdChildIndex index = new WbdChildIndex(indexStr);
					child.setParent(widget, index);
				}
			}
		}
		catch (XDataException e)
		{
			throw new WbdException("Error finding cell widget: " + e);
		}
	}

	@Override
	protected void writeProperties(WbdGenerator generator, WbdWidget instance, PrintWriter pw, int indent) throws WbdException
	{
		// Write the properties of this widget.
		instance.getProperties().writeProperties(pw, indent, null);

		// If there is a child, write it out now.
		for (WbdChildIndex index : instance.getChildIndexes()) {
			WbdWidget child = instance.findChildByIndex(index);
			if (child != null) {
				pw.println(indentStr(indent) + "<child>");
				pw.println(indentStr(indent + 1) + "<index>" + index.getIndexStr() + "</index>");
//				instance.getProperties().writeProperties(pw, indent + 1, index);
				child.saveToFile(generator, pw, indent + 1);
				pw.println(indentStr(indent) + "</child>");
			}
		}
	}

	/**
	 * Tidy up a list of children.
	 * This method is called prior to displaying free-format container widget, to make corrections
	 * to the indexes of child widgets, after adding or deleting any children.
	 * <p>
	 * The strategy:<br>
	 * Children have indexes sequential from 0 upwards as they are displayed on the page, but every
	 * second cell displayed on the page is left blank cell to allow inserting or re-ordering the list. In other
	 * words, only the odd-numbered indexes are actually used.
	 * <p>
	 * When a widget is inserted, it will appear in an odd numbered position.<p>
	 * When a widget is deleted, an even numbered entry will disappear.<p>
	 * <p>
	 * This method re-aligns the children into even numbered positions, and saves to disk as required. It
	 * should be called immediately before rendering in the Designer.
	 * 
	 * @param instance
	 * @param generator 
	 * @throws WbdException 
	 */
	public void tidyChildren(WbdGenerator generator, WbdWidget instance) throws WbdException {
		ArrayList<WbdWidget> list = new ArrayList<WbdWidget>();
		boolean changed = false;
		boolean previousOddWasEmpty = false;
		
		for (int i = 0; ; i++) {
			WbdChildIndex index = new WbdChildIndex("" + i);
			WbdWidget child = instance.findChildByIndex(index);
			
			//  The end of the list is recognized by two consecutive empty odd positions.

			// Check, depending upon whether this is an odd or even position.
			if ((i % 2) == 0) {
				
				// Even index - an insertion position.
				if (child == null) {
					
					// Nothing has been inserted here.
					int abc = 123;
				} else {
					
					// A new child has been inserted.
					changed = true;
					list.add(child);
				}
			} else {

				// Odd index - not a position reserved for inserting.
				if (child == null) {
					
					// This odd entry is missing. Either it was deleted, or perhaps we're past the end of the list.
					// See if we're past the end of the list
					if (previousOddWasEmpty)
						break;					
					previousOddWasEmpty = true;
					
				} else {
					
					// This odd entry exists.
					// If the previous odd entry was missing, it was deleted.
					if (previousOddWasEmpty) {
						changed = true;
					}
					previousOddWasEmpty = false;

					// Child as expected
					list.add(child);
					
				}
			}
		}
		
		// If the list needs to be changed, remve all the children and add them back with the new indexes.
		// Then save it to disk.
		if (changed) {
			
			// Delete all the current child indexes.
			ArrayList<WbdChildIndex> indexes = new ArrayList<WbdChildIndex>();
			for (WbdChildIndex tmpIndex : instance.getChildIndexes()) {
				indexes.add(tmpIndex);
			}
			for (WbdChildIndex tmpIndex: indexes) {
				WbdWidget tmpChild = instance.findChildByIndex(tmpIndex);
				tmpChild.setParent(null, tmpIndex);
			}
			
			// Add the children in their new positions.
			int oddIndex = 1;
			for (WbdWidget newChild : list) {
				WbdChildIndex newIndex = new WbdChildIndex("" + oddIndex);
				newChild.setParent(instance, newIndex);
				
				oddIndex += 2;
			}
			
			// Save the changes.
			instance.saveIfRequired(generator);
			
		}

	}


}
