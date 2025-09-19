package com.jonasesteves.posts.textprocessor.api.domain.service;

import com.jonasesteves.posts.textprocessor.api.domain.model.PostData;
import com.jonasesteves.posts.textprocessor.api.domain.model.PostDataResponse;

import java.math.BigDecimal;

public class PostProcessorService {

    private static final BigDecimal PRICE_PER_WORD = new BigDecimal("0.01");

    public PostDataResponse calculateValue(PostData postData) {

        // TODO: tratar erro aqui, possível entrada de valor nulo.
        String[] words = postData.getPostBody().trim().split("\\s+");
        int wordCount = words.length;

        BigDecimal result = PRICE_PER_WORD.multiply(BigDecimal.valueOf(wordCount));

        return new PostDataResponse(postData.getPostId(), wordCount, result);
    }
}
