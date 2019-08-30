package com.anjilibey.inventori;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText etname, etemail, etno, etpass, etconpass;
    Button btnSubmit;
    FirebaseAuth auth;
    FirebaseDatabase database;
    TextView signin;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);
        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etno = findViewById(R.id.etno);
        etpass = findViewById(R.id.etpass);
        etconpass = findViewById(R.id.etconpass);
        btnSubmit = findViewById(R.id.btnPass);
        signin = findViewById(R.id.signin);
        auth= FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);
        registerUser();
    }

    private void registerUser() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auth.getCurrentUser()!=null){
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    final String email = etemail.getText().toString().trim();
                    final String pass = etpass.getText().toString().trim();
                    final String no = etno.getText().toString().trim();
                    final String name = etname.getText().toString().trim();
                    String conpass = etconpass.getText().toString().trim();

                    //validasi email dan password
                    // jika email kosong
                    if (name.isEmpty()){
                        etname.setError("Nama tidak boleh kosong");
                    }
                    else if (no.isEmpty()){
                        etno.setError("nomor tidak boleh kosong");
                    }
                    else if (email.isEmpty()){
                        etemail.setError("nama tidak boleh kosong");
                    }
                    // jika email not valid
                    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        etemail.setError("Email tidak valid");
                    }
                    // jika password kosong
                    else if (pass.isEmpty()){
                        etpass.setError("Password tidak boleh kosong");
                    }
                    //jika password kurang dari 6 karakter
                    else if (pass.length() < 6){
                        etpass.setError("Password minimal terdiri dari 6 karakter");
                    }
                    else if (conpass.isEmpty()){
                        etconpass.setError("anda belum mengonfirmasi password");
                    }
//                    else if (conpass != pass){
//                        etconpass.setError("Password tidak sama");
//                    }
                    else{
                        progressDialog.setMessage("Registering Please Wait...");
                        progressDialog.show();
                        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //display some message here
                                    Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }else{
                                    //display some message here
                                    Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
//                        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if(task.isSuccessful()){
//                                    User user = new User(name, no, email, pass);
//                                    FirebaseDatabase.getInstance().getReference("users")
//                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if(task.isSuccessful()){
//                                                Toast.makeText(RegisterActivity.this, "user created successfully", Toast.LENGTH_LONG).show();
//                                            }
//                                            else {
//                                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                                                Toast.makeText(RegisterActivity.this, "not succesfull", Toast.LENGTH_LONG).show();
//                                            }
//                                            progressDialog.dismiss();
//                                        }
//                                    });
//                                }else{
//                                    progressDialog.dismiss();
//                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
                            }
//                        });
                    }
                }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}
