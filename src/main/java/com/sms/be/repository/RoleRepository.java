package com.sms.be.repository;

import com.sms.be.model.Role;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
    List<Role> findByNameIn(List<String> roles);
}
