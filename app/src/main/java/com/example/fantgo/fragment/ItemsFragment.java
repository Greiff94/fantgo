package com.example.fantgo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fantgo.R;
import com.example.fantgo.adapter.ItemListAdapter;
import com.example.fantgo.model.Item;
import com.example.fantgo.retrofit.APIClient;
import com.example.fantgo.retrofit.FantInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView itemsRecView;
    private ArrayList<Item> items = new ArrayList<>();
    private ItemListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        getActivity().setTitle("Home page");

        initViews(view);
        //Initial setup of all the items
        setItemList();

        // Makes the adapter convert the list of items given by the API
        adapter = new ItemListAdapter(getContext());
        adapter.setItems(items);

        itemsRecView.setAdapter(adapter);
        itemsRecView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setItemList();
            }
        });

        return view;
    }

    public void setItemList() {
        FantInterface fantAPI = APIClient.getClient().create(FantInterface.class);
        Call<List<Item>> call = fantAPI.getAllItems();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful()){
                    adapter.clear();
                    items = (ArrayList<Item>) response.body();
                    adapter.setItems(items);
                    swipeRefreshLayout.setRefreshing(false);
                }
                else {
                    Toast.makeText(getContext(), "Could not fetch items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getContext(), "something wong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initViews(View view)
    {
        itemsRecView = view.findViewById(R.id.itemsRecView);
        swipeRefreshLayout = view.findViewById(R.id.nav_home);
    }
}
