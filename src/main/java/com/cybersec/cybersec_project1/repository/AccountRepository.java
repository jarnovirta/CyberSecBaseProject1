package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
