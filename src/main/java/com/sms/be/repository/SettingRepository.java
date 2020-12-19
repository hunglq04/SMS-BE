package com.sms.be.repository;

import com.sms.be.model.Setting;
import com.sms.be.repository.base.BaseRepository;

import java.util.Optional;

public interface SettingRepository extends BaseRepository<Setting, Long> {
    Optional<Setting> findFirstByKey(String key);
}
