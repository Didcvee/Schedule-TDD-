package ru.didcvee.raspisanye.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Group> getByFilteringAndPagination(GroupFilteringPag groupFilter) {
        return groupRepository.getByFilteringAndPagination(groupFilter);
    }
    public String addGroup(Group group){
        int i = groupRepository.addGroup(group);
        throw new RuntimeException("Такая группа уже есть");
    }
}
