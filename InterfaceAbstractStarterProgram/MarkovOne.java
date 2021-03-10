
/**
 * 在这里给出对类 MarkovOne 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MarkovOne extends AbstractMarkovModel{
	
	public MarkovOne() {
		myRandom = new Random();
	}
		
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
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
	
	public String getName(){
	    return "MarkovModel of order 1";
	}
}
