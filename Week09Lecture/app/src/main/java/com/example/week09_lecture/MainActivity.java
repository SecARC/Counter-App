package com.example.week09_lecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String KEY_COUNTER = "com.example.week09_lecture.KEY_COUNTER";

    EditText editTextTextPersonName;
    Button buttonStart, buttonPlus, buttonMinus, buttonReset;
    TextView textViewNumber;

    private int counter;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(this);

        init_UI();

        editTextTextPersonName.setText(session.getName());
        counter = session.getCounter();
        updateNumber();
    }

    private void init_UI() {
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        textViewNumber = (TextView) findViewById(R.id.textViewNumber);

        editTextTextPersonName.setEnabled(true);
        buttonMinus.setEnabled(false);
        buttonPlus.setEnabled(false);
        buttonStart.setEnabled(true);
        buttonReset.setEnabled(false);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTextPersonName.setEnabled(false);
                buttonMinus.setEnabled(true);
                buttonPlus.setEnabled(true);
                buttonStart.setEnabled(false);
                buttonReset.setEnabled(true);


                if(!editTextTextPersonName.getText().toString().trim().equals(session.getName().trim())){
                    session.setName(editTextTextPersonName.getText().toString());
                    counter = 0;
                    updateNumber();
                } else {
                    counter = session.getCounter();
                    updateNumber();
                }
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                updateNumber();
                session.setCounter(counter);
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter == 0)
                    Toast.makeText(MainActivity.this, "Counter can not be less than zero", Toast.LENGTH_SHORT).show();
                else{
                    counter--;
                    updateNumber();
                    session.setCounter(counter);
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                updateNumber();
                session.setCounter(counter);
            }
        });
    }

    private void updateNumber(){
        textViewNumber.setText("Number: " + counter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_COUNTER, counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        counter = savedInstanceState.getInt(KEY_COUNTER);
        updateNumber();
    }
}