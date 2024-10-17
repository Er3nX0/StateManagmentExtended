package com.example.statemanagmentextended;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private CountViewModel countViewModel;
    private static final String KEY_COUNT = "count"; // Klucz do przechowywania danych w Bundle
    private static final String KEY_TEXT = "tekst";
    private static final String KEY_CHECK = "check";
    private static final String KEY_SWITCH = "check2";
    private TextView textViewCount; // Element widoku do wyświetlania liczby
    private TextView textViewWiadomosc;
    private int count = 0; // Zmienna do przechowywania wartości licznika
    private String tekst;
    private boolean check;
    private boolean przelacznik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCount = findViewById(R.id.tekst); // Inicjalizacja TextView
        Button buttonIncrement = findViewById(R.id.button); // Inicjalizacja Button
        CheckBox checkbox = findViewById(R.id.check);
        Switch zmienTryb = findViewById(R.id.zmienTryb);
        textViewWiadomosc = findViewById(R.id.ukrytawiadomosc);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) textViewWiadomosc.setVisibility(View.VISIBLE); else textViewWiadomosc.setVisibility(View.INVISIBLE);
            }
        });
        zmienTryb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        countViewModel = new ViewModelProvider(this).get(CountViewModel.class);
        updateCountText();

        // Odczyt danych ze stanu, jeśli istnieje
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT); // Przywróć wartość licznika
            tekst = savedInstanceState.getString(KEY_TEXT);
            check = savedInstanceState.getBoolean(KEY_CHECK);
            przelacznik = savedInstanceState.getBoolean(KEY_SWITCH);
        }
        updateCountText(); // Aktualizuj widok TextView

        // Ustawienie akcji kliknięcia przycisku
        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countViewModel.incrementCount();
                updateCountText(); // Aktualizuj widok TextView
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count); // Zapisz aktualny stan licznika do Bundle
        outState.putString(KEY_TEXT, tekst);
        outState.putBoolean(KEY_CHECK, check);
        outState.putBoolean(KEY_SWITCH, przelacznik);
    }
    private void updateCountText() {
        textViewCount.setText("Licznik: " + countViewModel.getCount()); // Ustaw tekst TextView na aktualną wartość licznika
    }
}