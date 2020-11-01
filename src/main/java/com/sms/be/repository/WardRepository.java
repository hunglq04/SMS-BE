package com.sms.be.repository;

import com.sms.be.model.District;
import com.sms.be.model.Province;
import com.sms.be.model.Ward;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.WardRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WardRepository extends BaseRepository<Ward, Long>, WardRepositoryCustom {
}
