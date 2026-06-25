USE ssmxynp9;

-- 知识科普：完善详细内容与发布时间
UPDATE zhishikepu SET
  fabushijian='2024-03-01 09:00:00', gengxinshijian='2024-03-15 10:30:00',
  shipinjieshao='http://localhost:8080/ssmxynp9/upload/zhishikepu_shipin1.mp4',
  xiangxineirong='<p><strong>一、早餐（占全天热量30%）</strong></p><p>建议摄入全谷物（燕麦、全麦面包）、优质蛋白（鸡蛋、牛奶）和新鲜水果。避免空腹喝冷饮，餐后适量活动。</p><p><strong>二、午餐（占全天热量40%）</strong></p><p>主食粗细搭配，保证蔬菜占餐盘一半，适量瘦肉或豆制品。少油少盐，避免油炸食物。</p><p><strong>三、晚餐（占全天热量30%）</strong></p><p>宜清淡易消化，可吃鱼、豆腐、蔬菜汤。睡前2小时尽量不进食，有助于睡眠和体重管理。</p>'
WHERE id=21;

UPDATE zhishikepu SET
  fabushijian='2024-03-02 09:00:00', gengxinshijian='2024-03-16 11:00:00',
  shipinjieshao='http://localhost:8080/ssmxynp9/upload/zhishikepu_shipin2.mp4',
  xiangxineirong='<p><strong>儿童青少年：</strong>每天累计运动60分钟以上，以跑跳、球类、游泳等趣味运动为主，促进骨骼发育。</p><p><strong>成年人：</strong>每周至少150分钟中等强度有氧运动（快走、慢跑、骑行），配合2次力量训练。</p><p><strong>老年人：</strong>以太极拳、快走、八段锦为主，注重平衡与柔韧，运动前充分热身。</p>'
WHERE id=22;

UPDATE zhishikepu SET
  fabushijian='2024-03-03 09:00:00', gengxinshijian='2024-03-17 09:30:00',
  shipinjieshao='http://localhost:8080/ssmxynp9/upload/zhishikepu_shipin3.mp4',
  xiangxineirong='<p>成年人建议每晚睡眠<strong>7-8小时</strong>，固定作息时间有助于调节生物钟。</p><p>睡前1小时避免使用手机、电脑等蓝光设备；卧室保持安静、昏暗、凉爽。</p><p>长期睡眠不足会增加肥胖、高血压和免疫力下降风险，建议记录睡眠日记跟踪改善效果。</p>'
WHERE id=23;

UPDATE zhishikepu SET
  fabushijian='2024-03-04 09:00:00', gengxinshijian='2024-03-18 14:00:00',
  shipinjieshao='http://localhost:8080/ssmxynp9/upload/zhishikepu_shipin4.mp4',
  xiangxineirong='<p>建议每天<strong>固定时间</strong>（如晨起如厕后）测量体重，穿轻便衣物，空腹状态更准确。</p><p>除体重外，应关注腰围变化：男性腰围≥90cm、女性≥85cm需警惕中心性肥胖。</p><p>体重波动1-2kg属正常，应关注长期趋势，结合饮食运动记录综合评估。</p>'
WHERE id=24;

UPDATE zhishikepu SET
  fabushijian='2024-03-05 09:00:00', gengxinshijian='2024-03-19 10:00:00',
  shipinjieshao='http://localhost:8080/ssmxynp9/upload/zhishikepu_shipin5.mp4',
  xiangxineirong='<p><strong>饮食：</strong>每日盐摄入控制在5g以内，多吃富含钾的蔬果（香蕉、菠菜）。</p><p><strong>监测：</strong>每天固定时间测血压并记录，遵医嘱规律服药，不可自行停药。</p><p><strong>生活：</strong>戒烟限酒，保持情绪稳定，避免突然用力或剧烈运动。</p>'
WHERE id=25;

UPDATE zhishikepu SET
  fabushijian='2024-03-06 09:00:00', gengxinshijian='2024-03-20 15:00:00',
  shipinjieshao='http://localhost:8080/ssmxynp9/upload/zhishikepu_shipin6.mp4',
  xiangxineirong='<p>长期心理压力会影响睡眠、食欲和免疫力，需要主动调节。</p><p>可尝试：<strong>深呼吸练习</strong>（每次5分钟）、<strong>正念冥想</strong>、规律运动、与亲友倾诉。</p><p>若持续焦虑抑郁超过2周，建议寻求专业心理咨询或就医，及早干预效果更好。</p>'
WHERE id=26;

-- 补充科普评论
UPDATE discusszhishikepu SET nickname='张三', content='科普内容通俗易懂，收藏了！', reply='感谢支持，欢迎分享给家人朋友。' WHERE id=81;
UPDATE discusszhishikepu SET nickname='李四', content='运动建议很实用，准备照着做。', reply='坚持就会有效果，加油！' WHERE id=82;
UPDATE discusszhishikepu SET nickname='王五', content='睡眠那篇讲得太好了，今晚就试试。', reply='' WHERE id=83;
UPDATE discusszhishikepu SET nickname='赵六', content='高血压患者必读，已转发给父母。', reply='祝家人健康！' WHERE id=84;
UPDATE discusszhishikepu SET nickname='孙七', content='体重管理方法很科学。', reply='' WHERE id=85;
UPDATE discusszhishikepu SET nickname='周八', content='心理健康也很重要，点赞。', reply='' WHERE id=86;
