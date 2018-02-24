package com.activity.service;

import com.activity.model.Adsense;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-24 14:05
 */
public interface AdsenseService {

    public Adsense selectOne(Integer id);

    public List<Adsense> selectList(Adsense record);

    public int insert(Adsense record);

    public int update(Adsense record);

}
