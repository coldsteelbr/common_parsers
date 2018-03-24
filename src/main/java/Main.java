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

    public static void main(String[] args) {
        System.out.println("CommonParsers");


        //
        //  FIRST LEVEL
        //
        String parserPatternEIF =
                "{\n" +
                "\t\"RowPattern\" : \" //*[@id='main']/html:div[3]/html:div[2]/html:table//html:tr[child::html:td[count(html:a)>0]]\",\n" +
                "\t\"ColPatterns\" : {\n" +
                "\t\t\"SECONDLEVEL\" : \"html:td/html:a/@href\"\n" +
                "\t}\n" +
                "}";

        Map<String, String> ns = new HashMap<>();
        ns.put("html", "http://www.w3.org/1999/html");

        System.out.println("JSoupXPathParser");
        JSoupXPathParser parser = new JSoupXPathParser();
        String testSource = null; //TestSource.getText();
        try {
            testSource = FileUtils.readFromFileToString("eip.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        parser.setXmlNamespaces(ns);
        parser.setSource(testSource);
        parser.setPattern(parserPatternEIF);
        parser.setMatchNames(new HashSet<>(Arrays.asList("SECONDLEVEL")));

        Map<String, String> bind = new HashMap<>();
        bind.put("SECONDLEVEL", "second");



        parser.setBindings(bind);

        parser.parse().subscribe(parseResult -> {
            System.out.println("Nice:\n" + parseResult.getNiceLook() + "\n --- \n");
        }, throwable -> {
            throwable.printStackTrace();
        });


    }
}
