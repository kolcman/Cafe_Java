package com.kolcman.cafe_java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderDetailActivity extends AppCompatActivity {
    private static final String EXTRA_USER_NAME = "userName";
    private static final String EXTRA_DRINK_TYPE = "drinkType";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ADDITIVES = "additives";
    private TextView textViewName;
    private TextView textViewDrink;
    private TextView textViewDrinkType;
    private TextView textviewAdditives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        setUpText();
    }

    public static Intent newIntent(
            Context context,
            String userName,
            String drink,
            String drinkType,
            String additives
    ) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        return intent;
    }

    private void setUpText() {
        Intent intent = getIntent();
        textViewName.setText(intent.getStringExtra(EXTRA_USER_NAME));
        textViewDrink.setText(intent.getStringExtra(EXTRA_DRINK));
        textViewDrinkType.setText(intent.getStringExtra(EXTRA_DRINK_TYPE));
        textviewAdditives.setText(intent.getStringExtra(EXTRA_ADDITIVES));
    }

    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textviewAdditives = findViewById(R.id.textviewAdditives);
    }
}