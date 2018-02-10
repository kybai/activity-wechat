package com.wx.activity.repository;

import com.wx.activity.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Created by ky.bai on 2018-02-10
 */
@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Integer> {
}
