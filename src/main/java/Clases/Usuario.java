package Clases;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "ID_Centro", nullable = false)
    private Integer idCentro;
    @Basic
    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;
    @Basic
    @Column(name = "Nombre", nullable = true, length = 20)
    private String nombre;
    @Basic
    @Column(name = "Apellidos", nullable = true, length = 20)
    private String apellidos;
    @Basic
    @Column(name = "Contraseña", nullable = true, length = 30)
    private String contraseña;
    @Basic
    @Column(name = "Rol", nullable = true, length = 15)
    private String rol;
    @Basic
    @Column(name = "Puntuacion", nullable = true, precision = 0)
    private Double puntuacion;
    @Basic
    @Column(name = "Familia_Profesional", nullable = false)
    private Integer familiaProfesional;
    @Basic
    @Column(name = "Email", nullable = true, length = 20)
    private String email;
    @Basic
    @Column(name = "Telefono", nullable = true)
    private Integer telefono;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "Escritor", nullable = false)
    private Comentarios comentariosByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "id_Usuario", nullable = false)
    private Participantes participantesByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    private ProyectosFav proyectosFavByIdUsuario;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(Integer idCentro) {
        this.idCentro = idCentro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Integer getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(Integer familiaProfesional) {
        this.familiaProfesional = familiaProfesional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (autoId != null ? !autoId.equals(usuario.autoId) : usuario.autoId != null) return false;
        if (idCentro != null ? !idCentro.equals(usuario.idCentro) : usuario.idCentro != null) return false;
        if (idUsuario != null ? !idUsuario.equals(usuario.idUsuario) : usuario.idUsuario != null) return false;
        if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(usuario.apellidos) : usuario.apellidos != null) return false;
        if (contraseña != null ? !contraseña.equals(usuario.contraseña) : usuario.contraseña != null) return false;
        if (rol != null ? !rol.equals(usuario.rol) : usuario.rol != null) return false;
        if (puntuacion != null ? !puntuacion.equals(usuario.puntuacion) : usuario.puntuacion != null) return false;
        if (familiaProfesional != null ? !familiaProfesional.equals(usuario.familiaProfesional) : usuario.familiaProfesional != null)
            return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (telefono != null ? !telefono.equals(usuario.telefono) : usuario.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (idCentro != null ? idCentro.hashCode() : 0);
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (contraseña != null ? contraseña.hashCode() : 0);
        result = 31 * result + (rol != null ? rol.hashCode() : 0);
        result = 31 * result + (puntuacion != null ? puntuacion.hashCode() : 0);
        result = 31 * result + (familiaProfesional != null ? familiaProfesional.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    public Comentarios getComentariosByIdUsuario() {
        return comentariosByIdUsuario;
    }

    public void setComentariosByIdUsuario(Comentarios comentariosByIdUsuario) {
        this.comentariosByIdUsuario = comentariosByIdUsuario;
    }

    public Participantes getParticipantesByIdUsuario() {
        return participantesByIdUsuario;
    }

    public void setParticipantesByIdUsuario(Participantes participantesByIdUsuario) {
        this.participantesByIdUsuario = participantesByIdUsuario;
    }

    public ProyectosFav getProyectosFavByIdUsuario() {
        return proyectosFavByIdUsuario;
    }

    public void setProyectosFavByIdUsuario(ProyectosFav proyectosFavByIdUsuario) {
        this.proyectosFavByIdUsuario = proyectosFavByIdUsuario;
    }
}
