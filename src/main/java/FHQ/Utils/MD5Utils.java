package FHQ.Utils;
import org.springframework.util.DigestUtils;

public class MD5Utils {

    public static String toMD5(String pwd) {
        if (pwd == null || pwd.length() == 0) {
            return pwd;
        }
        //进行加密
        return DigestUtils.md5DigestAsHex(pwd.getBytes());
    }

}
