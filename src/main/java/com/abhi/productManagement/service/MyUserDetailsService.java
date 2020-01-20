package com.abhi.productManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhi.productManagement.common.IOUtils;
import com.abhi.productManagement.domain.UserInfo;
import com.abhi.productManagement.entity.CustomerEntity;
import com.abhi.productManagement.exception.UserNameNotFoundException;
import com.abhi.productManagement.repo.CustomerRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	CustomerRepo customerRepo;

	/*
	 * @Override
	 * 
	 * public UserDetails loadUserByUsername(String userNameOrEmail) throws
	 * UsernameNotFoundException { UserEntity user =
	 * userRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail)
	 * .orElseThrow(() -> new
	 * UsernameNotFoundException("User not found with username or email : " +
	 * userNameOrEmail) ); return UserContext.create(user); }
	 */

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomerEntity custEntity = customerRepo.findByUserNameIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException("User not found!"));
			
		
		new UserInfo();
		return UserInfo.create(custEntity);
	}

	@Transactional
    public UserDetails loadUserById(Long id) {
        CustomerEntity custEntity = customerRepo.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserInfo.create(custEntity);
    }

}
