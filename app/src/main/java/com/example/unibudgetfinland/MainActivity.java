package com.example.unibudgetfinland;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtIncome, txtExpense, txtBalance;
    Button btnAdd, btnHistory, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtIncome = findViewById(R.id.txtIncome);
        txtExpense = findViewById(R.id.txtExpense);
        txtBalance = findViewById(R.id.txtBalance);

        btnAdd = findViewById(R.id.btnAdd);
        btnHistory = findViewById(R.id.btnHistory);
        btnAbout = findViewById(R.id.btnAbout);

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddTransactionActivity.class)));

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HistoryActivity.class)));

        btnAbout.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AboutActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTotals();
    }

    private void updateTotals() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (String transaction : AddTransactionActivity.transactions) {
            String[] parts = transaction.split(" - €");

            if (parts.length == 2) {
                double amount = Double.parseDouble(parts[1]);

                if (transaction.startsWith("Income")) {
                    totalIncome += amount;
                } else if (transaction.startsWith("Expense")) {
                    totalExpense += amount;
                }
            }
        }

        double balance = totalIncome - totalExpense;

        txtIncome.setText("Total Income: €" + totalIncome);
        txtExpense.setText("Total Expenses: €" + totalExpense);
        txtBalance.setText("Balance: €" + balance);
    }
}