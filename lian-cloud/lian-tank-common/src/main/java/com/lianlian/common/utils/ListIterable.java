package com.lianlian.common.utils;

import java.util.Iterator;
import java.util.List;

public class ListIterable<S> implements Iterable<S>{

    List<S> list;
    public ListIterable(List<S> list){
        this.list = list;
    }
    @Override
    public Iterator<S> iterator() {
        return list.iterator();
    }
}
