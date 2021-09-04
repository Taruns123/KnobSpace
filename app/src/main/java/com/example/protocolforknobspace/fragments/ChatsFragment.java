package com.example.protocolforknobspace.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.protocolforknobspace.Adapters.UsersAdapter;
import com.example.protocolforknobspace.Models.User;
import com.example.protocolforknobspace.R;
import com.example.protocolforknobspace.databinding.FragmentChatsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {
    UsersAdapter adapter;
    LinearLayoutManager layoutManager;
    public ChatsFragment(){



    }
    FragmentChatsBinding binding;
    ArrayList<User> list = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new UsersAdapter(list, getContext());
        layoutManager = new LinearLayoutManager(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentChatsBinding.inflate(inflater, container, false);

       database = FirebaseDatabase.getInstance();
        binding.chatRecyclerView.setAdapter(adapter);

        binding.chatRecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    user.setUserId(dataSnapshot.getKey());
                    if (!user.getUserId().equals(FirebaseAuth.getInstance().getUid())){
                    list.add(user);}
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


       return binding.getRoot();
    }
}