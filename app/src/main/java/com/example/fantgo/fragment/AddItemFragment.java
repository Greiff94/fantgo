package com.example.fantgo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fantgo.R;
import com.example.fantgo.model.Item;
import com.example.fantgo.retrofit.APIClient;
import com.example.fantgo.retrofit.FantInterface;
import com.example.fantgo.storage.UserPrefs;
import com.google.gson.internal.bind.util.ISO8601Utils;

import org.w3c.dom.ls.LSOutput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddItemFragment extends Fragment {
    private EditText editItemName;
    private EditText editItemDesc;
    private EditText editItemPrice;
    private Button btnAddItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_additem, container, false);
        initViews(view);
        btnAddItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int price = Integer.parseInt(editItemPrice.getText().toString());
                if(editItemName.getText().toString().isEmpty() ||
                editItemDesc.getText().toString().isEmpty() ||
                price <=0) {
                    Toast.makeText(getContext(), "Please fill empty fields", Toast.LENGTH_SHORT).show();
                } else {
                    insertItem();
                }
            }
        });
        return view;
    }

    public void insertItem() {
        final String itemName = editItemName.getText().toString();
        final String itemDesc = editItemDesc.getText().toString();
        final int price = Integer.parseInt(editItemPrice.getText().toString());

        UserPrefs userPrefs = new UserPrefs(getContext());
        String token = "Bearer " + userPrefs.getToken();
        FantInterface fantAPI = APIClient.getClient().create(FantInterface.class);
        Call<Item> call = fantAPI.addItem(token, itemName, itemDesc, price);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(getContext(), "Item added!", Toast.LENGTH_SHORT).show();
                    Fragment newFragment = new ItemsFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment).commit();
                }
                else {
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }


    public void initViews(View view) {
        editItemName = view.findViewById(R.id.editItemName);
        editItemDesc = view.findViewById(R.id.editItemDesc);
        editItemPrice = view.findViewById(R.id.editItemPrice);
        btnAddItem = view.findViewById(R.id.btnAddItem);
    }
}
