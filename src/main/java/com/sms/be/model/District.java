package com.sms.be.model;

import com.sms.be.constant.CommonConstants;
import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_DISTRICT", allocationSize = 1, initialValue=100)
public class District extends BaseEntity {
    @Column
    private String name;

    @Column
    private String prefix;

    @ManyToOne(fetch = FetchType.LAZY)
    private Province province;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("PREFIX ASC, NAME ASC")
    private List<Ward> wards = Collections.emptyList();

    public String getFullName() {
        return this.prefix + CommonConstants.SPACE + this.name;
    }

}
