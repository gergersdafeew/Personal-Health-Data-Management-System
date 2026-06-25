package com;

import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String webappPath = new File("src/main/webapp").getAbsolutePath();
        Context ctx = tomcat.addWebapp("/ssmxynp9", webappPath);

        // 嵌入式 Tomcat 需将父类加载器设为应用类加载器，否则解析 web.xml 会失败
        if (ctx instanceof StandardContext) {
            ((StandardContext) ctx).setParentClassLoader(Application.class.getClassLoader());
        }

        // 设置 session 超时（分钟）
        ctx.setSessionTimeout(30);

        tomcat.start();
        System.out.println("========================================");
        System.out.println("  Tomcat 启动成功！");
        System.out.println("  后端地址: http://localhost:8080/ssmxynp9");
        System.out.println("  管理员后台: http://localhost:8080/ssmxynp9/admin/dist");
        System.out.println("  前端页面: http://localhost:8080/ssmxynp9/front/h5");
        System.out.println("========================================");
        tomcat.getServer().await();
    }
}
