package ru.didcvee.raspisanye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.didcvee.raspisanye.entity.Amogus;
import ru.didcvee.raspisanye.entity.Group;
import ru.didcvee.raspisanye.entity.Rasp;
import ru.didcvee.raspisanye.repo.GroupRepo;
import ru.didcvee.raspisanye.service.RaspService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RaspController {
    private final RaspService raspService;
    private final GroupRepo groupRepo;

    public RaspController(RaspService raspService, GroupRepo groupRepo) {
        this.raspService = raspService;
        this.groupRepo = groupRepo;
    }
    @GetMapping("/aloha")
    public String hello(Model model,@ModelAttribute("group") String group, @ModelAttribute("time")String week){
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);


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
        if(group.isEmpty()){
            model.addAttribute("groups",groupRepo.getAll());
            model.addAttribute("weeks", List.of(res1 + " - " + res2,res3+" - "+res4));
            return "mamasito.html";
        }
        String[] replaced1 = group.split("=");
        String replaced = replaced1[1].replaceAll("'", "").replaceAll("}", "");


        String[] dates = week.split(" - ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
        Date date = null;
        Date date1 = null;
        try {
            date = dateFormat.parse(dates[0]);
            date1 = dateFormat.parse(dates[1]);
            // Это выведет дату в стандартном формате
        } catch (ParseException e) {
            e.printStackTrace();  // Обработка исключения, если входная строка не соответствует ожидаемому формату
        }
        List<Amogus> list = raspService.getBy(replaced, date, date1);



        model.addAttribute("shedules",list);
        model.addAttribute("sss","saska");





        model.addAttribute("groups",groupRepo.getAll());
        model.addAttribute("weeks", List.of(res1 + " - " + res2,res3+" - "+res4));
        return "mamasito.html";
    }

    @PostMapping("/aloha")
    public String ddd(Model model, @ModelAttribute("groups") ArrayList<Group> groups, @ModelAttribute("group") Group group, @ModelAttribute("time")String week){
        model.addAttribute("groups",groups);
        System.out.println(groups+"++++++");
        String[] dates = week.split(" - ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
        Date date = null;
        Date date1 = null;
        try {
            date = dateFormat.parse(dates[0]);
            date1 = dateFormat.parse(dates[1]);
              // Это выведет дату в стандартном формате
        } catch (ParseException e) {
            e.printStackTrace();  // Обработка исключения, если входная строка не соответствует ожидаемому формату
        }
        System.out.println(group.getName()+"---------------------------------");
        List<Amogus> list = raspService.getBy(group.getName(), date, date1);
        System.out.println(date);
        System.out.println(date1);
        System.out.println(list);
        model.addAttribute("shedules",list);
        model.addAttribute("sss","saska");
        return "mamasito.html";

    }


}
