package com.example.mediaescolarmvc.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import com.example.mediaescolarmvc.model.MediaEscolar;

import java.util.ArrayList;
import java.util.List;

public class DataSource extends SQLiteOpenHelper {
    private static final String DB_NAME = "media_escolar.sqlite";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public DataSource(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();

        //Verifica se a tabela existe
        db.execSQL(MediaEscolarDataModel.criarTabela());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(MediaEscolarDataModel.criarTabela());
            Log.e("Media", "Tabela Criada com Sucesso na versão 2 do banco de dados");
        } catch (Exception ex) {
            Log.e("Media", "db ----> Erro:" + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String tabela, ContentValues dados){

        boolean sucesso = false;
        try {
            sucesso = db.insert(tabela, null,dados) > 0;
        }
        catch (Exception e)
        {
            sucesso = false;
        }

        return sucesso;
    }
    public boolean deletar(String tabela, int id){

        boolean sucesso = true;

        sucesso = db.delete(tabela, "id=?",new String[]{Integer.toString(id)}) > 0;

        return  sucesso;
    }

    public boolean alterar(String tabela, ContentValues dados)
    {

        boolean sucesso = true;

        int id = dados.getAsInteger("id");

        sucesso = db.update(tabela, dados, "id=?",new String[]{Integer.toString(id)}) > 0;

        return  sucesso;
    }

    public List<MediaEscolar> getAllMediaEscolar() {
        try {
            MediaEscolar obj;
            // TIPADA
            List<MediaEscolar> lista = new ArrayList<>();
            String sql = "SELECT * FROM mediaescolar";
            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    obj = new MediaEscolar();
                    obj.setId(cursor.getInt(cursor.getColumnIndex(MediaEscolarDataModel.getId())));
                    obj.setMateria(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getMateria())));
                    obj.setSituacao(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getSituacao())));

                    lista.add(obj);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return lista;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public ArrayList<MediaEscolar> getAllResultadoFinal()
    {
        try {
            MediaEscolar obj;
            // TIPADA
            ArrayList<MediaEscolar> lista = new ArrayList<>();
            String sql = "SELECT * FROM mediaescolar";
            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    obj = new MediaEscolar();
                    obj.setId(cursor.getInt(cursor.getColumnIndex(MediaEscolarDataModel.getId())));
                    obj.setBimestre(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getBimestre())));
                    obj.setMateria(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getMateria())));
                    obj.setSituacao(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getSituacao())));

                    lista.add(obj);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return lista;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
