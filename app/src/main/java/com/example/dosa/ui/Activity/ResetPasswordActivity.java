package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dosa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        TextView txtMessage = findViewById(R.id.txtMessageForgot);
        findViewById(R.id.btnComfirm).setOnClickListener(view->{
            String email = ((TextView)findViewById(R.id.edtEmailForgot)).getText().toString();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.setLanguageCode("vi");
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>()    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                txtMessage.setText("Đã gửi mail. Vui lòng kiểm tra mail của bạn.");
                            }
                        }
                    });
        });

        findViewById(R.id.txtBackArrowForgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
            }
        });
        findViewById(R.id.txtBackForgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
            }
        });
    }
}