package jhonylara.geniusofzelda;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class Play extends AppCompatActivity {

    int[] sequencia = new int[30];

    int[] resposta = new int[30];

    int[] vetvidas = new int[10];

    int vidas = 3;
    int nivel = 0;
    int pontuacao = 0;

    int setarTimer;

    private MediaPlayer up;
    private MediaPlayer left;
    private MediaPlayer right;
    private MediaPlayer down;
    private MediaPlayer a;
    private MediaPlayer correto;
    private MediaPlayer errado;

    private MediaPlayer sounda;
    private MediaPlayer soundb;
    private MediaPlayer soundc;
    private MediaPlayer soundd;
    private MediaPlayer sounde;
    private MediaPlayer soundf;
    private MediaPlayer soundfinal;

    private LinearLayout mainBack;
    private TextView tvPontos;

    private ImageButton btup;
    private ImageButton btleft;
    private ImageButton btright;
    private ImageButton btdown;
    private ImageButton bta;

    private ImageView imgc0l1;
    private ImageView imgc0l2;
    private ImageView imgc0l3;
    private ImageView imgc0l4;
    private ImageView imgc0l5;
    private ImageView imgc1l1;
    private ImageView imgc1l2;
    private ImageView imgc1l3;
    private ImageView imgc1l4;
    private ImageView imgc1l5;
    private ImageView imgc2l1;
    private ImageView imgc2l2;
    private ImageView imgc2l3;
    private ImageView imgc2l4;
    private ImageView imgc2l5;
    private ImageView imgc3l1;
    private ImageView imgc3l2;
    private ImageView imgc3l3;
    private ImageView imgc3l4;
    private ImageView imgc3l5;
    private ImageView imgc4l1;
    private ImageView imgc4l2;
    private ImageView imgc4l3;
    private ImageView imgc4l4;
    private ImageView imgc4l5;
    private ImageView imgc5l1;
    private ImageView imgc5l2;
    private ImageView imgc5l3;
    private ImageView imgc5l4;
    private ImageView imgc5l5;

    private ImageView heart0;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private ImageView heart4;
    private ImageView heart5;
    private ImageView heart6;
    private ImageView heart7;
    private ImageView heart8;
    private ImageView heart9;

    private TextView tvNome;
    private TextView tvLv;

    private JogadorDataSource datasource;
    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;
    private static Jogador jogador;

    private boolean apagado = true;
    private boolean liberarResposta = false;
    int indice = 0;
    int posicaoResposta = 0;

    int posicao = 0;
    private int[][] coordenadas = new int[][]{
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        tvPontos = (TextView)findViewById(R.id.tvPontos);
        tvNome = (TextView)findViewById(R.id.tvNome);
        tvLv = (TextView)findViewById(R.id.tvLv);

        mainBack = (LinearLayout)findViewById(R.id.mainBack);

        up = MediaPlayer.create(Play.this, R.raw.up);
        left = MediaPlayer.create(Play.this, R.raw.left);
        right = MediaPlayer.create(Play.this, R.raw.right);
        down = MediaPlayer.create(Play.this, R.raw.down);
        a = MediaPlayer.create(Play.this, R.raw.a);

        correto = MediaPlayer.create(Play.this, R.raw.correto);
        errado = MediaPlayer.create(Play.this, R.raw.errado);

        sounda = MediaPlayer.create(Play.this, R.raw.sounda);
        soundb = MediaPlayer.create(Play.this, R.raw.soundb);
        soundc = MediaPlayer.create(Play.this, R.raw.soundc);
        soundd = MediaPlayer.create(Play.this, R.raw.soundd);
        sounde = MediaPlayer.create(Play.this, R.raw.soude);

        soundf = MediaPlayer.create(Play.this, R.raw.soundf);
        soundfinal = MediaPlayer.create(Play.this, R.raw.soundfinal);

        imgc0l1 = (ImageView)findViewById(R.id.imgc0l1);
        imgc0l2 = (ImageView)findViewById(R.id.imgc0l2);
        imgc0l3 = (ImageView)findViewById(R.id.imgc0l3);
        imgc0l4 = (ImageView)findViewById(R.id.imgc0l4);
        imgc0l5 = (ImageView)findViewById(R.id.imgc0l5);
        imgc1l1 = (ImageView)findViewById(R.id.imgc1l1);
        imgc1l2 = (ImageView)findViewById(R.id.imgc1l2);
        imgc1l3 = (ImageView)findViewById(R.id.imgc1l3);
        imgc1l4 = (ImageView)findViewById(R.id.imgc1l4);
        imgc1l5 = (ImageView)findViewById(R.id.imgc1l5);
        imgc2l1 = (ImageView)findViewById(R.id.imgc2l1);
        imgc2l2 = (ImageView)findViewById(R.id.imgc2l2);
        imgc2l3 = (ImageView)findViewById(R.id.imgc2l3);
        imgc2l4 = (ImageView)findViewById(R.id.imgc2l4);
        imgc2l5 = (ImageView)findViewById(R.id.imgc2l5);
        imgc3l1 = (ImageView)findViewById(R.id.imgc3l1);
        imgc3l2 = (ImageView)findViewById(R.id.imgc3l2);
        imgc3l3 = (ImageView)findViewById(R.id.imgc3l3);
        imgc3l4 = (ImageView)findViewById(R.id.imgc3l4);
        imgc3l5 = (ImageView)findViewById(R.id.imgc3l5);
        imgc4l1 = (ImageView)findViewById(R.id.imgc4l1);
        imgc4l2 = (ImageView)findViewById(R.id.imgc4l2);
        imgc4l3 = (ImageView)findViewById(R.id.imgc4l3);
        imgc4l4 = (ImageView)findViewById(R.id.imgc4l4);
        imgc4l5 = (ImageView)findViewById(R.id.imgc4l5);
        imgc5l1 = (ImageView)findViewById(R.id.imgc5l1);
        imgc5l2 = (ImageView)findViewById(R.id.imgc5l2);
        imgc5l3 = (ImageView)findViewById(R.id.imgc5l3);
        imgc5l4 = (ImageView)findViewById(R.id.imgc5l4);
        imgc5l5 = (ImageView)findViewById(R.id.imgc5l5);

        heart0 = (ImageView)findViewById(R.id.heart0);
        heart1 = (ImageView)findViewById(R.id.heart1);
        heart2 = (ImageView)findViewById(R.id.heart2);
        heart3 = (ImageView)findViewById(R.id.heart3);
        heart4= (ImageView)findViewById(R.id.heart4);
        heart5= (ImageView)findViewById(R.id.heart5);
        heart6 = (ImageView)findViewById(R.id.heart6);
        heart7 = (ImageView)findViewById(R.id.heart7);
        heart8 = (ImageView)findViewById(R.id.heart8);
        heart9 = (ImageView)findViewById(R.id.heart9);

        btup = (ImageButton)findViewById(R.id.btup);
        btleft = (ImageButton)findViewById(R.id.btleft);
        btright = (ImageButton)findViewById(R.id.btright);
        btdown = (ImageButton)findViewById(R.id.btdown);
        bta = (ImageButton)findViewById(R.id.bta);

        btup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (liberarResposta){
                    receberResposta(0);
                    tocarMusica(0);
                }
            }
        });

        btleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (liberarResposta){
                    receberResposta(1);
                    tocarMusica(1);
                }
            }
        });

        btright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (liberarResposta){
                    receberResposta(2);
                    tocarMusica(2);
                }
            }
        });

        btdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (liberarResposta){
                    receberResposta(3);
                    tocarMusica(3);
                }
            }
        });

        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (liberarResposta){
                    receberResposta(4);
                    tocarMusica(4);
                }
            }
        });

        datasource = new JogadorDataSource(this);
        datasource.open();

        jogador = datasource.retornarJogador();

        if (jogador.getVidas() == 0){
            gameOver();
        }

        vidas = jogador.getVidas();
        nivel = jogador.getLevel();
        pontuacao = jogador.getPontos();
        tvNome.setText(jogador.getNome());
        tvPontos.setText(String.valueOf(pontuacao));
        tvLv.setText(String.valueOf(jogador.getLevel()));

        comecar();
    }

    public void montarMatriz(int numero){
        if (posicao <= 5){
            for (int i = 0; i <= 4; i++){
                if (i == numero){
                    coordenadas[i][posicao] = 1;
                }
                else
                {
                    coordenadas[i][posicao] = 0;
                }
            }
            posicao++;
        }
        else
        {
            for (int coluna = 0; coluna < 5; coluna++){
                for (int linha = 0; linha <= 4; linha++){
                    coordenadas[linha][coluna] = coordenadas[linha][coluna+1];
                }
            }
            for (int i = 0; i <= 4; i++){
                if (i == numero){
                    coordenadas[i][5] = 1;
                }
                else
                {
                    coordenadas[i][5] = 0;
                }
            }
        }
    }

    public void mostrarSequencia (){
        mostrarVidas();

        int duracao = (sequencia.length * 1000) + 500;
        new CountDownTimer(duracao, 1000) {

            public void onTick(long millisUntilFinished) {
                if (indice < sequencia.length){
                    montarMatriz(sequencia[indice]);
                    tocarMusica(sequencia[indice]);
                    construirTela();
                    indice ++;
                    if (indice > sequencia.length){
                        cancel();
                        limparTela();
                        liberarResposta = true;
                    }
                }
            }
            public void onFinish() {
                limparTela();
                liberarResposta = true;
            }
        }.start();
    }

    public void tocarMusica(int numero){
        switch (numero){
            case 0:
                up.start();
                break;
            case 1:
                left.start();
                break;
            case 2:
                right.start();
                break;
            case 3:
                down.start();
                break;
            case 4:
                a.start();
                break;
        }
    }

    public void receberResposta(int numero){

        if (posicaoResposta < sequencia.length){
            resposta[posicaoResposta] = numero;

            montarMatriz(numero);
            construirTela();

            posicaoResposta++;
        }
        else {
            liberarResposta = false;
        }

        if (posicaoResposta == sequencia.length){
            liberarResposta = false;
            validarResultado();
        }
    }

    public void validarTecla(){

    }

    public void validarResultado(){
        if (Arrays.equals(sequencia,resposta)){
            liberarResposta = false;
            prepararProximaFase();
        }
        else
        {
            resetarContadores();
            liberarResposta = false;
            jogador = datasource.retornarJogador();
            datasource.alterarVidas(1, jogador.getVidas() -1);
            repetirFase();
        }
    }

    public void prepararProximaFase(){
        jogador = datasource.retornarJogador();
        nivel = jogador.getLevel();
        String texto = "Next level:" + (nivel+1);
        int tempo = 0;
        ImageView image = new ImageView(this);

        if (nivel == 0 ){
            sounda.start();
            tempo = 17000;
            texto = "You earned a Forest Medallion!";
            image.setImageResource(R.drawable.mma);
            datasource.alterarMedalhoes(1,1);
        }
        if (nivel == 1){
            soundb.start();
            tempo = 18000;
            texto = "You earned a Light Medallion!";
            image.setImageResource(R.drawable.mmb);
            datasource.alterarMedalhoes(1,2);
        }
        if (nivel == 2){
            soundc.start();
            tempo = 23000;
            texto = "You earned a Shadow Medallion!";
            image.setImageResource(R.drawable.mmc);
            datasource.alterarMedalhoes(1,3);
        }
        if (nivel == 3){
            soundd.start();
            tempo = 24000;
            texto = "You earned a Spirit Medallion!";
            image.setImageResource(R.drawable.mmd);
            datasource.alterarMedalhoes(1,4);
        }
        if (nivel == 4){
            soundd.start();
            tempo = 19000;
            texto = "You earned a Water Medallion!";
            image.setImageResource(R.drawable.mme);
            datasource.alterarMedalhoes(1,5);
        }
        if (nivel == 5){
            soundf.start();
            tempo = 20000;
            texto = "You earned a Fire Medallion!";
            image.setImageResource(R.drawable.mmf);
            datasource.alterarMedalhoes(1,6);
        }
        if (nivel == 6){
            soundfinal.start();
            tempo = 50000;
            texto = "You earned a Triforce!";
            image.setImageResource(R.drawable.triforce);
            datasource.alterarMedalhoes(1,7);
        }

        final AlertDialog.Builder alertaProxFase = new AlertDialog.Builder(Play.this);
        alertaProxFase.setIcon(android.R.drawable.stat_sys_warning);
        alertaProxFase.setIcon(R.drawable.fada);
        alertaProxFase.setCancelable(false);
        alertaProxFase.setTitle("You did it!");
        alertaProxFase.setMessage(texto);
        alertaProxFase.setView(image);
        alertaProxFase.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                if (nivel == 6){
                    pararSons();
                    Intent intent = new Intent(Play.this, Final.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Play.this.finish();
                }
                calcularPontos();
                jogador = datasource.retornarJogador();
                datasource.alterarLevel(1,jogador.getLevel()+1);
                tvLv.setText(String.valueOf(jogador.getLevel()+1));
                colocarBackgroud();
                resetarContadores();
                comecar();

            }
        });

        new CountDownTimer(tempo, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                alertaProxFase.show();
            }
        }.start();
    }

    public void mudarFase(){
        jogador = datasource.retornarJogador();
        nivel = jogador.getLevel();
        switch (nivel){
            case 0:
                int[] fase = {4,0,1,2,1,2};
                sequencia = Arrays.copyOf(fase, fase.length);
                resposta = Arrays.copyOf(fase, fase.length);
                break;
            case 1:
                int[] fase1 = {1,3,1,3,2,1};
                sequencia = Arrays.copyOf(fase1, fase1.length);
                resposta = Arrays.copyOf(fase1, fase1.length);
                break;
            case 2:
                int[] fase2 = {1,2,2,4,1,2,3};
                sequencia = Arrays.copyOf(fase2, fase2.length);
                resposta = Arrays.copyOf(fase2, fase2.length);
                break;
            case 3:
                int[] fase3 = {4,3,4,2,3,4};
                sequencia = Arrays.copyOf(fase3, fase3.length);
                resposta = Arrays.copyOf(fase3, fase3.length);
                break;
            case 4:
                int[] fase4 = {4,3,2,2,1};
                sequencia = Arrays.copyOf(fase4, fase4.length);
                resposta = Arrays.copyOf(fase4, fase4.length);
                break;
            case 5:
                int[] fase5 = {3,4,3,4,2,3,2,3};
                sequencia = Arrays.copyOf(fase5, fase5.length);
                resposta = Arrays.copyOf(fase5, fase5.length);
                break;
            case 6:
                int[] fase6 = {1,0,2,1,0,2};
                sequencia = Arrays.copyOf(fase6, fase6.length);
                resposta = Arrays.copyOf(fase6, fase6.length);
                break;
            default:
                randomizarFase();
        }
    }

    public void randomizarFase(){
        jogador = datasource.retornarJogador();
        nivel = jogador.getLevel();
        Random rand = new Random();
        if (nivel < 5) {
            int[] fase = new int[5];
            for (int i = 0; i <= 4; i++) {
                fase[i] = rand.nextInt(4);
            }
            sequencia = Arrays.copyOf(fase, fase.length);
            resposta = Arrays.copyOf(fase, fase.length);
        }
        else
        {
            int[] fase = new int[nivel];
            for (int i = 0; i <= 4; i++) {
                fase[i] = rand.nextInt(4);
            }
            sequencia = Arrays.copyOf(fase, fase.length);
            resposta = Arrays.copyOf(fase, fase.length);
        }
    }

    public void repetirFase(){
        jogador = datasource.retornarJogador();
        vidas = jogador.getVidas();
        if (vidas > 0){
            AlertDialog.Builder alerta = new AlertDialog.Builder(Play.this);
            alerta.setIcon(android.R.drawable.stat_sys_warning);
            alerta.setCancelable(false);
            alerta.setIcon(R.drawable.fada);
            alerta.setTitle("Listen!");
            alerta.setMessage("You only have " + vidas +" more hearts.");
            alerta.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    mostrarSequencia();
                }
            });
            alerta.show();
        }
        else
        {
            gameOver();
        }

    }

    public void resetarContadores(){
        indice = 0;
        posicaoResposta =0;
        posicao=0;
        coordenadas = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
    }

    public void limparTela(){
        posicao = 0;
        coordenadas = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        construirTela();
    }

    public void comecar(){
        jogador = datasource.retornarJogador();
        nivel = jogador.getLevel();
        if (nivel == 0){
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.ocarina);

            AlertDialog.Builder alerta = new AlertDialog.Builder(Play.this);
            alerta.setIcon(android.R.drawable.stat_sys_warning);
            alerta.setCancelable(false);
            alerta.setIcon(R.drawable.veio);
            alerta.setTitle("it's dangerous to go alone!");
            alerta.setMessage("Complete the songs to get the medallions\n" +
                    "Wait for the sequence of notes and try to play it back!\n" +
                    "\nTake this:");
            alerta.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    mudarFase();
                    //calcularPontos();
                    mostrarPontos();
                    mostrarSequencia();
                }
            }).
            setView(image);
            alerta.show();
        }
        else
        {
            colocarBackgroud();
            mudarFase();
            mostrarPontos();
            //calcularPontos();
            mostrarSequencia();
        }
    }

    public void calcularPontos(){

        jogador = datasource.retornarJogador();
        pontuacao = jogador.getPontos();
        pontuacao += (nivel * 10) + (vidas * 10);
        datasource.alterarPontos(1,pontuacao);
        //tvPontos = (TextView)findViewById(R.id.tvPontos);
        //tvPontos.setText(""+ pontuacao);
    }

    public void mostrarPontos(){
        jogador = datasource.retornarJogador();
        pontuacao = jogador.getPontos();
        tvPontos = (TextView)findViewById(R.id.tvPontos);
        tvPontos.setText(""+ pontuacao);
    }

    public void construirTela(){
        imgc0l1 = (ImageView)findViewById(R.id.imgc0l1);
        imgc0l2 = (ImageView)findViewById(R.id.imgc0l2);
        imgc0l3 = (ImageView)findViewById(R.id.imgc0l3);
        imgc0l4 = (ImageView)findViewById(R.id.imgc0l4);
        imgc0l5 = (ImageView)findViewById(R.id.imgc0l5);
        imgc1l1 = (ImageView)findViewById(R.id.imgc1l1);
        imgc1l2 = (ImageView)findViewById(R.id.imgc1l2);
        imgc1l3 = (ImageView)findViewById(R.id.imgc1l3);
        imgc1l4 = (ImageView)findViewById(R.id.imgc1l4);
        imgc1l5 = (ImageView)findViewById(R.id.imgc1l5);
        imgc2l1 = (ImageView)findViewById(R.id.imgc2l1);
        imgc2l2 = (ImageView)findViewById(R.id.imgc2l2);
        imgc2l3 = (ImageView)findViewById(R.id.imgc2l3);
        imgc2l4 = (ImageView)findViewById(R.id.imgc2l4);
        imgc2l5 = (ImageView)findViewById(R.id.imgc2l5);
        imgc3l1 = (ImageView)findViewById(R.id.imgc3l1);
        imgc3l2 = (ImageView)findViewById(R.id.imgc3l2);
        imgc3l3 = (ImageView)findViewById(R.id.imgc3l3);
        imgc3l4 = (ImageView)findViewById(R.id.imgc3l4);
        imgc3l5 = (ImageView)findViewById(R.id.imgc3l5);
        imgc4l1 = (ImageView)findViewById(R.id.imgc4l1);
        imgc4l2 = (ImageView)findViewById(R.id.imgc4l2);
        imgc4l3 = (ImageView)findViewById(R.id.imgc4l3);
        imgc4l4 = (ImageView)findViewById(R.id.imgc4l4);
        imgc4l5 = (ImageView)findViewById(R.id.imgc4l5);
        imgc5l1 = (ImageView)findViewById(R.id.imgc5l1);
        imgc5l2 = (ImageView)findViewById(R.id.imgc5l2);
        imgc5l3 = (ImageView)findViewById(R.id.imgc5l3);
        imgc5l4 = (ImageView)findViewById(R.id.imgc5l4);
        imgc5l5 = (ImageView)findViewById(R.id.imgc5l5);

        if (coordenadas[0][0] == 1){
            imgc0l1.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc0l1.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[1][0] == 1){
            imgc0l2.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc0l2.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[2][0] == 1){
            imgc0l3.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc0l3.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[3][0] == 1){
            imgc0l4.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc0l4.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[4][0] == 1){
            imgc0l5.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc0l5.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[0][1] == 1){
            imgc1l1.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc1l1.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[1][1] == 1){
            imgc1l2.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc1l2.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[2][1] == 1){
            imgc1l3.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc1l3.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[3][1] == 1){
            imgc1l4.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc1l4.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[4][1] == 1){
            imgc1l5.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc1l5.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[0][2] == 1){
            imgc2l1.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc2l1.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[1][2] == 1){
            imgc2l2.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc2l2.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[2][2] == 1){
            imgc2l3.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc2l3.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[3][2] == 1){
            imgc2l4.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc2l4.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[4][2] == 1){
            imgc2l5.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc2l5.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[0][3] == 1){
            imgc3l1.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc3l1.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[1][3] == 1){
            imgc3l2.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc3l2.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[2][3] == 1){
            imgc3l3.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc3l3.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[3][3] == 1){
            imgc3l4.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc3l4.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[4][3] == 1){
            imgc3l5.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc3l5.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[0][4] == 1){
            imgc4l1.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc4l1.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[1][4] == 1){
            imgc4l2.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc4l2.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[2][4] == 1){
            imgc4l3.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc4l3.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[3][4] == 1){
            imgc4l4.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc4l4.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[4][4] == 1){
            imgc4l5.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc4l5.setVisibility(View.INVISIBLE);
        }

        if (coordenadas[0][5] == 1){
            imgc5l1.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc5l1.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[1][5] == 1){
            imgc5l2.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc5l2.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[2][5] == 1){
            imgc5l3.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc5l3.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[3][5] == 1){
            imgc5l4.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc5l4.setVisibility(View.INVISIBLE);
        }
        if (coordenadas[4][5] == 1){
            imgc5l5.setVisibility(View.VISIBLE);
        }
        else
        {
            imgc5l5.setVisibility(View.INVISIBLE);
        }
    }

    public void mostrarVidas(){
        int vida = jogador.getVidas();
        for (int i = 1; i < 10; i++ ){
            if (i <= vida){
                vetvidas[i-1] = 1;
            }
            else
            {
                vetvidas[i-1] = 0;
            }
        }

        if (vetvidas[0] == 1){
            heart0.setVisibility(View.VISIBLE);
        }
        else
        {
            heart0.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[1] == 1){
            heart1.setVisibility(View.VISIBLE);
        }
        else
        {
            heart1.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[2] == 1){
            heart2.setVisibility(View.VISIBLE);
        }
        else
        {
            heart2.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[3] == 1){
            heart3.setVisibility(View.VISIBLE);
        }
        else
        {
            heart3.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[4] == 1){
            heart4.setVisibility(View.VISIBLE);
        }
        else
        {
            heart4.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[5] == 1){
            heart5.setVisibility(View.VISIBLE);
        }
        else
        {
            heart5.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[6] == 1){
            heart6.setVisibility(View.VISIBLE);
        }
        else
        {
            heart6.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[7] == 1){
            heart7.setVisibility(View.VISIBLE);
        }
        else
        {
            heart7.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[8] == 1){
            heart8.setVisibility(View.VISIBLE);
        }
        else
        {
            heart8.setVisibility(View.INVISIBLE);
        }
        if (vetvidas[9] == 1){
            heart9.setVisibility(View.VISIBLE);
        }
        else
        {
            heart9.setVisibility(View.INVISIBLE);
        }
    }

    public void colocarBackgroud(){
        jogador = datasource.retornarJogador();
        nivel = jogador.getLevel();
        if (nivel == 0){
            mainBack.setBackgroundResource(R.drawable.backa);
        }
        if (nivel == 1){
            mainBack.setBackgroundResource(R.drawable.backb);
        }
        if (nivel == 2){
            mainBack.setBackgroundResource(R.drawable.backc);
        }
        if (nivel == 3){
            mainBack.setBackgroundResource(R.drawable.backd);
        }
        if (nivel == 4){
            mainBack.setBackgroundResource(R.drawable.backe);
        }
        if (nivel == 5){
            mainBack.setBackgroundResource(R.drawable.backf);
        }
        if (nivel == 6){
            mainBack.setBackgroundResource(R.drawable.backfinal);
        }
    }

    public void pararSons(){
        up.stop();
        left.stop();
        right.stop();
        down.stop();
        a.stop();
        correto.stop();
        errado.stop();
        sounda.stop();
        soundb.stop();
        soundc.stop();
        soundd.stop();
        sounde.stop();
        soundf.stop();
        soundfinal.stop();
    }

    public void gameOver(){
        pararSons();
        Intent intent = new Intent(Play.this, GameOver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Play.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pararSons();
    }
}
