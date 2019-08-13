package FHQ.service.Impl;

import FHQ.mapper.UserMapper;
import FHQ.po.User;
import FHQ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) throws Exception {
        Integer sum = null;
        if (queryUserByName(user.getUsername()) == 0 || queryUserByName(user.getUsername()) == null ) {
            sum = userMapper.addtUser(user);
        } else {
            return null;
        }

        return sum;
    }

    @Override
    public Integer queryUserByName(String username) throws Exception {
        return userMapper.queryUserByName(username);
    }
}
