
/**
 * 在这里给出对类 LargestQuakes 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> answer = getLargest(list,50);
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        System.out.println(answer.size());
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int index = 0;
        double magnitude = 0;
        for(int i=0; i<data.size();i++){
            QuakeEntry qe = data.get(i);
            if(qe.getMagnitude()>magnitude){
                index=i;
                magnitude = qe.getMagnitude();
            }
        }
        //System.out.println(data.get(index));
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = quakeData;
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int i=0; i<howMany;i++){
            int temp=indexOfLargest(copy);
            ret.add(copy.get(temp));
            copy.remove(temp);
        }
        return ret;
    }
}
