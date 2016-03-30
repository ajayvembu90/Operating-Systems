package osp;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import osp.CmdCommand;
import osp.FileCommand;
import osp.ProcessException;
import osp.WDCommand;

public class BatchParser {
	Batch b = new Batch();
Batch buildBatch(Document doc)throws ProcessException{
	
	
	Element pnode = doc.getDocumentElement();
	NodeList nodes = pnode.getChildNodes();
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		Node node = nodes.item(idx);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) node;
			Command cmd;
			if ("pipe".equalsIgnoreCase(elem.getNodeName())) {
				cmd = buildCommand(elem);
				break;
			}
			else {
			cmd = buildCommand(elem);
			if (cmd.getClass() == WDCommand.class){
				b.setWorkingDirectory(System.getProperty("user.dir")+"\\"+((WDCommand)cmd).getPath());
			}
			b.addCommand(cmd.getId(),cmd);
			
			}
			
		}
 }
	return b;
}

Command buildCommand(Element elem)throws ProcessException{
	String cmdName = elem.getNodeName();
	Command cmd=null;
	if (cmdName == null) {
		throw new ProcessException("unable to parse command from " + elem.getTextContent());
	}
	else if ("wd".equalsIgnoreCase(cmdName)) {
		cmd = new WDCommand();
		cmd.parse(elem);
	}
	else if ("file".equalsIgnoreCase(cmdName)) {
		cmd = new FileCommand();
		cmd.parse(elem);
	}
	else if ("cmd".equalsIgnoreCase(cmdName)) {
		cmd = new CmdCommand();
		cmd.parse(elem);
	}
	else if ("pipe".equalsIgnoreCase(cmdName)) {
		new PipeCommand().parse(b,elem);
	}
	else {
		throw new ProcessException("Unknown command " + cmdName + " from: " + elem.getBaseURI());
	}
	
	return cmd;
}

}
