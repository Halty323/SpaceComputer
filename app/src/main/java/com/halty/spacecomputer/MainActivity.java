package com.halty.spacecomputer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int[] delayMinute = {20, 42, 85};
    // массивы пришлось сделать float потому что с учетом длительности вспышки результат должен быть точным
    private final float[] volumeData = {14, 1250, 883};
    private final float[] sunflareDuration = {60, 70, 85}; // длительность вспышек на каждой планете
    private final int FREQUENCY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView output = findViewById(R.id.result);
        output.setText(coreTime(delayMinute, volumeData) + " секунд");
    }

    private int delaySecond(int delayMinute) {
        return delayMinute * 60;
    }

    private float volumeTime(float volumeData) {
        return volumeData / FREQUENCY;
    }

    private int coreTime(int[] delayMinute, float[] volumeData) {
        int[] delay = new int[delayMinute.length];
        for (int i = 0; i < delay.length; i++) {
            delay[i] = delaySecond(delayMinute[i]);
        }

        float[] volume = new float[volumeData.length];
        for (int i = 0; i < volume.length; i++) {
            volume[i] = volumeTime(volumeData[i] * (sunflareDuration[i] / 60)); // увеличить количество данных в соответствии с длительностью солнечной вспышки на каждой планете
        }

        int count = 0;
        int countOperation = 0;
        boolean run = true;

        while(run) {
            count++;
            run = false;

            for (int i = 0; i < delay.length; i++) {
                if (delay[i] > 0) {
                    delay[i]--;
                    run = true;
                } else {
                    if (volume[i] > 0) {
                        volume[i]--;
                        countOperation++;
                        if (countOperation > 1) {
                            count++;
                            countOperation--;
                        }
                        run = true;
                    }
                }
            }
        }
        return count;
    }
}