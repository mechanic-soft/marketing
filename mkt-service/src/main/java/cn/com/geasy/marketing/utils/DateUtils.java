package cn.com.geasy.marketing.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Think on 2018/1/12.
 */
public class DateUtils {

    public static String getDate2FormatString(Date date , String formmater){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formmater) ;
        return simpleDateFormat.format(date);
    }

    /**
     *天数操作
     * @param date
     * @param dayCount
     * @return
     */
    public static  Date add(Date date ,Integer dayCount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,dayCount);
        return calendar.getTime();
    }
}
