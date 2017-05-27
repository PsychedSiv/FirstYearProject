/*****************************************************
* This class is a node that is supposte to be a node,
* in a distributed vector clock protocol system.
* It can be used to simulate Vector Clock protocol.
******************************************************/

import java.util.Arrays;
import java.util.concurrent.*;

public class Node extends Thread implements Runnable{

	private static Node[] nodeNetwork;
	private static int sizeOfNetwork = 3;
	public int[] v; //the vector
	private int myId; //id of the node 
	private int N; //number of nodes in the system
	ExecutorService exService = Executors.newFixedThreadPool(2);
	
	//Node is create it should know the amount of nodes in the network(numOfProc) 
	//and it's own id(myId)
	public Node( int numOfProc, int myId ){
		
		//Thread t_Receice

		this.myId = myId;
		this.N = numOfProc;
		this.v = new int[numOfProc];
		
		for(int i=0; i< this.N; i++) v[i] = 0;
		
		v[myId] = 1;
	}

	
	private void received(int[] receivedVector){
		//causually presedenc
		//condition variables
		for(int i = 0; i < N; i++){
			this.v[i] = Math.max(this.v[i], receivedVector[i]);
		}	
		
		tick();
	}

	public void send(Node node){
		tick();
		node.received(this.v);	
	}	

	public void tick(){
		this.v[this.myId] += 1;
	}

	public String getVector(){
		return Arrays.toString(this.v);
	}

}
