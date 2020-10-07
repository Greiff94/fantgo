package com.example.fantgo.fragment;

import android.app.Activity;
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
import com.example.fantgo.model.User;
import com.example.fantgo.retrofit.APIClient;
import com.example.fantgo.retrofit.FantInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private static User user = new User();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_login, container, false);

            usernameEditText = view.findViewById(R.id.luid);
            passwordEditText = view.findViewById(R.id.lpwd);

        Button lbutton = (Button) view.findViewById(R.id.lbutton);
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.lbutton:
                        userLogin();
                        break;
                }
            }
        });
        return view;
    }

    private void userLogin() {
        String uid = usernameEditText.getText().toString().trim();
        String pwd = passwordEditText.getText().toString().trim();

        if (uid.isEmpty()) {
            usernameEditText.setError("Please enter a username");
            usernameEditText.requestFocus();
            return;
        }
        if (pwd.isEmpty()) {
            passwordEditText.setError("Password must be 6 characters or longer");
            passwordEditText.requestFocus();
            return;
        }

        FantInterface api = APIClient.getClient().create(FantInterface.class);
        Call<ResponseBody> call = api.login(uid, pwd);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    user.setJwt(response.body().toString());
                    Activity rAct = getActivity();
                    String s = response.body().toString();
                    System.out.println(s);
                    Toast.makeText(rAct, "logged in!", Toast.LENGTH_SHORT).show();
                    Fragment fragment = new RegisterFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment).commit();
                } else {
                    Toast.makeText(getActivity(), "loggin failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Activity rAct = getActivity();
                Toast.makeText(rAct, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
