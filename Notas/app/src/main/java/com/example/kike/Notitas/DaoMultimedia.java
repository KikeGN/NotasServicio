package com.example.kike.Notitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DaoMultimedia {
    private Context _contexto;
    private SQLiteDatabase _midb;

    public DaoMultimedia(Context contexto){
        this._contexto = contexto;
        this._midb = new MyDBO(contexto).getWritableDatabase();
    }


    public long Insert(Multimedia c){
        ContentValues cv = new ContentValues();
        cv.put(MyDBO.COLUMNS_MULTIMEDIA[1],c.getArchivo());
        cv.put(MyDBO.COLUMNS_MULTIMEDIA[2],c.getDescripcion());
        cv.put(MyDBO.COLUMNS_MULTIMEDIA[3],c.getNota());
        return _midb.insert(MyDBO.TABLE_MULTIMEDIA,null,cv) ;

    }

    public long Update(Multimedia c){
        ContentValues cv = new ContentValues();
        cv.put(MyDBO.COLUMNS_MULTIMEDIA[1],c.getArchivo());
        cv.put(MyDBO.COLUMNS_MULTIMEDIA[2],c.getDescripcion());
        cv.put(MyDBO.COLUMNS_MULTIMEDIA[3],c.getNota());


        return _midb.update(MyDBO.TABLE_MULTIMEDIA,
                cv,
                "_id=?",
                new String[] { String.valueOf( c.getId())});
    }


    public int delete(String id){
        return  _midb.delete("multimedia","_id='"+id+"'",null);
    }
    ///////////////////////////////

    public List<Multimedia> getmultimedia(String id) {
        List<Multimedia> multimediaList = new ArrayList<Multimedia>();
        try{
            String selectQuery = "SELECT  * FROM multimedia WHERE Nota='"+id+"'";
            Log.d("", selectQuery);
            SQLiteDatabase db = this._midb;
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    Multimedia multimedia = new Multimedia();
                    multimedia.setId(c.getInt(c.getColumnIndex("_id")));
                    multimedia.setArchivo(c.getString(c.getColumnIndex("Archivo")));
                    multimedia.setDescripcion(c.getString(c.getColumnIndex("Descripcion")));
                    multimedia.setNota(c.getInt(c.getColumnIndex("Nota")));
                    multimediaList.add(multimedia);
                } while (c.moveToNext());
            }
        }catch (Exception err){
            Toast.makeText(_contexto,"NO SE CARGARON LAS NOTAS",Toast.LENGTH_SHORT).show();
        }
        return multimediaList;
    }




}
