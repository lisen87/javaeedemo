SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
CREATE TABLE IF NOT EXISTS `student6`  (
                                          `id` int NOT NULL COMMENT '学号',
                                          `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
    `score` int NOT NULL COMMENT '成绩',
    `birthplace` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '籍贯',
    `birthday` date NOT NULL COMMENT '生日',
    PRIMARY KEY (`id`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


create table IF NOT EXISTS log1
(
    time      datetime     null comment '时间',
    id        int auto_increment
        primary key,
    fun_name  varchar(255) null comment '接口名称',
    fun_value varchar(255) null,
    user_id   int          null comment '用户id',
    ip        varchar(100) null
)
    comment '日志';

SET FOREIGN_KEY_CHECKS = 1;