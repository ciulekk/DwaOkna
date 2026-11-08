
package com.example.mojedwaekrany;

import android.content.Intent;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class SummaryActivity extends AppCompatActivity {

    // Stałe klucze przekazywanych danych
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_PRIORITY = "priority";
    public static final String EXTRA_URGENT = "urgent";
    public static final String EXTRA_DATE = "date";

    // Klucz wyniku zwracanego do MainActivity
    public static final String EXTRA_RESULT_NUMBER = "result_number";

    private TextView textSummary;
    private Button buttonConfirm;
    private Button buttonCancel;
    private Button buttonShare;

    private String category;
    private String description;
    private int priority;
    private boolean urgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        textSummary = findViewById(R.id.textSummary);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonShare = findViewById(R.id.buttonShare);

        // Odbieranie danych z pierwszego ekranu
        Intent intent = getIntent();

        category = intent.getStringExtra(EXTRA_CATEGORY);
        description = intent.getStringExtra(EXTRA_DESCRIPTION);
        priority = intent.getIntExtra(EXTRA_PRIORITY, 1);
        urgent = intent.getBooleanExtra(EXTRA_URGENT, false);

        // Wyświetlanie priorytetu
        String priorityText;

        switch (priority) {
            case 1:
                priorityText = "Niski";
                break;
            case 2:
                priorityText = "Średni";
                break;
            case 3:
                priorityText = "Wysoki";
                break;
            default:
                priorityText = "Nieznany";
        }

        String urgentText = urgent ? "Tak" : "Nie";

        // Tworzenie podsumowania
        String summary =
                "PODSUMOWANIE ZGŁOSZENIA\n\n" +
                        "Kategoria: " + category + "\n\n" +
                        "Opis: " + description + "\n\n" +
                        "Priorytet: " + priorityText + "\n\n" +
                        "Pilna usterka: " + urgentText;

        textSummary.setText(summary);

        // Potwierdzenie zgłoszenia
        buttonConfirm.setOnClickListener(v -> {

            // Generowanie numeru zgłoszenia
            String number = UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_RESULT_NUMBER, number);

            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Rezygnacja ze zgłoszenia
        buttonCancel.setOnClickListener(v -> {

            setResult(RESULT_CANCELED);
            finish();
        });

        // Niejawna intencja - udostępnianie zgłoszenia
        buttonShare.setOnClickListener(v -> shareReport(summary));
    }

    private void shareReport(String summary) {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("text/plain");
        shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                summary
        );

        try {

            Intent chooser = Intent.createChooser(
                    shareIntent,
                    "Udostępnij zgłoszenie"
            );

            startActivity(chooser);

        } catch (ActivityNotFoundException e) {

            Toast.makeText(
                    this,
                    "Brak aplikacji do udostępniania",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
