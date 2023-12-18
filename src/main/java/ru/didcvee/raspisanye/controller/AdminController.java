package ru.didcvee.raspisanye.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.didcvee.raspisanye.entity.Group;
import ru.didcvee.raspisanye.service.DateService;
import ru.didcvee.raspisanye.service.GroupService;
import ru.didcvee.raspisanye.util.GroupFilteringPag;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping()
public class AdminController {

    private final GroupService groupService;
    private final DateService dateService;

    public AdminController(GroupService groupService, DateService dateService) {
        this.groupService = groupService;
        this.dateService = dateService;
    }

    @GetMapping("/search")
    public String getGroupsByFiltering(@RequestParam(value = "groupName", defaultValue = "") String groupName,
                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                       @RequestParam(value = "sort", defaultValue = "name") String sort,
                                       Model model){
        GroupFilteringPag groupFilteringPag = new GroupFilteringPag(page,size,groupName,sort);
        Page<Group> groupList = groupService.getByFilteringAndPagination(groupFilteringPag);
        model.addAttribute("groups",groupList);
        model.addAttribute("groupName", groupName);
        int totalPages = groupList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages-1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "search.html";
    }

    @GetMapping("/createGroup")
    public String create(Model model){
        Group group = new Group("",0);
        model.addAttribute("group",group);
        return "createGroup.html";
    }

    @PostMapping("/create")
    public String createGr(@ModelAttribute Group group){
        System.out.println(group);
        String group1 = groupService.addGroup(group);
        System.out.println(group1);
        return "redirect:/search";
    }
    @GetMapping("/rasp/{id}")
    public String getRaspFromGroupByDate(@PathVariable(value = "id") String id, Model model){
        Group one = groupService.getOne(id);
        model.addAttribute("group",one);

        return "createGroup.html";
    }
}
