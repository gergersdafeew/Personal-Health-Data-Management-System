USE ssmxynp9;

-- 为用户表添加账号状态字段（正常/禁用）
ALTER TABLE `yonghu`
  ADD COLUMN `zhuangtai` varchar(200) DEFAULT '正常' COMMENT '账号状态' AFTER `gerentouxiang`;

UPDATE `yonghu` SET `zhuangtai` = '正常' WHERE `zhuangtai` IS NULL OR `zhuangtai` = '';
