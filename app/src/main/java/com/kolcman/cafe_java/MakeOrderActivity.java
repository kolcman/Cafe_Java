package com.kolcman.cafe_java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";

    private TextView textviewGreetings;
    private RadioGroup radioGroupDrink;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;
    private TextView textviewAdditives;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private Button buttonMakeOrder;

    private String userName;
    private String drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_make_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        setUpUserName();
        radioGroupDrink.setOnCheckedChangeListener((group, id) -> {
            if (id == radioButtonTea.getId()) {
                onUserChoseTea();
            } else if (id == radioButtonCoffee.getId()) {
                onUserChoseCoffee();
            }
        });
        radioButtonTea.setChecked(true);
        buttonMakeOrder.setOnClickListener(v -> {
            onUserMadeOrder();
        });

    }

    private void onUserChoseTea() {
        drink = getString(R.string.tea_radiobutton);
        String additivesLabel = getString(R.string.additives_textview, drink);
        textviewAdditives.setText(additivesLabel);
        checkBoxLemon.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseCoffee() {
        drink = getString(R.string.coffee_radiobutton);
        String additivesLabel = getString(R.string.additives_textview, drink);
        textviewAdditives.setText(additivesLabel);
        checkBoxLemon.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }

    private void initViews() {
        textviewGreetings = findViewById(R.id.textviewGreetings);
        radioGroupDrink = findViewById(R.id.radioGroupDrink);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
        textviewAdditives = findViewById(R.id.textviewAdditives);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
    }

    private void setUpUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings_textview, userName);
        textviewGreetings.setText(greetings);
    }

    private void onUserMadeOrder() {
        ArrayList<String> additives = new ArrayList<>();
        if (checkBoxSugar.isChecked()) {
            additives.add(checkBoxSugar.getText().toString());
        }
        if (checkBoxMilk.isChecked()) {
            additives.add(checkBoxMilk.getText().toString());
        }
        if (radioButtonTea.isChecked() && checkBoxLemon.isChecked()) {
            additives.add(checkBoxLemon.getText().toString());
        }

        String drinkType = "";
        if (radioButtonTea.isChecked()) {
            drinkType = spinnerTea.getSelectedItem().toString();
        } else if (radioButtonCoffee.isChecked()) {
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }

        Intent intent = OrderDetailActivity.newIntent(
                this,
                userName,
                drink,
                drinkType,
                additives.toString()
        );
        startActivity(intent);
    }
}