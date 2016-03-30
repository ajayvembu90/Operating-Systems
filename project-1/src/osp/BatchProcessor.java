package osp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BatchProcessor {
public static void main(String args[]){
	Batch b=null;
	BatchProcessor bpe = new BatchProcessor();
	String CONSTNAME = args[0];
	try {
		
		BatchParser bp = new BatchParser();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(CONSTNAME);
		b=bp.buildBatch(doc);
		bpe.executeBatch(b);

	}
	catch (Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
}
/*void executeBatchs(Batch b,String batchName)throws ProcessException{
	 HashMap<String,Command> commandList = b.getCommands();
	 if (batchName.equals("batch1.xml")){
		 ExecuteBatch1.executeBatch1(commandList);
	 }
	 if (batchName.equals("batch2.xml")){
		 ExecuteBatch2.executeBatch2(commandList);
	 }
	 if (batchName.equals("batch3.xml")){
		 ExecuteBatch3.executeBatch3(commandList);
	 }
	 if (batchName.equals("batch4.xml")){
		 ExecuteBatch4.executeBatch4(commandList);
	 }
	 if (batchName.equals("batch5.xml")){
		 ExecuteBatch5.executeBatch5(commandList);
	 }
}*/

void executeBatch(Batch batch){
  Map<String,Command> allCommands = batch.getCommands(); 
  ProcessBuilder builder = new ProcessBuilder();
  String workingDirectory=batch.getWorkingDirectory();
  for (Map.Entry<String, Command> command : allCommands.entrySet()){
		
	  
	  if (command.getValue().getClass() == CmdCommand.class){
		  try {
	  
	     CmdCommand cmdCd = (CmdCommand)command.getValue();
	     String inFileId = cmdCd.getInFile();
	     if(inFileId != null){
	    	 System.out.println("Setting the In File for the process builder");
	    	 String inFilePath = ((FileCommand)batch.getCommands().get(inFileId)).getPath();
	    	 
	    	 if (inFilePath == null) 
	    		 throw new ProcessException("Unable to locate the id "+inFileId);
	    	 
	    	 builder.redirectInput(new File (workingDirectory+"\\"+inFilePath));
	     }
	         String outFileId = cmdCd.getOutFile();
	         if(outFileId != null){
	        	 System.out.println("Setting the Out File for the process builder");
	    	 String outFilePath = ((FileCommand)batch.getCommands().get(outFileId)).getPath();
	    
	    	 if (outFilePath == null) 
	    		 throw new ProcessException("Unable to locate the file id "+outFileId);
	    	 builder.redirectOutput(new File (workingDirectory+"\\"+outFilePath));
	    	}
	        ArrayList<String> arguments = cmdCd.getArgs();
	        String[] args = new String[arguments.size()+1];
	        args[0]=cmdCd.getPath();
	        for (int i=0;i<arguments.size();i++)
	        	args[i+1] = arguments.get(i);
	        System.out.println("Setting the commands for the process builder");    
	    builder.command(args);
	    System.out.println(builder.command());
	    System.out.println("starting the process builder");
	    Process p = builder.start();
	    int exit=p.waitFor();
	    System.out.println("Writing to file ");
	     }catch(ProcessException e){e.printStackTrace();}
		  catch(IOException ioe){ioe.printStackTrace();}
		  catch(InterruptedException ioee){ioee.printStackTrace();}
	  }
  }
	
}
}






