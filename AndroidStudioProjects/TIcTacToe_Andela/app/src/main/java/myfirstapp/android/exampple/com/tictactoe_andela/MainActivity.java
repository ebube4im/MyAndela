package myfirstapp.android.exampple.com.tictactoe_andela;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*  A Simple Tic- Tac Toe Application
    Time to climb challenge - Google Andela 2.0 Programme
    Written By Anyanwaokoro Chimdiebube Glory Mike
    Final Changes: 4/18/2018
    Location : Owerri, Nigeria
    Email: gloryebubemike@gmail.com

     */



    // To check how many rounds played to acertain draws
    private int CountRoundsplayed;


    private int PlayerXpoints;
    private int PlayerOpoints;

    private TextView DisplayForPlayerX;
    private TextView DisplayForPlayerO;


    // String to Store value of Player Type
    private String BtnSelectedX;
    private String BtnSelectedO;
    
    // An array of buttons to hold my buttons
    private Button[][] buttons = new Button[3][3];

// to determine if playerX is true to play    
    private boolean PlayerXturn;

    private boolean ComSelected= false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final Button buttonReset = (Button) findViewById(R.id.button_reset);

        DisplayForPlayerX = (TextView) findViewById(R.id.text_view_p1);
        DisplayForPlayerO = (TextView) findViewById(R.id.text_view_p2);


        final Button playerX = (Button) findViewById(R.id.playerX);
        final    Button playerO = (Button) findViewById(R.id.playerO);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
                playerO.setClickable(false);
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
playerX.setClickable(false);

                playerO.setTextColor(getResources().getColor( R.color.colorme));
                playerO.setBackgroundColor(getResources().getColor( R.color.colorPrimary));
                playerX.setTextColor(getResources().getColor( R.color.colorblack));
                playerX.setBackgroundColor(getResources().getColor( R.color.colorme));
            }
        });

        final Button PlayWithCOm = (Button) findViewById(R.id.Computer);

        PlayWithCOm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerX.setTextColor(getResources().getColor( R.color.colorblack));
                playerX.setBackgroundColor(getResources().getColor( R.color.colorme));
                playerO.setTextColor(getResources().getColor( R.color.colorblack));
                playerO.setBackgroundColor(getResources().getColor( R.color.colorme));
                playerX.setClickable(true);
                playerO.setClickable(true);
                BtnSelectedO = "";
                BtnSelectedX = "";
                fab.performClick();

                PlayWithCOm.setClickable(false);
                PlayWithCOm.setTextColor(getResources().getColor( R.color.colorme));
                PlayWithCOm.setBackgroundColor(getResources().getColor( R.color.colorblack));
                //  playerX.setVisibility(View.INVISIBLE);
                //playerX.isSelected();
            }
        });



        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerX.setTextColor(getResources().getColor( R.color.colorblack));
                playerX.setBackgroundColor(getResources().getColor( R.color.colorme));
                playerO.setTextColor(getResources().getColor( R.color.colorblack));
                playerO.setBackgroundColor(getResources().getColor( R.color.colorme));
                playerX.setClickable(true);
                playerO.setClickable(true);
                BtnSelectedO = "";
                BtnSelectedX = "";
                ResetAll();
                ComSelected = false;
                Snackbar.make(v, "You are now in Player Mode", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Toast mytoast = Toast.makeText(getApplicationContext(),"You are now in Player Mode", Toast.LENGTH_SHORT);
                mytoast.setGravity(Gravity.CENTER, 0,0);
                mytoast.show();
                PlayWithCOm.setClickable(true);
                PlayWithCOm.setTextColor(getResources().getColor( R.color.colorblack));
                PlayWithCOm.setBackgroundColor(getResources().getColor( R.color.colorme));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //    Intent i = new Intent(MainActivity.this, FiveByFIveActivity.class);
             //     startActivity(i);
         ResetAll();
                ComSelected = !ComSelected;
                if(ComSelected = true){
                    Snackbar.make(view, "You are now in COM mode, You are X", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    playerO.setClickable(false);
                    playerX.performClick();
                }else {
                    Snackbar.make(view, "You are now in Player Mode", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                Toast mytoast = Toast.makeText(getApplicationContext(),"You have Selected to Play against COM", Toast.LENGTH_SHORT);
                mytoast.setGravity(Gravity.CENTER, 0,0);
                mytoast.show();

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
         Toast mytoast =    Toast.makeText(this, "The Box is filled already", Toast.LENGTH_SHORT);
            mytoast.setGravity(Gravity.CENTER, 0,0);
            mytoast.show();


            return;
        }


        if(ComSelected){
            if (PlayerXturn) {

                ((Button) v).setText(BtnSelectedX);
                // Commpuer stuff

     CountRoundsplayed++;

                if (ConfirmWinning()) {
                    if (PlayerXturn) {
                        IsPlayerXWinner();
                    } else {
                        IsPlayerOWinner();
                    }
                } else if (CountRoundsplayed == 9) {
                    draw();
                } else {
                    PlayerXturn = !PlayerXturn;

                    ComputerPlays();
                }


            }



        }
        else{
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
            } else if (CountRoundsplayed == 9) {
                draw();
            } else {

                if (PlayerXturn) {
                    Toast mytoast =    Toast.makeText(this, "Turn for Player O", Toast.LENGTH_SHORT);
                    mytoast.setGravity(Gravity.CENTER, 0,0);
                    mytoast.show();
                } else {
                    Toast mytoast =    Toast.makeText(this, "Turn for Player X", Toast.LENGTH_SHORT);
                    mytoast.setGravity(Gravity.CENTER, 0,0);
                    mytoast.show();
                }
                PlayerXturn = !PlayerXturn;
            }



        }


    }



    private void ComputerPlays(){

        for (int check  = 0; check <3; check++){

            for (int x = 0; x < 3; x++) {
                if (buttons[x][0].equals(buttons[x][1]) && !buttons[x][0].equals("")) {
                    buttons[x][2].setText(BtnSelectedO);
                    x = 3;
                    check = 3;
                }
            }

            for (int y = 0; y < 3; y++) {
                if (buttons[0][y].equals(buttons[1][y]) && !buttons[0][y].equals("")) {
                    buttons[2][y].setText(BtnSelectedO);
                    y = 3;
                    check = 3;
                }
            }

            if (buttons[0][0].equals(buttons[1][1]) && !buttons[0][0].equals("")) {
                buttons[2][2].setText(BtnSelectedO);
                check = 3;
            }

            if (buttons[0][2].equals(buttons[1][1]) && !buttons[0][2].equals("")) {
                buttons[2][0].setText(BtnSelectedO);

                check = 3;
            }


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(buttons[i][j].getText().toString().isEmpty()){
                        buttons[i][j].setText(BtnSelectedO) ;
                        i = 3;
                        j= 3;
                        check = 3;
                    }

                }
            }



        }
        CountRoundsplayed++;

        if (ConfirmWinning()) {
            if (PlayerXturn) {
                IsPlayerXWinner();
            } else {
                IsPlayerOWinner();
            }
        } else if (CountRoundsplayed == 9) {
            draw();
        } else {
            PlayerXturn = !PlayerXturn;

        }

    }

    private boolean ConfirmWinning() {
        String[][] mySelectedButtonsArray = new String[3][3];

        //Create A 3 by 3 Array of String to Capture every text in the Buttons
         for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mySelectedButtonsArray[i][j] = buttons[i][j].getText().toString();
            }
        }

        /*
        This For Loop below checks whether there is any Match Horizontally
        On First Loop
        [  X | X  |  X ]
        [    |    |    ]
        [    |    |    ]

        OR

        [  O | O  |  O ]
        [    |    |    ]
        [    |    |    ]

        On Second Loop
        [    |   |    ]
        [ X  | X | X  ]
        [    |   |    ]

OR
        [    |   |    ]
        [ O  | O | O  ]
        [    |   |    ]

ON Third Loop

        [    |    |    ]
        [    |    |    ]
        [ X  | X  | X  ]

OR

        [    |    |    ]
        [    |    |    ]
        [ O  | O  | O  ]

        */
        for (int i = 0; i < 3; i++) {
            if (mySelectedButtonsArray[i][0].equals(mySelectedButtonsArray[i][1])
                    && mySelectedButtonsArray[i][0].equals(mySelectedButtonsArray[i][2])
                    && !mySelectedButtonsArray[i][0].equals("")) {
                return true;
            }
        }







                /*
        This For Loop checks whether there is any Match Vertically
        On First Loop
        [  X |    |    ]
        [  X |    |    ]
        [  X |    |    ]

        OR

        [  O |    |    ]
        [  O |    |    ]
        [  O |    |    ]

        On Second Loop
        [    | X |    ]
        [    | X |    ]
        [    | X |    ]

OR
        [    | O |    ]
        [    | O |    ]
        [    | O |    ]

ON Third Loop

        [    |    | X  ]
        [    |    | X  ]
        [    |    | X  ]

OR

        [    |    | O  ]
        [    |    | O  ]
        [    |    | O  ]

        */
        for (int i = 0; i < 3; i++) {
            if (mySelectedButtonsArray[0][i].equals(mySelectedButtonsArray[1][i])
                    && mySelectedButtonsArray[0][i].equals(mySelectedButtonsArray[2][i])
                    && !mySelectedButtonsArray[0][i].equals("")) {
                return true;
            }
        }






                /*
        This checks whether there is any Match Diagonally from the Left
        On First Loop
        [  X |    |    ]
        [    | X  |    ]
        [    |    | X  ]

        OR

        [  O |    |    ]
        [    |  O |    ]
        [    |    |  O ]


        */
        if (mySelectedButtonsArray[0][0].equals(mySelectedButtonsArray[1][1])
                && mySelectedButtonsArray[0][0].equals(mySelectedButtonsArray[2][2])
                && !mySelectedButtonsArray[0][0].equals("")) {
            return true;
        }






                       /*
        This checks whether there is any Match Diagonally from the Left
        On First Loop
        [    |    |  X ]
        [    | X  |    ]
        [  X |    |    ]

        OR

        [    |    |  O ]
        [    |  O |    ]
        [  O  |    |    ]

        */



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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
