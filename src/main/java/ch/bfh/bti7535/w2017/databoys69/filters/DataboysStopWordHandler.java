package ch.bfh.bti7535.w2017.databoys69.filters;

import weka.core.Stopwords;
import weka.core.stopwords.StopwordsHandler;

public class DataboysStopWordHandler implements StopwordsHandler {

    @Override
    public boolean isStopword(String word) {
        return Stopwords.isStopword(word);
    }

}
