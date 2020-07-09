package co.com.pilae.almacen.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import co.com.pilae.almacen.persistencia.Tabla;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = Tabla.PERSONA)
@NoArgsConstructor
public class Persona {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idPersona")
    private Integer idPersona;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "detalle")
    private String detalle;
    @ColumnInfo(name = "referencia")
    private String referencia;
    @ColumnInfo(name = "numeroTelefono")
    private String numeroTelefono;
    @ColumnInfo(name = "saldo")
    private double saldo;
    @ColumnInfo(name = "tope")
    private double tope;

}
