package com.example.practicalfive;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Municipality extends AppCompatActivity {

    private TextView tvMunicipalityName;
    private ImageView ivMunicipalityImage;
    private TextView tvMunicipalityInfo;
    private Button btnVisitSite;
    private String currentMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_municipality);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvMunicipalityName = findViewById(R.id.tvMunicipalityName);
        ivMunicipalityImage = findViewById(R.id.ivMunicipalityImage);
        tvMunicipalityInfo = findViewById(R.id.tvMunicipalityInfo);
        btnVisitSite = findViewById(R.id.btnVisitSite);

        Intent intent = getIntent();
        String receivedName = (intent != null) ? intent.getStringExtra(MainActivity.EXTRA_MUNICIPALITY_NAME) : null;

        TextProcessor processor = new TextProcessor();
        currentMunicipality = processor.processMunicipality(receivedName);

        if (currentMunicipality == null) {
            Toast.makeText(this, "Invalid municipality data", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        customiseScreen(currentMunicipality);
    }

    private void customiseScreen(String municipality) {
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        int colorResId;
        int imageResId;
        String summaryText;

        switch (municipality) {
            case "Beijing":
                colorResId = R.color.municipality_beijing;
                imageResId = R.drawable.beijing;
                summaryText = summaries[0];
                break;
            case "Shanghai":
                colorResId = R.color.municipality_shanghai;
                imageResId = R.drawable.shanghai;
                summaryText = summaries[1];
                break;
            case "Chongqing":
                colorResId = R.color.municipality_chongqing;
                imageResId = R.drawable.chongqing;
                summaryText = summaries[2];
                break;
            case "Tianjin":
                colorResId = R.color.municipality_tianjin;
                imageResId = R.drawable.tianjin;
                summaryText = summaries[3];
                break;
            default:
                return;
        }

        int themeColor = ContextCompat.getColor(this, colorResId);
        int lightTextColor = ContextCompat.getColor(this, R.color.color_text_light);

        tvMunicipalityName.setText(municipality);
        tvMunicipalityName.setBackgroundColor(themeColor);
        tvMunicipalityName.setTextColor(lightTextColor);

        ivMunicipalityImage.setImageResource(imageResId);

        tvMunicipalityInfo.setText(summaryText);
        tvMunicipalityInfo.setTextColor(themeColor);

        btnVisitSite.setBackgroundTintList(ColorStateList.valueOf(themeColor));
        btnVisitSite.setTextColor(lightTextColor);
    }
}