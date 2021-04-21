package ca.on.conestogac.clo.minesweeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final int BOARD_DIMENSION = 8;
    private final int NUMBER_OF_BOMBS = 10;

    private TextView txtNumberOfBombs;
    private TextView txtTimeElapsed;
    private int bombs = 10;
    private int timeElapsed = 0;
    private Timer timerTimeElapsed;
    private Field[][] board;

    private RecyclerView recyclerViewFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumberOfBombs = findViewById(R.id.txtNumberOfBombs);
        txtTimeElapsed = findViewById(R.id.txtTimeElapsed);

        txtNumberOfBombs.setText("Bombs: " + bombs);
        txtTimeElapsed.setText("Time: " + timeElapsed);

        timerTimeElapsed = new Timer(true);

        timerTimeElapsed.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeElapsed += 1;
                        txtTimeElapsed.setText("Time: " + timeElapsed);
                        Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG);
                    }
                });
            }
        }, 0, 1000);

        board = Field.createBoard(BOARD_DIMENSION, NUMBER_OF_BOMBS);

        recyclerViewFields = findViewById(R.id.recyclerViewFields);

        List<String> test = new ArrayList<String>();
        test.add("Test1");
        test.add("Test2");

        recyclerViewFields.setAdapter(new CustomAdapter(test));
        recyclerViewFields.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.minesweeper_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.menu_statistics:
                startActivity(new Intent(getApplicationContext(), StatisticsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}