package org.lsy.learn.security.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.lsy.learn.security.model.User;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends User {

    private String confirmPassword;

    private String token;
}
