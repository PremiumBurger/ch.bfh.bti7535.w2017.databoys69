package ch.bfh.bti7535.w2017.databoys69.filters;

import weka.core.Instances;
import weka.core.stemmers.SnowballStemmer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class DataboysFilterFactory {

    public static Filter buildStopwordFilter(Instances train) throws Exception {
        StringToWordVector vector = new StringToWordVector();
        vector.setStopwordsHandler(new DataboysStopWordHandler());
        vector.setInputFormat(train);
        return vector;
    }

    public static Filter buildSnowballStemFilter(Instances train) throws Exception {
        StringToWordVector vector = new StringToWordVector();
        vector.setStemmer(new SnowballStemmer());
        vector.setInputFormat(train);
        return vector;
    }

    public static Filter buildGoodBadWordFilter(Instances train) throws Exception {
        GoodBadWordFilter filter = new GoodBadWordFilter();
        filter.setInputFormat(train);
        return filter;
    }
}
