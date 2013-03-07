package tooltwist.bootstrap.widgets;

import java.io.PrintWriter;
import org.apache.log4j.Logger;

import tooltwist.bootstrap.properties.WbdSelectProperty;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.ContainerWidget;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdRadioTextProperty;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;

import com.dinaa.data.XNodes;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;


/**
 * Navs Widget
 * @see https://github.com/aravindnaidu/bootstrap-tooltwist/wiki/Navs-%28tabs%2C-pill-buttons%29
 * 
 * @author richarddimalanta
 */
public class NavsWidget extends ContainerWidget {

	Logger logger = Logger.getLogger(CarouselWidget.class);

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
		instance.defineProperty(new WbdStringProperty("activeTab", null, "Active Tab", ""));
		instance.defineProperty(new WbdSelectProperty("tabDirection", null, "Tabs Direction", "tabs-below,tabs-left,tabs-right", ""));

	}

	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		renderDesigner(generator, ud, instance, rh);
	}

	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		renderDesigner(generator, ud, instance, rh);
	}

	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception
	{
		renderJSP(generator, instance, ud, rh);
	}

	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {
					// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "navs_cssHeader.css")
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
					// Include a CSS snippet
					new StylesheetCodeInserter(generator, instance, "navs_cssHeader.css")
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

	private void renderDesigner(WbdGenerator generator, UimData ud, WbdWidget instance, WbdRenderHelper rh) throws WbdException {
		String elementId = instance.getFinalProperty(generator, "elementId");
		String tabs = instance.getFinalProperty(generator, "tabs");
		String type = instance.getFinalProperty(generator, "type");
		String activeTab = instance.getFinalProperty(generator, "activeTab");
		String tabDirection = instance.getFinalProperty(generator, "tabDirection");

		if (!elementId.equals("")) {
			elementId = "id='" + elementId + "' ";
		}
		rh.append("<div class='tabbable " + tabDirection + "'>\n");

		StringBuffer tabNav = new StringBuffer();
		tabNav.append("<ul " + elementId + "class='nav " + type + "'>\n");
		String[] tabList = tabs.split(",");
		for (String label: tabList) {

			if (label.equalsIgnoreCase(activeTab)) {
				tabNav.append("  <li class='active'>\n");
			} else {
				tabNav.append("  <li>\n");
			}
			tabNav.append("   <a href='#" + label + "' data-toggle='tab'>" + label + "</a>\n");
			tabNav.append("  </li>\n");
		}
		tabNav.append("</ul>\n");


		if (!tabDirection.equalsIgnoreCase("tabs-below")) {
			rh.append(tabNav);
		}

		int size = tabList.length;
		rh.append("<table class='tabContainer' cellpadding='5' cellspacing='5'>");
		rh.append("<tr>\n");
		for (int cnt = 0; cnt < size; cnt++) {
			String indexPrefix = cnt +",";
			rh.append("		<td class='item'>\n");
			this.flowChildren_renderForDesigner(generator, instance, ud, rh, indexPrefix);
			rh.append("     </td>\n");
		}
		rh.append("</tr>\n");
		rh.append("</table>");	

		if (tabDirection.equalsIgnoreCase("tabs-below")) {
			rh.append(tabNav);
		}

		rh.append("</div>\n");

	}

	private void renderJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception {

		String elementId = instance.getFinalProperty(generator, "elementId");
		String tabs = instance.getFinalProperty(generator, "tabs");
		String type = instance.getFinalProperty(generator, "type");
		String activeTab = instance.getFinalProperty(generator, "activeTab");
		String tabDirection = instance.getFinalProperty(generator, "tabDirection");

		if (!elementId.equals("")) {
			elementId = "id='" + elementId + "' ";
		}

		rh.append("<div class='tabbable " + tabDirection + "'>\n");

		StringBuffer tabNav = new StringBuffer();
		tabNav.append("<ul " + elementId + "class='nav " + type + "'>\n");
		String[] tabList = tabs.split(",");
		for (String label: tabList) {

			if (label.equalsIgnoreCase(activeTab)) {
				tabNav.append("  <li class='active'>\n");
			} else {
				tabNav.append("  <li>\n");
			}

			String id = "#" + label.toLowerCase();
			tabNav.append("   <a href='" + id + "' data-toggle='tab'>" + label + "</a>\n");
			tabNav.append("  </li>\n");
		}
		tabNav.append("  </ul>\n");

		if (!tabDirection.equalsIgnoreCase("tabs-below")) {
			rh.append(tabNav);
		}

		rh.append("  <div class='tab-content'>\n");

		int size = tabList.length;
		for (int cnt = 0; cnt < size; cnt++) {

			String indexPrefix = cnt + ",";

			if (tabList[cnt].equalsIgnoreCase(activeTab)) {
				rh.append(" 	<div class='tab-pane active' id='" + tabList[cnt].toLowerCase() + "'>\n");
			} else {
				rh.append(" 	<div class='tab-pane' id='" + tabList[cnt].toLowerCase() + "'>\n");
			}
			this.flowChildren_renderForJSP(generator, instance, ud, rh, indexPrefix);

			rh.append(" 	</div>\n");
		}
		rh.append("	  </div>\n");

		if (tabDirection.equalsIgnoreCase("tabs-below")) {
			rh.append(tabNav);
		}

		rh.append("	</div>\n");
	}

	@Override
	protected void loadPropertiesFromXml(WbdGenerator generator, WbdWidget widget, XNodes node) throws WbdException
	{
		super.loadPropertiesFromXml(generator, widget, node);
		this.flowChildren_loadPropertiesFromXml(generator, widget, node, null);
	}

	@Override
	protected void writeProperties(WbdGenerator generator, WbdWidget instance, PrintWriter pw, int indent) throws WbdException
	{
		instance.getProperties().writeProperties(pw, indent, null);
		this.flowChildren_writeProperties(generator, instance, pw, indent, null);
	}

}