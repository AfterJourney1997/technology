package com.technologygarden.testdem;


import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaTest {
    public static void main(String[] args) throws ParseException {
        Timestamp d = new Timestamp(System.currentTimeMillis());
        System.out.println(d);
        Date date1=d;
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        System.out.println(timestamp);

    }
}
