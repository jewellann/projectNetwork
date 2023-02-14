package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateEmail extends AppCompatActivity {

    EditText emailet,passet;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        button = findViewById(R.id.update_emailBtn);
        emailet = findViewById(R.id.newEmail_et);
        passet = findViewById(R.id.newpass_et);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newemail = emailet.getText().toString().trim();
                String newpasss =passet.getText().toString().trim();

                updateEmail(newemail);
                updatePassword(newpasss);

            }
        });

    }


    public void updateEmail(String email) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateEmail.this, "Email updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void updatePassword(String password) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateEmail.this, "user password updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}