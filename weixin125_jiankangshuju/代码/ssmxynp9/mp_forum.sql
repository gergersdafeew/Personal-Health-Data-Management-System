USE ssmxynp9;

-- 修复顶级帖子（parentid 必须为 0，isdone 必须为「开放」）
UPDATE forum SET parentid=0, isdone='开放',
  title='大家平时都怎么控制体重？',
  content='最近想减重，求分享有效的饮食和运动方法。我目前BMI偏高，每天久坐办公，想听听大家的经验。',
  username='张三', userid=11, addtime='2024-03-15 09:30:00'
WHERE id=71;

UPDATE forum SET parentid=0, isdone='开放',
  title='高血压患者饮食交流',
  content='家人们好，我父亲刚确诊高血压，医生让低盐饮食。请问有什么好吃又健康的低盐菜谱推荐？',
  username='孙七', userid=15, addtime='2024-03-16 14:20:00'
WHERE id=72;

UPDATE forum SET parentid=0, isdone='开放',
  title='适合上班族的拉伸动作',
  content='长期对着电脑，肩颈酸痛得厉害。有没有简单易学、在办公室就能做的拉伸动作？求推荐！',
  username='李四', userid=12, addtime='2024-03-17 18:45:00'
WHERE id=73;

UPDATE forum SET parentid=0, isdone='开放',
  title='跑步和快走哪个更适合减脂？',
  content='刚开始运动减脂，在纠结选跑步还是快走。有经验的朋友能说说各自的优缺点吗？',
  username='王五', userid=13, addtime='2024-03-18 07:15:00'
WHERE id=74;

UPDATE forum SET parentid=0, isdone='开放',
  title='如何养成早睡早起的习惯？',
  content='总是熬夜到凌晨，第二天精神很差。大家有什么调整作息的好方法吗？',
  username='赵六', userid=14, addtime='2024-03-19 22:30:00'
WHERE id=75;

UPDATE forum SET parentid=0, isdone='开放',
  title='健康早餐搭配分享',
  content='分享我的健康早餐：燕麦牛奶+水煮蛋+苹果，饱腹感强还不长胖。大家也来晒晒早餐吧！',
  username='周八', userid=16, addtime='2024-03-20 08:00:00'
WHERE id=76;

-- 清理无效测试帖
DELETE FROM forum WHERE id IN (1618412186853, 1618412190782);
DELETE FROM forum WHERE id=1618412181179;

-- 回复帖（parentid 指向主帖）
DELETE FROM forum WHERE id BETWEEN 201 AND 210;

INSERT INTO forum (id, addtime, title, content, parentid, userid, username, isdone) VALUES
(201, '2024-03-15 11:00:00', NULL, '建议控制碳水摄入，晚餐少吃主食，我两个月瘦了5斤。', 71, 12, '李四', NULL),
(202, '2024-03-15 15:30:00', NULL, '配合每周3次慢跑，每次30分钟，效果很明显。', 71, 13, '王五', NULL),
(203, '2024-03-16 09:00:00', NULL, '可以试试清蒸鱼、凉拌黄瓜，少放酱油用盐限器。', 72, 14, '赵六', NULL),
(204, '2024-03-16 16:00:00', NULL, '芹菜炒香干、番茄鸡蛋汤都不错，低盐也美味。', 72, 16, '周八', NULL),
(205, '2024-03-17 20:00:00', NULL, '推荐颈部左右转动、耸肩放松，每小时做一次。', 73, 11, '张三', NULL),
(206, '2024-03-18 08:30:00', NULL, '快走对膝盖友好，适合刚开始运动的人，循序渐进就好。', 74, 12, '李四', NULL),
(207, '2024-03-19 23:00:00', NULL, '睡前1小时放下手机，喝杯温牛奶，我坚持两周就改善了。', 75, 15, '孙七', NULL),
(208, '2024-03-20 09:30:00', NULL, '早餐很重要！你的搭配很科学，我也试试燕麦方案。', 76, 11, '张三', NULL);
