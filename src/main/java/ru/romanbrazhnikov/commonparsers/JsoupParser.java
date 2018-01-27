package ru.romanbrazhnikov.commonparsers;

import io.reactivex.Single;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;

public class JsoupParser implements ICommonParser {

    ///////TEST////////
    String row_1 = "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/universalnoe-pomeshchenie/raion_24_zaelcovskiy/101950135.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tуниверсальное помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/universalnoe-pomeshchenie/raion_24_zaelcovskiy/101950135.html\" target=\"_blank\" title=\"Сдам универсальное помещение г. Новосибирск, Заельцовский р-он, ул. Жуковского, д. 108\">\n" +
            "\t\t\tг. Новосибирск, Заельцовский р-он, ул. Жуковского, д. 108\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t10\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">4 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n";
    String row_2 = "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_25_kalininskiy/101722738.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_25_kalininskiy/101722738.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Калининский р-он\">\n" +
            "\t\t\tг. Новосибирск, Калининский р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t30\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">4 500 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tИП Назаров\t</td>\n" +
            "</tr>\n";
    ///////////////////

    private String mPatternString;
    private String mSourceString;
    private Set<String> mNames;
    private Map<String, String> mBindings;

    private String mRowSelector;
    private Map<String, String> mColSelectors;

    private ParseResult mResultTable = new ParseResult();

    public JsoupParser() {
        // row selector
        mRowSelector = "#panel_wrapper > div.container > div > div.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div:nth-child(3) > table > tbody > tr:nth-child(1)";

        // cols selector
        mColSelectors = new HashMap<>();
        mColSelectors.put("ADDRESS", "#panel_wrapper > div.container > div > div.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(3) > a");
        mColSelectors.put("SECOND", "#panel_wrapper > div.container > div > div.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(3) > a");
        mColSelectors.put("TOTALPRICE", "#panel_wrapper > div.container > div > div.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(5) > span");
    }

    @Override
    public void setPattern(String pattern) {
        mPatternString = pattern;
    }

    @Override
    public void setSource(String source) {
        mSourceString = source;
    }

    @Override
    public void setMatchNames(Set<String> names) {
        mNames = names;
    }

    @Override
    public void setBindings(Map<String, String> bindings) {
        mBindings = bindings;
    }

    @Override
    public Single<ParseResult> parse() {

        return Single.create(e -> {
            // (mocking) apply row pattern to get a list of rows
            List<String> rows = new ArrayList<>();
            rows.add(row_1);
            rows.add(row_2);

            // for each row get data from cols
            for(String currentRow : rows){
                // for each match name
                for(String currentMatchName : mNames){
                    // get next selector
                    String currentSelector = mColSelectors.get(currentMatchName);

                    // apply selector and retrieve data
                    Document doc = Jsoup.parse(currentRow);
                    Element element = doc.selectFirst(currentSelector);
                    String retrievedData = element.text();
                }
            }


        });
    }
}
