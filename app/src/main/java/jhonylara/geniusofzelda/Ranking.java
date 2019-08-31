package jhonylara.geniusofzelda;

import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Ranking extends AppCompatActivity {

    private JogadorDataSource datasource;
    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;

    private ImageView rankMed;

    private MediaPlayer rankBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rankBack = MediaPlayer.create(Ranking.this, R.raw.temple);

        rankBack.setLooping(true);
        rankBack.start();

        rankMed = (ImageView)findViewById(R.id.rankMed);

        datasource = new JogadorDataSource(this);
        datasource.open();

        Jogador jogador = datasource.retornarJogador();

        ImageView image = new ImageView(this);

        switch (jogador.getMedalhoes()){
            case 0:
                rankMed.setImageResource(R.drawable.meda);
                break;
            case 1:
                rankMed.setImageResource(R.drawable.medb);
                break;
            case 2:
                rankMed.setImageResource(R.drawable.medc);
                break;
            case 3:
                rankMed.setImageResource(R.drawable.medd);
                break;
            case 4:
                rankMed.setImageResource(R.drawable.mede);
                break;
            case 5:
                rankMed.setImageResource(R.drawable.medf);
                break;
            case 6:
                rankMed.setImageResource(R.drawable.medg);
                break;
            case 7:
                rankMed.setImageResource(R.drawable.medh);
                break;

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rankBack.stop();
    }
}
