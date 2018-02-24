package com.activity.mapper;

import com.activity.model.Adsense;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-24 13:57
 */
public interface AdsenseMapper {

    public Adsense selectOne(Integer id);

    public List<Adsense> selectList(Adsense record);

    public int insert(Adsense record);

    public int update(Adsense record);

}
