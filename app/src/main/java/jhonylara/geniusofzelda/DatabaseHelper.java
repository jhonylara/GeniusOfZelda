package jhonylara.geniusofzelda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DBNAME = "Jogo";
    static final String TABELA_JOGADOR = "jogador";
    static final String COL_ID = "id";
    static final String COLNOME = "nome";
    static final String COLLEVEL = "level";
    static final String COLVIDAS = "vidas";
    static final String COLMEDALHOES = "medalhoes";
    static final String COLPONTOS = "pontos";
    static final int DBVERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
        // TODO Auto-generated constructor stub
    }
    //Criando o banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                +TABELA_JOGADOR
                +" ("
                +COL_ID+ " INTEGER PRIMARY KEY , "
                +
                COLNOME+ " TEXT, "
                +
                COLLEVEL+ " INT, "
                +
                COLVIDAS+ " INT, "
                +
                COLMEDALHOES+ " INT, "
                +
                COLPONTOS+ " INT );");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                +TABELA_JOGADOR);
    }
}