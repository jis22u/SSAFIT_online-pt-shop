
DROP DATABASE IF EXISTS final_db;
CREATE DATABASE IF NOT EXISTS final_db;
USE final_db;

-- user Table Create SQL
CREATE TABLE user
(
    `u_id`         VARCHAR(45)      NOT NULL    COMMENT '유저 아이디', 
    `pw`           VARCHAR(50)      NOT NULL    COMMENT '비밀번호', 
    `name`         VARCHAR(45)      NOT NULL    COMMENT '이름', 
    `email`        VARCHAR(300)     NOT NULL    COMMENT '이메일', 
    `gender`       VARCHAR(10)      NOT NULL    COMMENT '성별', 
    `phone_no`     VARCHAR(45)      NOT NULL    COMMENT '핸드폰 번호', 
    `nickname`     VARCHAR(40)      NOT NULL    COMMENT '닉네임', 
    `exp`          INT              NOT NULL    DEFAULT 0 COMMENT '운동경력', 
    `address`      VARCHAR(300)     NULL        COMMENT '주소', 
    `profile`      VARCHAR(3000)    NULL        DEFAULT 'empty.png' COMMENT '프로필 이미지', 
    `signup_date`  DATETIME         NULL        DEFAULT NOW() COMMENT '회원가입일', 
     PRIMARY KEY (u_id)
);

CREATE UNIQUE INDEX UQ_user_1
    ON user(phone_no, email, nickname);


-- discount Table Create SQL
CREATE TABLE discount
(
    `d_id`    INT             NOT NULL    AUTO_INCREMENT COMMENT '할인 아이디', 
    `d_name`  VARCHAR(200)    NOT NULL    COMMENT '할인 명', 
    `d_rate`  DOUBLE          NOT NULL    COMMENT '할인 비율', 
     PRIMARY KEY (d_id)
);


-- trainer Table Create SQL
CREATE TABLE trainer
(
    `u_id`       VARCHAR(45)       NOT NULL    COMMENT '유저 아이디', 
    `t_name`     VARCHAR(100)      NOT NULL    COMMENT '트레이너 이름', 
    `career`     INT               NULL        COMMENT '코칭경력', 
    `t_content`  VARCHAR(10000)    NULL        COMMENT '트레이너 내용', 
    `youtube`    VARCHAR(300)      NULL        COMMENT '유튜브', 
    `insta`      VARCHAR(100)      NULL        COMMENT '인스타', 
     PRIMARY KEY (u_id)
);

ALTER TABLE trainer COMMENT '전문분야가 여러개라고 하면';

CREATE UNIQUE INDEX UQ_trainer_1
    ON trainer(t_name);

ALTER TABLE trainer
    ADD CONSTRAINT FK_trainer_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- routine Table Create SQL
CREATE TABLE routine
(
    `r_id`    INT            NOT NULL    AUTO_INCREMENT COMMENT '루틴 아이디', 
    `u_id`    VARCHAR(45)    NOT NULL    COMMENT '유저 아이디', 
    `r_name`  VARCHAR(45)    NOT NULL    COMMENT '루틴 이름', 
     PRIMARY KEY (r_id)
);

ALTER TABLE routine COMMENT '운동루틴 (플레이리스트)';

ALTER TABLE routine
    ADD CONSTRAINT FK_routine_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- purpose Table Create SQL
CREATE TABLE purpose
(
    `p_id`    INT            NOT NULL    AUTO_INCREMENT COMMENT '운동목적 아이디', 
    `p_name`  VARCHAR(45)    NOT NULL    COMMENT '운동목적 이름', 
     PRIMARY KEY (p_id)
);


-- video Table Create SQL
CREATE TABLE video
(
    `v_id`          VARCHAR(600)     NOT NULL    COMMENT '영상 아이디', 
    `title`         VARCHAR(1000)     NOT NULL    COMMENT '제목', 
    `t_name`        VARCHAR(100)    NOT NULL    COMMENT '작성자', 
    `reg_date`      DATETIME        NOT NULL    DEFAULT NOW() COMMENT '등록일', 
    `view_cnt`      INT             NULL        DEFAULT 0 COMMENT '조회수', 
    `favorite_cnt`  INT             NULL        DEFAULT 0 COMMENT '좋아요수', 
    `category`      VARCHAR(45)     NULL        COMMENT '카테고리', 
    `price`         INT             NOT NULL DEFAULT 0 COMMENT '가격', 
     PRIMARY KEY (v_id)
);

