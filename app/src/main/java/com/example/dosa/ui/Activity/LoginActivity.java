package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dosa.databinding.ActivityMain2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class LoginActivity extends AppCompatActivity {

    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.txtForgotPassword.setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), OTPActivity.class));
        });

        binding.btnLogin.setOnClickListener(view->{
            String email = binding.edtUsername.getText().toString();
            String password = binding.edtPassword.getText().toString();
            authenticateUser(email, password);


        });

        binding.txtSignIn.setOnClickListener(view -> {

            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });


    }

    private void authenticateUser(String email, String password) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User")
                .whereEqualTo("email", email)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                binding.txtMessage.setText("Sai email hoặc mật khẩu");
                                binding.txtMessage.setVisibility(View.VISIBLE);
                            }
                            else {
                                QueryDocumentSnapshot document = task.getResult().iterator().next();
//                                Log.d("FirebaseFirestore", document.get("name").toString());
                                // Store session
                                SharedPreferences preferences = getSharedPreferences(MainActivity.ACCOUNTPREFERENCES, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("id", document.getId());
                                editor.putString("name", document.get("name").toString());
                                editor.commit();

                                // to Home
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                            }
                        } else {
                            Log.w("FirebaseFirestore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}