package tooltwist.bootstrap.widgets;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;
import tooltwist.wbd.WbdGenerator.GenerationMode;

import com.dinaa.data.XData;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

/**
 * Bootstrap Page Header
 * @see http://twitter.github.com/bootstrap/components.html#typography
 * 
 * @author philipcallender
 *
 */
public class PageHeaderWidget extends WbdWidgetController {
	private static final String ICON_IMAGE = "/ttsvr/tooltwist/wbd/toolboxV8/pageTitle.png";
	
	private void renderWidget(WbdGenerator generator, WbdWidget instance, WbdRenderHelper buf) throws WbdException {
		String heading = instance.getFinalProperty(generator, "heading");
		if (heading==null | heading.trim().equals(""))
			heading = "Page heading";
		String subtext = instance.getFinalProperty(generator, "subtext");

		// Display the header, and an optional subtext
		buf.append("<h1>" + XData.htmlString(heading));
		if ( !subtext.equals("")) {
			buf.append(" <small>" + XData.htmlString(subtext) + "</small>");
		}
		buf.append("</h1>");
	}

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Page Header";
	}

	@Override
	public WbdSizeInfo getSizeInfo(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
	    return WbdSizeInfo.unknownSizeInfo();
	}

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("heading", null, "Heading", ""));
		instance.defineProperty(new WbdStringProperty("subtext", null, "Subtext", ""));
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
				new StylesheetCodeInserter(generator, instance, "pageHeader_cssHeader.css"),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
				// Include a CSS snippet
				new StylesheetCodeInserter(generator, instance, "pageHeader_cssHeader.css"),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
				// Include a CSS snippet
				new StylesheetCodeInserter(generator, instance, "pageHeader_cssHeader.css"),
			};
			codeInserterList.add(arr);
		}
	}

	@Override
	public String toolboxIcon(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
		return ICON_IMAGE;
	}

	@Override
	public boolean widgetIsStatic(WbdWidget instance, UimData ud)
	{
		return true;
	}
}