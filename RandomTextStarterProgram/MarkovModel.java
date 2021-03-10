
/**
 * 在这里给出对类 MarkovModel 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MarkovModel {
        private String myText;
	private Random myRandom;
	private int N;
	public MarkovModel(int length) {
		myRandom = new Random();
		N = length;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);//seed
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public ArrayList<String> getFollows(String key){
	    ArrayList<String> list = new ArrayList<String>();
	    for(int i = 0; i<myText.length()-1;i++){
	        i = myText.indexOf(key,i);
	        if(i == -1 || i>=myText.length()-key.length())
	           break;
	        list.add(myText.substring(i+key.length(),i+key.length()+1));
	    }
	    return list;
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		setRandom(365);
		int index = myRandom.nextInt(myText.length()-N);
		String word = myText.substring(index,index+N);
		//String word= "n";
		sb.append(word);
		for(int k=0; k < numChars-N; k++){
		    ArrayList<String> list = getFollows(word);
		    if(list.size() ==0)
		      break;
		    String tempWord = list.get(myRandom.nextInt(list.size()));
		    sb.append(tempWord);
		    word = word.substring(1)+tempWord;
		}
		
		return sb.toString();
	}
}
