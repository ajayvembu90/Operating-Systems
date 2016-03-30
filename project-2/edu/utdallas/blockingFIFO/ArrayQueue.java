package edu.utdallas.blockingFIFO;

public class ArrayQueue<E> {
	
private int tail=-1;
private E[] queue;

@SuppressWarnings({"unchecked"})

public ArrayQueue(int numOfElements){
	queue =  (E[]) new Object[numOfElements];
}

public void add(E element){
	
	if (tail==-1){
		queue[0] = element;
		tail+=1;
	}
	
	else{
		tail += 1;
		queue[tail]=element;
	}
		
}

public E remove(){
	
	E fisrtElement = queue[0];
	
	for (int i=1;i<=tail;i++)
		queue[i-1] = queue[i];
	
	tail -= 1;
	
	return fisrtElement;
}


public int size(){
	return tail+1;
}

public boolean isEmpty(){
	if (tail==-1)
		return true;
	return false;
}

}
