package com.diabetes.instameal.core.presenter;


import com.diabetes.instameal.core.view.View;

public class Presenter<V extends View> {

    protected V view;


    public Presenter(V view){
        this.view = view;
    }
}
