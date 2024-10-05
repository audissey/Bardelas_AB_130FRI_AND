package com.example.mycalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputField;
    private String operator = "";
    private double operand1 = 0;
    private double operand2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);

        // Optional: Limit input to valid decimal numbers
        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Logic to restrict input can be added here if needed
            }
        });
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        inputField.append(button.getText());
    }

    public void onDotClick(View view) {
        // Add a dot to the input field if it doesn't already contain one
        String currentText = inputField.getText().toString();
        if (!currentText.contains(".")) {
            inputField.append(".");
        }
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();
        if (!inputField.getText().toString().isEmpty()) {
            operand1 = Double.parseDouble(inputField.getText().toString());
            inputField.setText(""); // Clear input field for next number
        }
    }

    public void onClearClick(View view) {
        inputField.setText("");
        operator = ""; // Reset operator
        operand1 = 0;  // Reset operands
        operand2 = 0;
    }

    public void onCalculateClick(View view) {
        if (!inputField.getText().toString().isEmpty() && !operator.isEmpty()) {
            operand2 = Double.parseDouble(inputField.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }

            inputField.setText(String.valueOf(result)); // Show result in input field
            operator = ""; // Reset operator
            operand1 = result; // Store result as the first operand for further calculations
        } else {
            Toast.makeText(this, "Please enter a valid expression", Toast.LENGTH_SHORT).show();
        }
    }
}
