USE ssmxynp9;

-- 用户表账号状态（首次升级执行 yonghu_add_zhuangtai.sql）
-- ALTER TABLE `yonghu` ADD COLUMN `zhuangtai` varchar(200) DEFAULT '正常' COMMENT '账号状态' AFTER `gerentouxiang`;

-- 用户
UPDATE yonghu SET yonghuming='张三', xingbie='男', lianxidianhua='13823888881' WHERE id=11;
UPDATE yonghu SET yonghuming='李四', xingbie='女', lianxidianhua='13823888882' WHERE id=12;
UPDATE yonghu SET yonghuming='王五', xingbie='男', lianxidianhua='13823888883' WHERE id=13;
UPDATE yonghu SET yonghuming='赵六', xingbie='女', lianxidianhua='13823888884' WHERE id=14;
UPDATE yonghu SET yonghuming='孙七', xingbie='男', lianxidianhua='13823888885' WHERE id=15;
UPDATE yonghu SET yonghuming='周八', xingbie='女', lianxidianhua='13823888886' WHERE id=16;

-- 健康计划
UPDATE jiankangjihua SET biaoti='春季科学减重计划', nianling='28', shengao='172cm', tizhong='78kg',
  yinshijianyi='控制每日热量摄入，多吃蔬菜水果，少油少盐', yundongjianyi='每周慢跑3次，每次30分钟',
  beizhu='适合BMI偏高的青年人群', thumbsupnum=12, crazilynum=1, clicknum=56 WHERE id=41;
UPDATE jiankangjihua SET biaoti='办公室久坐人群健康计划', nianling='32', shengao='168cm', tizhong='62kg',
  yinshijianyi='午餐七分饱，下午补充坚果与酸奶', yundongjianyi='每小时起身活动5分钟，晚间拉伸15分钟',
  beizhu='缓解肩颈疲劳，改善体态', thumbsupnum=18, crazilynum=0, clicknum=73 WHERE id=42;
UPDATE jiankangjihua SET biaoti='中老年心脑血管养护计划', nianling='58', shengao='170cm', tizhong='70kg',
  yinshijianyi='低盐低脂饮食，适量补充优质蛋白', yundongjianyi='太极拳或快走，每周4次',
  beizhu='建议定期监测血压血脂', thumbsupnum=25, crazilynum=2, clicknum=91 WHERE id=43;
UPDATE jiankangjihua SET biaoti='青少年生长发育营养计划', nianling='16', shengao='165cm', tizhong='52kg',
  yinshijianyi='保证钙铁锌摄入，三餐规律不节食', yundongjianyi='篮球、游泳等有氧运动每周3次',
  beizhu='保证充足睡眠8小时以上', thumbsupnum=9, crazilynum=0, clicknum=44 WHERE id=44;
UPDATE jiankangjihua SET biaoti='产后恢复与体重管理计划', nianling='30', shengao='163cm', tizhong='58kg',
  yinshijianyi='均衡膳食，多喝汤水，避免高糖零食', yundongjianyi='产后瑜伽、盆底肌训练循序渐进',
  beizhu='根据恢复情况调整运动强度', thumbsupnum=21, crazilynum=1, clicknum=68 WHERE id=45;
UPDATE jiankangjihua SET biaoti='糖尿病前期饮食干预计划', nianling='45', shengao='175cm', tizhong='82kg',
  yinshijianyi='控制精制碳水，增加膳食纤维，少食多餐', yundongjianyi='餐后散步20分钟，配合力量训练',
  beizhu='建议每3个月复查血糖', thumbsupnum=16, crazilynum=0, clicknum=59 WHERE id=46;

-- 知识科普
UPDATE zhishikepu SET biaoti='合理膳食：一日三餐怎么吃更健康',
  xiangxineirong='<p>均衡摄入谷薯类、蔬菜水果、肉蛋奶和豆类，控制油盐糖，养成规律进餐习惯。</p>',
  thumbsupnum=32, crazilynum=1, clicknum=128 WHERE id=21;
UPDATE zhishikepu SET biaoti='科学运动：不同年龄段运动建议',
  xiangxineirong='<p>儿童以趣味运动为主，成年人每周至少150分钟中等强度有氧运动，老年人注重平衡与柔韧训练。</p>',
  thumbsupnum=28, crazilynum=0, clicknum=115 WHERE id=22;
UPDATE zhishikepu SET biaoti='睡眠质量与健康的关系',
  xiangxineirong='<p>成年人建议每晚7-8小时睡眠，睡前避免电子产品，保持卧室安静昏暗。</p>',
  thumbsupnum=19, crazilynum=0, clicknum=87 WHERE id=23;
UPDATE zhishikepu SET biaoti='如何正确测量和管理体重',
  xiangxineirong='<p>建议每天固定时间、空腹、穿轻便衣物测量，结合腰围综合评估体脂变化。</p>',
  thumbsupnum=24, crazilynum=2, clicknum=96 WHERE id=24;
UPDATE zhishikepu SET biaoti='高血压患者的日常注意事项',
  xiangxineirong='<p>低盐饮食、规律服药、监测血压、戒烟限酒，保持情绪稳定。</p>',
  thumbsupnum=35, crazilynum=1, clicknum=142 WHERE id=25;
