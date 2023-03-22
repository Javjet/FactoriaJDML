package ClasesMongo;

import org.bson.types.ObjectId;

public class CentrosDeProyectoMongo {
    private ObjectId id;
    private ObjectId proyectoId;
    private ObjectId centroId;

    public CentrosDeProyectoMongo(ObjectId id, ObjectId proyectoId, ObjectId centroId) {
        this.id = id;
        this.proyectoId = proyectoId;
        this.centroId = centroId;
    }

    public CentrosDeProyectoMongo() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(ObjectId proyectoId) {
        this.proyectoId = proyectoId;
    }

    public ObjectId getCentroId() {
        return centroId;
    }

    public void setCentroId(ObjectId centroId) {
        this.centroId = centroId;
    }

}

