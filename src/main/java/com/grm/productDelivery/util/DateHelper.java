package com.grm.productDelivery.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class DateHelper {
    public static String getLocalDateTime() throws ParseException {

       // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        //LocalDateTime now = LocalDateTime.now();
        //return LocalDateTime.parse(dtf.format(now));

      /*  Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.parse(dateFormat.format(currentDate));*/

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        ZonedDateTime nowa = ZonedDateTime.now( ZoneId.of("GMT+05:30") );
        return  dtf.format(nowa);
    }

    public static String generateUUID(){
       return UUID.randomUUID().toString();

    }
}
