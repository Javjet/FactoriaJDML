package ClasesMongo;

import org.bson.types.ObjectId;

public class FamiliaProfesionalMongo {
    private ObjectId familiaProdesionalId;
    private String nombre;

    public FamiliaProfesionalMongo(ObjectId familiaProdesionalId, String nombre) {
        this.familiaProdesionalId = familiaProdesionalId;
        this.nombre = nombre;
    }

    public FamiliaProfesionalMongo() {
    }

    public ObjectId getFamiliaProdesionalId() {
        return familiaProdesionalId;
    }

    public void setFamiliaProdesionalId(ObjectId familiaProdesionalId) {
        this.familiaProdesionalId = familiaProdesionalId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
