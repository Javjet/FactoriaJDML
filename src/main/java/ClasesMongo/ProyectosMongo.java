package ClasesMongo;

import org.bson.types.ObjectId;

public class ProyectosMongo {
    private ObjectId id;
    private String titulo;
    private String descripcion;
    private String coordinador;
    private boolean estado;
    private String visibilidad;
    private int visitas;

    public ProyectosMongo(ObjectId id, String titulo, String descripcion, String coordinador, boolean estado, String visibilidad, int visitas) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.coordinador = coordinador;
        this.estado = estado;
        this.visibilidad = visibilidad;
        this.visitas = visitas;
    }

    public ProyectosMongo() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }
}