UPDATE zhishikepu SET biaoti='心理健康：压力管理与情绪调节',
  xiangxineirong='<p>学会深呼吸、正念冥想，与亲友倾诉，必要时寻求专业心理咨询。</p>',
  thumbsupnum=27, crazilynum=0, clicknum=103 WHERE id=26;

-- 健康信息共享
UPDATE jiankangxinxigongxiang SET yonghuzhanghao='用户1', yonghuming='张三', xingbie='男', nianling=28,
  shengao='172cm', tizhong='78kg', yinshixiguan='清淡饮食，少喝碳酸饮料', yundongxiguan='每周跑步3次',
  beizhu='正在执行减重计划', sfsh='是', thumbsupnum=8, crazilynum=0, clicknum=34 WHERE id=31;
UPDATE jiankangxinxigongxiang SET yonghuzhanghao='用户2', yonghuming='李四', xingbie='女', nianling=26,
  shengao='165cm', tizhong='55kg', yinshixiguan='素食为主，补充豆制品', yundongxiguan='瑜伽、游泳',
  beizhu='注重体态管理', sfsh='是', thumbsupnum=12, crazilynum=1, clicknum=45 WHERE id=32;
UPDATE jiankangxinxigongxiang SET yonghuzhanghao='用户3', yonghuming='王五', xingbie='男', nianling=35,
  shengao='178cm', tizhong='75kg', yinshixiguan='高蛋白低脂', yundongxiguan='健身房力量训练',
  beizhu='增肌期', sfsh='是', thumbsupnum=6, crazilynum=0, clicknum=28 WHERE id=33;
UPDATE jiankangxinxigongxiang SET yonghuzhanghao='用户4', yonghuming='赵六', xingbie='女', nianling=42,
  shengao='160cm', tizhong='58kg', yinshixiguan='低糖饮食', yundongxiguan='快走、广场舞',
  beizhu='血糖偏高，控制饮食', sfsh='是', thumbsupnum=15, crazilynum=0, clicknum=52 WHERE id=34;
UPDATE jiankangxinxigongxiang SET yonghuzhanghao='用户5', yonghuming='孙七', xingbie='男', nianling=55,
  shengao='170cm', tizhong='72kg', yinshixiguan='低盐低脂', yundongxiguan='太极拳',
  beizhu='高血压患者', sfsh='是', thumbsupnum=20, crazilynum=1, clicknum=61 WHERE id=35;
UPDATE jiankangxinxigongxiang SET yonghuzhanghao='用户6', yonghuming='周八', xingbie='女', nianling=22,
  shengao='168cm', tizhong='52kg', yinshixiguan='规律三餐', yundongxiguan='骑行、羽毛球',
  beizhu='大学生，作息规律', sfsh='是', thumbsupnum=9, crazilynum=0, clicknum=37 WHERE id=36;

-- 健康数据
UPDATE jiankangshuju SET bianhao='JK2024001', yonghuzhanghao='用户1', yonghuming='张三', lianxidianhua='13823888881',
  nianling=28, shengao='172cm', tizhong='78kg', yinshixiguan='早餐燕麦牛奶，午餐鸡胸肉沙拉，晚餐七分饱',
  yundongxiguan='每周慢跑3次共90分钟', beizhu='BMI偏高，目标减重5kg', fabushijian='2024-03-15 09:30:00' WHERE id=51;
UPDATE jiankangshuju SET bianhao='JK2024002', yonghuzhanghao='用户2', yonghuming='李四', lianxidianhua='13823888882',
  nianling=26, shengao='165cm', tizhong='55kg', yinshixiguan='以蔬菜豆制品为主，少油烹饪',
  yundongxiguan='瑜伽每周2次，游泳每周1次', beizhu='体态良好', fabushijian='2024-03-16 10:15:00' WHERE id=52;
UPDATE jiankangshuju SET bianhao='JK2024003', yonghuzhanghao='用户3', yonghuming='王五', lianxidianhua='13823888883',
  nianling=35, shengao='178cm', tizhong='75kg', yinshixiguan='高蛋白饮食，训练日增加碳水',
  yundongxiguan='力量训练每周4次', beizhu='增肌阶段', fabushijian='2024-03-17 14:20:00' WHERE id=53;
UPDATE jiankangshuju SET bianhao='JK2024004', yonghuzhanghao='用户4', yonghuming='赵六', lianxidianhua='13823888884',
  nianling=42, shengao='160cm', tizhong='58kg', yinshixiguan='低GI食物，控制精制糖',
  yundongxiguan='餐后散步30分钟', beizhu='空腹血糖6.2', fabushijian='2024-03-18 08:45:00' WHERE id=54;
UPDATE jiankangshuju SET bianhao='JK2024005', yonghuzhanghao='用户5', yonghuming='孙七', lianxidianhua='13823888885',
  nianling=55, shengao='170cm', tizhong='72kg', yinshixiguan='每日盐摄入<5g，多吃钾丰富食物',
  yundongxiguan='太极拳每日晨练', beizhu='血压135/85', fabushijian='2024-03-19 07:00:00' WHERE id=55;
