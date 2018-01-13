package ch.bfh.bti7535.w2017.databoys69.filters;

import ch.bfh.bti7535.w2017.databoys69.App;
import weka.core.*;
import weka.filters.SimpleBatchFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class DataboysSentimentLexiconFilter extends SimpleBatchFilter {

    private List<String> sentimentWords = new ArrayList<>();

    public DataboysSentimentLexiconFilter() throws FileNotFoundException {
        ClassLoader classLoader = new App().getClass().getClassLoader();
        File powWordsFile = new File(classLoader.getResource("positiveWords.txt").getFile());
        Scanner s = new Scanner(powWordsFile);
        while (s.hasNext()){
            sentimentWords.add(s.next());
        }
        s.close();

        File negWordsFile = new File(classLoader.getResource("negativeWords.txt").getFile());
        s = new Scanner(negWordsFile);
        while (s.hasNext()){
            sentimentWords.add(s.next());
        }
        s.close();
    }

    @Override
    public Capabilities getCapabilities() {
        Capabilities result = super.getCapabilities();
        result.enableAllAttributes();
        result.enableAllClasses();
        result.enable(Capabilities.Capability.NO_CLASS);  //// filter doesn't need class to be set//
        return result;
    }

    @Override
    public String globalInfo() {
        return "A good and bad word filter";
    }

    @Override
    protected Instances determineOutputFormat(Instances inputFormat) throws Exception {
        Instances result = new Instances(inputFormat, 0);
        result.insertAttributeAt(new Attribute("SENTIMENT_WEIGHT"), result.numAttributes());
        return result;
    }

    @Override
    protected Instances process(Instances instances) throws Exception {
        Instances result = new Instances(determineOutputFormat(instances), 0);
        Enumeration<Instance> instanceEnumeration = instances.enumerateInstances();

        while(instanceEnumeration.hasMoreElements()) {
            Instance instance = instanceEnumeration.nextElement();

            for (int i = 1; i < instance.numValues(); i++) {
                int attrIndex = instance.index(i);
                Attribute attribute = instances.attribute(attrIndex);
                String name = attribute.name();
                double value = instance.value(attrIndex);
                System.out.println("attribute: " + name + " value: " + value);
            }

            result.add(new DenseInstance(instance));

        }
        return result;
    }
}