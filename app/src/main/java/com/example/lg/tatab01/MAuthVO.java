package com.example.lg.tatab01;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by a on 2018-11-19.
 */

public class MAuthVO {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    public String getUid() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        return mUser.getUid();
    }
}
