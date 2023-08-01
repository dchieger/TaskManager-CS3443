package cs3443.teamshoemaker.taskmanager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class LoginActivity extends AppCompatActivity {

    // objects
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginbutton;
    private TextView signbutton;
    FirebaseAuth mAuth;


    // When initializing your Activity, check to see if the user is currently signed in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find views set id
        emailEditText = findViewById(R.id.emailEdit);
        passwordEditText = findViewById(R.id.passwordEdit);
        loginbutton = findViewById(R.id.loginButton);
        signbutton=findViewById(R.id.signupText);
        mAuth = FirebaseAuth.getInstance();


            // open login Activity
        signbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),signupActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // read text'
                String email,password;
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                // email and pass empty or not
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_LONG).show();
                    return;
                }
                // SIGN IN WITH EMAIL AND PASSWORD
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}

