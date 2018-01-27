package ru.romanbrazhnikov.commonparsers;

import io.reactivex.annotations.NonNull;

public class NamedSelector {
    @NonNull
    private final String mName;
    @NonNull
    private final String mSelector;

    public NamedSelector(@NonNull final String name, @NonNull final String selector) {
        mName = name;
        mSelector = selector;
    }

    public String getName() {
        return mName;
    }

    public String getSelector() {
        return mSelector;
    }

}
