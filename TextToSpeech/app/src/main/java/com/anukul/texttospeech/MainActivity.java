package com.anukul.texttospeech;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private EditText speechTextEd;
    private Button speechBtn;
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechTextEd = findViewById(R.id.activity_main_speechTextEd);
        speechBtn = findViewById(R.id.activity_main_speechBtn);

        speechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSpeech();
            }
        });
    }

    private void performSpeech() {
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent,AppConstant.TTS_ENGINE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == AppConstant.TTS_ENGINE_REQUEST && resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
            textToSpeech = new TextToSpeech(this,this);
        }else{
            //api not available so
            Intent installIntent = new Intent();
            installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
        }
    }

    @Override
    public void onInit(int status) {
        //when the engine start successfully so this method will be involc
         if(status == TextToSpeech.SUCCESS){
             int languageStatus = textToSpeech.setLanguage(Locale.US);
             if(languageStatus == TextToSpeech.LANG_MISSING_DATA || languageStatus == TextToSpeech.LANG_NOT_SUPPORTED){
                 Toast.makeText(this, "language not supported", Toast.LENGTH_SHORT).show();
             }else{
                 String data = speechTextEd.getText().toString();
                 int speechStatus = textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);
                 if(speechStatus == TextToSpeech.ERROR){
                     Toast.makeText(this, "Error while speech", Toast.LENGTH_SHORT).show();
                 }
             }

         }else{
             Toast.makeText(this, "Text to Speech  engine failed", Toast.LENGTH_SHORT).show();
         }
    }
}
