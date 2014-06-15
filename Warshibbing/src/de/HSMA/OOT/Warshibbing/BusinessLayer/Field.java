<<<<<<< HEAD
package de.HSMA.OOT.Warshibbing.BusinessLayer;

/**
 *
 * @author Tobias
 */
public class Field
{   
    Object content;
    boolean hitted = false, marked = false;
    
    public Field()
    {
        this.content = null;
    }
    
    public Field(Object content)
    {
        this.content = content;
    }
    
    public void setMark()
    {
        if(IsHit())
            hitted = true;
        else
            marked = true;
    }
    
    public boolean IsHit()
    {
        return (content instanceof Ship);
    }
    
    public boolean isEmpty() {
        return content == null;
    }
    
    @Override public String toString()
    {
        if(!hitted && !marked)
            return content == null ? " " : content.toString();
        else
            return hitted ? "X" : "O";
    }
}
=======
package de.HSMA.OOT.Warshibbing.BusinessLayer;

/**
 *
 * @author Tobias
 */
public class Field
{   
    Object content;
    boolean hitted = false, marked = false;
    
    public Field()
    {
        this.content = null;
    }
    
    public Field(Object content)
    {
        this.content = content;
    }
    
    public void setMark()
    {
        if(IsHit())
            hitted = true;
        else
            marked = true;
    }
    
    public boolean IsHit()
    {
        return (content instanceof Ship);
    }
    
    @Override public String toString()
    {
        if(!hitted && !marked)
            return content == null ? " " : content.toString();
        else
            return hitted ? "X" : "O";
    }
}
>>>>>>> 77bb27fb5558fd4877b11d4e37b8eafd6625da54
