import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String tempString = new String();
        for(int i = whichSlice;i<message.length();i = i+totalSlices){
            tempString=tempString+message.charAt(i);
            
        }
        return tempString;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0;i<klength;i++){
            CaesarCracker cb = new CaesarCracker(mostCommon);
            String tempString = sliceString(encrypted,i,klength);
            key[i]=cb.getKey(tempString);
        }
        return key;
    }

    public void breakVigenereTest1 () {
        FileResource resource = new FileResource();
        String message = resource.asString();
        int keyLength = 4;
        char mostCommon = 'e';
        int[]key=tryKeyLength(message,keyLength,mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted=vc.decrypt(message);
        System.out.println(key[0]+","+key[1]+","+key[2]+","+key[3]);
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String s: fr.lines()){
            s=s.toLowerCase();
            dictionary.add(s);
        }
        return dictionary;
    }
    
    private int countWords(String message,HashSet<String> dictionary){
        int validNum = 0;
        for(String s : message.split("\\W+")){
            s = s.toLowerCase();
            if(dictionary.contains(s)){
                validNum++;
            }
        }
        return validNum;
    }
    
    public int breakForLanguage(String encrypted,HashSet<String> dictionary,char mostCommon){
        int maxValidNum = 0;
        int trueKey = 0;
        for(int i=1;i<=100;i++){
             VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted,i,mostCommon));
             //String decrypted=vc.decrypt(encrypted);
             int currNum=countWords(vc.decrypt(encrypted),dictionary);
             //System.out.println(i+"="currNum);
             //if(i==38)System.out.println(currNum);
             if(maxValidNum<currNum){
                    maxValidNum=currNum;
                    trueKey = i;
             }
        }
        System.out.println("maxValidNum="+maxValidNum);
        return trueKey;
    }
    
    public void breakVigenereTest2 () {
        FileResource fr1 =new FileResource();
        FileResource fr2 =new FileResource("dictionaries\\English");
        String message = fr1.asString();
        HashSet<String> dictionary = readDictionary(fr2);
        char mostCommon = 'e';
        int keyLength=breakForLanguage(message,dictionary,mostCommon);
        int[]key=tryKeyLength(message,keyLength,mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted=vc.decrypt(message);
        /*
        for(int i=0;i<key.length;i++){
            System.out.print(key[i]+",");
        }*/
        System.out.println("keyLength="+key.length);
        System.out.println(decrypted);
    }
    
    private char mostCommonCharln(HashSet<String> dictionary){
        HashMap<Character,Integer> cMap =new HashMap<Character,Integer>();
        for(String s : dictionary){
            for(int i=0;i<s.length();i++){
                char c = s.charAt(i);
                if(!cMap.containsKey(c)){
                    cMap.put(c,1);
                }
                else{
                    cMap.put(c,cMap.get(c)+1);
                }
            }
        }
        char commonChar=' ';
        int numberChar=0;
        for(char c :cMap.keySet()){
            int tempNum=cMap.get(c);
            if(tempNum>numberChar){
                commonChar = c;
                numberChar = tempNum;
            }
        }
        return commonChar;
    }
    
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        for(String lang : languages.keySet()){
            HashSet<String> partDictionary = languages.get(lang);
            System.out.println(lang+":");
            char commonChar = mostCommonCharln(partDictionary);
            //int keyLength=breakForLanguage(encrypted,partDictionary,commonChar);
            int[] keys=tryKeyLength(encrypted,breakForLanguage(encrypted,partDictionary,commonChar),commonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted=vc.decrypt(encrypted);
                    /*
                     * for(int i=0;i<key.length;i++){
                     *     System.out.print(key[i]+",");
                     *  }*/
            System.out.println("keyLength="+keys.length);
            System.out.println(decrypted);
        }
    }
    
    public HashMap<String,HashSet<String>> readMultiplelDictionary (){
        String[] languages = {"Danish","Dutch","English","French","German","Italian","Portuguese",
            "Spanish"};
        HashMap<String,HashSet<String>> langMap = new HashMap<String,HashSet<String>>();
        for(int i=0;i<languages.length;i++){
            FileResource resource = new FileResource("dictionaries/" + languages[i]);
            langMap.put(languages[i],readDictionary(resource));
        }
        return langMap;
    }
    
    public void breakVigenereTest3(){
        FileResource fr =new FileResource();
        String message = fr.asString();
        HashMap<String,HashSet<String>> langMap = readMultiplelDictionary();
        breakForAllLangs(message,langMap);
    }
}
