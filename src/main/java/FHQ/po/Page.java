package FHQ.po;

public class Page {
    //当前页数
    private Integer cur;
    //一页的记录数
    private Integer size;

    public Integer getCur() {
        return cur;
    }

    public void setCur(Integer cur) {
        this.cur = cur;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
