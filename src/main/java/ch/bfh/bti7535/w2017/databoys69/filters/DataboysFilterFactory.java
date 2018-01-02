package ch.bfh.bti7535.w2017.databoys69.filters;

import weka.attributeSelection.*;
import weka.core.Instances;
import weka.core.stemmers.NullStemmer;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class DataboysFilterFactory {

    public static Filter buildStringToVectorFilter(Instances train) throws Exception {
        StringToWordVector vector = new StringToWordVector();
        vector.setStopwordsHandler(new DataboysStopWordHandler());
        vector.setStemmer(new NullStemmer());
        vector.setInputFormat(train);
        vector.setAttributeIndices("first");
        return vector;
    }

    public static Filter buildAttributeSelectionFilter(Instances train) throws Exception {
        AttributeSelection selectionFilter = new AttributeSelection();
        selectionFilter.setInputFormat(train);
        selectionFilter.setEvaluator(new ClassifierAttributeEval());
        selectionFilter.setSearch(new Ranker());
        return selectionFilter;
    }
}
