package ClasesMongo;

import org.bson.types.ObjectId;

public class UsuariosMongo {
    private ObjectId usuarioId;
    private ObjectId centroId;
    private ObjectId familiaProId;
    private String nombreUsuario;
    private String nombre;
    private String apellidos;
    private String rol;
    private double puntuacion;
    private String email;
    private int telefono;

    public UsuariosMongo(ObjectId usuarioId, ObjectId centroId, ObjectId familiaProId, String nombreUsuario, String nombre, String apellidos, String rol, double puntuacion, String email, int telefono) {
        this.usuarioId = usuarioId;
        this.centroId = centroId;
        this.familiaProId = familiaProId;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.puntuacion = puntuacion;
        this.email = email;
        this.telefono = telefono;
    }

    public UsuariosMongo() {
    }

    public ObjectId getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(ObjectId usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ObjectId getCentroId() {
        return centroId;
    }

    public void setCentroId(ObjectId centroId) {
        this.centroId = centroId;
    }

    public ObjectId getFamiliaProId() {
        return familiaProId;
    }

    public void setFamiliaProId(ObjectId familiaProId) {
        this.familiaProId = familiaProId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}