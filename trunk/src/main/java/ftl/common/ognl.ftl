import java.text.SimpleDateFormat;
import java.util.Date;

public class Ognl
{
	/**
	 * 可以用于判断 Map,Collection,String,Array,Long是否不为空
	 * @param o java.lang.Object.
	 * @return boolean.
	 */
	public static boolean IsNotEmpty(Object o) throws IllegalArgumentException{
	    if(o instanceof Date){
	        SimpleDateFormat initSdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String date=initSdf.format(o);
	        if(o == null || date.equals("1970-01-01 00:00:00")){
	            return false;
	        }
	    }else{
	        if(o==null||o.toString().trim().equals("")){
	            return false;
	        }
	    }
	    return true;
	}

}
