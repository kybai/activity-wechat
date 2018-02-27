package com.activity.mapper;

import com.activity.model.UsersScore;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-27 17:11
 */
public interface UsersScoreMapper {

    public UsersScore selectByPrimaryKey(Integer id);

    public List<UsersScore> selectList(UsersScore record);

    public int insert(UsersScore record);

    public int delete(UsersScore record);
}
