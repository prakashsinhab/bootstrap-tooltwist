package tooltwist.bootstrap.widgets;

import tooltwist.bootstrap.properties.WbdSelectProperty;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdNavPointProperty;
import tooltwist.wbd.WbdRadioTextProperty;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;
import com.dinaa.data.XData;
//import tooltwist.bootstrap.productionHelpers.FooterProductionHelper;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

/**
 * Page Footer
 */
public class FooterWidget extends WbdWidgetController
{
	private static final String SNIPPET_PREVIEW = "footer_preview.html";
	private static final String SNIPPET_DESIGN = "footer_design.html";
	private static final String SNIPPET_PRODUCTION = "footer_production.jsp";
	private static final boolean USE_PRODUCTION_HELPER = false;

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
		instance.defineProperty(new WbdStringProperty("glyphicon", null, "Glyphicon", "copyright-mark"));
		instance.defineProperty(new WbdStringProperty("text", null, "Text", "TEXT HERE"));
		instance.defineProperty(new WbdNavPointProperty("navpoint", null, "Navpoint", ""));
		instance.defineProperty(new WbdStringProperty("linkText", null, "Link Text", "LINK HERE"));
		instance.defineProperty(new WbdRadioTextProperty("lead", null, "Lead", "True:true,False:false", "false"));
		instance.defineProperty(new WbdRadioTextProperty("position", null, "Position", "Left:left,Center:center,Right:right", ""));
		instance.defineProperty(new WbdSelectProperty("emphasis", null, "Emphasis", "muted,primary,success,info,warning,danger", ""));
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
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "footer_cssHeader.css")),
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
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "footer_jsHeader.js")),

//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "footer_cssHeader.css")),
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
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "footer_jsHeader.js")),
					
//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "footer_cssHeader.css")),

//				// Add import statements to the JSP
//				new PageImportCodeInserter(XData.class.getName()),
			};
			codeInserterList.add(arr);

			if (USE_PRODUCTION_HELPER)
			{
				SnippetParam[] productionHelperParams = null;
//				codeInserterList.add(WbdProductionHelper.codeInserter(instance, FooterProductionHelper.class.getName(), productionHelperParams));
//				codeInserterList.add(new PageImportCodeInserter(FooterProductionHelper.class.getName()));
			}
		}

	}
	
	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "Footer";
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
		rh.renderSnippetForStaticPage(generator, instance, SNIPPET_DESIGN, getSnippetParams(generator, instance, ud));
	}
	
	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception
	{
		rh.beforeProductionCode(generator, instance, getSnippetParams(generator, instance, ud), USE_PRODUCTION_HELPER);
		rh.renderSnippetForProduction(generator, instance, SNIPPET_PRODUCTION);
		rh.afterProductionCode(generator, instance);
	}
	
	private SnippetParam[] getSnippetParams(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		String glyphicon = instance.getProperty("glyphicon", null);
		if (glyphicon != null && !glyphicon.equals("")) {
			glyphicon = "<span class='glyphicon glyphicon-"+glyphicon+"'></span>";
		} else {
			glyphicon = "";
		}
		
		String text = instance.getProperty("text", null);
		String navpoint = RoutingUIM.navpointUrl(ud, instance.getProperty("navpoint", null), null);
		String linkText = instance.getProperty("linkText", null);
		String link = "<a href='"+navpoint+"'>"+linkText+"</a>";
		
		String lead = instance.getProperty("lead", null);
		if (Boolean.valueOf(lead)) {
			lead = "lead";
		} else {
			lead = "";
		}
		
		String position = instance.getProperty("position", null);
		if (position != null && !position.equals("")) {
			position = " text-"+position;
		} else {
			position = "";
		}
		
		String emphasis = instance.getProperty("emphasis", null);
		if (emphasis != null && !emphasis.equals("")) {
			emphasis = " text-"+emphasis;
		} else {
			emphasis = "";
		}
		
		SnippetParam[] params = {
			new SnippetParam("glyphicon", glyphicon),
			new SnippetParam("text", XData.htmlString(text), true),
			new SnippetParam("link", link),
			new SnippetParam("linkText", linkText),
			new SnippetParam("lead", lead),
			new SnippetParam("position", position),
			new SnippetParam("emphasis", emphasis)
		};
		return params;
	}
}
