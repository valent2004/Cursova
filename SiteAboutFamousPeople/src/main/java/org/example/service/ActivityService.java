package org.example.service;

import org.example.entity.Activity;
import org.example.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityService {

    private final ActivityRepository repository;

    @Autowired
    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public List<Activity> getActivities()
    {
        return repository.findAll();
    }

    public Activity getActivityById(Long id)
    {
        return repository.getReferenceById(id);
    }

    public List<Activity> getActivityByName(String name)
    {
        return repository.findAllByNameContains(name);
    }

    public void createActivity(Activity activity){repository.save(activity);}

    public void deleteActivity(Long id){repository.deleteById(id);}

    public Page<Activity> sortActivitiesByName(int pageNo, int pageSize, String sortBy, String sortOrder)
    {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return repository.findAll(pageable);
    }

    public void updateActivity(Activity activity, Long id){
        repository.findAll().set(id.intValue(), activity);
    }
}
