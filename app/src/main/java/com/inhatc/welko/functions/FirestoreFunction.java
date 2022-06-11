package com.inhatc.welko.functions;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirestoreFunction {

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public void sendFirestoreUserData(String first, String second, String third) {

        Map<String, String> data = new HashMap<>();
        data.put("first", first);
        data.put("second", second);
        data.put("third", third);

        Log.d("DATA", data.toString());
        firebaseFirestore
                .collection("users")
                .add(data)
                .addOnSuccessListener(documentReference -> Log.d("SUCCESS", "ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.e("ERROR", e.getMessage()));

    }

    public void getFirestoreUserData() {
        firebaseFirestore
                .collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("SUCCESS", document.getId() + " => " + document.getData());
                        }
                    } else {
                        assert task.getException() != null;
                        Log.e("ERROR", task.getException().toString());
                    }
                });
    }
}

