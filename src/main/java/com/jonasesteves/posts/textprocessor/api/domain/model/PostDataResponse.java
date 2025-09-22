package com.jonasesteves.posts.textprocessor.api.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class PostDataResponse {
    private String postId;
    private Integer wordCount;
    private BigDecimal calculatedValue;

    private PostDataResponse() {}

    public PostDataResponse(String postId, Integer wordCount, BigDecimal calculatedValue) {
        this.postId = postId;
        this.wordCount = wordCount;
        this.calculatedValue = calculatedValue;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public BigDecimal getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(BigDecimal calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostDataResponse that = (PostDataResponse) o;
        return Objects.equals(postId, that.postId) && Objects.equals(wordCount, that.wordCount) && Objects.equals(calculatedValue, that.calculatedValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, wordCount, calculatedValue);
    }

    @Override
    public String toString() {
        return "PostDataResponse{" +
                "postId='" + postId + '\'' +
                ", wordCount=" + wordCount +
                ", calculatedValue=" + calculatedValue +
                '}';
    }
}
