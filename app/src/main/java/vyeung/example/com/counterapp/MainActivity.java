package vyeung.example.com.counterapp;

import android.graphics.Color;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayCount = findViewById(R.id.countValue);
        lightSwitch = findViewById(R.id.lightSwitch);
        main_layout = (ConstraintLayout) findViewById(R.id.main_layout);
        incButton = findViewById(R.id.incButton);
        decButton = findViewById(R.id.decButton);
        resetButton = findViewById(R.id.resetButton);

        lightSwitch.setChecked(Boolean.TRUE);
        incButton.setBackgroundColor(Color.LTGRAY);
        decButton.setBackgroundColor(Color.LTGRAY);
        resetButton.setBackgroundColor(Color.RED);

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

    }

    public void incrementCount(View view) {
        count++;
        displayCount.setText(Integer.toString(count));
    }

    public void decrementCount(View view) {
        if (count > 0) {
            count--;
        }
        displayCount.setText(Integer.toString(count));
    }

    public void resetCount (View view) {
        count = 0;
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
        incButton.setBackgroundColor(Color.DKGRAY);
        incButton.setTextColor(Color.WHITE);
        decButton.setBackgroundColor(Color.DKGRAY);
        decButton.setTextColor(Color.WHITE);

    }

}
