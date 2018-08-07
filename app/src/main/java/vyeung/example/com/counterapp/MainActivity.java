package vyeung.example.com.counterapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected int count = 0;
    TextView displayCount;
    Switch lightSwitch;
    ConstraintLayout main_layout;
    Button incButton, decButton, resetButton;
    SharedPreferences sharedPrefs;
    Vibrator vibrator;
    MediaPlayer incSound;
    MediaPlayer decSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = MainActivity.this;
        sharedPrefs = context.getSharedPreferences(
                "CurrentUser", Context.MODE_PRIVATE);

        count = sharedPrefs.getInt("currCount", 0);
        displayCount = findViewById(R.id.countValue);
        displayCount.setText(Integer.toString(count));
        lightSwitch = findViewById(R.id.lightSwitch);
        main_layout = (ConstraintLayout) findViewById(R.id.main_layout);
        incButton = findViewById(R.id.incButton);
        decButton = findViewById(R.id.decButton);
        resetButton = findViewById(R.id.resetButton);

        lightSwitch.setChecked(Boolean.TRUE);
        incButton.setBackgroundColor(Color.LTGRAY);
        decButton.setBackgroundColor(Color.LTGRAY);
        resetButton.setBackgroundColor(Color.RED);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        incSound = MediaPlayer.create(this, R.raw.add_pop);
        decSound = MediaPlayer.create(this, R.raw.dec_pop);


        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setLightScheme();

                }
                else {
                    setDarkScheme();
                }
            }
        });

    } // onCreate

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor prefsEditor = sharedPrefs.edit();
        prefsEditor.putInt("currCount", count);
        prefsEditor.commit();
    }

    public void incrementCount(View view) {
        count++;
        incSound.start();
        vibrator.vibrate(20);
        displayCount.setText(Integer.toString(count));
    }

    public void decrementCount(View view) {
        if (count > 0) {
            count--;
            decSound.start();
            // creates a double vibration effect
            // 0 means start immediately.
            // Then vibrate for 10, off for 50, then vibrate for 10
            long[] pattern = {0, 10, 100, 10};
            vibrator.vibrate(pattern, -1);
        }
        displayCount.setText(Integer.toString(count));
    }

    public void resetCount (View view) {
        count = 0;
        vibrator.vibrate(1000);
        displayCount.setText(Integer.toString(count));
    }

    protected void setLightScheme() {
        main_layout.setBackgroundColor(Color.WHITE);
        displayCount.setTextColor(Color.BLACK);
        lightSwitch.setText("Light");
        lightSwitch.setTextColor(Color.BLACK);
        incButton.setBackgroundColor(Color.LTGRAY);
        incButton.setTextColor(Color.BLACK);
        decButton.setBackgroundColor(Color.LTGRAY);
        decButton.setTextColor(Color.BLACK);

    }

    protected void setDarkScheme() {
        main_layout.setBackgroundColor(Color.BLACK);
        displayCount.setTextColor(Color.WHITE);
        lightSwitch.setText("Dark");
        lightSwitch.setTextColor(Color.WHITE);
        incButton.setBackgroundColor(getResources().getColor(R.color.dkgray));
        incButton.setTextColor(Color.WHITE);
        decButton.setBackgroundColor(getResources().getColor(R.color.dkgray));
        decButton.setTextColor(Color.WHITE);

    }

}
