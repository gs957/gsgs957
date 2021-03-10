
/**
 * 在这里给出对类 EfficientMarkovModel 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel{
        private int N;
        private HashMap<String,ArrayList<String>> dic;
	public EfficientMarkovModel(int length) {
		myRandom = new Random();
		N = length;
		dic = new HashMap<String,ArrayList<String>>();
	}
	
	public void buildMap(){
	    for(int i = 0; i < myText.length()-N;i++){
	        String tempString = myText.substring(i,i+N);
	        if(!dic.containsKey(tempString)){
	            ArrayList<String> list = new ArrayList<String>();
	            list.add(myText.substring(i+N,i+N+1));
	            dic.put(tempString,list);
	           }
	        else{
	            ArrayList<String> list = dic.get(tempString);
	            list.add(myText.substring(i+N,i+N+1));
	            dic.put(tempString,list);
	           }
	       }
	}
	
	@Override
	public ArrayList<String> getFollows(String key){
	    return dic.get(key);
	   }
	
	public void printHashMapInfo(){
	    String tempString = new String();
	    ArrayList<String> tempList = new ArrayList<String>();
	    for(String s : dic.keySet()){
	        ArrayList<String> list = dic.get(s);   
	        if(tempString ==null){
	            tempString = s;
	            tempList = list;
	        }
	        else{
	            if(tempList.size()<list.size()){
	                tempString = s;
	                tempList = list;
	            }
	        }
	        //System.out.println(s+":"+list);
	    }
	    System.out.println("HashMap size is "+dic.size()+"\n"+
	                       "Large-size key is \""+ tempString +"\" size is "+tempList.size());
	    int aim = tempList.size();
	    for(String s: dic.keySet()){
	        if(dic.get(s).size()==aim){
	            System.out.println(s+";");
	           }
	       }
	}
	   
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		if(dic.size()==0){
		  buildMap();
                  printHashMapInfo();    
                }
                //printHashMapInfo();   
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-N);
		String word = myText.substring(index,index+N);
		//String word= "n";
		sb.append(word);
		for(int k=0; k < numChars-N; k++){
		    ArrayList<String> list = getFollows(word);
		    if(!dic.containsKey(word)){
		        System.out.println("plus 1 key!");
		        break;
		  }
		    String tempWord = list.get(myRandom.nextInt(list.size()));
		    sb.append(tempWord);
		    word = word.substring(1)+tempWord;
		}
		
		return sb.toString();
	}
	public String getName(){
	    return "EfficientMarkovModel of order "+N;
	}
}
