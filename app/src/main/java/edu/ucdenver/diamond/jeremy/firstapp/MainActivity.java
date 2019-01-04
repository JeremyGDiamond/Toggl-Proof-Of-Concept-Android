package edu.ucdenver.diamond.jeremy.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //user code


        Button runButtn = (Button) findViewById(R.id.runButtn);
        runButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkBackgroundStop stop = new NetworkBackgroundStop();
                stop.execute();

            }
        });

        Button timerTestButton = (Button) findViewById(R.id.timerTestButton);
        timerTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkBackgroundRunTest test = new NetworkBackgroundRunTest();
                test.execute();

            }
        });
    }
}
