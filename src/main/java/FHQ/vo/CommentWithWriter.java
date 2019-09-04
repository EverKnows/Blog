package FHQ.vo;

import FHQ.po.Comment;
import FHQ.po.User;

public class CommentWithWriter {
    private Comment comment;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "CommentWithWriter{" +
                "comment=" + comment +
                ", user=" + user +
                '}';
    }
}
