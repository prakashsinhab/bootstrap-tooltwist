package tooltwist.bootstrap.widgets;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdRadioTextProperty;
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
		instance.defineProperty(new WbdStringProperty("items", null, "Items [ SubItems]", "")); //sample format: Action[Save|Delete,Update], New, GoTo
		instance.defineProperty(new WbdRadioTextProperty("visible", null, "Visible", "yes,no", "yes"));
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
		String visible = instance.getFinalProperty(generator, "visible");
		
		String display = "";
		if (visible.equals("yes")) {
			display = "style='display:block; position: static; margin-bottom: 5px'";
		} else {
			display = "style='display:none;'";
		}
		
		//format sample: Action[Save|Delete,Update], New, GoTo
		String[] itemLists = items.split(",");
		
		if (!elementId.equals("")) {
			elementId = " id='" + elementId + "' ";
		}
		
		buf.append("<ul" + elementId + " class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu' " + display + ">\n");
		
		if (!items.equals("")) {
		
			for (String item: itemLists) {
				int startBracket = item.indexOf("[") + 1;
				if (startBracket == 0) {
					//no sub items found
					buf.append("  <li><a tabindex='-1' href='#'>" + item + "</a></li>\n");
				} else {
					String parentItem = item.substring(0, startBracket - 1).trim();
					int endBracket = item.indexOf("]");
					String subItems = item.substring(startBracket, endBracket);
					String[] subItemList = subItems.split("\\|");
					
					buf.append("<li class='dropdown-submenu'>\n");
					buf.append("<a tabindex='-1' href='#'>" + parentItem + "</a>\n");
					buf.append(" <ul class='dropdown-menu'>\n");
					
					for (String subItem: subItemList) {
						buf.append("  <li><a tabindex='-1' href='#'>" + subItem.trim() + "</a></li>\n");
					}
					buf.append(" </ul>\n");
					buf.append("</li>\n");
				}
			}
			
		} else {
			buf.append("<li><a tabindex='-1' href='#'>No items found</a></li>\n");
		}
		
		buf.append("</ul>\n");
	}
	
}