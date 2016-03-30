package osp;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PipeCommand implements Command{
	public String describe (){return null;}
	 public void execute (String workingDirectory) {}
	 public void parse (Element e)throws ProcessException{}
	 public void parse(Batch b,Element e)throws ProcessException{
		 int key = b.getCommands().size();
		 NodeList nodes = e.getChildNodes();
		 for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node node = nodes.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					Command cmd = buildCommand(elem);
					b.addCommand(Integer.toString(key),cmd);
					key++;
				}
	 }
	
}
	 Command buildCommand(Element elem)throws ProcessException{
			String cmdName = elem.getNodeName();
			Command cmd=null;
			
			if ("cmd".equalsIgnoreCase(cmdName)) {
				cmd = new CmdCommand();
				cmd.parse(elem);
			}
			
			
			return cmd;
		}
	 
	 public String getId(){
		 return "";
	 }
}
