
/**
 * 在这里给出对类 MarkovModel 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MarkovModel extends AbstractMarkovModel {
	private int N;
	public MarkovModel(int length) {
		myRandom = new Random();
		N = length;
	}
	

	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
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
	public String getName(){
	    return "MarkovModel of order "+N;
	}
}
