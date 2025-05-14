package com.example.taskforlat2025.Controllers;


import com.example.taskforlat2025.Entities.CollectionBox;
import com.example.taskforlat2025.Entities.DTO.*;
import com.example.taskforlat2025.Entities.Event;
import com.example.taskforlat2025.Services.RestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fundraising")
public class FundraisingController {

    //refer to application.properties to change the default currency
    @Value("${app.currency.default}")
    private String DEFAULT_CURRENCY;

    private final RestService restService;

    public FundraisingController(RestService restService) {
        this.restService = restService;
    }

    //Returns the following information about every collection box - id, if it's empty, if it's assigned
    @GetMapping("/box")
    public List<SimplifiedBoxOutput> getCollectionBoxes() {
        return restService.getCollectionBoxes();
    }

    //Returns the following information about every event - name, value inside, currency used
    @GetMapping("/event")
    public List<EventReportOutput> getCollectionEvents() {
        return restService.getEvents();
    }

    //returns the created box
    //the BoxRequest request exists only for easy expansion of the box entity and api modification
    @PostMapping("/box")
    public CollectionBox addBox(@RequestBody BoxRequest Request) {
        CollectionBox box = new CollectionBox();

        return restService.registerCollectionBox(box);
    }

    //returns the created event, or nothing if it failed
    @PostMapping("/event")
    public Event addEvent(@RequestBody EventRequest request) {
        //Someone might want to add a fundraising event without name or currency
        //for some reason, it probably should be legal
        Event event = new Event(
                request.getCurrency() != null ? request.getCurrency() : DEFAULT_CURRENCY,
                request.getName() != null? request.getName() : "");
        return restService.createFundraisingEvent(event);
    }

    //Returns true if a box has been deleted, false if not
    @DeleteMapping("/box/{id}")
    public boolean deleteBox(@PathVariable int id) {
        return restService.removeCollectionBox(id)!=null;
    }

    //Returns the collectionBox that has been assigned
    @PutMapping("/box/{boxId}/event/{eventId}")
    public CollectionBox assignEvent(@PathVariable int boxId, @PathVariable int eventId) {
        return restService.assignToEvent(boxId, eventId);
    }

    //return the collection box after the value has been added to it
    @PutMapping("/box/{id}")
    public CollectionBox putMoney(@PathVariable int id, @RequestBody AddMoneyRequest request){
        String currency = request.getCurrency();
        double amount = request.getAmount();
        return restService.put(id, amount, currency);
    }

    //returns nothing
    @PutMapping("/box/{id}/transfer/external")
    public void transferExternal(@PathVariable int id){
        restService.transferExternal(id);
    }

    //returns nothing
    @PutMapping("/box/{id}/transfer/internal")
    public void transferInternal(@PathVariable int id){
        restService.transferInternal(id);
    }
}
