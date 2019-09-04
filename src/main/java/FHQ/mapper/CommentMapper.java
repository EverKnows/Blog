package FHQ.mapper;

import FHQ.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {


    public Integer insertComment(@Param(value = "comment") Comment comment);
}
