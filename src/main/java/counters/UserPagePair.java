package counters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPagePair {
    private final String userId;
    private final String pageId;

    public UserPagePair(@JsonProperty("userId") String userId, @JsonProperty("pageId") String pageId) {
        this.userId = userId;
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    public String getUserId() {
        return userId;
    }
}
