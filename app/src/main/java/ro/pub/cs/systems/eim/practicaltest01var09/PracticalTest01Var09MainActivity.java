package ro.pub.cs.systems.eim.practicaltest01var09;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var09MainActivity extends AppCompatActivity {

    private int sum;
    private StringBuffer allTerms = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var09_main);

        if (savedInstanceState != null) {
            sum = savedInstanceState.getInt("savedSum");
        } else {
            sum = 0;
        }

        Button addButton = findViewById(R.id.add_button);
        Button computeButton = findViewById(R.id.compute_button);

        EditText nextTerm = findViewById(R.id.next_term);
        EditText result = findViewById(R.id.result);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = nextTerm.getText().toString();
                StringBuffer finalResult = new StringBuffer(result.getText().toString());

                if (!term.isEmpty()) {
                    if (finalResult.length() == 0) {
                        finalResult.append(term);
                    } else {
                        finalResult.append(" + " + term);
                    }
                    result.setText(finalResult);
                }
            }
        });

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticalTest01Var09MainActivity.this, PracticalTest01Var09SecondaryActivity.class);
                intent.setAction("ro.pub.cs.systems.eim.practicaltest01var09.COMPUTE");
                intent.putExtra("all_terms", result.getText().toString());

                allTerms.delete(0, allTerms.length());

                if (result.getText().length() != 0) {
                    allTerms.append(result.getText().toString());
                }

                Log.d("MAIN ACTIVITY", "onCompute: " + allTerms);

                startActivityForResult(intent, 2025);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2025 && resultCode == RESULT_OK) {
            int resultSum = data.getIntExtra("result", -1);
            sum = resultSum;
            allTerms.delete(0, allTerms.length());
            allTerms.append(sum + "");
            Log.d("MAIN ACTIVITY", resultSum + "");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        String savedterms = savedInstanceState.getString("allTerms");
        int savedSum = savedInstanceState.getInt("savedSum");

        if(Integer.parseInt(allTerms.toString()) == sum) {
            EditText result = findViewById(R.id.result);
            result.setText(sum);
        }

        if (sum == 0) {
            sum = savedSum;
        }

        Log.d("MAIN ACTIVITY", "onRestoreInstanceState: " + sum);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("savedSum", sum);
        savedInstanceState.putString("allTerms", allTerms.toString());
    }
}
