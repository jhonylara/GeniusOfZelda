package jhonylara.geniusofzelda;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class JogadorDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;
    private String[] allColumns = {DatabaseHelper.COL_ID, DatabaseHelper.COLNOME, DatabaseHelper.COLLEVEL,
            DatabaseHelper.COLVIDAS, DatabaseHelper.COLMEDALHOES, DatabaseHelper.COLPONTOS, };

    //Construtor - inicializa o helper
    public JogadorDataSource(Context context) {
        dbhelper = new DatabaseHelper(context);
    }

    //Abre o helper
    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    //Fecha o helper
    public void close() {
        dbhelper.close();
    }

    //Adapta o resultado do cursor para a classe Estudante
    private Jogador cursorToJogador(Cursor cursor) {
        Jogador jogador = new Jogador();
        jogador.setId(cursor.getInt(0));
        jogador.setNome(cursor.getString(1));
        jogador.setLevel(cursor.getInt(2));
        jogador.setVidas(cursor.getInt(3));
        jogador.setMedalhoes(cursor.getInt(4));
        jogador.setPontos(cursor.getInt(5));
        return jogador;
    }

    public Jogador criarJogador(int id, String nome, int level, int vidas, int medalhoes, int pontos) {
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.COLNOME, nome);
        valores.put(DatabaseHelper.COLLEVEL, level);
        valores.put(DatabaseHelper.COLVIDAS, vidas);
        valores.put(DatabaseHelper.COLMEDALHOES, medalhoes);
        valores.put(DatabaseHelper.COLPONTOS, pontos);
        long insertId = database.insert(DatabaseHelper.TABELA_JOGADOR, null, valores);
        Cursor cursor = database.query(DatabaseHelper.TABELA_JOGADOR,
                allColumns, DatabaseHelper.COL_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Jogador novoJogador = cursorToJogador(cursor);
        cursor.close();
        return novoJogador;
    }

    public void deletarJogador(int id, String nome) {
        database.execSQL("DELETE FROM " + DatabaseHelper.TABELA_JOGADOR + " WHERE id = '" + id + "' OR nome = '" + nome + "'");
    }

    public void alterarVidas(int id, int vidas) {
        try {
            database.execSQL("UPDATE "  + DatabaseHelper.TABELA_JOGADOR + " SET " + DatabaseHelper.COLVIDAS + " = '" + vidas + "' WHERE id = '" + id + "'");
        }
        catch (Exception erroVida){
            Log.i("Erro",erroVida.toString());
        }
    }

    public void alterarMedalhoes(int id,int medalhoes) {
        try{
            database.execSQL("UPDATE "  + DatabaseHelper.TABELA_JOGADOR + " SET " + DatabaseHelper.COLMEDALHOES + " = '" + medalhoes + "' WHERE id = '" + id + "'");
        }
        catch (Exception erroMedalhoes){
            Log.i("Erro Medalhoes",erroMedalhoes.toString());
        }
    }



    public int verificarJogador(){
        Cursor cursor = database.query(DatabaseHelper.TABELA_JOGADOR,
                allColumns, DatabaseHelper.COL_ID + " = " + 1, null, null, null, null);
        return cursor.getCount();
    }

    public void alterarLevel(int id,int level) {
        try{
            database.execSQL("UPDATE "  + DatabaseHelper.TABELA_JOGADOR + " SET " + DatabaseHelper.COLLEVEL + " = '" + level + "' WHERE id = '" + id + "'");
        }
        catch (Exception erroLevel){
            Log.i("Erro Level",erroLevel.toString());
        }
    }

    public void alterarPontos(int id, int pontos) {
        try{
        database.execSQL("UPDATE "  + DatabaseHelper.TABELA_JOGADOR + " SET " + DatabaseHelper.COLPONTOS + " = '" + pontos + "' WHERE id = '" + id + "'");
        }
        catch (Exception erroPontos){
            Log.i("Erro Pontos",erroPontos.toString());
        }
    }

    public Jogador retornarJogador() {
        Cursor cursor = database.query(DatabaseHelper.TABELA_JOGADOR,
                allColumns, DatabaseHelper.COL_ID + " = " + 1, null, null, null, null);
        cursor.moveToFirst();
        if( cursor != null && cursor.moveToFirst() ){
            Jogador jogador = cursorToJogador(cursor);
            cursor.close();
            return jogador;
        }
        else
        {
            Jogador jogador = null;
            return jogador;
        }
    }

    public ArrayList<Jogador>  getAllJogadores() {
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        Cursor cursor = database.query(DatabaseHelper.TABELA_JOGADOR,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Jogador jogador = cursorToJogador(cursor);
            jogadores.add(jogador);
            cursor.moveToNext();
        }
        cursor.close();
        return jogadores;
    }


}