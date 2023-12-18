package ru.didcvee.raspisanye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.didcvee.raspisanye.entity.Group;
import ru.didcvee.raspisanye.repo.GroupRepo;
import ru.didcvee.raspisanye.util.GroupFilteringPag;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepo groupRepository;
    @Autowired
    public GroupService(GroupRepo groupRepository) {
        this.groupRepository = groupRepository;
    }
    public List<Group> getAll(){
        return groupRepository.getAll();
    }

    public Page<Group> getByFilteringAndPagination(GroupFilteringPag groupFilter) {
        return groupRepository.getByFilteringAndPagination(groupFilter);
    }
    public Group getOne(String name){
        return groupRepository.findOne(name);
    }
    public String addGroup(Group group){
        try {
            groupRepository.addGroup(group);
        } catch (RuntimeException exception){
            throw new RuntimeException("Такая группа уже есть");
        }
        return "Успешно";
    }
}
