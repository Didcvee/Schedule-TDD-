package ru.didcvee.raspisanye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.didcvee.raspisanye.entity.Amogus;
import ru.didcvee.raspisanye.repo.RaspRepo;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RaspService {
    private final RaspRepo repo;

    @Autowired
    public RaspService(RaspRepo repo) {
        this.repo = repo;
    }
    @Transactional(readOnly = true)
    public List<Amogus> getBy(String group, Date from, Date to){
        return repo.getBy(group, from, to);
    }
}
