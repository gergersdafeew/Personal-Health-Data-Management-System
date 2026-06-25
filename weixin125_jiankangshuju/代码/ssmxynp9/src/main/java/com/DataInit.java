package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 一次性数据完善脚本：执行项目根目录 data_complete.sql
 * 运行: mvn exec:java -Dexec.mainClass=com.DataInit
 */
public class DataInit {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("src/main/resources/config.properties")) {
            props.load(in);
        }
        String url = props.getProperty("jdbc_url");
        String user = props.getProperty("jdbc_username");
        String password = props.getProperty("jdbc_password");

        File sqlFile = new File("data_complete.sql");
        if (!sqlFile.exists()) {
            System.err.println("未找到 data_complete.sql: " + sqlFile.getAbsolutePath());
            System.exit(1);
        }

        StringBuilder sql = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(sqlFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("--")) {
                    continue;
                }
                sql.append(line).append('\n');
            }
        }

        String[] statements = sql.toString().split(";");
        int ok = 0;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            for (String s : statements) {
                String st = s.trim();
                if (st.isEmpty()) {
                    continue;
                }
                stmt.execute(st);
                ok++;
            }
        }
        System.out.println("数据完善完成，共执行 " + ok + " 条 SQL。");
    }
}
