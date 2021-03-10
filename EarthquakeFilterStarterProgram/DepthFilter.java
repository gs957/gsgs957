
/**
 * 在这里给出对类 DepthFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DepthFilter implements Filter{
    private double min;
    private double max;
    public DepthFilter(double magMin,double magMax){
        min = magMin;
        max = magMax;
    }
    public  boolean satisfies(QuakeEntry qe){
        return qe.getDepth()>min&&qe.getDepth()<max;
    }
}
