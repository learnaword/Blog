package com.mjl.blog.utils.test;

public class Single {

    private Single INSTANCE;

    private Single(){}

    public Single getSingle(){
        synchronized (Single.class) {
            if (INSTANCE == null) {
                INSTANCE = new Single();
            }
        }
        return INSTANCE;
    }
}
