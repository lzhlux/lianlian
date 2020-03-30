package com.lianlian.common.lock;

import lombok.Getter;

@Getter
public enum LockKeyPattern {

    TABLE("%s:%s"), // tag:table
    TABLE_RECORD("%s:%s:%s"), // tag:table:id
    CACHE_DATA("%s:%s"), // tag:key
    METHOD("%s:%s:%s"), // tag:class:name
    ;

    private String pattern;

    LockKeyPattern(String pattern){
        this.pattern = pattern;
    }

}
