package tooltwist.bootstrap.viewHelpers;

import java.util.Properties;
import tooltwist.wbd.ViewHelper;
import com.dinaa.data.XData;
import com.dinaa.ui.UimData;

public class JunkViewHelper2 extends ViewHelper
{
	public JunkViewHelper2(Properties prop)
	{
		super(prop);
	}

	@Override
	public XData preFetch(UimData ud) throws Exception {
		return null;
	}
	
	public String getName() {
		return "viewHelper2";
	}
	
	public String getY() {
		return "viewHelper2";
	}
}
