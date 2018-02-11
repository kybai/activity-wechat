package com.wx.activity.repository;

import com.wx.activity.entity.WechatUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Created by ky.bai on 2018-02-10
 */
@Repository
public interface WechatUserRepository extends JpaRepository<WechatUserEntity, String> {

}
