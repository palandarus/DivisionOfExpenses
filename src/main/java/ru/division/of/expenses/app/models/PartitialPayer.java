package ru.division.of.expenses.app.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class PartitialPayer extends AbstractEntity {

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Double coefficient;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

}
