import static java.util.Calendar.*;
import java.util.Calendar;
import java.lang.Math;
class DateBetween{
	public static void main(String args[]){
		Calendar c1=Calendar.getInstance(),c2=Calendar.getInstance();
		c1.set(Integer.parseInt(args[0]),Integer.parseInt(args[1])-1,Integer.parseInt(args[2]));
		c2.set(Integer.parseInt(args[3]),Integer.parseInt(args[4])-1,Integer.parseInt(args[5]));
		long time1=c1.getTimeInMillis(),time2=c2.getTimeInMillis();
		System.out.println("相隔"+Math.abs((time1-time2)/1000/60/60/24)+"天");
	}
}
