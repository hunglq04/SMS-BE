package com.sms.be.model;

import com.sms.be.constant.BookingStatus;
import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_BOOKING", allocationSize = 1, initialValue=100)
public class Booking extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee stylist;

    @ManyToOne(fetch = FetchType.LAZY)
    private Salon salon;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

    @Column
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column
    private Double rating;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "booking_service",
            joinColumns = {@JoinColumn( name = "booking_id")},
            inverseJoinColumns = {@JoinColumn( name = "service_id")}
    )
    private List<Service> services;

    @Column
    private String walkInGuest;

    @Column(nullable = false)
    private boolean notified = false;

    public Long getTotalPrice() {
        return this.services.stream().map(Service::getPrice)
                .reduce(Long::sum).orElse(0L);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "booking")
    private Rating images;
}
