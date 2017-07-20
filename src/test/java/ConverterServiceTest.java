import ru.kochanovskiy.convertingrest.Services.ConverterService;

import org.junit.Test;

public class ConverterServiceTest {

    @Test
    public void ConvertServiceTest(){

        int enteredNumber = 131;

        String unitsRu[] = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
                "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

        String unitsEn[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen ", "fourteen", "fifteen ", "sixteen", "seventeen", "eighteen", "nineteen"};

        String tensRu[] = {"", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String tensEn[] = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String hundredsRu[] = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        String hundredsEn[] = {"", "one hundred", "two hundred", "three hundred", "four hundred", "five hundred", "six hundred", "seven hundred", "eight hundred", "nine hundred"};

        ConverterService converterService = new ConverterService();

        String decimalRu = converterService.decimalParser(Integer.toString(enteredNumber), unitsRu, tensRu, hundredsRu);
        String octalRu = converterService.decimalParser(converterService.octalConversion(enteredNumber), unitsRu, tensRu, hundredsRu);
        String decimalEn = converterService.decimalParser(Integer.toString(enteredNumber), unitsEn, tensEn, hundredsEn);
        String octalEn = converterService.decimalParser(converterService.octalConversion(enteredNumber), unitsEn, tensEn, hundredsEn);

        System.out.println(decimalRu + " в десятиричной системе счисления;");
        System.out.println(octalRu + " в восьмеричной системе счисления;");
        System.out.println(decimalEn + " in decimal notation;");
        System.out.println(octalEn + " in octal notation;");
    }
}
