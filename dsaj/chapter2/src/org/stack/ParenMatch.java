package org.stack;

public class ParenMatch {
    private String str;

    public ParenMatch(String str){
        this.str = str;
    }

    public boolean isMatch(){
        int n = str.length();
        Stack_Array stack_array = new Stack_Array(n);
        for (int i = 0; i < n; i++){
            char ch = str.charAt(i);
            if (isLeft(ch))
                stack_array.push(ch);
            if (isRight(ch)){
                if (stack_array.isEmpty())
                    return false;
                char re_char = matchChar(ch);
                char pop_char = (char) stack_array.pop();
                if (re_char != pop_char)
                    return false;
            }
        }
        if (stack_array.isEmpty())
            return true;
        return false;
    }

    public boolean isLeft(char ch){
        if (ch == '{' || ch == '[' || ch == '(')
            return true;
        return false;
    }

    public boolean isRight(char ch){
        if (ch == '}' || ch == ']' || ch == ')')
            return true;
        return false;
    }

    public char matchChar(char ch){
        char re_char = ch;
        switch (ch)
        {
            case ')':
                re_char = '(';
                break;
            case '}':
                re_char = '{';
                break;
            case ']':
                re_char = '[';
                break;
        }
        return re_char;
    }

    public static void main(String[] args) {
        String str = "{[(2+8)*(6+4)]*[(5-6)*(7-8)]}";
        ParenMatch parenMatch = new ParenMatch(str);
        if (parenMatch.isMatch())
            System.out.println("匹配成功！");
    }
}
