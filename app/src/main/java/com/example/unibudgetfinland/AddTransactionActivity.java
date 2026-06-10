package com.example.unibudgetfinland;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddTransactionActivity extends AppCompatActivity {

    EditText edtAmount;
    RadioButton rbIncome, rbExpense;
    Spinner spinnerCategory;
    Button btnSave;

    public static ArrayList<String> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        edtAmount = findViewById(R.id.edtAmount);
        rbIncome = findViewById(R.id.rbIncome);
        rbExpense = findViewById(R.id.rbExpense);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnSave);

        String[] categories = {
                "Salary", "Kela", "Scholarship", "Rent",
                "Groceries", "Transport", "Phone Bill",
                "Shopping", "Eating Out", "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String amount = edtAmount.getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();

            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
                return;
            }

            String type;

            if (rbIncome.isChecked()) {
                type = "Income";
            } else if (rbExpense.isChecked()) {
                type = "Expense";
            } else {
                Toast.makeText(this, "Please select income or expense", Toast.LENGTH_SHORT).show();
                return;
            }

            String transaction = type + " - " + category + " - €" + amount;
            transactions.add(transaction);

            Toast.makeText(this, "Transaction saved", Toast.LENGTH_SHORT).show();

            edtAmount.setText("");
            rbIncome.setChecked(false);
            rbExpense.setChecked(false);
        });
    }
}