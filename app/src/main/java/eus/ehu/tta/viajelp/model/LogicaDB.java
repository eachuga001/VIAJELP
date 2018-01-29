package eus.ehu.tta.viajelp.model;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edwin on 26/01/18.
 */

public class LogicaDB extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public LogicaDB(Activity a, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(a, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Usuario (idUsuario INT, " +
                "nombre VARCHAR, password VARCHAR, " +
                "usuario VARCHAR, edad INT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Usuario");
    }

    public void crearTablaUsuario(){
        db = getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuario (idUsuario INT, " +
                "nombre VARCHAR, usuario VARCHAR, edad INT, " +
                "password VARCHAR);");
        db.close();
    }

    public void borrarTablaUsuario(){
        db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        db.close();
    }

    //  0           1         2        3        4
    //idUsuario - nombre - usuario - edad - password

    public void saveUsuario(Usuario u){
        db = getWritableDatabase();
        db.execSQL("INSERT INTO Usuario VALUES ('" + u.getIdUsuario() + "'," +
                "'" + u.getNombre() + "','" + u.getUsuario() +"','" + u.getEdad()+ "','" + u.getPassword() + "');");
        db.close();
    }


    public Usuario getUsuarioFromDB(int idUsuario){
        Usuario usuario = new Usuario();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Usuario WHERE idUsuario='"+idUsuario+"'",null);
        if(c.getCount() == 0)
            System.out.println("Sin valoren en la tabla Tareas");
        else{
            while (c.moveToNext()){
                usuario.setIdUsuario(c.getInt(0));
                usuario.setNombre(c.getString(1));
                usuario.setUsuario(c.getString(2));
                usuario.setEdad(c.getInt(3));
                usuario.setPassword(c.getString(4));
            }

        }

        return usuario;
    }

    public Usuario getUsuarioByUserName(String userName){
        Usuario usuario = new Usuario();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Usuario WHERE usuario='"+userName+"'",null);
        if(c.getCount() == 0)
            System.out.println("Sin valoren en la tabla Tareas");
        else{
            while (c.moveToNext()){
                usuario.setIdUsuario(c.getInt(0));
                usuario.setNombre(c.getString(1));
                usuario.setUsuario(c.getString(2));
                usuario.setEdad(c.getInt(3));
                usuario.setPassword(c.getString(4));
            }

        }

        return usuario;
    }
}
