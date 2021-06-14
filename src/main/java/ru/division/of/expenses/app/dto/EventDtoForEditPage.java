package ru.division.of.expenses.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.division.of.expenses.app.model.Event;
import ru.division.of.expenses.app.model.EventMember;
import ru.division.of.expenses.app.model.User;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDtoForEditPage {

    private Long id;
    private String title;
    private String description;
    private Calendar date;
    private String eventManager;
    private BigDecimal totalEventSum;
    private List<String> eventUserList;
    private Integer amountOfExpense;
    private Map<String, BigDecimal> eventMemberMap=new HashMap<>();

    public EventDtoForEditPage(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.date = event.getDate();
        this.eventManager = event.getEventManager().getUsername();
        this.totalEventSum = event.getTotalEventSum();
        this.amountOfExpense = event.getExpenseList().size();
        this.eventUserList = event.getEventUserList().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        this.description = event.getDescription();
        for (EventMember eventMember: event.getEventMembers()) {
            eventMemberMap.put(eventMember.getUser().getUsername(), eventMember.getSaldo());
        }
    }

}
