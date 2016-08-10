package pracc06.metropolitan.fit.rs.pracc06;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private Button saveUser, displayUser;
    private TextView outputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onSaveUserListener();
        onDisplayUserListener();
    }

    private void onSaveUserListener() {
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        saveUser = (Button) findViewById((R.id.saveUser));

        saveUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences userPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPreferences.edit();

                        editor.putString("username", inputUsername.getText().toString());
                        editor.putString("password", inputPassword.getText().toString());
                        editor.apply();

                        Toast.makeText(MainActivity.this, "User details saved to Shared Preferences.", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    private void onDisplayUserListener() {
        outputMessage = (TextView) findViewById(R.id.outputMessage);
        displayUser = (Button) findViewById((R.id.displayUser));

        displayUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences userPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

                        // 2nd parameter is what is returned
                        String username = userPreferences.getString("username", "");
                        String password = userPreferences.getString("password", "");

                        outputMessage.setText("username: " + username + " | password: " + password);

                    }
                }
        );
    }
}
