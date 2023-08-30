package com.hotelalura.Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PriceModel {

    public Double priceForAccommodation(String checkin, String checkout, String dormType) {
        Date checkinConverted = convertDate(checkin);
        Date checkoutConverted = convertDate(checkout);

        long timeInMillisecond = checkoutConverted.getTime() - checkinConverted.getTime();
        long priceForMillisecond = timeInMillisecond / 60;

        Double roomPrice = 0.0;
        if(dormType.equals("Single room")) {
            roomPrice = 200.00;
        } else if (dormType.equals("King room")) {
            roomPrice = 350.00;
        } else if (dormType.equals("Suites")) {
            roomPrice = 500.00;
        } else if (dormType.equals("Presidential Suites")) {
            roomPrice = 1000.00;
        } else {
            throw new NullPointerException("Not possible to found this type of Dormitory, please try again");
        }

        return roomPrice + priceForMillisecond;
    }

    public Date convertDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date sqlDate = null;
        try {
            java.util.Date utilDate = dateFormat.parse(date);
            sqlDate = new Date(utilDate.getTime());


        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sqlDate;
    }


}
