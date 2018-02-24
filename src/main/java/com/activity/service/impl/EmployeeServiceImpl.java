package com.activity.service.impl;

import com.activity.mapper.EmployeeMapper;
import com.activity.model.Employee;
import com.activity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Create by ky.bai on 2018-02-24 13:22
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee selectByUsername(String username) {
        return employeeMapper.selectByUsername(username);
    }
}
