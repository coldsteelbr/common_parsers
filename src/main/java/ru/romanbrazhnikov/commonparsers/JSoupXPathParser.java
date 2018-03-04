package ru.romanbrazhnikov.commonparsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Single;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSoupXPathParser implements ICommonParser {

    private ParseResult mResultTable = new ParseResult();


    private ComplexPattern mComplexPattern;

    private String mSource;
    private Set<String> mNames;
    private Map<String, String> mBindings;

    private Gson mGson;
    private XPath mXPath;


    public JSoupXPathParser() {
        // XPATH
        mXPath = XPathFactory.newInstance().newXPath();

        // GSON for COMPLEX PATTERN
        GsonBuilder builder = new GsonBuilder();
        mGson = builder.setFieldNamingStrategy(f -> {
            // converting class field names to the config actual names
            switch (f.getName().toLowerCase()) {
                case "mrowpattern":
                    return "RowPattern";
                case "mcolpatterns":
                    return "ColPatterns";
                default:
                    return f.getName();
            }
        }).create();

    }

    @Override
    public void setPattern(String pattern) {
        mComplexPattern = mGson.fromJson(pattern, ComplexPattern.class);
    }

    @Override
    public void setSource(String source) {
        mSource = source;
    }

    @Override
    public void setMatchNames(Set<String> names) {
        mNames = names;
    }

    @Override
    public void setBindings(Map<String, String> bindings) {
        mBindings = bindings;
    }

    private Single<ParseResult> getResult() {
        return Single.create(emitter -> {
            mResultTable.clear();

            org.jsoup.nodes.Document jsDoc = Jsoup.parse(mSource);

            // removing namespace attrs from the root
            Elements elements = jsDoc.getElementsByTag("html");
            if (elements != null) {
                elements.removeAttr("xmlns");
                elements.removeAttr("xmlns:og");
                elements.removeAttr("prefix");
            }

            W3CDom w3cDom = new W3CDom();
            Document document = w3cDom.fromJsoup(jsDoc);

//            DocUtils.printDocument(document, System.out);
            // Rows
            NodeList rows = null;

            try {
                rows = (NodeList) mXPath.evaluate(mComplexPattern.mRowPattern, document, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
                emitter.onError(e);
            }

            // Cols
            String key = null;
            String value = null;
            Map<String, String> currentResultRow = null;
            for (int i = 0; i < rows.getLength(); i++) {
                currentResultRow = new HashMap<>();
                for (Map.Entry<String, String> currentEntry : mComplexPattern.mColPatterns.entrySet()) {
                    try {
                        key = currentEntry.getKey();
                        if (mBindings.containsKey(key)) {
                            key = mBindings.get(key);
                        }

                        value = (String) mXPath.evaluate(currentEntry.getValue(), rows.item(i), XPathConstants.STRING);
                        if (value == null) {
                            value = "";
                        }

                        currentResultRow.put(key, value);

                    } catch (XPathExpressionException e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                }
                mResultTable.addRow(currentResultRow);
            }

            emitter.onSuccess(mResultTable);
        }); // return
    }

    @Override
    public Single<ParseResult> parse() {

        if (mNames == null)
            return Single.error(new Exception("Matching names are not set"));

        if (mNames.size() == 0)
            return Single.error(new Exception("Matching names count is 0 (zero)"));

        if (mSource == null)
            return Single.error(new Exception("No source set"));


        return getResult();


    }
}
