package tooltwist.bootstrap.widgets;

import tooltwist.bootstrap.properties.WbdSelectProperty;
import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
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
 * @see https://github.com/aravindnaidu/bootstrap-tooltwist/wiki/Button-Dropdowns
 * 
 * @author richarddimalanta
 *
 */

public class ButtonDropdownWidget extends WbdWidgetController {

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "ButtonDropdown Widget";
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
		instance.defineProperty(new WbdStringProperty("label", null, "Label", "My Button"));
		instance.defineProperty(new WbdStringProperty("items", null, "Item | Navpoint", ""));
		instance.defineProperty(new WbdSelectProperty("type", null, "Type", "primary,danger,warning,success,info,inverse", ""));
		instance.defineProperty(new WbdRadioTextProperty("dropPosition", null, "Drop Position", "dropdown,dropup", "dropdown"));
		instance.defineProperty(new WbdSelectProperty("sizes", null, "Sizes", "mini,small,large", ""));
	}

	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		renderDesigner(generator, ud, instance, buf);
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		renderDesigner(generator, ud, instance, buf);
	}

	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper buf) throws WbdException
	{
		renderJSP(generator, ud, instance, buf);
	}

	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "buttonDropdown_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "buttonDropdown_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
				// Include a CSS snippet
			};
			codeInserterList.add(arr);
		}
	}

	@Override
	public boolean widgetIsStatic(WbdWidget instance, UimData ud)
	{
		return true;
	}
	
	private void renderDesigner(WbdGenerator generator, UimData ud, WbdWidget instance, WbdRenderHelper buf) throws WbdException {
		
		String buttonLabel = instance.getFinalProperty(generator, "label");
		String items = instance.getFinalProperty(generator, "items");
		
		buf.append("	<div class='btn-group'>\n");
		buf.append("<button class='btn'>" + buttonLabel + "</button>\n");
		buf.append(" <button class='btn dropdown-toggle' data-toggle='dropdown' href='#'>\n");
	    buf.append("   <span class='caret'></span>\n");
	    buf.append(" </button>\n");
	    buf.append("  <ul class='dropdown-menu'>\n");
		
		
		if (!items.equals("")) {
			
		    String[] itemList = items.split(",");
		    
		    for (String item: itemList) {
		    	
		    	String[] itemContent = item.split("\\|");
		    	String label = itemContent[0].trim();
		    	buf.append("<li><a href='#'>" + label + "</a></li>\n");
		    }
		} else {
			buf.append("<li><a href='#'>No items found</a></li>\n");
		}
	    
	    buf.append(" </ul>\n");
	    buf.append("</div>\n");
	}
	
	private void renderJSP(WbdGenerator generator, UimData ud, WbdWidget instance, WbdRenderHelper buf) throws WbdException {
		String elementId = instance.getFinalProperty(generator, "elementId");
		String buttonLabel = instance.getFinalProperty(generator, "label");
		String items = instance.getFinalProperty(generator, "items");
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
		
		buf.append("<div" + elementId + " class='btn-group " + dropPosition + "'>\n");
		buf.append("<button class='btn " + type + sizeClass + "'>" + buttonLabel + "</button>\n");
		buf.append(" <button class='btn dropdown-toggle " + type + sizeClass + "' data-toggle='dropdown' href='#'>\n");
	    buf.append("   <span class='caret'></span>\n");
	    buf.append(" </button>\n");
	    buf.append("  <ul class='dropdown-menu'>\n");
		
		
		if (!items.equals("")) {
			
		    String[] itemList = items.split(",");
		    
		    for (String item: itemList) {
		    	
		    	String[] itemContent = item.split("\\|");
		    	String label = itemContent[0].trim();
		    	String url = "#";
		    	
		    	//if item navpoint is defined.
		    	if (itemContent.length == 2) {
			    	String navpointId = itemContent[1].trim();
					url = RoutingUIM.navpointUrl(ud.getCredentials(), navpointId, AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS);
		    	}
		    	
		    	buf.append("<li><a href='" +  url + "'>" + label + "</a></li>\n");
		    }
		} else {
			buf.append("No items found.");
		}
	    
	    buf.append(" </ul>\n");
	    buf.append("</div>\n");
	}
	
}