package com.jonasesteves.posts.textprocessor.api.domain.model;

public class PostData {
    private String postId;
    private String postBody;

    private PostData() {}

    public PostData(String postId, String postBody) {
        this.postId = postId;
        this.postBody = postBody;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostBody() {
        return postBody;
    }
}
