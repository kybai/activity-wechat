package com.activity.mapper;

import com.activity.model.Users;
import com.activity.pojo.BasePageList;
import com.activity.service.UsersService;
import com.activity.utils.DateUtils;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Created by ky.bai on 2018-02-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersMapperTest {

    @Autowired
    private UsersService usersService;

    @Test
    public void findOneTest() {
        Users users = usersService.selectOne(1);
        System.out.println(users.getName());
    }

    @Test
    public void findListTest() {
        BasePageList page = new BasePageList();
        page.setCurrentPage(1);
        PageInfo info = usersService.findList(page);
        System.out.println(info.getList().size());
    }

    @Test
    public void insertTest() {
        Users users = new Users();
        users.setName("测试人员");
        users.setSex("男");
        users.setActive(Boolean.TRUE);
        users.setCity("苏州");
        users.setProvince("江苏");
        users.setCountry("中国");
        users.setCreateDate(DateUtils.getCurrentTimestamp());
        usersService.insert(users);
    }

}
