package com.sample.msf.user;

import com.mobigen.framework.security.IUserDao;
import com.mobigen.framework.security.IUserModel;
import org.springframework.stereotype.Repository;

@Repository("authMapper")
public interface AuthMapper extends IUserDao {
    IUserModel getUser(IUserModel user) throws Exception;
}
