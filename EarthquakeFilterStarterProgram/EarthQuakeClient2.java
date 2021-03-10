import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location city = new Location(55.7308, 9.1153);     
        //Filter f1 = new MinMagFilter(4.0);
        Filter f2 = new DistanceFilter(city,3000000);
        Filter f3 = new MagnitudeFilter(0,5.0);
        Filter f4 = new PhraseFilter("any","e");
        Filter f5 = new DepthFilter(-4000,-2000);
        MatchAllFilter f = new MatchAllFilter();
        //f.addFilter(f2);
        //f.addFilter(f3);
        //f.addFilter(f4);
        f.addFilter(f5);
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        /*
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        */
        System.out.println(m7.size()+" are found");
        //MatchAllFilter f12 = new MatchAllFilter();
        //f12.addFilter
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
