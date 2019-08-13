package FHQ.service;

import FHQ.po.User;

public interface LoginCheckService {
    boolean checkUser(User user)throws Exception;
}
