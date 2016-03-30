package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.blockingFIFO.BlockingQueue;
public class TaskRunner implements Runnable {
	
	private BlockingQueue<Task> taskQueue;
		
	public TaskRunner(BlockingQueue<Task> taskQueue1) {
		this.taskQueue = taskQueue1;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Task t = taskQueue.take();
				t.execute();
				}
			 catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
	}
	
	
}
