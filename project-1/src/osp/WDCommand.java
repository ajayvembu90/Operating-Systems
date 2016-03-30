package osp;
import org.w3c.dom.Element;
public class WDCommand implements Command{
	private String id;
	private String path;
	 public String describe(){
		 return "WD";
	 }
	 public void execute(String workingDirectory){
		 this.path=workingDirectory;
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
	       execute(path);
	 }
	 
	 public String getId(){
		 return id;
	 }
	 
	 String getPath(){
		 return path;
	 }
	 
}
