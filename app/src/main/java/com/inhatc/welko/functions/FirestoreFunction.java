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
import com.inhatc.welko.Travel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirestoreFunction {

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    ArrayList<Travel> travelList = new ArrayList<Travel>();
    Travel travel = new Travel();

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

//    public String[] getFirestoreTravelList(String type) {
//        String[] data;
//
//        firebaseFirestore
//                .collection("travel").whereEqualTo("type", type)
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            data[0] =
//
//                            travel.setType(document.get("type").toString());
//                            travel.setName(document.get("name").toString());
//                            travel.setLocation(document.get("location").toString());
//                            travel.setIntro(document.get("intro").toString());
//                            travel.setDescription(document.get("description").toString());
//                            travel.setTransportation(document.get("transportation").toString());
//                            travel.setThumbnail(document.get("thumbnail").toString());
//                            travelList.add(travel);
//
//
//                            Log.d("Success", String.valueOf(travelList.get(0).getName()));
//                        }
//                    } else {
//                        assert task.getException() != null;
//                        Log.e("ERROR", task.getException().toString());
//                    }
//                });
//        return travelList;
//    }

    public ArrayList<Travel> getFirestoreTravelList(String type) {
        firebaseFirestore
                .collection("travel")
                .whereEqualTo("type", type)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            travel.setType(document.get("type").toString());
                            travel.setName(document.get("name").toString());
                            travel.setLocation(document.get("location").toString());
                            travel.setIntro(document.get("intro").toString());
                            travel.setDescription(document.get("description").toString());
                            travel.setTransportation(document.get("transportation").toString());
                            travel.setThumbnail(document.get("thumbnail").toString());
                            travelList.add(travel);


                            Log.d("Success", String.valueOf(travelList.get(0).getName()));
                        }
                    } else {
                        assert task.getException() != null;
                        Log.e("ERROR", task.getException().toString());
                    }
                });
        return travelList;
    }

//    public Map<String, String> getFirestoreTravelList(String name) {
//        ArrayList<Travel> travelList = new ArrayList<Travel>();
//        Travel travel = new Travel();
//
//        Map<String, String> data = new HashMap<>();
//
//        firebaseFirestore
//                .collection("travel").whereEqualTo("name", name)
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//
//                            data.put("type",document.get("type").toString());
//                            data.put("name",document.get("name").toString());
//                            data.put("location",document.get("location").toString());
//                            data.put("intro",document.get("intro").toString());
//                            data.put("description",document.get("description").toString());
//                            data.put("transportation",document.get("transportation").toString());
//                            data.put("thumbnail",document.get("thumbnail").toString());
//
//
////                            Log.d("Success", String.valueOf(travelList.size()));
//                        }
//                    } else {
//                        assert task.getException() != null;
//                        Log.e("ERROR", task.getException().toString());
//                    }
//                });
//        return data;
//    }
}

