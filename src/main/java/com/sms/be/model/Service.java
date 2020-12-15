package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_SERVICE", allocationSize = 1, initialValue=100)
public class Service extends BaseEntity {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String descriptionImage;

    @Column
    private Long price;

    @Column
    private Integer duration;

    @Column
    private String image;

    @Column
    private String bookingImage;

    @Column
    private String bookingRecommendImage;

    @Column
    private boolean isRecommend = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceType serviceType;

}
