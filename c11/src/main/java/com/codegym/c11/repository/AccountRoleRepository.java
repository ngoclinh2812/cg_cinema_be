package com.codegym.c11.repository;

import com.codegym.c11.model.entity.AccountRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRoles, Long> {
}
