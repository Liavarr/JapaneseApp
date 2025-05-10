package com.example.japaneseapplication.interfaces;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public interface AdminPanelCRUDInterface<R, T> {
    void create(R repository, T item);
    void retrieve(R repository, T item);
    void update(R repository, T item);
    void delete(R repository, T item);


}
