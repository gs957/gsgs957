
/**
 * 在这里给出对类 MatchAllFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filterList;
    public MatchAllFilter(){
         filterList = new ArrayList<Filter>();   
    }
    
    public void addFilter(Filter f){
        filterList.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filterList){
            if(!f.satisfies(qe))
                return false;
        }
        return true;
    }
}
