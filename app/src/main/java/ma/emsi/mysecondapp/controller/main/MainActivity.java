package ma.emsi.mysecondapp.controller.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ma.emsi.mysecondapp.R;
import ma.emsi.mysecondapp.controller.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private EditText et1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            System.out.println("this activity has be re-created du to re-orientation");
        } else {
            System.out.println("this activity has wan't re-created");

        }

        tv1 = findViewById(R.id.myTextVieww);
        tv2 = findViewById(R.id.myTextView);
        et1 = findViewById(R.id.myEditText);
        btn1 = findViewById(R.id.myButton);
        btn1.setEnabled(false);

        SharedPreferences preferences = getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SHARED_PREF_USER_INFO_NAME", "Fahd");
        editor.putString("SHARED_PREF_USER_INFO_LAST_NAME", "KORAICHE");
        editor.apply();
        tv1.setText(tv1.getText() +
                preferences.getString("SHARED_PREF_USER_INFO_NAME", null) +
                " " +
                preferences.getString("SHARED_PREF_USER_INFO_LAST_NAME", null)
        );
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("here is the new written value : " + s.toString());
                if(s.toString().length() > 4){
                    btn1.setEnabled(true);
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "the string you entered is bigger than 4", Toast.LENGTH_LONG).show();
                Intent firstChangeOfActivity = new Intent(MainActivity.this, SecondActivity.class);
                firstChangeOfActivity.putExtra("enteredValue", et1.getText().toString());
                startActivityForResult(firstChangeOfActivity, 42); //42 to specify which of our starts
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42) {
            Toast.makeText(getApplicationContext(), "you cliecked on " + resultCode, Toast.LENGTH_LONG).show();
            //Temporisation !!!
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "You must be tired of playing huh !", Toast.LENGTH_LONG).show();

                }
            }, 5_000); // LENGTH_SHORT is usually 2 second long
        }

    }
    @Override //whenever we touch the screen
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Toast.makeText(getApplicationContext(), "Vous avez touché l'ecran !!!", Toast.LENGTH_LONG).show();
        System.out.println("Vous avez touché l'ecran !!!");
        //we can myVar && uper.dispatchTouchEvent(ev); to stop triggering th events of onClick according to the value of our myVar
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("BUNDLE_STATE_SCORE", 1);
        outState.putInt("BUNDLE_STATE_QUESTION", 5);
        //here we can save data for after the user re-orient the device so that when onCreated
        // is recalled , we'd still have our data saved, that is if there is some dynamicality in
        //the activity that requies "saving steps or last choices..."
    }


}