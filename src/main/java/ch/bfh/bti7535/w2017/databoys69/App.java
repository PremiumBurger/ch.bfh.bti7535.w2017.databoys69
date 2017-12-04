package ch.bfh.bti7535.w2017.databoys69;

import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysNaiveBayes;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        File file = new File("/home/cevo/work/databoys69/sentimentanalysis/src/main/resources/resources.arff");
        DataboysNaiveBayes bayes = new DataboysNaiveBayes(file, file);
        bayes.run();
    }
}
