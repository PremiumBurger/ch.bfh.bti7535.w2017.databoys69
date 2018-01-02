package ch.bfh.bti7535.w2017.databoys69;

import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysNaiveBayes;

import java.io.File;

import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysNaiveBayes;
import ch.bfh.bti7535.w2017.databoys69.algorithm.OpenNLPNaiveBayes;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        File file = new File("/home/cevo/work/databoys69/sentimentanalysis/src/main/resources/resources.arff");
        DataboysNaiveBayes bayes = new DataboysNaiveBayes(file);
        bayes.run();

        // ClassLoader clsLoader = new App().getClass().getClassLoader();
        // File trainingData = new File(clsLoader.getResource("trainingData.txt").getFile());
        // OpenNLPNaiveBayes.doStuff(trainingData, trainingData);
    }
}
