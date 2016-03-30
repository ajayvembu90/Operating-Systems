package osp;
import org.w3c.dom.Element;
public interface Command {
  public String describe ();
  public void execute (String workingDirectory);
  public void parse(Element e)throws ProcessException; 
  public String getId();
}


