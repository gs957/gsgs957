
/**
 * 在这里给出对类 TitleLastAndMagnitudeComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        String[] list1 = qe1.getInfo().split("\\s");
        String[] list2 = qe2.getInfo().split("\\s");
        String qe1word = list1[list1.length-1];
        String qe2word = list2[list2.length-1];
        if(qe1word.equals(qe2word)){
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        else {
            return qe1word.compareTo(qe2word);
        }
    }
}
