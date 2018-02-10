package com.wx.activity.repository;

import com.wx.activity.entity.ActivityEnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Created by ky.bai on 2018-02-11
 */
@Repository
public interface ActivityEnrollRepository extends JpaRepository<ActivityEnrollEntity, Integer> {
}
