package com.example.fantgo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fantgo.R;
import com.example.fantgo.adapter.ItemListAdapter;
import com.example.fantgo.model.Item;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {
    private RecyclerView itemsRecView;
    private ArrayList<Item> items = new ArrayList<>();
    private ItemListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        itemsRecView.setAdapter(adapter);
        itemsRecView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }
}
