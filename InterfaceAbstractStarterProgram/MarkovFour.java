
/**
 * 在这里给出对类 MarkovFour 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MarkovFour extends AbstractMarkovModel{
        
	public MarkovFour() {
		myRandom = new Random();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-4);
		String word = myText.substring(index,index+4);
		//String word= "n";
		sb.append(word);
		for(int k=0; k < numChars-4; k++){
		    ArrayList<String> list = getFollows(word);
		    if(list.size() ==0)
		      break;
		    String tempWord = list.get(myRandom.nextInt(list.size()));
		    sb.append(tempWord);
		    word = word.substring(1)+tempWord;
		}
		
		return sb.toString();
	}
	public String getName(){
	    return "MarkovModel of order 4";
	}
}
