package myfirstapp.android.exampple.com.tictactoe_andela;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class FiveByFIveActivity extends AppCompatActivity implements View.OnClickListener  {


    /*  A Simple Tic- Tac Toe Application
    Time to climb challenge - Google Andela 2.0 Programme
    Written By Anyanwaokoro Chimdiebube Glory Mike
    Final Changes: 4/11/2018
    Location : Owerri, Nigeria
    Email: gloryebubemike@gmail.com

     */

    // String to Store value of Player Type
    private String BtnSelectedX;
    private String BtnSelectedO;

    // An array of buttons to hold my buttons
    private Button[][] buttons = new Button[5][5];

    // to determine if playerX is true to play
    private boolean PlayerXturn;


    // To check how many rounds played to acertain draws
    private int CountRoundsplayed;


    private int PlayerXpoints;
    private int PlayerOpoints;

    private TextView DisplayForPlayerX;
    private TextView DisplayForPlayerO;


    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_by_five);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DisplayForPlayerX = (TextView) findViewById(R.id.text_view_p1);
        DisplayForPlayerO = (TextView) findViewById(R.id.text_view_p2);


        final Button playerX = (Button) findViewById(R.id.playerX);
        final    Button playerO = (Button) findViewById(R.id.playerO);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }


        playerX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnSelectedX = "X ";
                BtnSelectedO = "O";
                PlayerXturn = true;
                playerX.setTextColor(getResources().getColor( R.color.colorme));
                playerX.setBackgroundColor(getResources().getColor( R.color.colorPrimary));
                playerO.setTextColor(getResources().getColor( R.color.colorblack));
                playerO.setBackgroundColor(getResources().getColor( R.color.colorme));
                //  playerX.setVisibility(View.INVISIBLE);
                //playerX.isSelected();
            }
        });

        playerO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnSelectedX = "X ";
                BtnSelectedO = "O";
                PlayerXturn = false;

                playerO.setTextColor(getResources().getColor( R.color.colorme));
                playerO.setBackgroundColor(getResources().getColor( R.color.colorPrimary));
                playerX.setTextColor(getResources().getColor( R.color.colorblack));
                playerX.setBackgroundColor(getResources().getColor( R.color.colorme));
            }
        });

        Button buttonReset = (Button) findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerX.setTextColor(getResources().getColor( R.color.colorblack));
                playerX.setBackgroundColor(getResources().getColor( R.color.colorme));
                playerO.setTextColor(getResources().getColor( R.color.colorblack));
                playerO.setBackgroundColor(getResources().getColor( R.color.colorme));
                BtnSelectedO = "";
                BtnSelectedX = "";
                ResetAll();
            }
        });









        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResetAll();
                Snackbar.make(view, "Returning To By 3 Matrix", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(FiveByFIveActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            Toast.makeText(this, "Please Select a Player", Toast.LENGTH_SHORT).show();
            return;
        }

        if (PlayerXturn) {

            ((Button) v).setText(BtnSelectedX);
        } else {
            ((Button) v).setText(BtnSelectedO);
        }

        CountRoundsplayed++;

        if (ConfirmWinning()) {
            if (PlayerXturn) {
                IsPlayerXWinner();
            } else {
                IsPlayerOWinner();
            }
        } else if (CountRoundsplayed == 25) {
            draw();
        } else {
            PlayerXturn = !PlayerXturn;
        }

    }

    private boolean ConfirmWinning() {
        String[][] mySelectedButtonsArray = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mySelectedButtonsArray[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 5; i++) {
            if (mySelectedButtonsArray[i][0].equals(mySelectedButtonsArray[i][1])
                    && mySelectedButtonsArray[i][0].equals(mySelectedButtonsArray[i][2])
                    && mySelectedButtonsArray[i][0].equals(mySelectedButtonsArray[i][3])
                    && mySelectedButtonsArray[i][0].equals(mySelectedButtonsArray[i][4])
                    && !mySelectedButtonsArray[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (mySelectedButtonsArray[0][i].equals(mySelectedButtonsArray[1][i])
                    && mySelectedButtonsArray[0][i].equals(mySelectedButtonsArray[2][i])
                    && mySelectedButtonsArray[0][i].equals(mySelectedButtonsArray[3][i])
                    && mySelectedButtonsArray[0][i].equals(mySelectedButtonsArray[4][i])
                    && !mySelectedButtonsArray[0][i].equals("")) {
                return true;
            }
        }

        if (mySelectedButtonsArray[0][0].equals(mySelectedButtonsArray[1][1])
                && mySelectedButtonsArray[0][0].equals(mySelectedButtonsArray[2][2])
                && mySelectedButtonsArray[0][0].equals(mySelectedButtonsArray[3][3])
                && mySelectedButtonsArray[0][0].equals(mySelectedButtonsArray[4][4])
                && !mySelectedButtonsArray[0][0].equals("")) {
            return true;
        }

        if (mySelectedButtonsArray[0][2].equals(mySelectedButtonsArray[1][1])
                && mySelectedButtonsArray[0][2].equals(mySelectedButtonsArray[2][0])
                && !mySelectedButtonsArray[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void IsPlayerXWinner() {
        PlayerXpoints++;
        UpdatePoints();
        Toast.makeText(this, "Hurray: Player X has Won", Toast.LENGTH_SHORT).show();
        ResetGridButtons();

    }

    private void IsPlayerOWinner() {
        PlayerOpoints++;
        UpdatePoints();
        Toast.makeText(this, "Hurray: Player O has Won", Toast.LENGTH_SHORT).show();

        ResetGridButtons();
    }

    private void draw() {
        Toast.makeText(this, "Oops! It's  a Draw", Toast.LENGTH_SHORT).show();
        ResetGridButtons();
    }

    private void UpdatePoints() {
        DisplayForPlayerX.setText(getString(R.string.player_X, PlayerXpoints));
        DisplayForPlayerO.setText(getString(R.string.player_O, PlayerOpoints));
    }

    private void ResetGridButtons() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
            }
        }

        CountRoundsplayed = 0;
        PlayerXturn = true;
    }

    private void ResetAll() {
        PlayerXpoints = 0;
        PlayerOpoints = 0;
        UpdatePoints();
        ResetGridButtons();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("CountRoundsplayed", CountRoundsplayed);
        outState.putInt("PlayerXpoints", PlayerXpoints);
        outState.putInt("PlayerOpoints", PlayerOpoints);
        outState.putBoolean("PlayerXturn", PlayerXturn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        CountRoundsplayed = savedInstanceState.getInt("CountRoundsplayed");
        PlayerXpoints = savedInstanceState.getInt("PlayerXpoints");
        PlayerOpoints = savedInstanceState.getInt("PlayerOpoints");
        PlayerXturn = savedInstanceState.getBoolean("PlayerXturn");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}
