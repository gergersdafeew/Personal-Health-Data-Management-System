# -*- coding: utf-8 -*-
import os
import pymysql

base = os.path.dirname(os.path.abspath(__file__))
sql_path = os.path.join(base, "data_complete.sql")


def load_statements(path):
    statements = []
    buf = []
    with open(path, encoding="utf-8") as f:
        for line in f:
            stripped = line.strip()
            if not stripped or stripped.startswith("--"):
                continue
            buf.append(line)
            if stripped.endswith(";"):
                statements.append("".join(buf).strip())
                buf = []
    return statements


conn = pymysql.connect(
    host="127.0.0.1",
    user="root",
    password="Ma123456@",
    database="ssmxynp9",
    charset="utf8mb4",
)
cur = conn.cursor()
count = 0
for stmt in load_statements(sql_path):
    upper = stmt.upper()
    if upper.startswith("USE "):
        continue
    cur.execute(stmt)
    count += 1
conn.commit()
conn.close()
print("数据完善完成，共执行 %d 条 SQL。" % count)
