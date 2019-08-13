package FHQ.service.Impl;

import FHQ.Utils.MD5Utils;
import FHQ.mapper.UserMapper;
import FHQ.po.User;
import FHQ.service.LoginCheckService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCheckServiceImpl implements LoginCheckService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkUser(User user)throws Exception {
        user.setPassword(MD5Utils.toMD5(user.getPassword()));
        User target = userMapper.selectUserByName(user.getUsername());
        if (target != null) {
            if (target.getPassword().equals(user.getPassword())) {
                BeanUtils.copyProperties(target, user);
                return true;
            }
        }
        return false;
    }
}
