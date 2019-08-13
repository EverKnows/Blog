package FHQ.po;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;


public class User {
    private Integer id;
//
    @NotBlank(message = "用户名不准为空")
    @Pattern(regexp = "^[A-Za-z0-9]{3,16}$", message = "用户名格式错误")
    private String username;

    /*

     */
    @NotEmpty(message = "密码不准为空")
    @Pattern(regexp = "^[a-zA-Z]{1,1}[a-zA-Z0-9.]{7,15}$", message = "密码格式错误")
    private String password;


    private String realname;

    private String intro;

    private String pic;

    @NotEmpty
    @Email(message = "邮箱格式错误")
    private String email;

    private String tag;

    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public User(Integer id, String username, String password, String realname, String intro, String pic, String email, String tag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.intro = intro;
        this.pic = pic;
        this.email = email;
        this.tag = tag;
    }


    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", intro='" + intro + '\'' +
                ", pic='" + pic + '\'' +
                ", email='" + email + '\'' +
                ", tag='" + tag + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
