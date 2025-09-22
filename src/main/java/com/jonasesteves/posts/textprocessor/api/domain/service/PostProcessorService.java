package com.jonasesteves.posts.textprocessor.api.domain.service;

import com.jonasesteves.posts.textprocessor.api.domain.model.PostData;
import com.jonasesteves.posts.textprocessor.api.domain.model.PostDataResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class PostProcessorService {

    private static final BigDecimal PRICE_PER_WORD = new BigDecimal("0.10");

    public PostDataResponse calculateValue(PostData postData) {
        Objects.requireNonNull(postData);

        String[] words = postData.getPostBody().trim().split("\\s+");
        int wordCount = words.length;

        BigDecimal result = PRICE_PER_WORD.multiply(BigDecimal.valueOf(wordCount));

        return new PostDataResponse(postData.getPostId(), wordCount, result);
    }
}
