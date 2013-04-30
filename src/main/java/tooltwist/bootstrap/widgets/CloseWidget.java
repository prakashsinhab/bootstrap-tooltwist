package tooltwist.bootstrap.widgets;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.SnippetParam;
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


/*
 * A generic close widget. You need to specify what is the type of
 * the element that you want to hide/close.
 * If the element type is CLASS, all of the elements that contain the specified name will be hidden.
 * otherwise only single element will be close.
 */

public class CloseWidget extends WbdWidgetController {

	private static final String SNIPPET_PRODUCTION = "close_production.jsp";
	private static final boolean USE_PRODUCTION_HELPER = false;

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Close Widget";
	}

	@Override
	public WbdSizeInfo getSizeInfo(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
		return WbdSizeInfo.unknownSizeInfo();
	}

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Element Id", ""));
		instance.defineProperty(new WbdRadioTextProperty("elementType", null, "Element Type", "id,class", "id"));
		instance.defineProperty(new WbdStringProperty("elementName", null, "Close Element Name", ""));
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
		buf.beforeProductionCode(generator, instance, getSnippetParams(generator, instance, ud), USE_PRODUCTION_HELPER);
		buf.renderSnippetForProduction(generator, instance, SNIPPET_PRODUCTION);
		buf.afterProductionCode(generator, instance);
	}

	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {

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
		}
	}

	@Override
	public boolean widgetIsStatic(WbdWidget instance, UimData ud)
	{
		return true;
	}

	private void renderWidget(WbdGenerator generator, WbdWidget instance, WbdRenderHelper buf) throws WbdException {

		buf.append("<button class='close'>&times;</button>\n");
	}

	private SnippetParam[] getSnippetParams(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		String elementType = instance.getFinalProperty(generator, "elementType");
		String elementName = instance.getFinalProperty(generator, "elementName");
		SnippetParam[] params = {
				new SnippetParam("elementType", elementType),
				new SnippetParam("elementName", elementName),
		};
		return params;
	}

}