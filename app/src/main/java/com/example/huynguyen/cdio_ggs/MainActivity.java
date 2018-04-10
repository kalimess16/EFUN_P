package com.example.huynguyen.cdio_ggs;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public TextView textView;
    public TextView view;
    private Button buttonSS,buttonChat;
    private ImageButton buttonvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        buttonvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInPutVoid();
            }
        });
        buttonSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
                   textView.getText().toString().equals(view.toString());
                    Toast.makeText(getApplicationContext(),"Right",Toast.LENGTH_LONG).show();
                }
                catch (ActivityNotFoundException a){
                    Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).toString();
                }

            }
        });
    }

    private void AnhXa() {
        textView = findViewById(R.id.text1);
        view = findViewById(R.id.tbThu);
        buttonvoice = findViewById(R.id.btv);
        buttonChat=findViewById(R.id.btSS);
        buttonChat=findViewById(R.id.btC);

    }

    public void getInPutVoid() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        try{
            startActivityForResult(intent,100);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),"Your Device Don't Support Speech Input",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                view.setText(result.get(0));
            }
        }
    }
}
