package tooltwist.bootstrap.widgets;

import tooltwist.bootstrap.properties.WbdSelectProperty;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.WbdRadioTextProperty;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;

import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;


public class ProgressBarWidget extends WbdWidgetController {
	
	private static final String SNIPPET_PRODUCTION = "progressBar_production.jsp";
	private static final String SNIPPET_PREVIEW = "progressBar_preview.html";
	private static final boolean USE_PRODUCTION_HELPER = false;

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Progress Bar Widget";
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
		instance.defineProperty(new WbdStringProperty("width", null, "width", ""));
		instance.defineProperty(new WbdSelectProperty("subType", null, "Sub Type", "info,success,warning,danger", ""));
		instance.defineProperty(new WbdRadioTextProperty("striped", null, "Striped", "yes,no", "yes"));
		instance.defineProperty(new WbdRadioTextProperty("active", null, "Active", "yes,no", "yes"));
	}

	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		buf.renderSnippetForStaticPage(generator, instance, SNIPPET_PREVIEW, getSnippetParams(generator, instance, ud));
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		buf.renderSnippetForStaticPage(generator, instance, SNIPPET_PREVIEW, getSnippetParams(generator, instance, ud));
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
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "progressBar_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "progressBar_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "progressBar_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
	}

	@Override
	public boolean widgetIsStatic(WbdWidget instance, UimData ud)
	{
		return true;
	}
	
	private SnippetParam[] getSnippetParams(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		String width = instance.getFinalProperty(generator, "width");
		String subType = instance.getFinalProperty(generator, "subType");
		String striped = instance.getFinalProperty(generator, "striped");
		String active = instance.getFinalProperty(generator, "active");
		
		String stripedClass = "";
		if (striped.equalsIgnoreCase("yes")) {
			stripedClass = " progress-striped";
		}
		
		String activeClass = "";
		if (active.equalsIgnoreCase("yes")) {
			activeClass = " active";
		}
		
		SnippetParam[] params = {
			new SnippetParam("width", width),	
			new SnippetParam("subType", subType),	
			new SnippetParam("active", activeClass),	
			new SnippetParam("striped", stripedClass),	
		};
		return params;
	}
	
}