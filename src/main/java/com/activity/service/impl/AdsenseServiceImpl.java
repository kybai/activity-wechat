package com.activity.service.impl;

import com.activity.mapper.AdsenseMapper;
import com.activity.model.Adsense;
import com.activity.service.AdsenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-24 14:06
 */
@Service
public class AdsenseServiceImpl implements AdsenseService {

    @Autowired
    private AdsenseMapper adsenseMapper;

    @Override
    public Adsense selectOne(Integer id) {
        return adsenseMapper.selectOne(id);
    }

    @Override
    public List<Adsense> selectList(Adsense record) {
        return adsenseMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(Adsense adsense) {
        return adsenseMapper.insert(adsense);
    }

    @Override
    @Transactional
    public int update(Adsense adsense) {
        return adsenseMapper.update(adsense);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        return adsenseMapper.delete(id);
    }
}
