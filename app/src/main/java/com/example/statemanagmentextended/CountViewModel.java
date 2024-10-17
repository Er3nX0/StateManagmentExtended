package com.example.statemanagmentextended;

import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {
    private int count = 0;

    public int getCount(){
        return count;
    }
    public void incrementCount(){
        count++;
    }
}