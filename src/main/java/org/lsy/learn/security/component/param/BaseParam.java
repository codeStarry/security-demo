package org.lsy.learn.security.component.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseParam<T> extends TimeParam<T> {

    private String orderBy;

    private int asc = 0;

}
