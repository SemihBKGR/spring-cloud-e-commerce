package com.semihbkgr.ecommerce.ecommerce.userserver.service;

import com.semihbkgr.ecommerce.ecommerce.userserver.util.Page;
import com.semihbkgr.ecommerce.modelcommon.user.User;
import com.semihbkgr.ecommerce.modelcommon.user.UserInfo;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<UserInfo> searchByUsername(String username, Page page);

}
