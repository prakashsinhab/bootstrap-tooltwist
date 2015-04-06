package tooltwist.bootstrap.viewHelpers;

import java.util.Properties;
import tooltwist.wbd.ViewHelper;
import com.dinaa.data.XData;
import com.dinaa.ui.UimData;

public class JunkViewHelper1 extends ViewHelper
{
	public JunkViewHelper1(Properties prop)
	{
		super(prop);
	}

	@Override
	public XData preFetch(UimData ud) throws Exception {
		return null;
	}
	
	public String getName() {
		return "junkViewHelper1";
	}
}
