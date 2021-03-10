
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov.getName());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runModel(IMarkovModel markov, String text, int size,int seed) {
        markov.setTraining(text);
        
        System.out.println("running with " + markov.getName());
        
        for(int k=0; k < 3; k++){
            markov.setRandom(seed);
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runEfficientMarkov(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        st = st.replace('\n', ' ');
        int size = 500;
        
        EfficientMarkovModel mz = new EfficientMarkovModel(6);
        runModel(mz, st, size,792);
        //mz.printHashMapInfo();
    }
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 500;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,1024);
        
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,365);
        
        MarkovModel mThree = new MarkovModel(7);
        runModel(mThree, st, size,953);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,715);
        
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
