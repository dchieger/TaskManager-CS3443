package cs3443.teamshoemaker.taskmanager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find views set id
        emailEditText = findViewById(R.id.emailEdit);
        passwordEditText = findViewById(R.id.passwordEdit);
        loginbutton = findViewById(R.id.loginButton);

        //set onClick Listeners
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String enteredEmail = emailEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();
                // Compare the entered credentials with the stored credentials
                boolean isAuthenticated = checkCredentials(enteredEmail, enteredPassword);

                // Compare the entered credentials with the stored credentials
                if (isAuthenticated) {
                    // Authentication successful
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                    //pass email to todolist
                    Intent intent = new Intent(LoginActivity.this, todoList.class);
                    intent.putExtra("email", enteredEmail); // Pass the logged-in user's email as an extra
                    startActivity(intent);

                       //startActivity((new Intent(LoginActivity.this, todoList.class)));
                } else {
                    // Authentication failed
                    Toast.makeText(LoginActivity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //load json and fetch
    private boolean checkCredentials(String email, String password) {
        // Read JSON data from the "users.json" file in the "assets" folder
        String json;
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("loginDB.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Parse JSON data to extract user credentials and perform authentication
        try {
            JSONObject jsonData = new JSONObject(json);
            JSONArray usersArray = jsonData.getJSONArray("users");
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObject = usersArray.getJSONObject(i);
                String storedEmail = userObject.getString("email");
                String storedPassword = userObject.getString("password");
                if (email.equals(storedEmail) && password.equals(storedPassword)) {
                    // Authentication successful
                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Authentication failed
        return false;
    }
}

