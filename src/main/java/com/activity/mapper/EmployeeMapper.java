package com.activity.mapper;

import com.activity.model.Employee;

/**
 * @author Create by ky.bai on 2018-02-24 13:30
 */
public interface EmployeeMapper {

    public Employee selectByUsername(String username);
}
