package ch.bfh.bti7535.w2017.databoys69.filters;

import weka.core.Instances;
import weka.core.stemmers.SnowballStemmer;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class DataboysFilterFactory {

    public static StringToWordVector buildStopwordFilter(Instances train) throws Exception {
        StringToWordVector vector = new StringToWordVector();
        vector.setStopwordsHandler(new DataboysStopWordHandler());
        vector.setInputFormat(train);
        return vector;
    }

    public static StringToWordVector buildSnowballStemFilter(Instances train) throws Exception {
        StringToWordVector vector = new StringToWordVector();
        vector.setStemmer(new SnowballStemmer());
        vector.setInputFormat(train);
        return vector;
    }
}
