package jhonylara.geniusofzelda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {

    private ImageView img;
    private ImageButton btInfo;
    private MediaPlayer jogBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        jogBack = MediaPlayer.create(About.this, R.raw.temple);
        jogBack.setLooping(true);
        jogBack.start();

        ImageButton btInfo = (ImageButton)findViewById(R.id.btInfo);

        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog d = new AlertDialog.Builder(About.this)
                        .setPositiveButton(android.R.string.ok, null)
                        .setMessage(Html.fromHtml("Developed by:<br/>" +
                                "Jhony Lara<br/>" +
                                "<a href=\"https://www.linkedin.com/in/jhonylara/\">in/jhonylara</a><br/>" +
                                "<br/>" +
                                "Thanks to:<br/>" +
                                "Prof: Charles W. H. Fung<br/>"))
                        .setNeutralButton(
                                "Profile",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.linkedin.com/in/jhonylara/")));
                                    }
                                })
                        .setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .create();
                d.show();
                // Make the textview clickable. Must be called after show()
                ((TextView)d.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());





            }
        });


        ImageView mimageView = (ImageView) findViewById(R.id.imagem);

        /*Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.pp)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 500, 500, mpaint);// Round Image Corner 100 100 100 100
        mimageView.setImageBitmap(imageRounded);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jogBack.stop();
    }

}
