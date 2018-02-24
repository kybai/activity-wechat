package com.activity.service;

import com.activity.model.RollingImage;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-24 14:24
 */
public interface RollingImageService {

    public RollingImage selectOne(Integer id);

    public List<RollingImage> selectList(RollingImage record);

    public int insert(RollingImage record);

    public int update(RollingImage record);

    public int delete(Integer id);
}
