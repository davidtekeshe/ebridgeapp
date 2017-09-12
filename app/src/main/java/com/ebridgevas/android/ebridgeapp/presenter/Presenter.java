package com.ebridgevas.android.ebridgeapp.presenter;

public abstract class Presenter<T> {
    public abstract void initialize(String parentId);
    public abstract void resume();
    public abstract void pause();
    public abstract void destroy();

    protected T view;

    public void setView(T v) {
        view = v;
    }
}
