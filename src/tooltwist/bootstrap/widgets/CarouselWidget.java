package tooltwist.bootstrap.widgets;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;

import com.dinaa.data.XNodes;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

/**
 * Carousel Widget
 * 
 */
public class CarouselWidget extends ContainerWidget
{
	
	Logger logger = Logger.getLogger(CarouselWidget.class);
	private static final boolean USE_PRODUCTION_HELPER = false;

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", "myCarousel"));
		instance.defineProperty(new WbdStringProperty("noOfItems", null, "No of Items", "3"));
		instance.defineProperty(new WbdStringProperty("width", null, "width", ""));
		instance.defineProperty(new WbdStringProperty("activeIndex", null, "Active Index", "0"));
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
				new StylesheetCodeInserter(generator, instance, "carousel_cssHeader.css"),
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
		return "Carousel";
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
		renderJSP(generator, instance, ud, rh);
	}
	
	private void renderJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception {

		String elementId = instance.getFinalProperty(generator, "elementId");
		String noOfItems = instance.getFinalProperty(generator, "noOfItems");
		String width = instance.getFinalProperty(generator, "width");
		String activeIndexStr = instance.getFinalProperty(generator, "activeIndex");
		int activeIndex = Integer.parseInt(activeIndexStr);
		
		int items = Integer.parseInt(noOfItems);
		
		rh.append("<div id='" + elementId + "' class='carousel slide' style='width:" + width + "'>\n");
		rh.append("  <ol class='carousel-indicators'>\n");
		for (int cnt = 0; cnt < items; cnt++) {
			if (cnt == activeIndex) {
				rh.append("    <li data-target='#" + elementId + "' data-slide-to='" + cnt + "' class='active'></li>\n");
			} else {
				rh.append("    <li data-target='#" + elementId + "' data-slide-to='" + cnt + "'></li>\n");
			}
		}
		rh.append("</ol>\n");	
		rh.append(" <div class='carousel-inner'>\n");
		
		for (int cnt = 0; cnt < items; cnt++) {
			String indexPrefix = "column-"+cnt+"-";
			
			if (cnt == activeIndex) {
				rh.append(" 	<div class='active item'>\n");
			} else {
				rh.append(" 	<div class='item'>\n");
			}
			this.flowChildren_renderForJSP(generator, instance, ud, rh, indexPrefix);
			rh.append("     </div>\n");
		}
		
		rh.append("</div>\n"); 
		
		rh.append("  <a class='carousel-control left' href='#" + elementId  + "' data-slide='prev'>&lsaquo;</a>\n");
		rh.append("  <a class='carousel-control right' href='#" + elementId + "' data-slide='next'>&rsaquo;</a>\n");
		rh.append("	</div>\n");
	}
	
	private void render(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException {
		
		String noOfItems = instance.getFinalProperty(generator, "noOfItems");
		int items = Integer.parseInt(noOfItems);
		
		rh.append("<div class='carousel slide'>\n");
		rh.append(" <div class='carousel-inner'>\n");
		
		
		for (int cnt = 0; cnt < items; cnt++) {
			String indexPrefix = "column-"+cnt+"-";
			rh.append(" 	<div class='active item'>\n");
			this.flowChildren_renderForDesigner(generator, instance, ud, rh, indexPrefix);
			rh.append("     </div>\n");
		}
		
		rh.append(" </div>\n");
		rh.append("</div> \n");
		
	}

	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XNodes node) throws WbdException
	{
		super.loadPropertiesFromXml(generator, widget, node);
		this.flowChildren_loadPropertiesFromXml(generator, widget, node, null);
	}

	@Override
	protected void writeProperties(WbdGenerator generator, WbdWidget instance, PrintWriter pw, int indent) throws WbdException
	{
		instance.getProperties().writeProperties(pw, indent, null);
		this.flowChildren_writeProperties(generator, instance, pw, indent, null);
	}
	
}
