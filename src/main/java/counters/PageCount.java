package counters;

public class PageCount {
    private String pageId;
    private long count;

    public PageCount(String pageId, long count){
        this.pageId = pageId;
        this.count = count;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
