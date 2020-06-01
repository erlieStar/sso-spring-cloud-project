CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `username` varchar(30) NOT NULL COMMENT '用户姓名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `token` varchar(64) NOT NULL COMMENT 'token',
  `token_expire` datetime NOT NULL COMMENT 'token失效时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_token_tokenExpire` (`token`,`token_expire`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';