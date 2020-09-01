package org.lsy.learn.security.component.param;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeParam<T> {

    private String timeName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private T condition;

}
