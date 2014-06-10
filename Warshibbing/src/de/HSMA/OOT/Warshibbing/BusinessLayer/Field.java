package de.HSMA.OOT.Warshibbing.BusinessLayer;

/**
 *
 * @author Tobias
 */
public class Field
{   
    Object content;
    
    public Field()
    {
        this.content = null;
    }
    
    public Field(Object content)
    {
        this.content = content;
    }
    
    public boolean IsHit()
    {
        return content.getClass().isAssignableFrom(Ship.class);
    }
    
    @Override public String toString()
    {
        return content == null ? "" : content.toString();
    }
}
