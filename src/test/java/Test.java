
public class Test {

    public static void main(String[] args) {

        System.out.println(getCount("aaabbbbbccdeeffffaabbccdde"));
    }

    public static String getCount(String s){

        String output="";
        int count=1;
        for (int i=0;i<s.length();i++){

            if (i+1==s.length()){
                output=output+s.charAt(i)+count;
                break;
            }
            if (s.charAt(i)==s.charAt(i+1)){
                count++;
                continue;
            }
            output=output+s.charAt(i)+count;
            count=1;
        }
        return output;
    }



}
