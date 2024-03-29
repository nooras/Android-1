package com.example.mariyam.frostyy;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private Button btnlogin;
    private Button btnGoSignup;
    private FirebaseAuth mAuth;
    private EditText edtemail,edtpassword;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Intent intent=this.getIntent();
        if(intent !=null)
            Name= intent.getStringExtra("Name");
         Log.d("Frostyy",Name);
         System.out.print(Name);

        mAuth = FirebaseAuth.getInstance();

        btnlogin = (Button) findViewById(R.id.btnlogin);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtpassword = (EditText) findViewById(R.id.edtpassword);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getEmail = edtemail.getText().toString().trim();
                String getpass = edtpassword.getText().toString().trim();
                if (getEmail == null && getpass == null) {
                    CallSignin(getEmail, getpass);
                    Toast.makeText(login.this,"Please fill the Detail!",Toast.LENGTH_SHORT).show();


                }
                    else{
                    CallSignin(getEmail, getpass);


                }

            }
        });

        btnGoSignup = (Button) findViewById(R.id.btnGoSignup);
        btnGoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aa = new Intent(login.this,Signup.class);
                startActivity(aa);
            }
        });
    }

    private void CallSignin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(login.this, "Authentication Successful.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, Category.class);
                            intent.putExtra("Name",Name);

                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
