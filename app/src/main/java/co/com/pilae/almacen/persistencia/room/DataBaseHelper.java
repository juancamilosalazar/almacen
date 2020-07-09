package co.com.pilae.almacen.persistencia.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.pilae.almacen.entidades.Persona;
import co.com.pilae.almacen.persistencia.dao.PersonaDao;

@Database(entities = {
        Persona.class,
            }, version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false)

public abstract class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "almacen";
    private static DataBaseHelper instace;


    public static DataBaseHelper getSimpleDB(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return instace;
    }

    public static DataBaseHelper getDBMainThread(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instace;
    }


    /**
     * Listado de DAO
     */

    public abstract PersonaDao getPersonaDao();
}