ALTER TABLE video
    ADD CONSTRAINT FK_video_t_name_trainer_t_name FOREIGN KEY (t_name)
        REFERENCES trainer (t_name) ON DELETE CASCADE ON UPDATE CASCADE;


-- buy Table Create SQL
CREATE TABLE buy
(
    `b_id`       INT            NOT NULL  AUTO_INCREMENT  COMMENT '주문 아이디', 
    `u_id`       VARCHAR(45)    NOT NULL    COMMENT '유저 아이디. 유저의 아이디', 
    `b_date`     DATETIME       NOT NULL    DEFAULT NOW() COMMENT '주문 날짜', 
    `price_sum`  INT            NOT NULL    COMMENT '총 주문금액', 
    `d_id`       INT            NULL        COMMENT '할인코드', 
     PRIMARY KEY (b_id)
);

ALTER TABLE buy
    ADD CONSTRAINT FK_buy_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE buy
    ADD CONSTRAINT FK_buy_d_id_discount_d_id FOREIGN KEY (d_id)
        REFERENCES discount (d_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- major Table Create SQL
CREATE TABLE major
(
    `m_id`    INT            NOT NULL    AUTO_INCREMENT COMMENT '전문분야 아이디', 
    `m_name`  VARCHAR(50)    NOT NULL    COMMENT '전문분야 이름. 필라테스 등등', 
     PRIMARY KEY (m_id)
);


-- auth Table Create SQL
CREATE TABLE auth
(
    `a_id`    INT            NOT NULL    AUTO_INCREMENT COMMENT '권한 아이디', 
    `a_name`  VARCHAR(45)    NOT NULL    COMMENT '권한 이름', 
     PRIMARY KEY (a_id)
);


-- comment Table Create SQL
CREATE TABLE comment
(
    `c_id`      INT              NOT NULL    AUTO_INCREMENT COMMENT '댓글 아이디', 
    `u_id`      VARCHAR(45)      NOT NULL    COMMENT '작성자', 
    `v_id`      VARCHAR(600)      NOT NULL    COMMENT '영상 아이디', 
    `content`   VARCHAR(1000)    NOT NULL    COMMENT '내용', 
    `reg_date`  DATETIME         NULL        DEFAULT NOW() COMMENT '등록일', 
     PRIMARY KEY (c_id)
);

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_v_id_video_v_id FOREIGN KEY (v_id)
        REFERENCES video (v_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- follow Table Create SQL
CREATE TABLE follow
(
    `from_user`  VARCHAR(45)    NOT NULL    COMMENT '팔로워', 
    `to_user`    VARCHAR(45)    NOT NULL    COMMENT '팔로우',
    PRIMARY KEY (from_user, to_user)
);

ALTER TABLE follow
    ADD CONSTRAINT FK_follow_from_user_user_u_id FOREIGN KEY (from_user)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE follow
    ADD CONSTRAINT FK_follow_to_user_user_u_id FOREIGN KEY (to_user)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- favorite Table Create SQL
CREATE TABLE favorite
(
    `u_id`  VARCHAR(45)    NOT NULL    COMMENT '유저 아이디. 좋아요한 유저 아이디', 
    `v_id`  VARCHAR(600)    NOT NULL    COMMENT '영상 아이디', 
     PRIMARY KEY (u_id, v_id)
);

ALTER TABLE favorite
    ADD CONSTRAINT FK_favorite_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE favorite
    ADD CONSTRAINT FK_favorite_v_id_video_v_id FOREIGN KEY (v_id)
        REFERENCES video (v_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- buy_detail Table Create SQL
CREATE TABLE buy_detail
(
    `bd_id`  INT            NOT NULL AUTO_INCREMENT    COMMENT '주문세부 아이디', 
    `b_id`   INT            NOT NULL    COMMENT '주문 아이디', 
    `v_id`   VARCHAR(600)    NOT NULL    COMMENT '영상 아이디', 
     PRIMARY KEY (bd_id)
);

ALTER TABLE buy_detail
    ADD CONSTRAINT FK_buy_detail_v_id_video_v_id FOREIGN KEY (v_id)
        REFERENCES video (v_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE buy_detail
    ADD CONSTRAINT FK_buy_detail_b_id_buy_b_id FOREIGN KEY (b_id)
        REFERENCES buy (b_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- major_detail Table Create SQL
CREATE TABLE major_detail
(
    `u_id`  VARCHAR(45)    NOT NULL    COMMENT '유저 아이디', 
    `m_id`  INT            NOT NULL    COMMENT '전문분야 아이디', 
     PRIMARY KEY (u_id, m_id)
);

ALTER TABLE major_detail
    ADD CONSTRAINT FK_major_detail_u_id_trainer_u_id FOREIGN KEY (u_id)
        REFERENCES trainer (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE major_detail
    ADD CONSTRAINT FK_major_detail_m_id_major_m_id FOREIGN KEY (m_id)
        REFERENCES major (m_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- purpose_detail Table Create SQL
CREATE TABLE purpose_detail
(
    `u_id`  VARCHAR(45)    NOT NULL    COMMENT '유저 아이디', 
    `p_id`  INT            NOT NULL    COMMENT '운동목적 아이디', 
     PRIMARY KEY (u_id, p_id)
);

ALTER TABLE purpose_detail
    ADD CONSTRAINT FK_purpose_detail_p_id_purpose_p_id FOREIGN KEY (p_id)
        REFERENCES purpose (p_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE purpose_detail
    ADD CONSTRAINT FK_purpose_detail_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- routine_detail Table Create SQL
CREATE TABLE routine_detail
(
    `rd_id`  INT            NOT NULL    AUTO_INCREMENT COMMENT '디테일 아이디', 
    `v_id`   VARCHAR(600)    NOT NULL    COMMENT '영상 아이디', 
    `r_id`   INT            NOT NULL    COMMENT '루틴 아이디', 
    `li`     INT            NOT NULL    COMMENT '순서', 
     PRIMARY KEY (rd_id)
);

ALTER TABLE routine_detail COMMENT '루틴에 들어가있는 영상';

ALTER TABLE routine_detail
    ADD CONSTRAINT FK_routine_detail_v_id_video_v_id FOREIGN KEY (v_id)
        REFERENCES video (v_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE routine_detail
    ADD CONSTRAINT FK_routine_detail_r_id_routine_r_id FOREIGN KEY (r_id)
        REFERENCES routine (r_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- auth_detail Table Create SQL
CREATE TABLE auth_detail
(
    `u_id`  VARCHAR(45)    NOT NULL    COMMENT '유저 아이디', 
    `a_id`  INT            NOT NULL    COMMENT '권한 아이디', 
     PRIMARY KEY (u_id, a_id)
);

ALTER TABLE auth_detail
    ADD CONSTRAINT FK_auth_detail_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE auth_detail
    ADD CONSTRAINT FK_auth_detail_a_id_auth_a_id FOREIGN KEY (a_id)
        REFERENCES auth (a_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- discount_detail Table Create SQL
CREATE TABLE discount_detail
(
    `u_id`   VARCHAR(45)    NOT NULL    COMMENT '유저 아이디. 유저의 아이디', 
    `d_id`   INT            NOT NULL    COMMENT '할인 아이디', 
    `d_cnt`  INT            NOT NULL    DEFAULT 1 COMMENT '쿠폰 수', 
     PRIMARY KEY (u_id, d_id)
);

ALTER TABLE discount_detail
    ADD CONSTRAINT FK_discount_detail_d_id_discount_d_id FOREIGN KEY (d_id)
        REFERENCES discount (d_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE discount_detail
    ADD CONSTRAINT FK_discount_detail_u_id_user_u_id FOREIGN KEY (u_id)
        REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE;


