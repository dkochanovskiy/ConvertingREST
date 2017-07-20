package ru.kochanovskiy.convertingrest.Services;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ConverterService {

    static private Logger logger = Logger.getLogger(ConverterService.class);

    private static String outputString = null;

    /**
     * One numeral convertation
     */
    private static void oneNumeral(String enteredString, String[] inputArrayU){
        for (int i = 0; i < 10; i++){
            if (enteredString.equals(Integer.toString(i))) outputString = inputArrayU[i];
        }
    }

    /**
     * Two numeral convertation
     */
    private static void twoNumeral(char[] arrayOfDigits, String[] inputArrayT, String[] inputArrayU){
        if(arrayOfDigits[0] == '1'){
            int unit = 1;
            for (char c = '0'; c <= '9'; c++){
                if (arrayOfDigits[1] == c) outputString = inputArrayU[Integer.parseInt(Integer.toString(unit) + String.valueOf(c))];
            }
        } else {
            for (char i = '2'; i <= '9'; i++){
                if (arrayOfDigits[0] == i){
                    if (arrayOfDigits[1] == '0') outputString = inputArrayT[Integer.parseInt(String.valueOf(i))];
                    for (char c = '1'; c <= '9'; c++){
                        if (arrayOfDigits[1] == c) outputString = inputArrayT[Integer.parseInt(String.valueOf(i))] + " " + inputArrayU[Integer.parseInt(String.valueOf(c))];
                    }
                }
            }
        }
    }

    /**
     * Three numeral convertation
     */
    private static void threeNumeral(char[] arrayOfDigits, String[] inputArrayT, String[] inputArrayU, String[] inputArrayH){
        for (char c = '1'; c <= '9'; c++){
            if (arrayOfDigits[0] == c){
                if (arrayOfDigits[1] == '0'){
                    if (arrayOfDigits[2] == '0') outputString = inputArrayH[Integer.parseInt(String.valueOf(c))];
                    for (char i = '1'; i <= '9'; i++){
                        if (arrayOfDigits[2] == i) outputString = inputArrayH[Integer.parseInt(String.valueOf(c))] + " " + inputArrayU[Integer.parseInt(String.valueOf(i))];
                    }
                }if (arrayOfDigits[1] == '1'){
                    for (char i = '0'; i <= '9'; i++){
                        if (arrayOfDigits[2] == i) outputString = inputArrayH[Integer.parseInt(String.valueOf(c))] + " " + inputArrayU[Integer.parseInt(1 + String.valueOf(i))];
                    }
                }
                for (char j = '2'; j <= '9'; j++){
                    if (arrayOfDigits[1] == j){
                        if (arrayOfDigits[2] == '0') outputString = inputArrayH[Integer.parseInt(String.valueOf(c))] + " " + inputArrayT[Integer.parseInt(String.valueOf(j))];
                        for (char i = '1'; i <= '9'; i++){
                            if (arrayOfDigits[2] == i) outputString = inputArrayH[Integer.parseInt(String.valueOf(c))] + " " + inputArrayT[Integer.parseInt(String.valueOf(j))] + " " + inputArrayU[Integer.parseInt(String.valueOf(i))];
                        }
                    }
                }
            }
        }
    }

    /**
     * Verbal number notation in decimal notation
     */
    public String decimalParser(String enteredString, String[] inputArrayU, String[] inputArrayT, String[] inputArrayH){

        char[] arrayOfDigits = enteredString.toCharArray();
        if(enteredString.length() == 1){
            oneNumeral(enteredString, inputArrayU);
        }
        if (enteredString.length() == 2){
            twoNumeral(arrayOfDigits, inputArrayU, inputArrayT);
        }
        if (enteredString.length() == 3){
            threeNumeral(arrayOfDigits, inputArrayU, inputArrayT, inputArrayH);
        }
        return outputString;
    }

    /**
     * Conversion to octal notation
     */
    public String octalConversion(int convertNumber){
        int notation = 8;
        return Integer.toString(convertNumber, notation);
    }

    /**
     * Saving results
     */
    public void saveResult(String decimalRu, String octalRu, String decimalEn, String octalEn){
        String text = "{\n" +
                "\"result\": {\n" +
                "  \"en\": {\n" +
                "     \"10\": " + decimalEn + ",    \n" +
                "     \"8\": " + octalEn + " \n" +
                "   },\n" +
                "  \"ru\": {\n" +
                "     \"10\": " + decimalRu + ",   \n" +
                "     \"8\": " + octalRu + " \n" +
                "   }\n" +
                "  }\n" +
                "}\n"; // строка для записи

        try(FileOutputStream fos = new FileOutputStream("C:\\Users\\Денис\\Desktop\\ConvertingREST\\response.txt"))
        {
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}