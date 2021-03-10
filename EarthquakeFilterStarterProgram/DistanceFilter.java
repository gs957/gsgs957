
/**
 * 在这里给出对类 DistanceFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DistanceFilter implements Filter{
    private Location loc;
    private double max;
    public DistanceFilter(Location location,double maxDistance){
        max = maxDistance;
        loc = location;
    }
    public  boolean satisfies(QuakeEntry qe){
        return loc.distanceTo(qe.getLocation())<max;
    }
}
