package ch.bfh.bti7535.w2017.databoys69.filters;

import weka.core.*;
import weka.filters.SimpleBatchFilter;

import java.util.Enumeration;

public class GoodBadWordFilter extends SimpleBatchFilter {

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
        result.insertAttributeAt(new Attribute("test"), result.numAttributes());
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
            result.add(instance);
        }
        return result;
    }
}
