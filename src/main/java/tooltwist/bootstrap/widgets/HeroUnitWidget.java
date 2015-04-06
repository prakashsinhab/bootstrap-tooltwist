package tooltwist.bootstrap.widgets;

import java.io.PrintWriter;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;

import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;
import com.tooltwist.xdata.XDException;
import com.tooltwist.xdata.XSelector;

/**
 * Hero Unit
 */
public class HeroUnitWidget extends ContainerWidget
{

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
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
				new StylesheetCodeInserter(generator, instance, "herounit_cssHeader.css")
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
		rh.append("<div class=\"ttdesigner-bootstrap-herounit-div\">");
		
		// Display the children, starting with index 0.
		flowChildren_renderForDesigner(generator, instance, ud, rh, null);
		
		rh.append("</div>");

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
//    		String className = instance.getProperty("class", null);
//    		String label = instance.getProperty("label", null);
//    		String width = instance.getProperty("width", null);
//    		String height = instance.getProperty("height", null);
//    		String navpoint = instance.getProperty("navpoint", null);

//    		String link = "";
//			if ( !navpoint.equals(""))
//				link = generator.navpointUrlWithoutAutomaticParameters(ud, navpoint);


			// Decide which style and style class to use (may be defined in frame.css)
			String styleClass = "HeroUnitWidget";
//			if ( !className.equals(""))
//				styleClass += " " + className;
////			if ( !link.equals(""))
////				styleClass += " DivWidget-link";
//			
			String style = "";
//			if ( !width.equals(""))
//				style += " width:" + width + ";";
//			if ( !height.equals(""))
//				style += " height:" + height + ";";
			
//			String startAnchor = WidgetHtmlHelper.getHLinkStart(link, null, null, null, null, null);
//			String endAnchor = WidgetHtmlHelper.getHLinkEnd();

//			rh.append(startAnchor);
			rh.append("<div class='hero-unit "+styleClass+"' style=\""+style+"\"");
//			if ( !link.equals(""))
//				rh.append(" onclick=\"alert('ok');\"");
			rh.append(">");
			
			flowChildren_renderForJSP(generator, instance, ud, rh, null);
			rh.append("</div>\n");
//			rh.append(endAnchor);
			

    	} catch (Exception e) {
    		WbdException wbdException = new WbdException("Error rendering FrameWidget:\n" + e);
    		wbdException.setStackTrace(e.getStackTrace());
    		throw wbdException;
    	}
	}

	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XSelector node) throws WbdException, XDException
	{
		// Read the properties of this widget
		super.loadPropertiesFromXml(generator, widget, node);

		// Get the child, if there is one.
		flowChildren_loadPropertiesFromXml(generator, widget, node, null);
	}

	@Override
	protected void writeProperties(WbdGenerator generator, WbdWidget instance, PrintWriter pw, int indent) throws WbdException
	{
		// Write the properties of this widget.
		instance.getProperties().writeProperties(pw, indent, null);
		
		// Save the children.
		flowChildren_writeProperties(generator, instance, pw, indent, null);
	}


}
