package com.example.donation20.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.donation20.database.DBManager;
import com.example.donation20.models.Donation;

public class DonationApp extends Application {
    public final int target = 10000;
    public int totalDonated = 0;
//    public List<Donation> donations = new ArrayList<Donation>();
    public DBManager dbManager;

    public void newDonation(Donation donation)
    {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved)
        {
            dbManager.add(donation);
            totalDonated += donation.amount;
        }
        else
        {
            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Donate", "Donation App Started");
        dbManager = new DBManager(this);
        Log.v("Donate", "Database Created");
    }


}
