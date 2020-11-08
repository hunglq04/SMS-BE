package com.sms.be.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerInfoDto {
    private Long id;
    private String name;
    private String avatar;
    private String idCard;

    @QueryProjection
    public ManagerInfoDto(long id, String name, String avatar, String idCard) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.idCard = idCard;
    }
}
