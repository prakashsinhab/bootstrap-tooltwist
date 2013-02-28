package tooltwist.bootstrap.widgets;

import java.util.ArrayList;
import java.util.List;

import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.Navpoint;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdCache;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdNavPointProperty;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;

import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;


public class BreadCrumbsWidget extends WbdWidgetController {

	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "BreadCrumbs";
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
		instance.defineProperty(new WbdNavPointProperty("navpoint", null, "Navpoint", ""));
	}

	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		renderDesigner(generator, instance, ud, buf);
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException
	{
		renderDesigner(generator, instance, ud, buf);
	}

	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper buf) throws WbdException
	{
		renderJSP(generator, instance, ud, buf);
	}

	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "breadCrumbs_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
				// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "breadCrumbs_cssHeader.css")
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
	
	private void renderDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException {
		List<WidgetNavpoint> navpointList = getNavpointList(instance, ud);
		
		if (navpointList.size() != 0) {
			
			buf.append("<ul class='breadcrumb'>\n");
			
			int size = navpointList.size();
			for (int i = 0; i < size; i++) {
				WidgetNavpoint widgetNavpoint = navpointList.get(i);
				
				buf.append("  <li><a href='#'>" + widgetNavpoint.getLabel() + "</a> \n");
				
				int lastIndex = size - 1;
				if (i != lastIndex) {
					buf.append("		<span class='divider'>/</span>");
				}
				buf.append("	  </li>\n");
			}
			buf.append("</ul>\n");
		} else {
			buf.append("<span><li><a href='#'>Select base navpoint</a></li></span>");
		}
	}
	
	private void renderJSP(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper buf) throws WbdException {
		
		List<WidgetNavpoint> navpointList = getNavpointList(instance, ud);
	
		if (navpointList.size() != 0) {
			
			buf.append("<ul class='breadcrumb'>\n");
			
			int size = navpointList.size();
			for (int i = 0; i < size; i++) {
				WidgetNavpoint widgetNavpoint = navpointList.get(i);
				
				buf.append("  <li><a href='" + widgetNavpoint.getUrl() + "'>" + widgetNavpoint.getLabel() + "</a> \n");
				
				int lastIndex = size - 1;
				if (i != lastIndex) {
					buf.append("		<span class='divider'>/</span>");
				}
				buf.append("	  </li>\n");
			}
			buf.append("</ul>\n");
		} else {
			buf.append("<span><li><a href='#'>Select base navpoint</a></li></span>");
		}
	}
	
	
	
	private List<WidgetNavpoint> getNavpointList(WbdWidget instance, UimData ud) throws WbdException {
		List<WidgetNavpoint> navpointList = new ArrayList<WidgetNavpoint>();
		
		//obtain the base navigation point
		String navpointId = instance.getProperty("navpoint", null);
		Navpoint navpoint = WbdCache.findNavPoint(navpointId, false);

		if (navpoint != null) {
			for (Navpoint child : navpoint.getChildren()) {
				String label = child.getLabel();
				String url = RoutingUIM.navpointUrl(ud.getCredentials(), child.getId(), AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS);
				
				WidgetNavpoint widgetNavpoint = new WidgetNavpoint(label, url);
				navpointList.add(widgetNavpoint);	
			}
		}
		
		return navpointList;
	}
	
	//This is use for getting the properties of a navpoint
	class WidgetNavpoint {
		
		private String label;
		private String url;
		
		public WidgetNavpoint(String label, String url) {
			this.label = label;
			this.url = url;
		}
		
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}	
	}
	
}