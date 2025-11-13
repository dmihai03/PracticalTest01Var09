package ro.pub.cs.systems.eim.practicaltest01var09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var09SecondaryActivity extends AppCompatActivity {

    private int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var09_secondary);

        sum = 0;

        Button finishButton = findViewById(R.id.finish_button);

        Intent intentFromParent = getIntent();
        if (!intentFromParent.getAction().isEmpty() &&
                intentFromParent.getAction().equals("ro.pub.cs.systems.eim.practicaltest01var09.COMPUTE")) {
            String[] terms = intentFromParent.getStringExtra("all_terms").split(" \\+ ");

            for (String term : terms) {
                int iTerm = Integer.parseInt(term);
                sum += iTerm;
            }
        }

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToParent = new Intent();
                intentToParent.putExtra("result", sum);
                setResult(RESULT_OK, intentToParent);
                finish();
            }
        });
    }
}