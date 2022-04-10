package com.example.search.filter;

public class RequestCorrelation {
    public static final String CORRELATION_ID_HEADER = "correlationId";


    private static final ThreadLocal<String> id = new ThreadLocal<String>();
//    public static String Correlation_ID_Header;

    public static String getId() {
        return id.get();
    }
    public static void setId(String correlationId) {
        id.set(correlationId);
    }

}

