package com.activity.pojo;

import com.activity.model.Activity;
import com.activity.model.ActivityCourse;
import com.activity.model.ActivityTag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Created by ky.bai on 2018-02-19
 */
public class ActivityPojo implements Serializable {
    private static final long serialVersionUID = -6052186864217040101L;

    private Activity activity;
    private String desc;
    private ActivityTag activityTag;
    private List<ActivityCourse> courseList;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ActivityTag getActivityTag() {
        return activityTag;
    }

    public void setActivityTag(ActivityTag activityTag) {
        this.activityTag = activityTag;
    }

    public List<ActivityCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<ActivityCourse> courseList) {
        this.courseList = courseList;
    }
}
