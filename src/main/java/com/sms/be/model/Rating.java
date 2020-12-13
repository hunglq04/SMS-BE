package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_RATING", allocationSize = 1, initialValue=100)
public class Rating extends BaseEntity {
    @Column
    private String image1;

    @Column
    private String image2;

    @Column
    private String image3;

    @Column
    private String image4;

    @Column
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee createdBy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_rating",
            joinColumns = {@JoinColumn(name = "rating_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private List<Customer> customers;

    @OneToOne(fetch = FetchType.LAZY)
    private Booking booking;
}
