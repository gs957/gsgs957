
/**
 * 在这里给出对类 MagnitudeFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MagnitudeFilter implements Filter{
    private double min;
    private double max;
    public MagnitudeFilter(double magMin,double magMax){
        min = magMin;
        max = magMax;
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= min&& qe.getMagnitude()<=max;
    }
}
