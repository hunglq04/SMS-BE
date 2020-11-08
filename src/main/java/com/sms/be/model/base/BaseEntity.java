package com.sms.be.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @JsonIgnore
    @Transient
    private final Long TRANSIENT_ID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
    private Long id;
    @JsonIgnore
    @Version
    private Long version;

    @JsonIgnore
    @Transient
    public boolean isPersisted() {
        return this.id != null && this.id > TRANSIENT_ID;
    }
}
