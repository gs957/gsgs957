
/**
 * 在这里给出对类 PhraseFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String Where,String Phrase){
        where = Where;
        phrase = Phrase;
    }
    public  boolean satisfies(QuakeEntry qe){
        return (where=="start"&&qe.getInfo().startsWith(phrase))||
               (where=="end"&&qe.getInfo().endsWith(phrase))||
               (where=="any"&&qe.getInfo().indexOf(phrase)!=-1);
    }
}
