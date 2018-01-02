package ch.bfh.bti7535.w2017.databoys69.evaluation;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.util.Random;

public class DataboysEvaluator {

    private int folds;

    public DataboysEvaluator(int folds) {
        this.folds = folds;
    }

    public void evaluate(Classifier classifier, Instances dataSet) throws Exception {
        Evaluation eval = new Evaluation(dataSet);

        // FilteredClassifier filteredClassifier = new FilteredClassifier();
        // filteredClassifier.setClassifier(classifier);
        // filteredClassifier.setFilter(new StringToWordVector());

        eval.crossValidateModel(classifier, dataSet, folds, new Random(1));
        eval.evaluateModel(classifier, dataSet);
        System.out.println("Summary: " + eval.toSummaryString());
    }
}
