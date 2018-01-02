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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        File file = new File("/home/cevo/work/databoys69/sentimentanalysis/src/main/resources/resources.arff");
        DataboysNaiveBayes bayes = new DataboysNaiveBayes(file);
        bayes.run();
    }

    /*
    public static void main( String[] args ) throws IOException {

        ClassLoader clsLoader = new App().getClass().getClassLoader();
        File trainingData = new File(clsLoader.getResource("trainingData.txt").getFile());

        OpenNLPNaiveBayes classifier = new OpenNLPNaiveBayes();
        classifier.train(trainingData);

        classifier.test("pos", content);

        System.out.println( "Hello World!" );
    }


    static String content = "films adapted from comic books have had plenty of success , whether they're about superheroes ( batman , superman , spawn ) , or geared toward kids ( casper ) or the arthouse crowd ( ghost world ) , but there's never really been a comic book like from hell before . \n" +
            "for starters , it was created by alan moore ( and eddie campbell ) , who brought the medium to a whole new level in the mid '80s with a 12-part series called the watchmen . \n" +
            "to say moore and campbell thoroughly researched the subject of jack the ripper would be like saying michael jackson is starting to look a little odd . \n" +
            "the book ( or \" graphic novel , \" if you will ) is over 500 pages long and includes nearly 30 more that consist of nothing but footnotes . \n" +
            "in other words , don't dismiss this film because of its source . \n" +
            "if you can get past the whole comic book thing , you might find another stumbling block in from hell's directors , albert and allen hughes . \n" +
            "getting the hughes brothers to direct this seems almost as ludicrous as casting carrot top in , well , anything , but riddle me this : who better to direct a film that's set in the ghetto and features really violent street crime than the mad geniuses behind menace ii society ? \n" +
            "the ghetto in question is , of course , whitechapel in 1888 london's east end . \n" +
            "it's a filthy , sooty place where the whores ( called \" unfortunates \" ) are starting to get a little nervous about this mysterious psychopath who has been carving through their profession with surgical precision . \n" +
            "when the first stiff turns up , copper peter godley ( robbie coltrane , the world is not enough ) calls in inspector frederick abberline ( johnny depp , blow ) to crack the case . \n" +
            "abberline , a widower , has prophetic dreams he unsuccessfully tries to quell with copious amounts of absinthe and opium . \n" +
            "upon arriving in whitechapel , he befriends an unfortunate named mary kelly ( heather graham , say it isn't so ) and proceeds to investigate the horribly gruesome crimes that even the police surgeon can't stomach . \n" +
            "i don't think anyone needs to be briefed on jack the ripper , so i won't go into the particulars here , other than to say moore and campbell have a unique and interesting theory about both the identity of the killer and the reasons he chooses to slay . \n" +
            "in the comic , they don't bother cloaking the identity of the ripper , but screenwriters terry hayes ( vertical limit ) and rafael yglesias ( les mis ? rables ) do a good job of keeping him hidden from viewers until the very end . \n" +
            "it's funny to watch the locals blindly point the finger of blame at jews and indians because , after all , an englishman could never be capable of committing such ghastly acts . \n" +
            "and from hell's ending had me whistling the stonecutters song from the simpsons for days ( \" who holds back the electric car/who made steve guttenberg a star ? \" ) . \n" +
            "don't worry - it'll all make sense when you see it . \n" +
            "now onto from hell's appearance : it's certainly dark and bleak enough , and it's surprising to see how much more it looks like a tim burton film than planet of the apes did ( at times , it seems like sleepy hollow 2 ) . \n" +
            "the print i saw wasn't completely finished ( both color and music had not been finalized , so no comments about marilyn manson ) , but cinematographer peter deming ( don't say a word ) ably captures the dreariness of victorian-era london and helped make the flashy killing scenes remind me of the crazy flashbacks in twin peaks , even though the violence in the film pales in comparison to that in the black-and-white comic . \n" +
            "oscar winner martin childs' ( shakespeare in love ) production design turns the original prague surroundings into one creepy place . \n" +
            "even the acting in from hell is solid , with the dreamy depp turning in a typically strong performance and deftly handling a british accent . \n" +
            "ians holm ( joe gould's secret ) and richardson ( 102 dalmatians ) log in great supporting roles , but the big surprise here is graham . \n" +
            "i cringed the first time she opened her mouth , imagining her attempt at an irish accent , but it actually wasn't half bad . \n" +
            "the film , however , is all good . \n" +
            "2 : 00 - r for strong violence/gore , sexuality , language and drug content \n";
            */
}
