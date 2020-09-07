package org.lsy.learn.security.tools;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemInfo {
    
    private SystemInfo() {}
    
    public static void printSystemInfo() {
        log.info("java版本号：{}", System.getProperty("java.version")); // java版本号
        log.info("Java提供商名称：{}", System.getProperty("java.vendor")); // Java提供商名称
        log.info("Java提供商网站：{}", System.getProperty("java.vendor.url")); // Java提供商网站
        log.info("jre目录：{}", System.getProperty("java.home")); // Java，哦，应该是jre目录
        log.info("Java虚拟机规范版本号：{}", System.getProperty("java.vm.specification.version")); // Java虚拟机规范版本号
        log.info("Java虚拟机规范提供商：{}", System.getProperty("java.vm.specification.vendor")); // Java虚拟机规范提供商
        log.info("Java虚拟机规范名称：{}", System.getProperty("java.vm.specification.name")); // Java虚拟机规范名称
        log.info("Java虚拟机版本号：{}", System.getProperty("java.vm.version")); // Java虚拟机版本号
        log.info("Java虚拟机提供商：{}", System.getProperty("java.vm.vendor")); // Java虚拟机提供商
        log.info("Java虚拟机名称：{}", System.getProperty("java.vm.name")); // Java虚拟机名称
        log.info("Java规范版本号：{}", System.getProperty("java.specification.version")); // Java规范版本号
        log.info("Java规范提供商：{}", System.getProperty("java.specification.vendor")); // Java规范提供商
        log.info("Java规范名称：{}", System.getProperty("java.specification.name")); // Java规范名称
        log.info("Java类版本号：{}", System.getProperty("java.class.version")); // Java类版本号
        log.info("Java类路径：{}", System.getProperty("java.class.path")); // Java类路径
        log.info("Java lib路径：{}", System.getProperty("java.library.path")); // Java lib路径
        log.info("Java输入输出临时路径：{}", System.getProperty("java.io.tmpdir")); // Java输入输出临时路径
        log.info("Java编译器：{}", System.getProperty("java.compiler")); // Java编译器
        log.info("Java执行路径：{}", System.getProperty("java.ext.dirs")); // Java执行路径
        log.info("操作系统名称：{}", System.getProperty("os.name")); // 操作系统名称
        log.info("操作系统的架构：{}", System.getProperty("os.arch")); // 操作系统的架构
        log.info("操作系统版本号：{}", System.getProperty("os.version")); // 操作系统版本号
        log.info("操作系统用户名：{}", System.getProperty("user.name")); // 用户名
        log.info("操作系统用户的主目录：{}", System.getProperty("user.home")); // 用户的主目录
        log.info("当前程序所在目录：{}", System.getProperty("user.dir")); // 当前程序所在目录
    }
}
