package ch.bfh.bti7535.w2017.databoys69.evaluation;

import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysNaiveBayes;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

/**
 * @author databoys69
 * Evaluates the result of our @{@link DataboysNaiveBayes} algorithm.
 * It uses k-folding to create multiple validation sets.
 */
public class DataboysEvaluator {

    private int folds;

    /**
     * Constructor to create a new evaluator
     * @param folds number of folds (best = 10)
     */
    public DataboysEvaluator(int folds) {
        this.folds = folds;
    }

    /**
     * K-Folds the data and cross validates the given data set.
     * Prints out the result into the console log.
     * @param classifier NaiveBayes
     * @param dataSet data (movie reviews)
     */
    public void evaluate(Classifier classifier, Instances dataSet) throws Exception {
        Evaluation eval = new Evaluation(dataSet);
        eval.crossValidateModel(classifier, dataSet, folds, new Random());
        eval.evaluateModel(classifier, dataSet);

        System.out.println("*** NAIVE BAYES (WEKA) ALGORITHM ***");
        System.out.println();
        System.out.println("Summary: " + eval.toSummaryString());
        System.out.println();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        System.out.println();
    }
}
