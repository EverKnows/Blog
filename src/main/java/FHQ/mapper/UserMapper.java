package FHQ.mapper;

import FHQ.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {


    @Insert("insert into t_user(username, password, email) values(#{username}, #{password}, #{email})")
    public Integer addtUser(User user);

    @Select("select count(*) from t_user where username=#{username}")
    public Integer queryUserByName(String username);



    @Select("select * from t_user where username=#{username}")
    @Results(id = "accResultMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "realname",column = "realname"),
            @Result(property = "intro",column = "intro"),
            @Result(property = "pic",column = "pic"),
            @Result(property = "email",column = "email"),
            @Result(property = "tag",column = "tag"),
            @Result(property = "avatar",column = "avatar")
    })
    public User selectUserByName(String username);
}
