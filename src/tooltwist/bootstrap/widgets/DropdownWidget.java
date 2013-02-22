package tooltwist.bootstrap.widgets;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;

import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;


/**
 * @see https://github.com/aravindnaidu/bootstrap-tooltwist/wiki/Dropdown-Menu
 * 
 * @author richarddimalanta
 */

public class DropdownWidget extends WbdWidgetController {

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Dropdown Widget";
	}

	@Override
	public WbdSizeInfo getSizeInfo(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
	    return WbdSizeInfo.unknownSizeInfo();
	}

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
		instance.defineProperty(new WbdStringProperty("items", null, "Items", ""));
		instance.defineProperty(new WbdStringProperty("subMenuLabel", null, "Sub Menu Label", ""));
		instance.defineProperty(new WbdStringProperty("subMenuItems", null, "Sub Menu Items", ""));
	}

	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		renderWidget(generator, instance, buf);
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		renderWidget(generator, instance, buf);
	}

	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper buf) throws WbdException
	{
		renderWidget(generator, instance, buf);
	}

	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {
				// Include a CSS snippet
				new StylesheetCodeInserter(generator, instance, "dropdown_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
				// Include a CSS snippet
				new StylesheetCodeInserter(generator, instance, "dropdown_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "dropdown_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
	}

	@Override
	public boolean widgetIsStatic(WbdWidget instance, UimData ud)
	{
		return true;
	}
	
	private void renderWidget(WbdGenerator generator, WbdWidget instance, WbdRenderHelper buf) throws WbdException {
		String elementId = instance.getFinalProperty(generator, "elementId");
		String items = instance.getFinalProperty(generator, "items");
		String subMenuLabel = instance.getFinalProperty(generator, "subMenuLabel");
		String subMenuItems = instance.getFinalProperty(generator, "subMenuItems");
		
		if (!elementId.equals("")) {
			elementId = " id='" + elementId + "' ";
		}
		
		
		buf.append("<ul" + elementId + " class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu' style='display: block; position: static; margin-bottom: 5px;'>\n");
		String[] itemList = items.split(",");
		for (String item: itemList) {
			buf.append("  <li><a tabindex='-1' href='#'>" + item + "</a></li>\n");
		}
		
		if (!subMenuLabel.equals("")) {
			buf.append("<li class='divider'></li>\n");
			buf.append("<li class='dropdown-submenu'>\n");
			buf.append("<a tabindex='-1' href='#'>" + subMenuLabel + "</a>\n");
			buf.append(" <ul class='dropdown-menu'>\n");
			
			String[] menuItemList = subMenuItems.split(",");
			for (String item: menuItemList) {
				buf.append("  <li><a tabindex='-1' href='#'>" + item + "</a></li>\n");
			}
			buf.append(" </ul>\n");
			buf.append("</li>\n");
		}
		
		buf.append("</ul>\n");
	}
	
}