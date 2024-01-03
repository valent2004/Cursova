package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Activity;
import org.example.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
    private ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @PostMapping
    public void postActivity(@RequestBody Activity activity){ service.createActivity(activity);}

    @GetMapping
    public List<Activity> getActivities(){
        return service.getActivities();
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id){ return service.getActivityById(id); }

    @GetMapping("/sort/{sortBy}")
    public ResponseEntity<Page<Activity>> sortActivityByName(@RequestParam(defaultValue = "0") int pageNo,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   @PathVariable String sortBy,
                                   @RequestParam(defaultValue = "asc") String sortOrder)
    {
        Page<Activity> activityPage = service.sortActivitiesByName(pageNo, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(activityPage, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public List<Activity> getActivityByName(@PathVariable String name){ return service.getActivityByName(name); }

    @PutMapping("/{id}")
    public void updateActivity(@PathVariable Long id, @RequestBody Activity activity){service.updateActivity(activity, id);}

    @DeleteMapping("/{id}")
    public void removeActivity(@PathVariable Long id){service.deleteActivity(id);}
}
