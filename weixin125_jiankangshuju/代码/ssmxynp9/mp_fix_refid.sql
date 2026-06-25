USE ssmxynp9;

-- 修正评论 refid 与主表 id 对应关系
-- 知识科普 id 21-26，健康计划 id 41-46
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

-- 兜底：若 refid 仍为 1-6 则批量偏移
UPDATE discusszhishikepu SET refid=refid+20 WHERE refid BETWEEN 1 AND 6;
UPDATE discussjiankangjihua SET refid=refid+40 WHERE refid BETWEEN 1 AND 6;
