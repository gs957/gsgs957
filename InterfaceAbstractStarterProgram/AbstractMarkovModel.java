
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
	myRandom = new Random(seed);//seed
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected ArrayList<String> getFollows(String key){
	    ArrayList<String> list = new ArrayList<String>();
	    for(int i = 0; i<myText.length()-1;i++){
	        i = myText.indexOf(key,i);
	        if(i == -1 || i>=myText.length()-key.length())
	           break;
	        list.add(myText.substring(i+key.length(),i+key.length()+1));
	    }
	    return list;
	   }
	
    abstract public String getRandomText(int numChars);
    
    abstract public String getName();
}
