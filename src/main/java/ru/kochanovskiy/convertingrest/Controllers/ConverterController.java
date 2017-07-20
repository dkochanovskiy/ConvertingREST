package ru.kochanovskiy.convertingrest.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kochanovskiy.convertingrest.Services.ConverterService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class ConverterController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String converter() {
        return "converter";
    }

    @RequestMapping(value= "/", method = RequestMethod.POST)
    public String getNumber(ModelMap model, HttpServletRequest req) {

        int enteredNumber = Integer.parseInt(req.getParameter("number"));

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

        converterService.saveResult(decimalRu, octalRu, decimalEn, octalEn);

        model.addAttribute("decimalRu", decimalRu);
        model.addAttribute("octalRu", octalRu);
        model.addAttribute("decimalEn", decimalEn);
        model.addAttribute("octalEn", octalEn);

        return "converter";
    }
}