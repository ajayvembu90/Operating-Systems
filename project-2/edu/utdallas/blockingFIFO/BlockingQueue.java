package edu.utdallas.blockingFIFO;

public class BlockingQueue<E> {
	
  ArrayQueue<E> taskQueue;
  private int sizeOfQueue;
  
  public BlockingQueue(int numOfElements){
	  sizeOfQueue=numOfElements;
	  taskQueue = new ArrayQueue<E>(numOfElements);
	}
  
  public synchronized void put(E task)throws InterruptedException{
	  while (taskQueue.size()>=sizeOfQueue)
		  wait();
	  taskQueue.add(task);
	  notifyAll();
}
  
  public synchronized E take()throws InterruptedException{
	  while(taskQueue.isEmpty())
		  wait();
	  notifyAll();
	  return taskQueue.remove();
	 }
  
}
