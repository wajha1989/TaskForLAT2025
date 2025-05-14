package com.example.taskforlat2025.Services;

import com.example.taskforlat2025.Entities.CollectionBox;
import com.example.taskforlat2025.Entities.DTO.EventReportOutput;
import com.example.taskforlat2025.Entities.Event;
import com.example.taskforlat2025.Entities.DTO.SimplifiedBoxOutput;
import com.example.taskforlat2025.Repositories.CollectionBoxRepository;
import com.example.taskforlat2025.Repositories.EventRepository;
import com.example.taskforlat2025.util.CurrencyConverterExternal;
import com.example.taskforlat2025.util.CurrencyConverterInternal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {
    private final CollectionBoxRepository boxRepository;
    private final EventRepository eventRepository;

    private static final List<String> ALLOWED_CURRENCIES = new ArrayList<>();

    static{
        ALLOWED_CURRENCIES.add("USD");
        ALLOWED_CURRENCIES.add("EUR");
        ALLOWED_CURRENCIES.add("PLN");
    }

    public RestService(CollectionBoxRepository boxRepository, EventRepository eventRepository) {
        this.boxRepository = boxRepository;
        this.eventRepository = eventRepository;
    }
    public Event createFundraisingEvent(Event event) {
        if (ALLOWED_CURRENCIES.contains(event.getCurrency())){
            eventRepository.save(event);
            return event;
        }
        return null;
    }
    public CollectionBox registerCollectionBox(CollectionBox box) {
        boxRepository.save(box);
        return box;
    }
    public List<SimplifiedBoxOutput> getCollectionBoxes() {
        var boxes = boxRepository.findAll();
        return boxes.isEmpty() ? null : boxes.stream().map(SimplifiedBoxOutput::new).toList();
    }

    public List<EventReportOutput> getEvents(){
        var events = eventRepository.findAll();
        return events.isEmpty() ? null : events.stream().map(EventReportOutput::new).toList();
    }

    public CollectionBox removeCollectionBox(long id) {
        var box = boxRepository.findById(id);
        box.ifPresent(boxRepository::delete);
        return box.orElse(null);
    }

    public CollectionBox assignToEvent(long id_box, long id_event) throws RuntimeException {
        var box = boxRepository.findById(id_box).orElseThrow(() -> new RuntimeException("Box not found"));
        var event = eventRepository.findById(id_event).orElseThrow(() -> new RuntimeException("Event not found"));
        if (box.isAssigned()){
            return box;
        }
        box.setEvent(event);
        boxRepository.save(box);
        return box;
    }

    public CollectionBox put(long id, double amount, String currency) {
        var box = boxRepository.findById(id);
        if(!(Double.doubleToRawLongBits(amount) < 0)){
            if(ALLOWED_CURRENCIES.contains(currency)){
                box.ifPresent(collectionBox -> {
                    collectionBox.put(amount, currency);
                    boxRepository.save(collectionBox);
                });
            }
        }
        return box.orElse(null);
    }

    public void transferInternal(long id) {
        var box = boxRepository.findById(id).orElseThrow(() -> new RuntimeException("Box not found"));
        eventRepository.save(box.transferAll(new CurrencyConverterInternal()));
    }

    public void transferExternal(long id) {
        var box = boxRepository.findById(id).orElseThrow(() -> new RuntimeException("Box not found"));
        eventRepository.save(box.transferAll(new CurrencyConverterExternal()));
    }

}
