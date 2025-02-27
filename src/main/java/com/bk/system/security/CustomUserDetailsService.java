package com.bk.system.security;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bk.system.dao.UserRepository;
import com.bk.system.model.entity.User;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
    UserRepository userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		log.info("loadByUserName " +user);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return createSpringSecurityUser(user);
	}
	@Transactional
	private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
		 List<GrantedAuthority> grantedAuthorities = Collections.emptyList();
//        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRole()))
//                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }

}
