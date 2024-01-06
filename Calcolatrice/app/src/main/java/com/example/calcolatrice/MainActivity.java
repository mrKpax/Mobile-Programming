package com.example.calcolatrice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvDP, tvDS, tvDM, tvDO;
    Button bClear;
    String memoryDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDM = findViewById(R.id.display_memoria);
        tvDP = findViewById(R.id.display_principale);
        tvDS = findViewById(R.id.display_secondario);
        tvDO = findViewById(R.id.display_operatore);
        bClear = findViewById(R.id.buttonClear);

        bClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view)
            {
                tvDP.setText("");
                return false;
            }
        });
    }

    public void digitPressed(View v)
    {
        Button b = (Button) v;
        tvDP.setText(tvDP.getText().toString() + b.getText());
    }

    public void operatorPressed(View v)
    {
        Button b = (Button) v;
        String op1 = tvDP.getText().toString();
        if(op1.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Operatore nullo", Toast.LENGTH_LONG).show();
            return;
        }

        tvDO.setText(b.getText().toString());
        tvDS.setText(tvDP.getText());
        tvDP.setText("");
    }

    public void equalPressed(View v)
    {
        String str_op1 = tvDP.getText().toString();
        if(str_op1.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Operatore nullo", Toast.LENGTH_LONG).show();
            return;
        }

        float op1 = Float.parseFloat(tvDS.getText().toString());

        String str_op2 = tvDP.getText().toString();
        if(str_op2.length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Operatore nullo", Toast.LENGTH_LONG).show();
            return;
        }

        float op2 = Float.parseFloat(tvDP.getText().toString());
        char op = tvDO.getText().toString().charAt(0);

        float res = 0;

        switch (op)
        {
            case '+':
                res = op1 + op2;
                break;

            case '-':
                res = op1 - op2;
                break;

            case '*':
                res = op1 * op2;
                break;

            case '/':
                if (op2 == 0)
                {
                    Toast.makeText(getApplicationContext(),"Divisione per 0 non ammessa", Toast.LENGTH_LONG).show();
                    tvDP.setText("");
                    tvDS.setText("");
                    tvDO.setText("");
                    return;
                }

                res = op1 / op2;
                break;
        }

        if (res == (int) res)
            tvDP.setText(String.valueOf((int) res));
        else
            tvDP.setText(String.valueOf(res));

        tvDS.setText("");
        tvDO.setText("");
    }

    public void cPressed(View v)
    {
        String s = tvDP.getText().toString();
        if(s.length() > 0)
            tvDP.setText(s.substring(0, s.length()-1));
    }



    public void mcPressed(View v)
    {
        memoryDel = (tvDM.getText().toString());
        tvDM.setText("");
    }

    public void msPressed(View v)
    {
        memoryDel = (tvDM.getText().toString());
        tvDM.setText(tvDP.getText().toString());
    }

    public void mrPressed(View v)
    {
        tvDM.setText(memoryDel);
    }

    public void dotPressed(View v)
    {
        tvDP.setText(tvDP.getText().toString() + ".");
    }
}