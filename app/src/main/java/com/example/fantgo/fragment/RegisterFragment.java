package com.example.fantgo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fantgo.R;
import com.example.fantgo.retrofit.APIClient;
import com.example.fantgo.retrofit.FantInterface;


public class RegisterFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        usernameEditText = view.findViewById(R.id.ruid);
        passwordEditText = view.findViewById(R.id.rpwd);
        emailEditText = view.findViewById(R.id.remail);

        Button rbutton = (Button) view.findViewById(R.id.rbutton);
        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.rbutton:
                        userSignUp();
                        break;
                    case R.id.rchangescene:
                        changeToLogin();
                        break;
                }

            }
        });
        return view;
    }

    private void changeToLogin() {
        Fragment fragment = new LoginFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }

    private void userSignUp() {
        String uid = usernameEditText.getText().toString().trim();
        String pwd = passwordEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (uid.isEmpty()) {
            usernameEditText.setError("Please enter a username");
            usernameEditText.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email");
            emailEditText.requestFocus();
            return;

        }
        if (pwd.length() < 3) {
            passwordEditText.setError("Password must be 6 characters or longer");
            passwordEditText.requestFocus();
            return;
        }


        // if all requirements are valid, sign up the user through the api.

        FantInterface api = APIClient.getClient().create(FantInterface.class);
        Call<ResponseBody> call = api.registerUser(uid, pwd, email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null) {
                    Activity rAct = getActivity();
                    String s = response.body().toString();
                    Toast.makeText(rAct, s, Toast.LENGTH_SHORT).show();
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