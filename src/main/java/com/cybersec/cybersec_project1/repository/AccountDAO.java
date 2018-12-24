package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Account;

public interface AccountDAO {
    Account findByUsername(String username);
    Account findById(Long id);
    void save(Account account);
}
