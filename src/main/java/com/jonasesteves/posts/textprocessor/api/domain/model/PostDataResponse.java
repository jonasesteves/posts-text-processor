package com.jonasesteves.posts.textprocessor.api.domain.model;

import java.math.BigDecimal;

public record PostDataResponse(String postId, Integer wordCount, BigDecimal calculatedValue) {

}
