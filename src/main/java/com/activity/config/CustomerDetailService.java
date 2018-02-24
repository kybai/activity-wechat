package com.activity.config;

import com.activity.model.Employee;
import com.activity.service.EmployeeService;
import com.activity.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Create by ky.bai on 2017-11-23 10:56
 */
@Component
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) throw new UsernameNotFoundException(Constants.LOGIN_FAILURE_NOT_EMPTY);

        Employee employee = employeeService.selectByUsername(username);
        if (employee == null || StringUtils.isBlank(employee.getUsername())) throw new UsernameNotFoundException(Constants.LOGIN_FAILURE_NOT_EXIST);
        if (!employee.getActive()) throw new UsernameNotFoundException(Constants.LOGIN_FAILURE_DISABLED);

        //权限,配置权限为USER
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new User(username, new BCryptPasswordEncoder().encode(employee.getPassword()), authorities);
    }
}
