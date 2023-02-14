package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.razorpay.Checkout;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity {


    CardView paycv;
    RadioButton day_30, day_60;
    String amount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());
        paycv = findViewById(R.id.cv_pay);

        paycv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializepayment(amount);

            }
        });

    }

    private void initializepayment(String amount) {

        final Activity activity = this;
        Checkout checkout = new Checkout();
        checkout.setKeyID("");
        checkout.setImage(R.drawable.ic_launcher_background);

        double finalamount = Float.parseFloat(amount)*100;

        try {
            JSONObject options = new JSONObject();
            options.put("name","name");
            options.put("desc","desc");
            options.put("image","");
            options.put("theme.color","#3399cc");
            options.put("currency", "INR");
            options.put("amount",finalamount+"");
            options.put("prefill.email","abc@gmail.com");
            options.put("prefill.contact","9131969571");

            checkout.open(activity,options);


        }catch (Exception e){

        }

    }
}