UPDATE jiankangshuju SET bianhao='JK2024006', yonghuzhanghao='用户6', yonghuming='周八', lianxidianhua='13823888886',
  nianling=22, shengao='168cm', tizhong='52kg', yinshixiguan='食堂三餐规律，少熬夜',
  yundongxiguan='校园骑行、羽毛球', beizhu='作息规律', fabushijian='2024-03-20 16:30:00' WHERE id=56;

-- 数据分析
UPDATE shujufenxi SET bianhao='FX2024001', yonghuzhanghao='用户1', yonghuming='张三', fabushijian='2024-03-15',
  fenxijieguo='一般', jiankangjianyi='建议减少油脂摄入，增加有氧运动时长至每周150分钟，定期监测体重变化。' WHERE id=61;
UPDATE shujufenxi SET bianhao='FX2024002', yonghuzhanghao='用户2', yonghuming='李四', fabushijian='2024-03-16',
  fenxijieguo='良好', jiankangjianyi='当前饮食运动习惯较好，建议保持并注意钙质补充。' WHERE id=62;
UPDATE shujufenxi SET bianhao='FX2024003', yonghuzhanghao='用户3', yonghuming='王五', fabushijian='2024-03-17',
  fenxijieguo='良好', jiankangjianyi='肌肉量达标，注意训练后拉伸与蛋白质补充。' WHERE id=63;
UPDATE shujufenxi SET bianhao='FX2024004', yonghuzhanghao='用户4', yonghuming='赵六', fabushijian='2024-03-18',
  fenxijieguo='一般', jiankangjianyi='血糖略偏高，建议控制碳水摄入，增加膳食纤维，3个月后复查。' WHERE id=64;
UPDATE shujufenxi SET bianhao='FX2024005', yonghuzhanghao='用户5', yonghuming='孙七', fabushijian='2024-03-19',
  fenxijieguo='较差', jiankangjianyi='血压偏高，请严格低盐饮食，遵医嘱服药，避免剧烈运动。' WHERE id=65;
UPDATE shujufenxi SET bianhao='FX2024006', yonghuzhanghao='用户6', yonghuming='周八', fabushijian='2024-03-20',
  fenxijieguo='优秀', jiankangjianyi='各项指标正常，继续保持规律作息和适量运动。' WHERE id=66;

-- 评论
UPDATE discussjiankangjihua SET nickname='张三', content='这个减重计划很实用，坚持两周已瘦2斤！', reply='加油，注意循序渐进。' WHERE id=101;
UPDATE discussjiankangjihua SET nickname='李四', content='办公室计划对久坐族太友好了', reply='感谢反馈！' WHERE id=102;
UPDATE discussjiankangjihua SET nickname='王五', content='中老年养护计划讲解很详细', reply='' WHERE id=103;
UPDATE discusszhishikepu SET nickname='张三', content='科普内容通俗易懂，收藏了', reply='' WHERE id=81;
UPDATE discusszhishikepu SET nickname='李四', content='运动建议很实用', reply='谢谢支持' WHERE id=82;
UPDATE discussjiankangxinxigongxiang SET nickname='王五', content='分享的经验很有帮助', reply='' WHERE id=91;

-- 修正评论 refid（知识科普 21-26，健康计划 41-46）
UPDATE discusszhishikepu SET refid=21 WHERE id=81;
UPDATE discusszhishikepu SET refid=22 WHERE id=82;
UPDATE discusszhishikepu SET refid=23 WHERE id=83;
UPDATE discusszhishikepu SET refid=24 WHERE id=84;
UPDATE discusszhishikepu SET refid=25 WHERE id=85;
UPDATE discusszhishikepu SET refid=26 WHERE id=86;
UPDATE discussjiankangjihua SET refid=41 WHERE id=101;
UPDATE discussjiankangjihua SET refid=42 WHERE id=102;
UPDATE discussjiankangjihua SET refid=43 WHERE id=103;
UPDATE discussjiankangjihua SET refid=44 WHERE id=104;
UPDATE discussjiankangjihua SET refid=45 WHERE id=105;
UPDATE discussjiankangjihua SET refid=46 WHERE id=106;
UPDATE discusszhishikepu SET refid=refid+20 WHERE refid BETWEEN 1 AND 6;
UPDATE discussjiankangjihua SET refid=refid+40 WHERE refid BETWEEN 1 AND 6;

-- 交流区（parentid=0 为顶级帖，接口才显示）
UPDATE forum SET parentid=0, isdone='开放', title='大家平时都怎么控制体重？',
  content='最近想减重，求分享有效的饮食和运动方法。', username='张三' WHERE id=71;
UPDATE forum SET parentid=0, isdone='开放', title='高血压患者饮食交流',
  content='低盐饮食有什么好吃的菜谱推荐吗？', username='孙七' WHERE id=72;
UPDATE forum SET parentid=0, isdone='开放', title='适合上班族的拉伸动作',
  content='长期对着电脑，肩颈酸痛，求推荐简单拉伸。', username='李四' WHERE id=73;
