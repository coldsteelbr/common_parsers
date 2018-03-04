import ru.romanbrazhnikov.commonparsers.JSoupXPathParser;
import ru.romanbrazhnikov.commonparsers.ICommonParser;
import ru.romanbrazhnikov.commonparsers.XPathParser;
import ru.romanbrazhnikov.fileutils.FileUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {

    private static String sFullDocPattern =
            "\t\t\t{\n" +
                    "            \t\"RowPattern\" : \"//*[contains(@class, 'realty-item') and contains(@class, 'js-realty-item')]\",\n" +
                    "            \t\"ColPatterns\" : {\n" +
                    "            \t    \"SECONDLEVEL\" : \"a/@href\",\n" +
                    "\n" +
                    "\t\t\t\t\t\"ROOMS\" : \"a/div[2]/div[1]/div[1]\",\n" +
                    "            \t\t\"ADDRESS\" : \"a/div[2]/div[1]/div[2]\",\n" +
                    "            \t\t\"FLOOR\" : \"a/div[2]/div[2]/div[1]\",\n" +
                    "            \t\t\"MATERIAL\" : \"a/div[2]/div[2]/div[2]\",\n" +
                    "            \t\t\"SQUARE\" : \"a/div[2]/div[3]/div[1]/text()[2]\",\n" +
                    "            \t\t\"TOTALPRICE\" : \"a/div[1]/div/div[1]/text()\"\n" +
                    "\n" +
                    "            \t}\n" +
                    "            }";

    static String sTestSource =
            "<div>\n" +
                    "\t<a class = \"row\" href=\"http://1.bla.ru\">\n" +
                    "\t\t<img src =\"bla1.jpg\" />\n" +
                    "\t\t<div>CONTENT 1</div>\n" +
                    "\t</a>\n" +
                    "\t<a class = \"row\" href=\"http://2.bla.ru\">\n" +
                    "\t\t<img src =\"bla2.jpg\" />\n" +
                    "\t\t<div>CONTENT 2</div>\n" +
                    "\t</a>\n" +
                    "</div>";
    static String sTestPattern =
            "{\n" +
                    "\"RowPattern\" : \"//*[contains(@class, 'row')]\",\n" +
                    "\"ColPatterns\" : {\n" +
                    "\"IMG\" : \"img/@src\",\n" +
                    "\"CONT\" : \"div[1]/text()\"" +
                    "}\n" +
                    "}";

    static String sTestTablePattern =
            "{\n" +
                "\"RowPattern\" : \"//*[@id='myDoc']//a\",\n" +
                "\"ColPatterns\" : {\n" +
                    "\"MARK\" : \"td[1]\",\n" +
                    "\"MODEL\" : \"td[2]\"" +
                "}\n" +
            "}";

    static String sTestTableDoc =
            "<table id = \"myDoc\">\n" +
                "<a class = \"row\">\n" +
                    "<td>Renault</td>\n" +
                    "<td>Logan</td>\n" +
                "</a>\n" +
                "<a class = \"row\">\n" +
                    "<td>Lada</td>\n" +
                    "<td>Kalina</td>\n" +
                "</a>\n" +
            "</table>";

    public static void main(String[] args) {
        System.out.println("CommonParsers");

        ///////////////
        /*
        System.out.println("Little test:");

        ICommonParser testParser = new JSoupXPathParser();

        testParser.setSource(sTestTableDoc);
        testParser.setPattern(sTestTablePattern);
        testParser.parse().subscribe(parseResult -> {
            System.out.println("Little test res:\n\n" + parseResult.getNiceLook());
        });
        System.out.println("\nEnd of test\n====\n");
        if (false) return;
        */
        ///////////////

        String IRRPatternJSON =
                "{\n" +
                "\t\"RowPattern\" : \"//main/div/div\",\n" +
                "\t\"ColPatterns\": {\n" +
                "\t\t\"DISTRICT\"       :    \"div[5]/div[1]/div/ul/li[contains(text(), 'Район города')]/text()\",\n" +
                "\t\t\"SQUARE_KITCHEN\" : \"div[5]/div[2]/ul/li[5]/text()\",\n" +
                "\t\t\"SQUARE_LIVING\"  : \"div[5]/div[2]/ul/li[6]/text()\",\n" +
                "\t\t\"NOTES\"          : \"div[4]/p\"\n" +
                "\t}\n" +
                "}";


        String testJson =
                "{\n" +
                "\t\"RowPattern\" : \"//main/div/div\",\n" +
                "\t\"ColPatterns\": {\n" +
                "\t\t\"DISTRICT\"       :    \"div[5]/div[1]/div/ul/li[contains(text(), 'Район города')]/text()\",\n" +
                "\t\t\"SQUARE_KITCHEN\" : \"div[5]/div[2]/ul/li[5]/text()\",\n" +
                "\t\t\"SQUARE_LIVING\"  : \"div[5]/div[2]/ul/li[6]/text()\",\n" +
                "\t\t\"NOTES\"          : \"div[4]/p\"\n" +
                "\t}\n" +
                "}";
                                            // /div[5]/div[2]/div[1]/ul/li[5]/text()
        System.out.println("JSoupXPathParser");
        ICommonParser parser = new JSoupXPathParser();
        String testSource = null; //TestSource.getText();
        try {
            testSource = FileUtils.readFromFileToString("full_irr_sub.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        parser.setSource(testSource);
        parser.setPattern(IRRPatternJSON);
        parser.setMatchNames(new HashSet<>(Arrays.asList("DISTRICT", "SQUARE_KITCHEN", "SQUARE_LIVING", "NOTES")));

        Map<String, String> bind = new HashMap<>();
        bind.put("DISTRICT", "district");
        bind.put("SQUARE_KITCHEN", "square_kitchen");
        bind.put("SQUARE_LIVING", "square_living");
        bind.put("NOTES", "notes");


        parser.setBindings(bind);

        parser.parse().subscribe(parseResult -> {
            System.out.println("Nice:" + parseResult.getNiceLook() + "\n --- \n");
        }, throwable -> {
            throwable.printStackTrace();
        });
    }
}
