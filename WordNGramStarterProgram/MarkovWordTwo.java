
/**
 * 在这里给出对类 MarkovWordTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String[] key = new String[2];
        key[0] = myText[index];
        key[1] = myText[index+1];
        sb.append(key[0]);
        sb.append(" ");
        sb.append(key[1]);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key[0] = key[1];
            key[1] = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target[], int start){
        for(int i = start ; i< words.length ; i++){
            if(words[i].equals(target[0])&&words[i+1].equals(target[1])&&i<words.length-2){
                return i+2;
            }
        }
        return -1;
    }
    private ArrayList<String> getFollows(String[] key) {
        ArrayList<String> follows = new ArrayList<String>();
        for(int i =0; i<myText.length;){
            i = indexOf(myText,key,i);
            if(i == -1)
                break;
            follows.add(myText[i]);
            i=i-1;
        }
        return follows;
    }

}
