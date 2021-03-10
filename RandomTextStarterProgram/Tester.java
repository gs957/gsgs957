
/**
 * 在这里给出对类 Tester 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import edu.duke.*;
public class Tester {
    public void testGetSmallFollows(){
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        for(int i = 0; i<st.length();i++){
            String key = st.substring(i,i+1);
            ArrayList<String> list = markov.getFollows(key);
            System.out.println(key + ": " +list.size()+" " +list);
        }
        String key =  "st";
        ArrayList<String> list = markov.getFollows(key);
        System.out.println(key + ": " +list.size()+" " +list);
    }   
    public void testGetLargeFollows(){
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	MarkovOne markov = new MarkovOne();
	markov.setTraining(st);
	String key =  "o";
        ArrayList<String> list = markov.getFollows(key);
        System.out.println(key + ": " +list.size());
        key =  "he";
        list = markov.getFollows(key);
        System.out.println(key + ": " +list.size());
    }
}
