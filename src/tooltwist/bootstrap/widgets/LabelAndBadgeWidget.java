package tooltwist.bootstrap.widgets;

import tooltwist.bootstrap.properties.WbdSelectProperty;
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

import com.dinaa.data.XData;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

/**
 * 
 * @author richarddimalanta
 *@see https://github.com/aravindnaidu/bootstrap-tooltwist/wiki/Labels-and-badges
 */

public class LabelAndBadgeWidget extends WbdWidgetController {

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Labels and Badges";
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
		instance.defineProperty(new WbdRadioTextProperty("type", null, "Type",  "label,badge", "label"));
		instance.defineProperty(new WbdStringProperty("labelText", null, "Label Text", ""));
		instance.defineProperty(new WbdSelectProperty("subType", null, "Sub Type", "success,warning,important,info,inverse", ""));
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
					new StylesheetCodeInserter(generator, instance, "labelAndBadge_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "labelAndBadge_cssHeader.css")
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
	
	private void renderWidget(WbdGenerator generator, WbdWidget instance, WbdRenderHelper buf) throws WbdException {
		String elementId = instance.getFinalProperty(generator, "elementId");
		String labelText = instance.getFinalProperty(generator, "labelText");
		String type = instance.getFinalProperty(generator, "type");
		String subType = instance.getFinalProperty(generator, "subType");
		if (labelText==null | labelText.trim().equals("")) {
			labelText = "Default";
		}
		
		if (!subType.equalsIgnoreCase("")) {
			subType = " " + type + "-" + subType;
		}
			
		buf.append("<span id='" + elementId + "' class='" + type + subType + "'>" + XData.htmlString(labelText) + "</span>");
	}
}