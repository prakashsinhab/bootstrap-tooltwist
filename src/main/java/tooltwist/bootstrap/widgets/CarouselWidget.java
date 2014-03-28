package tooltwist.bootstrap.widgets;

import java.util.Properties;

import tooltwist.wbd.GenericMustacheWidget;
import tooltwist.wbd.UsesPageData;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSession;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;

import com.dinaa.data.XData;
import com.dinaa.ui.UimData;

public class CarouselWidget extends GenericMustacheWidget implements UsesPageData {

//	private static final boolean USE_PRODUCTION_HELPER = true;
	
	protected void init(WbdWidget instance) throws WbdException
	{
		super.init(instance);
	    instance.defineProperty(new WbdStringProperty("pageDataSection", null, "PageDataSection", "carousel"));
	}

	@Override
	public XData getInitialPageData(WbdWidget instance) {
		 StringBuffer xml = new StringBuffer();
		    xml.append("<pageData>\n");
		    xml.append("  <imagePath>/ttsvr/n/cloudmall/images/banner1.jpg</imagePath>\n");
		    xml.append("</pageData>");     
		    return new XData(xml);
	}
	
	@Override
    public String getLabel(WbdWidget instance) throws WbdException {
	    return "Carousel Mustache Widget";
    }

	
	@Override
	public Properties getPropertiesForViewHelper(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		Properties properties = new Properties();
		String pageDataSection = instance.getFinalProperty(generator, "pageDataSection");
		String navpointId = WbdSession.getNavpointId(ud.getCredentials());

		properties.setProperty("navpointId", navpointId);
		properties.setProperty("pageDataSection", pageDataSection);
		
		return properties;
	}
	
}
