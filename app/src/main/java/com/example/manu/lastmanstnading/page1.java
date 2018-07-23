package com.example.manu.lastmanstnading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class page1 extends AppCompatActivity {
    private Button Login;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        Login = (Button)findViewById(R.id.btn1);

        Password = (EditText)findViewById(R.id.etxt1);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Password.getText().toString().equals("007")) {
                    Intent intent = new Intent(page1.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } });

    }
}
