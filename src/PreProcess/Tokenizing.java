package PreProcess;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class Tokenizing {
    private StringTokenizer stringToken;
    private LinkedList<String> list;
    private String numerical[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private char quote = '"';
    private String quotes = ""+quote;
    private String character[] = {quotes, "!", "?", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "+", "=", "_", "<", ">", ".", ",", ";", "/", ":","[","]","'","{","}",""," ",""};
    
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public LinkedList<String> getTokenizingResult(String input) {
        list = new LinkedList<String>();
        list.clear();
        stringToken = new StringTokenizer(input);
        while (stringToken.hasMoreElements()) {
            String token = stringToken.nextToken();
            boolean isNumber;

            for (String i : character) {
                token = token.replace(i, "");
            }
            token = token.replace("�", "");
            isNumber = isNumeric(token);
            if(!isNumber){
                list.add(token);
            }
        }
        return list;
    }
}