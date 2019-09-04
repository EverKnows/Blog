package FHQ.controller;

import FHQ.Utils.MD5Utils;
import FHQ.Validation.LoginValidate;
import FHQ.po.User;
import FHQ.service.Impl.UserServiceImpl;
import FHQ.service.LoginCheckService;
import FHQ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginCheckService loginCheckService;

    @RequestMapping("/logins")
    public String toLoginPage()throws Exception {
        return "logins";
    }


    @PostMapping("/login")
    @ResponseBody
    public Map login(User user, HttpSession session)throws Exception {
        Map<String, String> map = new HashMap<>();
        if (loginCheckService.checkUser(user)) {
            session.setAttribute("user", user);
            map.put("status", "success");
            map.put("message", "登录成功！即将跳转到个人主页..");
            return map;
        } else {
            map.put("status", "error");
            map.put("message", "用户名或密码错误");
            return map;
        }

    }

    @PostMapping("/regist")
    @ResponseBody
    public Map resigt(@Validated(LoginValidate.class) User user, BindingResult bindingResult) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        // 如果校验时有不符合校验规则的情况出现，springMVC会将错误信息放在BindingResult对象的错误提示信息里面
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
            map.put("errors", errors.get(0).getDefaultMessage());
            return map;
        } else {
            // 使用MD5进行加密
            user.setPassword(MD5Utils.toMD5(user.getPassword()));
            System.out.println(user.getPassword());
            if (userService.addUser(user) == null) {
                map.put("errors", "该用户名已经被注册！");
                return map;
            } else {

                map.put("success", "恭喜你成功注册！即将为您跳到登录界面");
            }
        }

        return map;
    }
}
