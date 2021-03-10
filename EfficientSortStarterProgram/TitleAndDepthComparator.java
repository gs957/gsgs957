
/**
 * 在这里给出对类 TitleAndDepthComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        if(qe1.getInfo().equals(qe2.getInfo())){
            return Double.compare(qe1.getDepth(), qe2.getDepth());
        }
        else{
            return qe1.getInfo().compareTo(qe2.getInfo());
        }
    }
    
}
