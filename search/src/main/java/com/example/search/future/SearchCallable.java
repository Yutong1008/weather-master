//package com.example.search.future;
//
//import com.example.search.filter.RequestCorrelation;
//
//import java.util.concurrent.Callable;
//
//public class SearchCallable<V> implements Callable<V> {
//    private final String correlationId;
//    private final Callable<V> callable;
//
//    public SearchCallable(Callable<V> targetCallable) {
//        correlationId = RequestCorrelation.getId();
//        callable = targetCallable;
//    }
//
//    @Override
//    public V call() throws Exception {
//        RequestCorrelation.setId(correlationId);
//        return callable.call();
//    }
//
//}
