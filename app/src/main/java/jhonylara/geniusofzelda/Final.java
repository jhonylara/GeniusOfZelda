package jhonylara.geniusofzelda;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Final extends AppCompatActivity {

    private MediaPlayer soundfinal;
    private ImageButton btFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        btFinal = (ImageButton) findViewById(R.id.btFinal);
        soundfinal = MediaPlayer.create(Final.this, R.raw.soundfinal);
        soundfinal.start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundfinal.stop();
                Intent intent = new Intent(Final.this, TelaInicial.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Final.this.finish();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        soundfinal.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundfinal.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundfinal.stop();
    }
}
