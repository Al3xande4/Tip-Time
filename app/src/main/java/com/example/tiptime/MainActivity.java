package com.example.tiptime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tiptime.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.calculateButton.setOnClickListener((view) -> calculateTip());
    }

    public void calculateTip(){
        String stringCost = binding.costOfService.getText().toString();
        if("".equals(stringCost)){
            Toast.makeText(this, "Uncorrect symbol, please enter a digit", Toast.LENGTH_SHORT).show();
            return;
        }
        Double cost = Double.valueOf(stringCost);
        double precenct = 0;
        int buttonId = binding.tipOption.getCheckedRadioButtonId();
        switch (buttonId){
            case R.id.option_twenty_percent: precenct = .2; break;
            case R.id.option_eighteen_percent: precenct = .18; break;
            case R.id.option_fifteen_percent: precenct = .15; break;
        }
        Double tip = cost * precenct;
        if(binding.roundTip.isChecked()){
            tip = Math.ceil(tip);
        }
        String formatTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.amountText.setText(getString(R.string.tip_amount, formatTip));
    }
}