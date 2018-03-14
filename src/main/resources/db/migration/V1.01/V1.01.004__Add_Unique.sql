ALTER TABLE activity_enroll ADD UNIQUE KEY `UK_ENROLL_ACTIVITY_USER`(activity_id, user_id);

ALTER TABLE users_score ADD UNIQUE KEY `UK_SCORE_COURSE_USER`(course_id, user_id, reason);
