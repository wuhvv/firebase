package com.example.firebaseTest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
        findViewById(R.id.delaccountButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.logoutButton:
                    Toast.makeText(MainActivity.this, "로그아웃 버튼 눌림", Toast.LENGTH_SHORT).show();
                    logOut();
                    break;
                case R.id.delaccountButton:
                    Toast.makeText(MainActivity.this, "탈퇴 버튼 눌림", Toast.LENGTH_SHORT).show();
                    delAccount();
                    break;
            }
        }
    };

    private void logOut(){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void delAccount(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}