package com.example.firebaseTest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class StoreTestActivity extends AppCompatActivity {

    FirebaseFirestore db;
    private static final String TAG = "StoreTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_test);

        db = FirebaseFirestore.getInstance();

        findViewById(R.id.inputButton).setOnClickListener(onClickListener);
        findViewById(R.id.outputButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.inputButton:
                    Toast.makeText(StoreTestActivity.this, "내보내기", Toast.LENGTH_SHORT).show();
                    inputData();
                    break;
                case R.id.outputButton:
                    Toast.makeText(StoreTestActivity.this, "가져오기", Toast.LENGTH_SHORT).show();
                    outputData();
            }
        }
    };

    private void inputData(){
        Map<String, Object> user = new HashMap<>();
        String data1 = ((EditText) findViewById(R.id.editText1)).getText().toString();
        String data2 = ((EditText) findViewById(R.id.editText2)).getText().toString();
        user.put("첫번째 데이터", data1);
        user.put("두번째 데이터", data2);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void outputData(){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                ((TextView)findViewById(R.id.textView2)).append(document.getData().toString()+"\n");
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}