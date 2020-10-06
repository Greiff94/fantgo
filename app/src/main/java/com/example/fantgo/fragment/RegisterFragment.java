package com.example.fantgo.fragment;

import android.os.Bundle;
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

import com.example.fantgo.R;
import com.example.fantgo.retrofit.APIClient;
import com.example.fantgo.retrofit.FantInterface;


public class RegisterFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Button registerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        usernameEditText = findViewById(R.id.newUser);
        passwordEditText = findViewById(R.id.newPassword);
        emailEditText = findViewById(R.id.newEmail);
        registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameEditText.getText().toString().length() == 0 ||
                        passwordEditText.getText().toString().length() == 0)
                {
                    //something
                }else{
                    registerUser();
                }
            }
        });
    }

    public void registerUser()
    {
        final String uid = usernameEditText.getText().toString();
        final String pwd = passwordEditText.getText().toString();
        final String email = emailEditText.getText().toString();

        FantInterface api = APIClient.getClient().create(FantInterface.class);

        Call<ResponseBody> call = api.registerUser(uid, pwd, email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(com.example.fantgo.fragment.RegisterFragment.this, "Succesfull registration", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(com.example.fantgo.fragment.RegisterFragment.this, "u suck", Toast.LENGTH_SHORT).show();
            }
        });
    }

}