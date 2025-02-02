
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    public int getLargestDepth(ArrayList<QuakeEntry>quakeData,int index){
        QuakeEntry tempQuake1 = quakeData.get(index);
        for(int i = index; i < quakeData.size(); i++){
            QuakeEntry tempQuake2 = quakeData.get(i);
            if(tempQuake2.getDepth()>tempQuake1.getDepth()){
                tempQuake1=tempQuake2;
                index = i;
            }
        }
        return index;
    }
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i=0; i< 50; i++) {//111
            int minIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=0;i<quakes.size()-1;i++){
            if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude())
                return false;
        }
        return true;
    }
    public int sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for(int i=0;i<in.size()-1;i++){
            if(checkInSortedOrder(in)){
                return i;
            }
            for(int j=0;j<in.size()-1-i;j++){
                if(in.get(j).getMagnitude()>in.get(j+1).getMagnitude()){
                    QuakeEntry temp = in.get(j);
                    in.set(j,in.get(j+1));
                    in.set(j+1,temp);
                }
            }
        }
        return in.size()-1;
    }
    
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)){
                return i;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        return in.size();
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByLargestDepth(list);
        //sortByMagnitude(list);
        int i=sortByMagnitudeWithCheck(list);
        //int i = sortByMagnitudeWithBubbleSortWithCheck(list);
        /*
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } */
        System.out.println(i);
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
