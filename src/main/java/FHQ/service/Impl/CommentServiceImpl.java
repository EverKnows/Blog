package FHQ.service.Impl;

import FHQ.mapper.CommentMapper;
import FHQ.po.Comment;
import FHQ.service.CommentService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Integer insertComment(Comment comment) {
        Integer integer = commentMapper.insertComment(comment);
        return integer;
    }
}
