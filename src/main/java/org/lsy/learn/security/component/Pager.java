package org.lsy.learn.security.component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.component.param.InParam;

/**
 * 分页
 *
 * @author lsy
 */
@Getter
@Setter
public class Pager<T> {

    private Long page = 1L;

    private Long pageSize = 10L;

    private T condition;

    private Long optId;

    public static <T> Page<T> decorateBaseParam(Pager<BaseParam<T>> pager) {
        return new Page<>(pager.getPage(), pager.getPageSize());
    }

    public static <V, T> Page<T> decorateInParam(Pager<InParam<V, T>> pager) {
        return new Page<>(pager.getPage(), pager.getPageSize());
    }
}
