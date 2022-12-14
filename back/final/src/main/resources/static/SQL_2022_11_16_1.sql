DROP DATABASE testDB;
CREATE DATABASE IF NOT EXISTS testDB;


USE testDB;

-- user Table Create SQL
CREATE TABLE user
(
    `u_id`      VARCHAR(45)      NOT NULL    COMMENT '유저 아이디', 
    `pw`        VARCHAR(2000)    NOT NULL    COMMENT '비밀번호', 
    `name`      VARCHAR(45)      NOT NULL    COMMENT '이름', 
    `email`     VARCHAR(300)     NOT NULL    COMMENT '이메일', 
    `gender`    VARCHAR(10)      NOT NULL    COMMENT '성별', 
    `phone_no`  VARCHAR(70)      NOT NULL    COMMENT '전화 번호', 
    `nickname`  VARCHAR(40)      NOT NULL    COMMENT '닉네임', 
     PRIMARY KEY (u_id)
);

ALTER TABLE user COMMENT '유저 테이블';

-- 닉네임 중복 금지
CREATE UNIQUE INDEX UQ_user_1
    ON user(nickname);

-- 이메일 중복 금지 
CREATE UNIQUE INDEX UQ_user_2
    ON user(email);

-- 전화번호 중복 금지 
CREATE UNIQUE INDEX UQ_user_3
    ON user(phone_no);

-- video Table Create SQL
CREATE TABLE video
(
    `v_id`      VARCHAR(33)     NOT NULL    COMMENT '비디오 아이디', 
    `title`     VARCHAR(300)    NOT NULL    COMMENT '제목', 
    `v_writer`  VARCHAR(45)     NOT NULL    COMMENT '비디오 작성자', 
    `reg_date`  DATETIME        NOT NULL    COMMENT '등록 날짜', 
    `view_cnt`  INT             NOT NULL    DEFAULT 0 COMMENT '조회 수', 
    `category`  VARCHAR(30)     NOT NULL    COMMENT '카테고리', 
     PRIMARY KEY (v_id)
);

ALTER TABLE video COMMENT '비디오 테이블';

-- follow Table Create SQL
CREATE TABLE follow
(
	`from_user` VARCHAR(45) NOT NULL COMMENT '팔로우 함', 
    `to_user`   VARCHAR(45) NOT NULL COMMENT '팔로우당함', 
     PRIMARY KEY (from_user, to_user)
);

ALTER TABLE follow COMMENT '팔로우 테이블';

ALTER TABLE follow
    ADD CONSTRAINT FK_follow_from_user_user_u_id FOREIGN KEY (from_user)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE follow
    ADD CONSTRAINT FK_follow_to_user_user_u_id FOREIGN KEY (to_user)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- favorite Table Create SQL
CREATE TABLE favorite
(
    `u_id`     VARCHAR(45)    NOT NULL    COMMENT '유저 아이디', 
    `v_id`     VARCHAR(33)    NOT NULL    COMMENT '비디오 아이디', 
     PRIMARY KEY (u_id, v_id)
);

ALTER TABLE favorite COMMENT '좋아요 테이블';

ALTER TABLE favorite
    ADD CONSTRAINT FK_favorite_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE favorite
    ADD CONSTRAINT FK_favorite_v_id_video_v_id FOREIGN KEY (v_id)
        REFERENCES video (v_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- comment Table Create SQL
CREATE TABLE comment
(
    `c_id`      INT               NOT NULL    AUTO_INCREMENT COMMENT '댓글 아이디', 
    `u_id`      VARCHAR(45)       NOT NULL    COMMENT '유저 아이디', 
    `v_id`      VARCHAR(33)       NOT NULL    COMMENT '비디오 아이디', 
    `content`   VARCHAR(10000)    NOT NULL    COMMENT '내용', 
    `reg_date`  DATETIME          NOT NULL    DEFAULT NOW() COMMENT '등록 날짜', 
     PRIMARY KEY (c_id)
);

ALTER TABLE comment COMMENT '댓글 테이블';

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_v_id_video_v_id FOREIGN KEY (v_id)
        REFERENCES video (v_id) ON DELETE CASCADE ON UPDATE CASCADE;


