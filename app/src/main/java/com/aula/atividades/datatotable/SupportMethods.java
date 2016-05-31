package com.aula.atividades.datatotable;

import android.text.format.Time;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Jose on 07/05/2016.
 */
public class SupportMethods {

    public Date FormataData(String dataR) {
        String pattern = "dd/MM/yyyy HH:mm";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        sdf.setLenient(false);
        try {
            date = sdf.parse(dataR);

            // Data formatada corretamente
        } catch (ParseException e) {
            // Erro de parsing!!
            e.printStackTrace();

        }
        return date;
    }

    public String FormataDataVW(String dataR) {

        String pattern = "dd/MM/yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Locale brasil = new Locale("pt", "BR"); //Retorna do país e a língua
        DateFormat f2;
        String s = "";


        sdf.setLenient(false);
        try {
            date = sdf.parse(dataR);

            f2 = DateFormat.getDateInstance(DateFormat.FULL, brasil);
            s = f2.format(date).toString();
            // Data formatada corretamente
        } catch (ParseException e) {
            // Erro de parsing!!
            e.printStackTrace();

        }
        return s;
    }

    public String FmtData(String dataR) {

        String pattern = "dd/MM/yyyy";
        Date date = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Locale brasil = new Locale("pt", "BR"); //Retorna do país e a língua
        DateFormat f2;
        String s = "";

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", brasil);
        cal = sdf.getCalendar();
        sdf.setLenient(false);
        try {
            cal.setTime(sdf.parse(dataR));// all done
            s = "" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH);
            // Data formatada corretamente
        } catch (ParseException e) {
            // Erro de parsing!!
            e.printStackTrace();

        }
        return s;
    }

    /*public String Adicionar(int hour, int minute, int minutesToAdd) {
        Calendar calendar = new GregorianCalendar(1990, 1, 1, hour, minute);
        calendar.add(Calendar.MINUTE, minutesToAdd);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(calendar.getTime());
        return date;*/
    public String Adicionar_mes(Calendar calendar, int minutesToAdd) {
        calendar.add(Calendar.MONTH, minutesToAdd);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(calendar.getTime());
        return date;
    }

    public String Adicionar_dia(Calendar calendar, int minutesToAdd) {
        calendar.add(Calendar.DAY_OF_MONTH, minutesToAdd);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(calendar.getTime());
        return date;
    }

    public String Adicionar_ano(Calendar calendar, int minutesToAdd) {
        calendar.add(Calendar.YEAR, minutesToAdd);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(calendar.getTime());
        return date;
    }
}