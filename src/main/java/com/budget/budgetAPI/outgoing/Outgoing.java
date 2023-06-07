package com.budget.budgetAPI.outgoing;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "outgoings")
@Entity(name = "outgoing")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Outgoing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String member;
    private String date;
    private BigDecimal price;
    private String comments;

    public Outgoing(DataOutgoings data) {
        this.member = data.member();
        this.date = data.date();
        this.price = data.price();
        this.comments = data.comments();
    }

    public void updateData(UpdateOutgoingData data) {
        if (data.member() != null) {
            this.member = data.member();
        }

        if (data.date() != null) {
            this.date = data.date();
        }

        if (data.price() != null) {
            this.price = data.price();
        }

        if (data.comments() != null) {
            this.comments = data.comments();
        }
    }
}
