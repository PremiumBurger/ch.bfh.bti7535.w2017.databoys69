package ch.bfh.bti7535.w2017.databoys69;

import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysBaseLine;
import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysBaseLineWeighted;
import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysNaiveBayes;
import ch.bfh.bti7535.w2017.databoys69.algorithm.OpenNLPNaiveBayes;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        // load resource file
        ClassLoader classLoader = new App().getClass().getClassLoader();
        File file = new File(classLoader.getResource("movie-reviews.arff").getFile());

        // launch baseline
        DataboysBaseLine baseLine = new DataboysBaseLine(file);
        baseLine.run();

        // launch weighted baseline
        DataboysBaseLineWeighted baseLineWeighted = new DataboysBaseLineWeighted(file);
        baseLineWeighted.run();

        // launch open nlp
        OpenNLPNaiveBayes openNLPNaiveBayes = new OpenNLPNaiveBayes();
        openNLPNaiveBayes.train();
        openNLPNaiveBayes.test(file);

        // launch Naive Bayes
        DataboysNaiveBayes bayes = new DataboysNaiveBayes(file);
        bayes.run();
    }
}
