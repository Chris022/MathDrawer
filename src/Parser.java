import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parser {

    public static Symbol parseString(String s) throws Exception {

        SymbolChain symChain = new SymbolChain();

        //first remove all spaces
        s = s.replace(" ", "");

        //convert to Character ArrayList
        ArrayList<Character> string = new ArrayList<Character>();
        Character[] chars = s.chars()
                            .mapToObj(ch -> (char) ch)
                            .toArray(Character[]::new);
        Collections.addAll(string, chars);

        while(string.size()>0){
            Character c = string.remove(0);
            if(c == '('){
                String arg = parseBracket(string);
                symChain.addSymbol(new Bracket(parseString(arg)));      
            }else if(c != '$'){
                symChain.addSymbol(new AsciiSymbol(c));
            }else{
                Character command = string.remove(0);
                ArrayList<Symbol> argSyms = new ArrayList<Symbol>();
                while(string.size()>0&&string.get(0) == '{'){
                    argSyms.add(parseString(parseArg(string)));
                }
                if(command=='f'){
                    symChain.addSymbol(new Fracture(argSyms));
                }
                if(command=='i'){
                    symChain.addSymbol(new Integral(argSyms));
                }
                if(command=='p'){
                    symChain.addSymbol(new Power(argSyms));
                }

            }
        }
        

        return symChain;
    }

    public static String parseBracket(ArrayList<Character> string) throws Exception {
        String inside ="";
        char c;
        int depth = 1;
        while (depth != 0){
            if(string.size() == 0){
                throw new Exception();
            }
            c = string.remove(0);
            if(c == '('){
                depth +=1;
            }
            if(c == ')'){
                depth -=1;
            }
            if(depth != 0){
                inside +=c;
            }
        }
        return inside;
    }

    public static String parseArg(ArrayList<Character> string) throws Exception {
        String inside ="";
        if(string.remove(0) != '{'){
            throw new Exception();
        }
        char c;
        int depth = 1;
        while (depth != 0){
            if(string.size() == 0){
                throw new Exception();
            }
            c = string.remove(0);
            if(c == '{'){
                depth +=1;
            }
            if(c == '}'){
                depth -=1;
            }
            if(depth != 0){
                inside +=c;
            }
        }
        return inside;
    }
    
}