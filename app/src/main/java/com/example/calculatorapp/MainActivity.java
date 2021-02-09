package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;

    private Button btnAdd;
    private Button btnMinus;
    private Button btnMult;
    private Button btnDiv;
    private Button btnSqrt;
    private Button btnDot;
    private Button btnEqual;
    private Button btnAC;

    private EditText txtResult;

    public String op = "";


    public Double calculator_helper(String o, Double[] num){
        switch (o){
            case "+":
                Double ret = num[0]+num[1];
                return ret;
            case "-":
                ret = num[0]-num[1];
                return ret;
            case "*":
                ret = num[0]*num[1];
                return ret;
            case "/":
               ret = num[0]/num[1];
                return ret;
            case "sqrt":
                ret = Math.sqrt(num[0]);
                return ret;
            default:
                Log.i("helper","default case reached");
                return -0.000001;

        }

    }

    public Double sqrt_helper(Double d){

        return Math.sqrt(d);
    }

    public Boolean dot_helper(){
        Boolean ret = true;
        String[] nums;
        try{
        if(txtResult.getText().toString().charAt(0)==('-')){
            nums=txtResult.getText().toString().substring(1).replaceAll("[^\\.0123456789]"," ").split(" ");
        }
        else{
            nums=txtResult.getText().toString().replaceAll("[^\\.0123456789]"," ").split(" ");
        }
        for(String s:nums){
            if(!(s.indexOf(".")>-1)){
                return true;
            }
        }}catch (StringIndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    public void num_txtResult(String n) {
        String prev = txtResult.getText().toString();
        if (prev.length()<1){
            txtResult.setText(n);
        }else{
            txtResult.setText(prev+n);
            txtResult.setSelection(txtResult.getText().length());
        }
    }

    public void op_txtResult (String o) throws NumberFormatException{
        txtResult.setHint("");
        String prev = txtResult.getText().toString();
        double tempNum;
        String[] nums;
        if(prev.length()!=0){
            Double ret;
            try{
                tempNum = Double.parseDouble(txtResult.getText().toString());
                if(o.equals("=")||o.equals("sqrt")){
                    if(o.equals("sqrt")){
                        ret = sqrt_helper(tempNum);
                        if(ret.toString().equals("Infinity") || ret.toString().equals("-Infinity")||ret.toString().equals("NaN")){
                            txtResult.setText("");
                            txtResult.setHint("math error");
                        }
                        else {
                            txtResult.setText(ret.toString());
                        }
                    }
                }
                else{
                    txtResult.setText(txtResult.getText().toString()+o);
                    op=o;
                }

            }catch (NumberFormatException nfe){
                Log.i("MyFlag", "exception");
                Log.i("MyFlag", "op is "+op);
                String updateText = "";
                if(txtResult.getText().toString().charAt(0)==('-')){
                    nums = txtResult.getText().toString().substring(1).split(Pattern.quote(op));
                    nums[0] = "-"+nums[0];
                }
                else {
                    nums = txtResult.getText().toString().split(Pattern.quote(op));
                }
                if(nums.length>1 && nums.length<3){
                    Log.i("nums are: ",Arrays.toString(nums));
                    ret = calculator_helper(op, new Double[]{Double.parseDouble(nums[0]), Double.parseDouble(nums[1])});
                }
                else{
                    ret = Double.parseDouble(nums[0]);
                }
                if(o.equals("sqrt")||o.equals("=")){
                    if(o.equals("sqrt")){
                        ret = sqrt_helper(ret);
                    }
                    op="";
                    updateText = ret.toString();
                }
                else{
                    op=o;
                    updateText = ret+o;
                }
                if(ret.toString().equals("Infinity") || ret.toString().equals("-Infinity")||ret.toString().equals("NaN")){
                    updateText = "";
                    txtResult.setHint("math error");
                }
                txtResult.setText(updateText);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnZero = (Button) findViewById(R.id.btnZero);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnSqrt = (Button) findViewById(R.id.btnSqrt);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnAC = (Button) findViewById(R.id.btnAC);

        txtResult = (EditText) findViewById(R.id.txtResult);

        btnOne.setOnClickListener((v)->{
//            txtResult.setText("1");
            num_txtResult("1");
        });

        btnTwo.setOnClickListener((v)->{
            num_txtResult("2");
        });

        btnThree.setOnClickListener((v)->{
            num_txtResult("3");
        });

        btnFour.setOnClickListener((v)->{
            num_txtResult("4");
        });

        btnFive.setOnClickListener((v)->{
            num_txtResult("5");
        });

        btnSix.setOnClickListener((v)->{
            num_txtResult("6");
        });

        btnSeven.setOnClickListener((v)->{
            num_txtResult("7");
        });

        btnEight.setOnClickListener((v)->{
            num_txtResult("8");
        });

        btnNine.setOnClickListener((v)->{
            num_txtResult("9");
        });

        btnZero.setOnClickListener((v)->{
            num_txtResult("0");
        });

        btnAdd.setOnClickListener((v)->{
            op_txtResult("+");

        });

        btnMult.setOnClickListener((v)->{
            op_txtResult("*");
        });

        btnMinus.setOnClickListener((v)->{
            op_txtResult("-");
//            op_txtResult("m");
        });

        btnDiv.setOnClickListener((v)->{
            op_txtResult("/");
        });

        btnSqrt.setOnClickListener((v)->{
            op_txtResult("sqrt");
        });

        btnDot.setOnClickListener((v)->{
            if(dot_helper()){
                txtResult.setText(txtResult.getText().toString()+".");
            };
        });

        btnEqual.setOnClickListener((v)->{
            op_txtResult("=");
        });

        btnAC.setOnClickListener((v)->{
            txtResult.setText("");
        });


    }
}