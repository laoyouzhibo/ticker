package com.robinhood.ticker.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.robinhood.ticker.TickerView;

import java.time.format.TextStyle;
import java.util.Random;

public class MainActivity extends BaseActivity {
    private final String alphabetlist = "abcdefghijklmnopqrstuvwxyz";

    private TickerView ticker1, ticker2, ticker3, ticker4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ticker1 = findViewById(R.id.ticker1);
        ticker2 = findViewById(R.id.ticker2);
        ticker3 = findViewById(R.id.ticker3);
        ticker4 = findViewById(R.id.ticker4);

        ticker1.setPreferredScrollingDirection(TickerView.ScrollingDirection.DOWN);
        ticker2.setPreferredScrollingDirection(TickerView.ScrollingDirection.UP);
        ticker3.setPreferredScrollingDirection(TickerView.ScrollingDirection.ANY);
        ticker4.setPreferredScrollingDirection(TickerView.ScrollingDirection.ANY);
        ticker4.setTextStyle(Typeface.BOLD, ResourcesCompat.getFont(this, R.font.din_alternate_bold));

        findViewById(R.id.perfBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PerfActivity.class));
            }
        });
    }

    @Override
    protected void onUpdate() {
        final int digits = RANDOM.nextInt(2) + 6;

        ticker1.setText(getRandomNumber(digits));
        final String currencyFloat = Float.toString(RANDOM.nextFloat() * 100);
        ticker2.setText("$" + currencyFloat.substring(0, Math.min(digits, currencyFloat.length())));
        ticker3.setText(generateChars(RANDOM, alphabetlist, digits));
        ticker4.setText(getSt());
    }

    private String getSt() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (int i = 1;i <= 8; i++) {
                stringBuilder.append(RANDOM.nextInt(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String generateChars(Random random, String list, int numDigits) {
        final char[] result = new char[numDigits];
        for (int i = 0; i < numDigits; i++) {
            result[i] = list.charAt(random.nextInt(list.length()));
        }
        return new String(result);
    }
}
