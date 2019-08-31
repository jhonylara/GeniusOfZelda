package jhonylara.geniusofzelda;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


public class GameOver extends AppCompatActivity {

    private MediaPlayer gameoverBack;
    private ImageButton yes;
    private ImageButton no;
    private MediaPlayer start;
    private JogadorDataSource datasource;
    private Jogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        gameoverBack = MediaPlayer.create(GameOver.this, R.raw.gameover);
        start = MediaPlayer.create(GameOver.this, R.raw.start);

        gameoverBack.start();

        yes = (ImageButton)findViewById(R.id.gameoverYes);
        no = (ImageButton)findViewById(R.id.gameoverNo);

        datasource = new JogadorDataSource(this);
        datasource.open();



        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.start();
                jogador = datasource.retornarJogador();
                datasource.alterarVidas(1,3);
                datasource.alterarLevel(1,0);
                datasource.alterarPontos(1,0);
                gameoverBack.stop();
                Intent intent = new Intent(GameOver.this, Play.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                GameOver.this.finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, TelaInicial.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                GameOver.this.finish();
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameoverBack.stop();
    }

}
