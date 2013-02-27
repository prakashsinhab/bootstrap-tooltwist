package tooltwist.bootstrap.widgets;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.JavascriptCodeInserter;
import tooltwist.wbd.PageImportCodeInserter;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdNavPointProperty;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;
import tooltwist.wbd.WbdProductionHelper;
//import tooltwist.bootstrap.productionHelpers.ThumbnailProductionHelper;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

/**
 * Thumbnail Widget
 */
public class ThumbnailWidget extends ContainerWidget
{
	private static final String SNIPPET_PREVIEW = "thumbnail_preview.html";
	private static final String SNIPPET_DESIGN = "thumbnail_design.html";
	private static final String SNIPPET_PRODUCTION = "thumbnail_production.jsp";
	private static final boolean USE_PRODUCTION_HELPER = false;

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
		instance.defineProperty(new WbdStringProperty("width", null, "Width", ""));
		instance.defineProperty(new WbdStringProperty("height", null, "Height", ""));
		instance.defineProperty(new WbdStringProperty("image", null, "Image", ""));
//		instance.defineProperty(new WbdNavPointProperty("navpoint", null, "Navpoint", ""));
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
				new StylesheetCodeInserter(generator, instance, "thumbnail_cssHeader.css"),
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
//				new JavascriptCodeInserter(generator, instance, "thumbnail_jsHeader.js")),

				// Include a CSS snippet
//				new StylesheetCodeInserter(generator, instance, "thumbnail_cssHeader.css"),
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
//				new JavascriptCodeInserter(generator, instance, "thumbnail_jsHeader.js")),
					
				// Include a CSS snippet
//				new StylesheetCodeInserter(generator, instance, "thumbnail_cssHeader.css"),

//				// Add import statements to the JSP
//				new PageImportCodeInserter(XData.class.getName()),
			};
			codeInserterList.add(arr);

			if (USE_PRODUCTION_HELPER)
			{
				SnippetParam[] productionHelperParams = null;
//				codeInserterList.add(WbdProductionHelper.codeInserter(instance, ThumbnailProductionHelper.class.getName(), productionHelperParams));
//				codeInserterList.add(new PageImportCodeInserter(ThumbnailProductionHelper.class.getName()));
			}
		}

	}
	
	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Thumbnail";
	}
	
	@Override
	public WbdSizeInfo getSizeInfo(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
		return WbdSizeInfo.unknownSizeInfo();
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		rh.renderSnippetForStaticPage(generator, instance, SNIPPET_PREVIEW, getSnippetParams(generator, instance, ud));
	}
	
	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		
		String width = instance.getProperty("width", null);
		String height = instance.getProperty("height", null);
		String image = instance.getProperty("image", null);
		
		
		rh.append("<div%%idDefinition%% class=\"thumbnail\" style=\"width: "+width+"px; height: " + height + "px;position: relative;\">");
		
		
		if (image == null || image.equals("")) {
			rh.append("<img alt=\"" + width + "x" + height + "\" style=\"width: " + width + "px; height: " + height + "px;\"");
			rh.append("src=\"data:image/png;base64,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\">");
			rh.append("<div style=\"position: absolute;top: "+(Integer.valueOf(height)/2-20)+"px;left: "+(Integer.valueOf(width)/2-30)+"px;\" class=\"caption\">" + width + " x " + height + "</div>");
		}
		else {
			rh.append("<img data-src=\"holder.js/260x180\" alt=\"" + width + "x" + height + "\" style=\"width: " + width + "px; height: " + height + "px;\"");
			rh.append(" src=\""+image+"\" />");
		}
		
		rh.append("</div>");
		
	}
	
	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception
	{
		rh.beforeProductionCode(generator, instance, getSnippetParams(generator, instance, ud), USE_PRODUCTION_HELPER);
		rh.renderSnippetForProduction(generator, instance, SNIPPET_PRODUCTION);
		rh.afterProductionCode(generator, instance);
	}
	
	
	
	private SnippetParam[] getSnippetParams(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		String width = instance.getProperty("width", null);
		String height = instance.getProperty("height", null);
		String image = instance.getProperty("image", null);
		SnippetParam[] params = {
			new SnippetParam("width", width),
			new SnippetParam("height", height),
			new SnippetParam("image", image)
		};
		return params;
	}
}
