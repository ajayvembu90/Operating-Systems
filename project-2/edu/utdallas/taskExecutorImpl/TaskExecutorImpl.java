package edu.utdallas.taskExecutorImpl;
import edu.utdallas.blockingFIFO.BlockingQueue;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

public class TaskExecutorImpl implements TaskExecutor {

	private int threadcount;
	private boolean started = false;
	
	BlockingQueue<Task> blockqueue = new BlockingQueue<Task>(100);
	
	Thread[] threadpool;

	public TaskExecutorImpl(int giventhreadcount) {
		this.threadcount = giventhreadcount;
		threadpool = new Thread[threadcount];
		
	}

	private void initializeandstartthreadpool() {
		TaskRunner r = new TaskRunner(blockqueue);
		for (int i = 0; i < threadcount; i++) {

			threadpool[i] = new Thread(r);
			threadpool[i].setName("Thread_Named_" + i);
			threadpool[i].start();
			
		}
	}

	@Override
	public void addTask(Task task) {
		
		try {
			if(!started){
			blockqueue.put(task);
			started = true;
			initializeandstartthreadpool();
			}else{
				blockqueue.put(task);
		    }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
