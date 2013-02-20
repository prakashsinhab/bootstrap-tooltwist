package tooltwist.bootstrap.widgets;

import tooltwist.repository.ToolTwist;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.StylesheetLinkInserter;
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


public class NavsWidget extends WbdWidgetController {

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Navs Widget";
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
		instance.defineProperty(new WbdStringProperty("tabs", null, "Tabs", ""));
		instance.defineProperty(new WbdRadioTextProperty("type", null, "Type", "nav-tabs,nav-pills", "nav-tabs"));
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
					new StylesheetCodeInserter(generator, instance, "navs_cssHeader.css"),
					new StylesheetLinkInserter(ToolTwist.getWebapp() + "/bootstrap/css/bootstrap.min.css"),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "navs_cssHeader.css"),
					new StylesheetLinkInserter(ToolTwist.getWebapp() + "/bootstrap/css/bootstrap.min.css"),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "navs_cssHeader.css"),
					new StylesheetLinkInserter(ToolTwist.getWebapp() + "/bootstrap/css/bootstrap.min.css"),
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
		String tabs = instance.getFinalProperty(generator, "tabs");
		String type = instance.getFinalProperty(generator, "type");
		
		if (!elementId.equalsIgnoreCase("")) {
			elementId = " id='" + elementId + "' ";
		}
		
		buf.append("<ul " + elementId + "class='nav " + type + "'>\n");
		String[] tabList = tabs.split(",");
		for (String label: tabList) {
			buf.append("  <li>\n");
			buf.append("   <a href='#'>" + label + "</a>\n");
			buf.append("  </li>\n");
		}
		buf.append("</ul>\n");
	}
	
}