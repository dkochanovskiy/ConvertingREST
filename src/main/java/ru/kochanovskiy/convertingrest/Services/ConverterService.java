package ru.kochanovskiy.convertingrest.Services;

import org.apache.log4j.Logger;
import java.io.*;

public class ConverterService {

    static private Logger logger = Logger.getLogger(ConverterService.class);
    private static final String FILENAME = "C:\\Users\\Денис\\Desktop\\ConvertingREST\\src\\main\\resources\\response.json";
    private static String outputString = null;

    /**
     * Verbal number notation in decimal notation
     */
    public String decimalParser(String enteredString, String[] inputArrayU, String[] inputArrayT, String[] inputArrayH){

        char[] arrayOfDigits = enteredString.toCharArray();
        if(enteredString.length() == 1){
            for (int i = 0; i < 10; i++){
                if (enteredString.equals(Integer.toString(i))) outputString = inputArrayU[i];
            }
        }
        if (enteredString.length() == 2){
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
        if (enteredString.length() == 3){
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
                "     \"10\": \"" + decimalEn + "\",\n" +
                "     \"8\": \"" + octalEn + "\"\n" +
                "   },\n" +
                "  \"ru\": {\n" +
                "     \"10\": \"" + decimalRu + "\",\n" +
                "     \"8\": \"" + octalRu + "\"\n" +
                "   }\n" +
                "  }\n" +
                "}\n";

        try {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(FILENAME), "UTF8"))) {
                bw.write(text);
                bw.flush();
            }
        } catch (IOException ex) {
            logger.trace("IOException" + ex);
        }
    }
}