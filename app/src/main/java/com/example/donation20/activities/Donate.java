package com.example.donation20.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.donation20.R;
import com.example.donation20.models.Donation;

public class Donate extends Base {
    private RadioGroup paymentMethod;
    public ProgressBar progressBar;
    private NumberPicker amountPicker;
    private EditText amountText;
    private TextView amountTotal;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paymentMethod = findViewById(R.id.paymentMethod);
        progressBar = findViewById(R.id.progressBar);
        amountPicker = findViewById(R.id.amountPicker);
        amountText = findViewById(R.id.paymentAmount);
        amountTotal = findViewById(R.id.totalSoFar);

        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(super.app.target/10);

        progressBar.setMax(super.app.target);
        amountTotal.setText("$0");

        progressBar.setProgress(app.totalDonated);
        amountTotal.setText("$" + app.totalDonated);
    }

    @SuppressLint("SetTextI18n")
    public void donateButtonPressed (View view)
    {
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ?
                "PayPal" : "Direct";
        int donatedAmount = amountPicker.getValue();
        String text = amountText.getText().toString();
        if (!text.equals(""))
            donatedAmount += Integer.parseInt(text);
        if (donatedAmount > 0)
        {
            app.newDonation(new Donation(donatedAmount, method));
            progressBar.setProgress(app.totalDonated);
            amountTotal.setText("$" + app.totalDonated);
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void reset(MenuItem item)
    {
        app.totalDonated = 0;
        progressBar.setProgress(app.totalDonated);
        amountTotal.setText("$" + app.totalDonated);
        app.dbManager.reset();
    }

}

