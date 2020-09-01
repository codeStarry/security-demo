package org.lsy.learn.security.component.param;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * in参数
 *
 * @param <V>
 * @param <T>
 */
@Getter
@Setter
public class InParam<V, T> extends BaseParam<T> {


    private String inName;

    private List<V> inValue;

}
