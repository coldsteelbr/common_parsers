import ru.romanbrazhnikov.commonparsers.AnotherXPathParser;
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
            "{\n" +
                    "\"RowPattern\" : \"//*[@id='main']//div[contains(@class, 'realty-item')]\",\n" +
                    "\"ColPatterns\" : {\n" +
                    "\"ROOMS\" : \"a/@href\",\n" +
                    "\"ADDRESS\" : \"a/div[2]/div[1]/div[2]/text()\",\n" +
                    "\"SECONDLEVEL\" : \"a[1]/@href\"\n" +
                    "}\n" +
                    "}";

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
        System.out.println("Little test:");

        ICommonParser testParser = new AnotherXPathParser();

        testParser.setSource(sTestTableDoc);
        testParser.setPattern(sTestTablePattern);
        testParser.parse().subscribe(parseResult -> {
            System.out.println("Little test res:\n\n" + parseResult.getNiceLook());
        });
        System.out.println("\nEnd of test\n====\n");
        if (true) return;
        ///////////////


        ICommonParser parser = new XPathParser();
        String testSource = null; //TestSource.getText();
        try {
            testSource = FileUtils.readFromFileToString("full_doc.htm");
        } catch (IOException e) {
            e.printStackTrace();
        }
        parser.setSource(testSource);
        parser.setPattern(sFullDocPattern);
        parser.setMatchNames(new HashSet<>(Arrays.asList("ADDRESS", "SECONDLEVEL", "ROOMS")));

        Map<String, String> bind = new HashMap<>();
        bind.put("ADDRESS", "address");
        bind.put("SECONDLEVEL", "second");
        bind.put("ROOMS", "flats");

        parser.setBindings(bind);

        parser.parse().subscribe(parseResult -> {
            System.out.println("Nice:" + parseResult.getNiceLook() + "\n --- \n");
        }, throwable -> {
            throwable.printStackTrace();
        });
    }
}
