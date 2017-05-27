import java.sql.Timestamp;
import java.util.Calendar;

public class main{
	
	private static long epochTime = System.currentTimeMillis();
	private static Node[] nodeNetworkArray = new Node[3];
	
	public static void main(String[] args){
		System.out.println("timestamp befor Berkeley Algorithm");

		//nodeId, master boolean, epoch timestamp
		Node n0 = new Node(0, true, epochTime );
		Node n1 = new Node(1, false, epochTime-(3600*1000) );
		Node n2 = new Node(2, false, epochTime-(7200*1000) );
	
		nodeNetworkArray[0] = n0;	
		nodeNetworkArray[1] = n1;
		nodeNetworkArray[2] = n2;
		
		System.out.println("timestamp after Berkeley Algorithm");

		n0.startNode(nodeNetworkArray);
		n1.startNode(nodeNetworkArray);
		n2.startNode(nodeNetworkArray);

	}

}
