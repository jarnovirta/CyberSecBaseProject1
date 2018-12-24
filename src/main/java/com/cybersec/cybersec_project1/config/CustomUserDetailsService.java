package com.cybersec.cybersec_project1.config;

import com.cybersec.cybersec_project1.domain.Account;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cybersec.cybersec_project1.repository.AccountDAO;
import org.springframework.security.core.GrantedAuthority;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountDAO accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        GrantedAuthority auth;
        if (username.equals("admin")) auth = new SimpleGrantedAuthority("ADMIN");
        else auth = new SimpleGrantedAuthority("USER");
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(auth));
    }
}
