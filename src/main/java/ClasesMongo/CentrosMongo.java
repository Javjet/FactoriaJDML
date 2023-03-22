package ClasesMongo;

import org.bson.types.ObjectId;

public class CentrosMongo {

    private ObjectId id;
    private String nombre;
    private String web;
    private String contacto;
    private boolean activo;

    public CentrosMongo(ObjectId id, String nombre, String web, String contacto, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.web = web;
        this.contacto = contacto;
        this.activo = activo;
    }

    public CentrosMongo() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
