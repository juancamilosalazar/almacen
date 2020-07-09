package co.com.pilae.almacen.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.pilae.almacen.entidades.Persona;

@Dao
public interface PersonaDao {
    @Insert
    void insert(Persona persona);
    @Update
    void update(Persona persona);
    @Query("SELECT * FROM persona WHERE idPersona=:idPersona ")
    Persona findById(Integer idPersona);
    @Query("SELECT * FROM persona")
    List<Persona> listar();
}
