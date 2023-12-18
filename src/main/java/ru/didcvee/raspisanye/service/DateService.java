package ru.didcvee.raspisanye.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class DateService {
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
    public List<String> getDateOfWeek(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startOfWeek = calendar.getTime(); // дата начала недели

        String res1 = outputFormat.format(startOfWeek);


        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date endOfWeek = calendar.getTime(); // дата конца недели

        String res2 = outputFormat.format(endOfWeek);


        calendar.add(Calendar.DAY_OF_WEEK, 7);
        Date startOfNextWeek = calendar.getTime(); // дата начала следующей недели

        String res3 = outputFormat.format(startOfNextWeek);


        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date endOfNextWeek = calendar.getTime(); // дата конца следующей недели
        String res4 = outputFormat.format(endOfNextWeek);
        return List.of(res1,res2,res3,res4);
    }


}
