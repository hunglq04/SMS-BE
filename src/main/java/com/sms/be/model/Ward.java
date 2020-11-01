package com.sms.be.model;

import com.sms.be.constant.CommonConstants;
import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_WARD", allocationSize = 1, initialValue=100)
public class Ward extends BaseEntity {
    @Column
    private String name;

    @Column
    private String prefix;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Province province;

    public String getFullName() {
        return this.prefix + CommonConstants.SPACE + this.name;
    }

}
