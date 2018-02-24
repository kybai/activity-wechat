package com.activity.service.impl;

import com.activity.mapper.RollingImageMapper;
import com.activity.model.RollingImage;
import com.activity.service.RollingImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-24 14:25
 */
@Service
public class RollingImageServiceImpl implements RollingImageService {

    @Autowired
    private RollingImageMapper rollingImageMapper;

    public RollingImage selectOne(Integer id) {
        return rollingImageMapper.selectOne(id);
    }

    public List<RollingImage> selectList(RollingImage record) {
        return rollingImageMapper.selectList(record);
    }

    @Transactional
    public int insert(RollingImage record) {
        return rollingImageMapper.insert(record);
    }

    @Transactional
    public int update(RollingImage record) {
        return rollingImageMapper.update(record);
    }

    @Transactional
    public int delete(Integer id) {
        return rollingImageMapper.delete(id);
    }
}
