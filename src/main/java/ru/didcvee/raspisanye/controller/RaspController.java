package ru.didcvee.raspisanye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.didcvee.raspisanye.entity.Amogus;
import ru.didcvee.raspisanye.entity.Group;
import ru.didcvee.raspisanye.repo.GroupRepo;
import ru.didcvee.raspisanye.service.DateService;
import ru.didcvee.raspisanye.service.RaspService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RaspController {

    private static final List<Integer> orders = List.of(1,2,3,4,5,6,7,8,9,10);
    private static final List<String> weekDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    private final RaspService raspService;
    private final GroupRepo groupRepo;
    private final DateService dateService;

    public RaspController(RaspService raspService, GroupRepo groupRepo, DateService dateService) {
        this.raspService = raspService;
        this.groupRepo = groupRepo;
        this.dateService = dateService;
    }
    @GetMapping("/aloha")
    public String hello(Model model, @RequestParam(value = "group", defaultValue = "") String group, @RequestParam(value = "time", defaultValue = "") String week){
        List<String> dateOfWeek = dateService.getDateOfWeek();
        if(group.isEmpty()){
            model.addAttribute("groups",groupRepo.getAll());
            model.addAttribute("weeks", List.of(dateOfWeek.get(0) + " - " + dateOfWeek.get(1)
                    ,dateOfWeek.get(2)+" - "+dateOfWeek.get(3)));
            model.addAttribute("stringNameGroup","");
            return "mamasito.html";
        }




        model.addAttribute("stringDate",week);
        model.addAttribute("stringNameGroup",group);


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
        List<Amogus> list = raspService.getBy(group, date, date1);



        model.addAttribute("shedules",list);
        model.addAttribute("sss","saska");
        model.addAttribute("weekDays",weekDays);





        model.addAttribute("groups",groupRepo.getAll());
        model.addAttribute("weeks", List.of(dateOfWeek.get(0) + " - " + dateOfWeek.get(1)
                ,dateOfWeek.get(2)+" - "+dateOfWeek.get(3)));
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
