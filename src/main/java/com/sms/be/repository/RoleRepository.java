package com.sms.be.repository;

import com.sms.be.model.Role;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
}
