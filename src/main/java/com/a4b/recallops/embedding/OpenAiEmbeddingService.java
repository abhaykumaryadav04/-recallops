package com.a4b.recallops.embedding;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAiEmbeddingService implements EmbeddingService {
     @Autowired
      private EmbeddingModel embeddingModel;
    @Override
    public float[] generateEmbedding(String text) {
     return embeddingModel.embed(text);
    }



}
