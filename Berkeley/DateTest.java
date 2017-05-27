import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateTest{

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS");

	public static void main(String[] args){
		
		sendTimeStamp();	
		System.out.println("\n");
	
		//method 1
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp.getClass().getName());
		System.out.println("one hour back time");
		System.out.println(new Timestamp(System.currentTimeMillis() - 3600*1000));
		
		//method 2
		Date date = new Date();
		System.out.println("\n");
		System.out.println(new Timestamp(date.getTime()));

		//return number of milliseconds since january 1, 1970, 00:00:00 GMT
		System.out.println(timestamp.getTime());

		//format timestamp
		System.out.println(sdf.format(timestamp));

	}

	private static void sendTimeStamp(){
		long timestamp = System.currentTimeMillis();

		receiveAndSendDifference(timestamp);
	}

	private static void receiveAndSendDifference(long timestamp){
		long localTimestamp = System.currentTimeMillis() - 3600 * 1000;
		long differenceInTime = timestamp-localTimestamp;	
	
		System.out.println(new Timestamp(localTimestamp));
		theCorrectionOfTimestamp(differenceInTime);
	}

	private static void theCorrectionOfTimestamp(long difference){
		System.out.println(new Timestamp(System.currentTimeMillis()+difference));	
	} 

}
