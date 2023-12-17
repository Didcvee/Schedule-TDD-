package ru.didcvee.raspisanye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.didcvee.raspisanye.entity.Group;
import ru.didcvee.raspisanye.repo.GroupRepo;

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
}
