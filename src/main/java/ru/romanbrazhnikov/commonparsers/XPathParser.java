package ru.romanbrazhnikov.commonparsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Single;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Entities;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XPathParser implements ICommonParser {

    private String mSourceString;
    private Set<String> mNames;
    private Map<String, String> mBindings;

    private ComplexPattern mComplexPattern;

    private ParseResult mResultTable = new ParseResult();

    private HtmlCleaner mCleaner;
    // TODO: delete testing fields
    private Document mDoc;
    private XPath mXPath;
    private String mErrorMessage;
    private Gson mGson;

    public XPathParser() {
        mXPath = XPathFactory.newInstance().newXPath();

        mCleaner = new HtmlCleaner();
        CleanerProperties cleanerProperties = mCleaner.getProperties();
        cleanerProperties.setOmitDeprecatedTags(false);
        cleanerProperties.setOmitUnknownTags(false);
        cleanerProperties.setOmitHtmlEnvelope(false);

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
        //mPatternString = pattern;

        mComplexPattern = mGson.fromJson(pattern, ComplexPattern.class);
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

        if (mNames == null)
            return Single.error(new Exception("Matching names are not set"));

        if (mNames.size() == 0)
            return Single.error(new Exception("Matching names count is 0 (zero)"));

        if (mSourceString == null)
            return Single.error(new Exception("No source set"));


        return getResult();


    }

    private Single<ParseResult> getResult() {
        return Single.create(e -> {

            /* // BEFORE
            // Getting clean html
            String cleanedHtml = getCleanedHtmlByJSoup();

            // trying to get an xml document
            mDoc = readDocFromXmlString(cleanedHtml);
            */
            // AFTER
            mDoc = getCleanedDocumentByHtmlCleaner();


            if (mDoc == null) {
                e.onError(new NullPointerException("mDoc is null (error while building from an xml string)"));
                return;
            }

            NodeList parsed = null;

            try {
                parsed = (NodeList) mXPath.evaluate(mComplexPattern.mRowPattern, mDoc, XPathConstants.NODESET);
            } catch (XPathExpressionException ew) {
                ew.printStackTrace();
            }


            Map<String, String> currentRow;
            String currentKeyToPut;
            String field;
            String value;
            // getting data from each row
            if (parsed != null) {
                for (int i = 0; i < parsed.getLength(); i++) {
                    currentRow = new HashMap<>();
                    for (Map.Entry<String, String> currentSelector : mComplexPattern.mColPatterns.entrySet()) {
                        field = currentSelector.getKey();
                        value = getByXPath(currentSelector.getValue(), parsed.item(i));

                        // checking for bindings (aliases)
                        if (mBindings != null) {
                            currentKeyToPut = mBindings.get(field) == null ? field : mBindings.get(field);
                        } else {
                            currentKeyToPut = field;
                        }

                        currentRow.put(currentKeyToPut, value==null?"":value);
                    }
                    mResultTable.addRow(currentRow);
                }
            }


            e.onSuccess(mResultTable);
        });
    }

    private Document getCleanedDocumentByHtmlCleaner() {
        /* // BEFORE
        // returning String
        TagNode tagNode = mCleaner.clean(mSourceString);

        CleanerProperties props = mCleaner.getProperties();
        props.setOmitComments(true);

        XmlSerializer serializer = new PrettyXmlSerializer(props);
        return serializer.getAsString(tagNode);
        */

        TagNode RootNode = mCleaner.clean(mSourceString);
        Document FullDocument = null;
        try {
            FullDocument = new DomSerializer(mCleaner.getProperties()).createDOM(RootNode);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return FullDocument;
    }

    private String getCleanedHtmlByJSoup() {

        org.jsoup.nodes.Document doc = Jsoup.parse(mSourceString);
        doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        doc.outputSettings().escapeMode(Entities.EscapeMode.extended);

        return doc.html();
    }

    private String getCleanedHtmlByJTidy() {
        return null;
    }
    //
    // XPATH METHODS
    //

    public Document readDocFromXmlString(String xmlString) {
        DocumentBuilderFactory myDocFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder myDocBuilder;
        Document myDoc = null;
        try {
            myDocBuilder = myDocFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            mErrorMessage = e.getMessage();
            return null;
        }


        try {
            myDoc = myDocBuilder.parse(new InputSource(new StringReader(xmlString)));
        } catch (SAXException e) {
            mErrorMessage = "SAXException: " + e.getMessage();
            return null;
        } catch (IOException e) {
            mErrorMessage = "IOException: " + e.getMessage();
            return null;
        }


        // read successfully
        return myDoc;
    }

    private String getParserAttributeAsString(String p_xPath) {
        String resultString = getByXPath(p_xPath);
        if (resultString == null || resultString.equals("")) {
            return null;
        } else {
            return resultString;
        }

    }

    /**
     * Gets a string value by XPath from root
     */
    private String getByXPath(String p_xPathString) {
        return (String) getByXPath(p_xPathString, null, null);
    }

    /**
     * Gets a string value by XPath from an item
     */
    private String getByXPath(String p_xPathString, Object p_item) {
        return (String) getByXPath(p_xPathString, p_item, null);
    }

    /**
     * Gets a specified type value by XPath from root
     */
    private Object getByXPath(String p_xPathString, QName p_xPathReturnType) {
        return getByXPath(p_xPathString, null, p_xPathReturnType);
    }

    /**
     * Gets a specified type value by XPath from mentioned item
     */
    private Object getByXPath(String p_xPathString, Object p_item, QName p_xPathReturnType) {

        Object item;
        // checking if the item is set
        if (p_item == null) {
            // ... not set - use root document
            item = mDoc;
        } else {
            // ... else use the item
            item = p_item;
        }

        QName xPathReturnType;
        if (p_xPathReturnType == null) {
            xPathReturnType = XPathConstants.STRING;
        } else {
            xPathReturnType = p_xPathReturnType;
        }

        try {
            // applying xPath expression to the item
            Object temp = mXPath.compile(p_xPathString).evaluate(item, xPathReturnType);
            return temp;
        } catch (XPathExpressionException e) {
            mErrorMessage = "[XPathExpressionException]: " + e.getMessage();
            return null;
        }
    }
}
