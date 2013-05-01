package hyperGraphs;

import java.util.Properties;

public class HyperEdge  extends MovableComponent
{
	// nadrzedna hiperkrawedy
    private ObjectHE parentEdge;
//    atrybuty
    private Properties attributes;
  
    public HyperEdge()
    {
        attributes = new Properties();
    }
    /**
     * konstruktor kopuijacy
     * @param he
     */
    public HyperEdge(HyperEdge he)
    {
        attributes = new Properties(he.attributes);
        parentEdge =he.parentEdge;
    }

    public String getAttribute(String str)
    {
        return attributes.getProperty(str);
    }

    public void setAttribute(String atr, String value)
    {
        attributes.setProperty(atr, value);
    }

    public ObjectHE getParentEdge()
    {
        return parentEdge;
    }

    public void setParentEdge(ObjectHE parentEdge)
    {
        this.parentEdge = parentEdge;
    }
    

}
