package com.example.donation20.models;

import androidx.annotation.NonNull;

public class Donation {
    public int amount;
    public String method;
    public int id;

    public Donation (int amount, String method)
    {
        this.amount = amount;
        this.method = method;
    }

    public Donation ()
    {
        this.amount = 0;
        this.method = "";
    }

    @NonNull
    public String toString()
    {
        return id + ", " + amount + ", " + method;
    }
}


