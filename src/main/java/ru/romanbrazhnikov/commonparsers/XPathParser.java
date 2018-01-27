package ru.romanbrazhnikov.commonparsers;

import io.reactivex.Single;

import java.util.Map;
import java.util.Set;

public class XPathParser implements ICommonParser {
    private String mPatternString;
    private String mSourceString;
    private Set<String> mNames;
    private Map<String, String> mBindings;

    private String mRowSelector;
    private Map<String, String> mColSelectors;

    private ParseResult mResultTable = new ParseResult();

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
        return null;
    }
}
