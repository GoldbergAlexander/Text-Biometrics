/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/22/15.
 */

import java.io.*;

public class TextModel {
    private static String filePath = "src/main/resources/StockText.txt";
    private static FileInputStream fileInputStream;
    private static int r;

    public static char[] readFile(){
        int r;
        char[] array = new char[]{0};
        try {
            fileInputStream = new FileInputStream(filePath);
            while ((r = fileInputStream.read()) != -1) {
                char c = (char) r;
                array[array.length-1] = c;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return array;
    }

    public static char getNextChar(){
        try {
            return getNextCharInternal();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private static char getNextCharInternal() throws Exception{
        if(fileInputStream == null){
            init();
        }
        if((r = fileInputStream.read()) != -1){
            return (char)r;
        }else{
            fileInputStream.reset();
            return getNextChar();
        }
    }


    private static void init(){
        try {
            fileInputStream = new FileInputStream(filePath);
            for(int i = 0; i < (int)(Math.random()*500);i++){
                r = fileInputStream.read();
            }
            while ((char)(r = fileInputStream.read()) != '.'){

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
