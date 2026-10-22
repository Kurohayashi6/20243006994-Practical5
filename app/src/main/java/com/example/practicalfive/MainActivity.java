package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY_NAME = "MUNICIPALITY_NAME";
    private EditText etMunicipalityInput;
    private TextProcessor textProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etMunicipalityInput = findViewById(R.id.etMunicipalityInput);
        textProcessor = new TextProcessor();

        etMunicipalityInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean isEnterKey = (event != null
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN);
                boolean isImeAction = (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_GO
                        || actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_NULL);

                if (isImeAction || isEnterKey) {
                    handleUserInput();
                    return true;
                }
                return false;
            }
        });
    }

    private void handleUserInput() {
        String rawInput = etMunicipalityInput.getText().toString();

        if (rawInput.trim().isEmpty()) {
            etMunicipalityInput.setError("Please enter a municipality name");
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String validMunicipality = textProcessor.processMunicipality(rawInput);

        if (validMunicipality == null) {
            etMunicipalityInput.setError("Unsupported municipality. Enter Beijing, Shanghai, Tianjin, or Chongqing.");
            Toast.makeText(this, "Invalid municipality: " + rawInput.trim(), Toast.LENGTH_SHORT).show();
        } else {
            etMunicipalityInput.setError(null);
            Intent intent = new Intent(MainActivity.this, Municipality.class);
            intent.putExtra(EXTRA_MUNICIPALITY_NAME, validMunicipality);
            startActivity(intent);
        }
    }
}