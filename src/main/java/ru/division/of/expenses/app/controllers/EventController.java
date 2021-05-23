package ru.division.of.expenses.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import ru.division.of.expenses.app.dto.EventDto;
import ru.division.of.expenses.app.exceptions_handling.EventNotFoundException;
import ru.division.of.expenses.app.models.Event;
import ru.division.of.expenses.app.models.User;
import ru.division.of.expenses.app.services.EventService;
import ru.division.of.expenses.app.utils.EmptyJsonResponse;
import ru.division.of.expenses.app.utils.PrincipalImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findEventById(@PathVariable Long id) {
        return eventService.findEventById(id);
    }

    @GetMapping
    public List<EventDto> findAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size

    ) {
        if (page <= 0) {
            page = 1;
        }
        return eventService.findAll(page, size);
    }

    @PostMapping
    public Event saveEvent(@RequestBody EventDto eventDto) {
        return eventService.saveEvent(eventDto);
    }

    @PutMapping
    public ResponseEntity<?> updateEvent(@RequestBody EventDto eventDto){
        return eventService.updateEvent(eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) throws EventNotFoundException {
        eventService.deleteEvent(id);
    }

    // Поиск событий по менеджеру
    @GetMapping("/byUserId/{id}")
    public List<EventDto> findEventsByUserId(
            @PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        if (page <= 0) {
            page = 1;
        }
        return eventService.findEventsByUserId(
                id,
                page,
                size
        );
    }

    @GetMapping("/byManager")
    public List<EventDto> findEventsByUserId(
            Principal principal,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        if (page <= 0) {
            page = 1;
        }
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        return eventService.findEventsByUserId(
                user.getId(),
                page,
                size
        );
    }

//    @GetMapping("/byManager")
//    public List<EventDto> findEventsByUserId(
//            @AuthenticationPrincipal User user,
//            @RequestParam(name = "page", defaultValue = "1") int page,
//            @RequestParam(name = "size", defaultValue = "5") int size
//    ) {
//        if (page <= 0) {
//            page = 1;
//        }
//        return eventService.findEventsByUserId(
//                user.getId(),
//                page,
//                size
//        );
//    }

    // поиск событий по любому участнику.
    @GetMapping("/byParticipantId/{id}")
    public List<EventDto> findEventByParticipantId(
            @PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ){
        if (page <= 0) {
            page = 1;
        }
        return eventService.findEventByParticipantId(
                id,
                page,
                size
        );
    }



    ///////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////


    //    @GetMapping("/{id}")
//    public EventDto findEventById(@PathVariable("id") Long id) throws EventNotFoundException {
//        return eventService.findEventById(id);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findEventById(@PathVariable Long id) {
//        EventDto eventDto = eventService.findEventById(id);
//        if(eventDto.getId() != null){
//            return new ResponseEntity<EventDto>(eventDto, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<EmptyJsonResponse>(new EmptyJsonResponse(), HttpStatus.OK);
//        }
//    }

//        @PostMapping
//    public Event saveEvent(@RequestBody Event event) {
//        return eventService.saveEvent(event);
//    }

    //    @PutMapping
//    public Event updateEvent(@RequestBody Event event) throws EventNotFoundException {
//        return eventService.updateEvent(event);
//    }

}
