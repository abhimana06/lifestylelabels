package com.abhi.productManagement.domain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abhi.productManagement.entity.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserInfo implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  Long id;
	
	private String userName;
	
	private String password;
	
	
	private Collection<? extends GrantedAuthority> authorities;

    public static UserInfo create(CustomerEntity  customer) {
    	
    	log.info(customer.toString());
        List<GrantedAuthority> authorities = customer.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName())
        ).collect(Collectors.toList());

        return new UserInfo(
        		customer.getId(),
        		customer.getUserName(),
                customer.getPassword(), authorities
        );
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
