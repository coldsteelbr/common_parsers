package ru.romanbrazhnikov.commonparsers;

import java.util.*;

public class ParseResult {

    // list of rows "field/value"
    private List<Map<String, String>> mResult = new LinkedList<>();

    public List<Map<String, String>> getResult() {
        return mResult;
    }

    public void addRow(Map<String, String> row){
        mResult.add(row);
    }

    public void clear(){
        mResult.clear();
    }

    public boolean isEmpty() {
        return mResult.size() == 0;
    }

    public Set<String> getMatchingNames(){
        Set<String> toReturn = new LinkedHashSet<>();
        for(Map<String, String> currentRow : mResult){
            toReturn.addAll(currentRow.keySet());
        }
        return toReturn;
    }

    public String getNiceLook(){
        StringBuilder builder = new StringBuilder();
        for(Map<String, String> currentRow : mResult){
            for(Map.Entry<String, String> currentCol : currentRow.entrySet()){
                builder.append(currentCol.getKey())
                       .append(":").append(currentCol.getValue())
                       .append("\t|\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
