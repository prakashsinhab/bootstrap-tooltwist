package tooltwist.bootstrap.properties;

import tooltwist.wbd.WbdChildIndex;
import tooltwist.wbd.WbdProperty;
import tooltwist.wbd.WbdRenderHelper;

import com.dinaa.data.XData;

public class WbdSelectProperty extends WbdProperty
{
	private String options = null;
	
	public WbdSelectProperty(String name, WbdChildIndex index, String label, String options, String initialValue)
	{
		super(name, index, label, initialValue);
		setOptions(options);
	}

	@Override
	public WbdProperty cloneIt()
	{
		return new WbdSelectProperty(getName(), getIndex(), getLabel(), getOptions(), getValue());
	}

	@Override
	public void render(WbdRenderHelper rh, String location, boolean readOnly)
	{
		StringBuffer html = new StringBuffer();
		html.append("<select class=\"wbdPropertyString ui-corner-all designer-savable-property\"" +
								" value='"+XData.quotedString(this.getValue())+"' name=\""+ this.getName() +"\" location=\""+location+"\">\n");
		html.append("<option value=\"\">-- use the default --</option>\n");
		String[] options = getOptions().split(",");
		for (int i = 0; i < options.length; i++) {
			String checked = (options[i].equals(getValue()) ? "selected" : "");
			html.append("<option value=\"" + XData.htmlString(options[i]) + "\" " + checked + ">" + XData.htmlString(options[i]) + "</option>\n");
		}
		html.append("</select>\n");
		rh.renderPropertyRow(this.getLabel(), html.toString());
	}

	public void setOptions(String options)
	{
		this.options = options;
	}

	public String getOptions()
	{
		return options;
	}

}
