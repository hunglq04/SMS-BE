package com.sms.be.model.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @Transient
    private final Long TRANSIENT_ID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
    private Long id;
    @Version
    private Long version;

    @Transient
    public boolean isPersisted() {
        return this.id != null && this.id > TRANSIENT_ID;
    }
}
