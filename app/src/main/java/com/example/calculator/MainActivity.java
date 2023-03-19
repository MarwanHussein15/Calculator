package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity {

    private TextView tv_clear, tv_brackets, tv_percentage, tv_division, tv_multiplication, tv_minus, tv_plus, tv_equal, tv_zero,
            tv_decimalPoint, tv_plusMinus, tv_one, tv_two, tv_three, tv_four, tv_five, tv_six, tv_seven, tv_eight, tv_nine, tv_finalResult;

    private FloatingActionButton fab_delete;
    private EditText et_input;
    private static String input;
    private static int textLength;
    private static boolean lastInputIsOperator;
    private static String data = " ";
    private static boolean isDecimal;
    private static boolean bracketIsOpened;
    private static int consecutiveNumbersCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        views();
        clicks();

        et_input.setShowSoftInputOnFocus(false);
        et_input.setCursorVisible(true);

    }

    void views() {
        tv_finalResult = (TextView) findViewById(R.id.tv_finalResult);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_brackets = (TextView) findViewById(R.id.tv_brackets);
        tv_percentage = (TextView) findViewById(R.id.tv_percentage);
        tv_division = (TextView) findViewById(R.id.tv_division);
        tv_multiplication = (TextView) findViewById(R.id.tv_multiplication);
        tv_minus = (TextView) findViewById(R.id.tv_minus);
        tv_plus = (TextView) findViewById(R.id.tv_plus);
        tv_equal = (TextView) findViewById(R.id.tv_equal);
        tv_decimalPoint = (TextView) findViewById(R.id.tv_decimalPoint);
        tv_plusMinus = (TextView) findViewById(R.id.tv_plusMinus);
        tv_zero = (TextView) findViewById(R.id.tv_zero);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        tv_four = (TextView) findViewById(R.id.tv_four);
        tv_five = (TextView) findViewById(R.id.tv_five);
        tv_six = (TextView) findViewById(R.id.tv_six);
        tv_seven = (TextView) findViewById(R.id.tv_seven);
        tv_eight = (TextView) findViewById(R.id.tv_eight);
        tv_nine = (TextView) findViewById(R.id.tv_nine);
        et_input = (EditText) findViewById(R.id.et_input);
        fab_delete = (FloatingActionButton) findViewById(R.id.fab_delete);

    }

    void clicks() {

        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_input.getText().toString().isEmpty()) {
                    return;
                } else {
                    et_input.setText(et_input.getText().toString().substring(0, et_input.getText().toString().length() - 1));
                    data = data.substring(0, data.length() - 1);
                    consecutiveNumbersCounter--;
                }
            }
        });


        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_input.getText().clear();
                tv_finalResult.setText("");
                input = " ";
                data = " ";
                consecutiveNumbersCounter = 0;
                textLength = 0;
                lastInputIsOperator = false;
                isDecimal = false;
                bracketIsOpened = false;
            }
        });


        tv_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textLength = et_input.getText().toString().length();
                if (et_input.getText().toString().isEmpty()) {

                    et_input.setText(tv_zero.getText());


                } else if (et_input.getText().toString().equals("0")) {

                    et_input.setEnabled(false);

                } else if (consecutiveNumbersCounter >= 15) {

                    et_input.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Can't enter more than 15 digits", Toast.LENGTH_SHORT).show();

                } else {
                    input = et_input.getText().toString() + "0";
                    data = data + "0";
                    et_input.setText(input);
                    consecutiveNumbersCounter++;
                    lastInputIsOperator = false;

                }


                if (textLength >= 17) {
                    et_input.setTextSize(22);
                    et_input.setWidth(800);
                } else {
                    et_input.setTextSize(30);
                    et_input.setWidth(5800);

                }
            }
        });


        tv_one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                numClick("1");

            }
        });


        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numClick("2");
            }
        });


        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick("3");

            }


        });


        tv_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick("4");
            }
        });


        tv_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick("5");
            }
        });


        tv_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick("6");
            }
        });


        tv_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick("7");
            }
        });


        tv_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick("8");
            }
        });


        tv_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick("9");
            }
        });


        tv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClick("+", "+");
            }
        });


        tv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClick("-", "-");
            }
        });


        tv_multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                operatorClick("×", "*");


            }
        });


        tv_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                operatorClick("÷", "/");

            }
        });


        tv_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String finalResult = getResult(data);
                if (finalResult.endsWith(".0")) {
                    finalResult = finalResult.substring(0, finalResult.length() - 2);
                    tv_finalResult.setText(finalResult);
                    et_input.setText(finalResult);
                    data = finalResult;
                } else if (finalResult.equalsIgnoreCase("nan")) {
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();


                } else {
                    tv_finalResult.setText(finalResult);
                    et_input.setText(finalResult);
                    data = finalResult;

                }
            }
        });

        tv_decimalPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDecimal) {
                    return;
                } else {
                    if (et_input.getText().toString().isEmpty()) {
                        input = et_input.getText().toString() + "0" + ".";
                        et_input.setText(input);
                        data = data + ".";
                        isDecimal = true;
                    } else {
                        input = et_input.getText().toString() + tv_decimalPoint.getText().toString();
                        et_input.setText(input);
                        data = data + ".";
                        isDecimal = true;
                    }
                }
            }
        });

        tv_brackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bracketIsOpened) {
                    input = et_input.getText().toString() + ")";
                    // et_input.setTextColor(Color.GREEN);
                    data = data + ")";
                    et_input.setText(input);
                    bracketIsOpened = false;
                } else {
                    input = et_input.getText().toString() + "(";
                    et_input.setText(input);
                    data = data + "(";
                    bracketIsOpened = true;
                }

            }
        });


        tv_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_input.getText().toString().isEmpty() || lastInputIsOperator) {
                    Toast.makeText(MainActivity.this, "Invalid Format", Toast.LENGTH_SHORT).show();
                } else {
                    input = et_input.getText().toString() + "%";
                    lastInputIsOperator = true;
                    et_input.setText(input);
                    data = data + "%";
                }

            }
        });


        tv_plusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_input.getText().toString().isEmpty()) {

                    input = "(-";
                    et_input.setText(input);
                    data = "(-";
                    bracketIsOpened = true;

                } else if (lastInputIsOperator) {
                    input = input + "(-";
                    data = data + "(-";
                    et_input.setText(input);

                    bracketIsOpened = true;
                } else if (et_input.getText().toString().startsWith("-")) {

                    input = et_input.getText().toString().substring(1, et_input.getText().toString().length());
                    data = input;
                    et_input.setText(input);

                }
            }
        });
    }

    void numClick(String num) {

        textLength = et_input.getText().toString().length();
        if (et_input.getText().toString().equals("0")) {
            input = num;
            et_input.setText(input);
            data = data + num;

            ++consecutiveNumbersCounter;
            lastInputIsOperator = false;
            Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
        } else if (consecutiveNumbersCounter >= 15) {
            et_input.setEnabled(false);

            Toast.makeText(MainActivity.this, "max", Toast.LENGTH_SHORT).show();


        } else {
            input = et_input.getText().toString() + num;
            data = data + num;
            lastInputIsOperator = false;
            et_input.setText(input);
            ++consecutiveNumbersCounter;

        }


        if (textLength >= 17) {
            et_input.setTextSize(22);
            et_input.setWidth(800);
        } else {
            et_input.setTextSize(30);
            et_input.setWidth(5800);

        }

    }


    void operatorClick(String visibleOperator, String actualOperator) {


        textLength = et_input.getText().toString().length();
        if (et_input.getText().toString().isEmpty() || lastInputIsOperator) {
            Toast.makeText(MainActivity.this, "Invalid Format", Toast.LENGTH_SHORT).show();
        } else {
            input = et_input.getText().toString() + visibleOperator;
            data = data + actualOperator;
            isDecimal = false;
        }
        et_input.setText(input);
        consecutiveNumbersCounter = 0;
        lastInputIsOperator = true;


        if (textLength >= 17) {
            et_input.setTextSize(22);
            et_input.setWidth(800);
        } else {
            et_input.setTextSize(30);
            et_input.setWidth(5800);

        }
    }

    String getResult(String data) {

        Expression expression = new Expression(data);

        Double result = expression.calculate();
        return result.toString();
    }

}