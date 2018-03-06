package com.activity.service.impl;

import com.activity.mapper.AdsenseMapper;
import com.activity.model.Adsense;
import com.activity.service.AdsenseService;
import com.activity.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
        Adsense adsense = adsenseMapper.selectOne(id);
        return ObjectUtils.isEmpty(adsense) ? new Adsense() : adsense;
    }

    @Override
    public List<Adsense> selectList(Adsense record) {
        return adsenseMapper.selectList(record);
    }

    @Override
    @Transactional
    public int insert(Adsense adsense) {
        if (adsense.getName() == null) {
            adsense.setName("");
        }
        if (adsense.getPageName() == null) {
            adsense.setPageName("");
        }
        adsense.setActive(Boolean.TRUE);
        adsense.setCreateDate(DateUtils.getCurrentTimestamp());
        return adsenseMapper.insert(adsense);
    }

    @Override
    @Transactional
    public int update(Adsense record) {
        return adsenseMapper.update(record);
    }

}
