import java.sql.Timestamp;

public class Node{
	private int sizeOfNetwork = 3;

	private int nodeId;
	private boolean master = false;
	private long currentTimeMillis;
	private Node[] nodeNetworkArray = new Node[sizeOfNetwork];
	private long[] nodeDifference = new long[sizeOfNetwork];	


	public Node(int nodeId, boolean master, long currentTimeMillis){
		this.nodeId = nodeId;
		this.master = master;
		this.currentTimeMillis = currentTimeMillis;
		
		//System.out.println("nodeId: " + this.nodeId);
		//System.out.println("MasterRole: " + master);	
		System.out.println(new Timestamp(currentTimeMillis));
	}
	
	public void startNode(Node nodeNetworkArray[]){
		if(master == true){
			this.nodeNetworkArray = nodeNetworkArray;
			ping();
		}
	}
	
	private void ping(){
		if(master == true){
			for(int i=0; i<nodeNetworkArray.length; i++){
				if(nodeNetworkArray[i] != null){
					nodeNetworkArray[i].receivePing(nodeNetworkArray[0] ,currentTimeMillis);
				}
			}
			//calculate average time
			long averageTimeDifference = 0;
			for(int i=0; i<nodeNetworkArray.length; i++){
				averageTimeDifference += nodeDifference[i];	
			}	
			averageTimeDifference = averageTimeDifference/sizeOfNetwork;	
			long averageTime = currentTimeMillis-averageTimeDifference;
			//System.out.println("averageTimeDifference: " + averageTimeDifference);
			long theCurrentTimeMillis = currentTimeMillis;
			for(int i=0; i<sizeOfNetwork; i++){
				nodeNetworkArray[i].receiveCorrection(averageTime-(theCurrentTimeMillis-nodeDifference[i]));
			}
		}
	}

	private void receiveDifference(int id, long difference){
		
		nodeDifference[id] = difference;

		//System.out.println("nodeId: " + this.nodeId + "--diff: " + difference);
		//System.out.println(nodeDifference[id]);
		
	}

	private void sendCorrection(Node node, Timestamp correction){

	}

	private void receivePing(Node MasterNode, long theMasterTime){
		long difference = this.currentTimeMillis-theMasterTime;
		MasterNode.receiveDifference(this.nodeId, difference);

	} 

	/*private void sendDifference(Node node, Timestamp difference){

	}*/
	
	//receive and correct time
	private void receiveCorrection(long correction){
		currentTimeMillis = currentTimeMillis+(-correction);
		System.out.println(new Timestamp(currentTimeMillis));
	}

}
