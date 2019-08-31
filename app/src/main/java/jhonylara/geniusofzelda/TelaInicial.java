package jhonylara.geniusofzelda;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TelaInicial extends AppCompatActivity {

    private Button btJogar;
    private Button btSobre;
    private Button btRank;

    private JogadorDataSource datasource;
    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;

    private boolean nomevalido = false;

    private static Jogador jogador;

    private MediaPlayer menuBack;
    private MediaPlayer start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        datasource = new JogadorDataSource(this);
        datasource.open();

        menuBack = MediaPlayer.create(TelaInicial.this, R.raw.menuback);
        start = MediaPlayer.create(TelaInicial.this, R.raw.start);

        jogador = datasource.retornarJogador();

        btJogar = (Button) findViewById(R.id.btJogar);
        btSobre = (Button) findViewById(R.id.btSobre);
        btRank = (Button) findViewById(R.id.btRank);

        final EditText input = new EditText(TelaInicial.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        if (datasource.verificarJogador() == 0){

            AlertDialog.Builder alerta = new AlertDialog.Builder(TelaInicial.this);

            final EditText saySomething = new EditText(TelaInicial.this);
            alerta.setPositiveButton("ok", null);
            alerta.setView(input);
            final AlertDialog mAlertDialog = alerta.create();
            mAlertDialog.setIcon(R.drawable.fada);
            mAlertDialog.setMessage("Can you remember your name?");
            mAlertDialog.setCancelable(false);
            mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

                @Override
                public void onShow(DialogInterface dialog) {

                    Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    b.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (!TextUtils.isEmpty(input.getText().toString()) && input.getText().length() >= 3 && input.getText().length() <= 8){
                                Jogador jogador = datasource.criarJogador( 1,input.getText().toString(), 0,3,0,0);
                                nomevalido = true;
                                mAlertDialog.dismiss();
                            }
                            else{
                                if (input.getText().length() <= 3){
                                    Toast.makeText(TelaInicial.this, "I remember that your name had more than 3 letters", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(TelaInicial.this, "I remember his name was less than 8 letters", Toast.LENGTH_SHORT).show();
                                }
                                nomevalido = false;
                            }
                        }
                    });
                }
            });
            mAlertDialog.show();
        }

        menuBack.setLooping(true);
        menuBack.start();

        btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.start();
                menuBack.pause();
                Intent intent = new Intent(TelaInicial.this, Play.class);
                startActivity(intent);
            }
        });

        btSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.start();
                menuBack.pause();
                Intent intent = new Intent(TelaInicial.this, About.class);
                startActivity(intent);
            }
        });

        btRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.start();
                menuBack.pause();
                Intent intent = new Intent(TelaInicial.this, Ranking.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        menuBack.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_inicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.menuAluno:
                Intent intent = new Intent(TelaInicial.this, About.class);
                startActivity(intent);
                break;

            case R.id.menuRanking:
                Intent intent2 = new Intent(TelaInicial.this, Ranking.class);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        menuBack.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        menuBack.pause();
    }
}
