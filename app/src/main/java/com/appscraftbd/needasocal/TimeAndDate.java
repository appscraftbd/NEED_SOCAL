package com.appscraftbd.needasocal;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeAndDate {

    String datenow;
    public  String time(){

        LocalTime currentTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentTime = LocalTime.now();
        }
        long seconds = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            seconds = currentTime.toSecondOfDay();
        }

        return ""+String.valueOf(seconds);
    }

    public String date(){
        ////////////// post date code
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.now();
        }
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            datenow =  date.format(formatter);

        }
        /////// post date code close

        return ""+datenow;
    }

    public  String formatTime(long seconds) {
        if (seconds < 60) {
            return seconds + "s";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + "m";
        } else {
            long hours = seconds / 3600;
            return hours + "h";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  String today(String time) {

//
        String inputDateStr = "20 Jan 2024";
        DateTimeFormatter inputFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            inputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        }
        DateTimeFormatter outputFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }

        LocalDate inputDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            inputDate = LocalDate.parse(inputDateStr, inputFormat);
        }
        String outputDateStr = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            outputDateStr = inputDate.format(outputFormat);
        }

        String up = ""+outputDateStr;


        LocalDate nextDay = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            nextDay = inputDate.plusDays(1);
        }

        DateTimeFormatter formatter2 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy");
        }

        return ""+nextDay.format(formatter2);
    }

}
