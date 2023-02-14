package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileViewed extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference profileRef;
    RecyclerView recyclerView_profile;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_viewed);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = user.getUid();
        database = FirebaseDatabase.getInstance();
        profileRef = database.getReference("Viewed Profile");

        recyclerView_profile = findViewById(R.id.rv_pv);
        recyclerView_profile.setHasFixedSize(true);
        recyclerView_profile.setLayoutManager(new LinearLayoutManager(ProfileViewed.this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<All_UserMmber> options1 =
                new FirebaseRecyclerOptions.Builder<All_UserMmber>()
                        .setQuery(profileRef, All_UserMmber.class)
                        .build();

        FirebaseRecyclerAdapter<All_UserMmber, ProfileViewholder> firebaseRecyclerAdapter1 =
                new FirebaseRecyclerAdapter<All_UserMmber, ProfileViewholder>(options1) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProfileViewholder holder, int position, @NonNull All_UserMmber model) {

                        final String postkey = getRef(position).getKey();
                        holder.setProfilepv(getApplication(), model.getName(), model.getUid(), model.getProf(), model.getUrl());

                        String name = getItem(position).getName();
                        String url = getItem(position).getUrl();
                        String uid = getItem(position).getUid();
                        holder.textViewProfession.setVisibility(View.GONE);
                        holder.viewUserprofile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (currentUserId.equals(uid)) {
                                    Intent intent = new Intent(ProfileViewed.this, MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    Intent intent = new Intent(ProfileViewed.this, ShowUser.class);
                                    intent.putExtra("n", name);
                                    intent.putExtra("u", url);
                                    intent.putExtra("uid", uid);
                                    startActivity(intent);
                                }
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ProfileViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.profile, parent, false);

                        return new ProfileViewholder(view);
                    }
                };

        firebaseRecyclerAdapter1.startListening();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProfileViewed.this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView_profile.setLayoutManager(gridLayoutManager);
        recyclerView_profile.setAdapter(firebaseRecyclerAdapter1);
    }
}