package org.lsy.learn.security.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 * @author lsy
 */
@Configuration
@MapperScan("org.lsy.learn.security.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页配置
     * @return
     */
    @Bean
    public PaginationInnerInterceptor interceptor() {
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        innerInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        innerInterceptor.setMaxLimit(500L);
        // 开启 count 的 join 优化,只针对部分 left join
        innerInterceptor.setDbType(DbType.MYSQL);
        innerInterceptor.setDialect(new MySqlDialect());
        return innerInterceptor;
    }
}
