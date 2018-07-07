package vyeung.example.com.counterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected int count = 0;
    TextView displayCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayCount = findViewById(R.id.countValue);

    }

    public void incrementCount(View view) {
        count++;
        displayCount.setText(Integer.toString(count));
    }

    public void decrementCount(View view) {
        count--;
        displayCount.setText(Integer.toString(count));
    }

    public void resetCount (View view) {
        count = 0;
        displayCount.setText(Integer.toString(count));
    }
}
