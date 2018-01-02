package ch.bfh.bti7535.w2017.databoys69.filters;

import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.SimpleStreamFilter;

public class GoodBadWordFilter extends SimpleStreamFilter {

    @Override
    public String globalInfo() {
        return "A good and bad word filter";
    }

    @Override
    protected Instances determineOutputFormat(Instances inputFormat) throws Exception {
        return null;
    }

    @Override
    protected Instance process(Instance instance) throws Exception {
        return null;
    }

    @Override
    public Capabilities getCapabilities() {
        Capabilities result = super.getCapabilities();
        result.enableAllAttributes();
        result.enableAllClasses();
        result.enable(Capabilities.Capability.NO_CLASS);  //// filter doesn't need class to be set//
        return result;
    }
}
