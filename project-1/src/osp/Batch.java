package osp;
import java.util.HashMap;
import java.util.LinkedHashMap;
public class Batch {
	
 private String workingDirectory;
 private LinkedHashMap<String,Command> commands;
 
 void addCommand(String key,Command command){
	 if (commands == null) {commands = new LinkedHashMap<>();}
	 this.commands.put (key,command);
 }
 
 String getWorkingDirectory(){
	 return workingDirectory;
 }
 
 void setWorkingDirectory(String workDir){
	 this.workingDirectory = workDir;
 }
 HashMap<String,Command> getCommands(){
	 return commands;
 }

 
 
}
