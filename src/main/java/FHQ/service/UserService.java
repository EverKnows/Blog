package FHQ.service;

import FHQ.po.User;

public interface UserService {
    Integer addUser(User user) throws Exception;

    Integer queryUserByName(String username) throws Exception;
}
