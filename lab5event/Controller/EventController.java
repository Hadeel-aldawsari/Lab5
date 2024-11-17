package com.example.lab5event.Controller;


import com.example.lab5event.ApiResponse.ApiResponse;
import com.example.lab5event.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList <Event> events=new ArrayList();


    @PostMapping("/create")
    public ApiResponse createEvent(@RequestBody Event event){
        if(event.getStartDate().isAfter(event.getEndDate()))  return new ApiResponse("Start date can't be after end date");
        events.add(event);
        return new ApiResponse("Event created successfully");
    }


    @GetMapping("/get-events")
    public ArrayList <Event> getEvents(){
        return events;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@RequestBody Event event , @PathVariable int index){
        if(index>events.size()) return new ApiResponse("Event index out of bounds");
        events.set(index, event);
        return new ApiResponse("Event updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index){
        if(index>events.size())return new ApiResponse("index out of bound");
        events.remove(index);
        return new ApiResponse("Event deleted successfully");
    }

    @PutMapping("/change-capacity/{index}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable int index,@PathVariable int capacity){
        if(index>events.size())return new ApiResponse("index out of bound");
        if(capacity<0)return new ApiResponse("capacity can't be negative");
        events.get(index).setCapacity(capacity);
        return new ApiResponse("Capacity updated successfully");

    }


    @GetMapping("/search/{id}")
    public Event searchByID(@PathVariable String id){
        for (Event event : events) {
            if(event.getID().equalsIgnoreCase(id))return event;
        }
      return null;
    }






}
