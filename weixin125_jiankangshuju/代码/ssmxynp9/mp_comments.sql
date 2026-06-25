USE ssmxynp9;

-- 修正原评论关联（refid 应对应 jiankangxinxigongxiang 的 id 31-36）
UPDATE discussjiankangxinxigongxiang SET refid=31, nickname='李四', content='张三的减重经验很实用，向你学习！', reply='一起加油，健康饮食！' WHERE id=91;
UPDATE discussjiankangxinxigongxiang SET refid=32, nickname='王五', content='瑜伽和游泳搭配很好，体态管理值得借鉴。', reply='谢谢鼓励～' WHERE id=92;
UPDATE discussjiankangxinxigongxiang SET refid=33, nickname='赵六', content='增肌期饮食安排很专业，收藏了。', reply='' WHERE id=93;
UPDATE discussjiankangxinxigongxiang SET refid=34, nickname='张三', content='低糖饮食控制得很好，向你取经！', reply='我们可以互相交流食谱。' WHERE id=94;
UPDATE discussjiankangxinxigongxiang SET refid=35, nickname='周八', content='太极拳很适合中老年养生，坚持就有效果。', reply='感谢支持！' WHERE id=95;
UPDATE discussjiankangxinxigongxiang SET refid=36, nickname='李四', content='作息规律真好，大学生榜样！', reply='' WHERE id=96;

-- 补充更多评论
DELETE FROM discussjiankangxinxigongxiang WHERE id BETWEEN 301 AND 320;

INSERT INTO discussjiankangxinxigongxiang (id, addtime, refid, userid, nickname, content, reply) VALUES
-- 张三 减重分享 id=31
(301, NOW(), 31, 12, '王五', '慢跑计划很科学，我准备照着做。', '欢迎一起打卡！'),
(302, NOW(), 31, 14, '赵六', '控制饮食确实关键，已点赞。', ''),
-- 李四 瑜伽分享 id=32
(303, NOW(), 32, 11, '张三', '素食搭配豆制品，营养又低脂。', ''),
(304, NOW(), 32, 16, '周八', '游泳每周一次强度刚好，学习了。', '谢谢～'),
-- 王五 增肌分享 id=33
(305, NOW(), 33, 15, '孙七', '力量训练要注意保护膝盖哦。', '会的，训练前都会热身。'),
(306, NOW(), 33, 12, '李四', '高蛋白食谱能分享一下吗？', '下次发帖详细写。'),
-- 赵六 控糖分享 id=34
(307, NOW(), 34, 11, '张三', '低糖饮食加油！餐后散步很有效。', '谢谢，正在坚持中。'),
(308, NOW(), 34, 12, '李四', '我也血糖偏高，可以交流低GI食谱。', '太好了，私信聊～'),
(309, NOW(), 34, 15, '孙七', '广场舞运动量适中，很适合控糖。', ''),
(310, NOW(), 34, 16, '周八', '备注写得很清楚，向你学习自律。', '一起保持健康！'),
-- 孙七 高血压分享 id=35
(311, NOW(), 35, 11, '张三', '低盐饮食不容易，您坚持得很好。', ''),
(312, NOW(), 35, 14, '赵六', '太极拳晨练对血压有帮助，推荐。', '感谢分享经验。'),
-- 周八 作息分享 id=36
(313, NOW(), 36, 11, '张三', '大学生能保持规律作息太棒了！', ''),
(314, NOW(), 36, 13, '王五', '骑行和羽毛球都是很好的有氧运动。', '');
