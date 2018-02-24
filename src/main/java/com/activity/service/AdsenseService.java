package com.activity.service;

import com.activity.model.Adsense;

import java.util.List;

/**
 * @author Create by ky.bai on 2018-02-24 14:05
 */
public interface AdsenseService {

    public List<Adsense> selectByPageName(String pageName);

    public int insert(Adsense adsense);

    public int update(Adsense adsense);

}
