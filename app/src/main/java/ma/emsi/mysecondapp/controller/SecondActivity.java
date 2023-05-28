package ma.emsi.mysecondapp.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import ma.emsi.mysecondapp.R;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.myTextViewwww);
        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("enteredValue");
        textView.setText(receivedValue);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("The value you entered is  " + receivedValue)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("OK Clicked");
                        Intent intent = new Intent();
                        intent.putExtra("BUNDLE_EXTRA_SCORE", 1);
                        setResult(RESULT_OK, intent); //return -1
                        finish(); //closes activity
                    }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("Cancel Clicked");
                        Intent intent = new Intent();
                        intent.putExtra("BUNDLE_EXTRA_SCORE", 0);
                        setResult(RESULT_CANCELED, intent); // return 0
                        finish(); //closes activity
                    }

                })
                .create()
                .show();
    }
    @Override //whenever we touch the screen
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Toast.makeText(getApplicationContext(), "Vous avez touché l'ecran !!!", Toast.LENGTH_LONG).show();
        System.out.println("Vous avez touché l'ecran !!!");
        return super.dispatchTouchEvent(ev);
    }
}