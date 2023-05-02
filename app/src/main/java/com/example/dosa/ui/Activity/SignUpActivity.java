package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dosa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText edtEmail, edtName, edtPassword;
    TextView txtMessage;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        edtEmail = findViewById(R.id.edtEmailSignUp);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtMessage = findViewById(R.id.txtMessageSignUp);

        findViewById(R.id.txtSignIn).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });

        btnSignUp.setOnClickListener(view -> {

            String email = edtEmail.getText().toString();
            String name = edtName.getText().toString();
            String password = edtPassword.getText().toString();

            if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
                txtMessage.setText("Vui lòng nhập đầy đủ thông tin");
                txtMessage.setVisibility(View.VISIBLE);
                return;
            }

            if (!validate(email)) {
                txtMessage.setText("Email không đúng định dạng");
                txtMessage.setVisibility(View.VISIBLE);
                return;
            }
            if (password.length() < 6) {
                txtMessage.setText("Mật khẩu phải chứa ít nhất 6 kí tự");
                txtMessage.setVisibility(View.VISIBLE);
                return;
            }
            createAccountOnServer(email, name, password);
        });
    }

    private void createAccountOnServer(String email, String name, String password) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("name", name);
        user.put("password", password);

        db.collection("User")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        // Store session
                        SharedPreferences preferences = getSharedPreferences(MainActivity.ACCOUNTPREFERENCES, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("id", documentReference.getId());
                        editor.putString("name", name);
                        editor.commit();

                        // to Home
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                });
    }


    public static boolean validate(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}