
/**
 * 在这里给出对类 MarkovOne 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MarkovOne {
        private String myText;
	private Random myRandom;
	
	public MarkovOne() {
		myRandom = new Random();
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
		setRandom(273);
		int index = myRandom.nextInt(myText.length()-1);
		String word = myText.substring(index,index+1);
		//String word= "n";
		sb.append(word);
		for(int k=0; k < numChars-1; k++){
		    ArrayList<String> list = getFollows(word);
		    if(list.size() ==0)
		      break;
		    word = list.get(myRandom.nextInt(list.size()));
		    sb.append(word);
		}
		
		return sb.toString();
	}
}
