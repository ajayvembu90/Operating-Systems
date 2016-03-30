package osp;
import org.w3c.dom.Element;
public class FileCommand implements Command{
	private String id;
	private String path;
	public String describe(){
		 return "File";
	 }
	 public void execute(String workingDirectory){
		 this.path=workingDirectory;
	 }
	 public void parse(Element elem)throws ProcessException{
		 String id = elem.getAttribute("id");
		 System.out.println(describe()+" is parsed");
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
