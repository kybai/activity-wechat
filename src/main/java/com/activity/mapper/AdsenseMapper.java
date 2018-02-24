package com.activity.mapper;

import com.activity.model.Adsense;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-24 13:57
 */
public interface AdsenseMapper {

    public List<Adsense> selectByPageName(String pageName);

    public int insert(Adsense adsense);

    public int update(Adsense adsense);

}
