package org.lsy.learn.security.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.component.param.InParam;

import java.util.Objects;

public class Translations {

    private Translations() {}

    public static <T> QueryWrapper<T> translationBaseParam(BaseParam<T> param) {
        if (null == param) {
            return new QueryWrapper<>(param.getCondition());
        }
        QueryWrapper<T> wrapper = new QueryWrapper<>(param.getCondition());

        wrapper.ge(StringUtils.isNotBlank(param.getTimeName()), StringUtils.camelToUnderline(param.getTimeName()), param.getStartTime())
               .le(StringUtils.isNotBlank(param.getTimeName()), StringUtils.camelToUnderline(param.getTimeName()), param.getEndTime())
               .orderBy(StringUtils.isNotBlank(param.getOrderBy()), param.getAsc() > 0, StringUtils.camelToUnderline(param.getOrderBy()));

        return wrapper;
    }

    public static <V, T> QueryWrapper<T> translationInParam(InParam<V, T> param) {
        QueryWrapper<T> wrapper = translationBaseParam(param);

        if (Objects.isNull(param.getInName())
                  || CollectionUtils.isEmpty(param.getInValue())) {

            return wrapper;
        }

        wrapper.in(StringUtils.camelToUnderline(param.getInName()), param.getInValue());

        return wrapper;
    }
}
