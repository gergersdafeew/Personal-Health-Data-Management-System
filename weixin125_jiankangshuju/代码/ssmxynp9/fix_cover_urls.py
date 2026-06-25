# -*- coding: utf-8 -*-
"""为所有图片 URL 添加版本参数，强制浏览器加载新图片。"""
import pymysql

BASE = "http://localhost:8080/ssmxynp9/upload"
VER = "?v=3"


def set_url(filename):
    return "%s/%s%s" % (BASE, filename, VER)


def update_pattern(cur, table, column, filename_pattern):
    url = set_url(filename_pattern)
    like = "%%%s%%" % filename_pattern.replace("%", "%%")
    cur.execute(
        "UPDATE `%s` SET `%s`=%s WHERE `%s` LIKE %s" % (table, column, "%s", column, "%s"),
        (url, like),
    )
    return cur.rowcount


conn = pymysql.connect(
    host="127.0.0.1", user="root", password="Ma123456@",
    database="ssmxynp9", charset="utf8mb4",
)
cur = conn.cursor()
total = 0

for i in range(1, 7):
    total += update_pattern(cur, "jiankangjihua", "fengmian", "jiankangjihua_fengmian%d.jpg" % i)
    total += update_pattern(cur, "zhishikepu", "fengmian", "zhishikepu_fengmian%d.jpg" % i)
    total += update_pattern(cur, "shujufenxi", "keguantuxiang", "shujufenxi_keguantuxiang%d.jpg" % i)
    total += update_pattern(cur, "yonghu", "gerentouxiang", "yonghu_gerentouxiang%d.jpg" % i)
    total += update_pattern(cur, "jiankangshuju", "gerentouxiang", "jiankangshuju_gerentouxiang%d.jpg" % i)
    total += update_pattern(cur, "jiankangxinxigongxiang", "gerentouxiang", "jiankangxinxigongxiang_gerentouxiang%d.jpg" % i)

carousel = {
    "1618412361473.jpg": 1,
    "picture2.jpg": 2,
    "1618412369578.jpg": 3,
    "1618412392377.jpg": 6,
}
for fname, cid in carousel.items():
    cur.execute("UPDATE config SET value=%s WHERE id=%s", (set_url(fname), cid))
    total += cur.rowcount

conn.commit()
print("已更新 %d 条图片 URL (版本 %s)" % (total, VER))

cur.execute("SELECT id, bianhao, keguantuxiang FROM shujufenxi ORDER BY id LIMIT 6")
for row in cur.fetchall():
    print(row)

conn.close()
