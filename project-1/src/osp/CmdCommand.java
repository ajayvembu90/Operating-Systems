package osp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.w3c.dom.Element;

import osp.ProcessException;
public class CmdCommand implements Command{
	private String id;
	private String path;
	private ArrayList<String> args;
	private String inFile;
	private String outFile;
	
 public String describe(){
	 return "Cmd";
 }
 public void execute(String workingDirectory){
	 
 }
 public void parse(Element elem)throws ProcessException{
	 System.out.println(describe()+"Command is parsed");
	 
	 String id = elem.getAttribute("id");
		if (id == null || id.isEmpty()) {
			throw new ProcessException("Missing ID in CMD Command");
		}
		this.id = id;
		String path = elem.getAttribute("path");
		if (path == null || path.isEmpty()) {
			throw new ProcessException("Missing PATH in CMD Command");
		}
        this.path=path;
		// Arguments must be passed to ProcessBuilder as a list of
		// individual strings. 
		ArrayList<String> cmdArgs = new ArrayList<String>();
		String arg = elem.getAttribute("args");
		if (!(arg == null || arg.isEmpty())) {
			StringTokenizer st = new StringTokenizer(arg);
			while (st.hasMoreTokens()) {
				String tok = st.nextToken();
				cmdArgs.add(tok);
			}
		}
		 this.args = cmdArgs;

		String inID = elem.getAttribute("in");
		if (!(inID == null || inID.isEmpty())) {
			this.inFile = inID;
		}

		String outID = elem.getAttribute("out");
		if (!(outID == null || outID.isEmpty())) {
			this.outFile = outID;
		}
		
 }
 public String getId(){
	 return id;
 }
 
 String getPath(){
	 return path;
 }
 
 ArrayList<String> getArgs(){
	 return args;
 }
 
 String getInFile(){
	 return inFile;
 }
 
 String getOutFile(){
	 return outFile;
 }
 
}
