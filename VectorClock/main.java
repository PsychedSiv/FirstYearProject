import java.util.Random;

public class main implements Runnable{
	
	private static Node[] nodeArray = new Node[3];

	private static Node n0;
	private static Node n1;
	private static Node n2;

	private void events(String threadName){
		switch(threadName){
			case "Thread-0":
				Random rand = new Random();
				int randomNum = rand.nextInt(10)+1;
				
				for(int i = 0; i <= randomNum; i++){
					int operation = rand.nextInt(2);
					int nodeToSendTo = rand.nextInt(3);
					if(operation == 1){
						n0.send(nodeArray[nodeToSendTo]);	
					}else{
						n0.tick();	
					}
				}

				System.out.println(threadName);		
				break;
			case "Thread-1":
				Random rand1 = new Random();
				int randomNum1 = rand1.nextInt(10)+1;

				for(int i = 0; i <= randomNum1; i++){
					int operation = rand1.nextInt(2);
					int nodeToSendTo = rand1.nextInt(3);
					if(operation == 1){
						n1.send(nodeArray[nodeToSendTo]);	
					}else{
						n1.tick();	
					}
				}

				System.out.println(threadName);		
				break;

			case "Thread-2":
				Random rand2 = new Random();
				int randomNum2 = rand2.nextInt(10)+1;

				for(int i = 0; i <= randomNum2; i++){
					int operation = rand2.nextInt(2);
					int nodeToSendTo = rand2.nextInt(3);
					if(operation == 1){
						n2.send(nodeArray[nodeToSendTo]);	
					}else{
						n2.tick();	
					}
				}

				System.out.println(threadName);		
				break;
			default:
				System.out.println("FAIL: no threadName match");	
				break;
		}
	}

	@Override
	public void run(){
		Thread t = Thread.currentThread();
		String name = t.getName();
		//System.out.println("name=" + name);
		
			

		//waiting for all the nodes to be created
		try{
			//System.out.println("5 second wait for the Nodes to be initialized.");
			t.sleep(5000);
		} catch(InterruptedException e){

		}
	
		events(name);	
	
		//System.out.println(name + " is done");
	}

	public static void main(String[] args){
		Thread t0 = new Thread(new main());
		Thread t1 = new Thread(new main());
		Thread t2 = new Thread(new main());

		//Creating the Node objects
		n0 = new Node(3, 0);
		n1 = new Node(3, 1);
		n2 = new Node(3, 2);
		
		nodeArray[0] = n0;
		nodeArray[1] = n1;
		nodeArray[2] = n2;

		t0.start();
		t1.start();
		t2.start();
		
		try{
			t0.join();
			t1.join();
			t2.join();
		
			System.out.println("node0 vector: " + n0.getVector());
			System.out.println("node1 vector: " + n1.getVector());
			System.out.println("node2 vector: " + n2.getVector());	
		
		}catch(InterruptedException e){

		}
	}

}
