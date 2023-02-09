package Clases;

import jakarta.persistence.*;

@Entity
public class Centros {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "ID_CENTRO", nullable = false)
    private Integer idCentro;
    @Basic
    @Column(name = "Nombre", nullable = true, length = 20)
    private String nombre;
    @Basic
    @Column(name = "Web", nullable = true, length = 20)
    private String web;
    @Basic
    @Column(name = "Contacto", nullable = true, length = 20)
    private String contacto;
    @ManyToOne
    @JoinColumn(name = "ID_CENTRO", referencedColumnName = "ID_Centro", nullable = false)
    private CentrosDeProyecto centrosDeProyectoByIdCentro;
    @ManyToOne
    @JoinColumn(name = "ID_CENTRO", referencedColumnName = "ID_Centro", nullable = false)
    private Usuario usuarioByIdCentro;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Centros centros = (Centros) o;

        if (autoId != null ? !autoId.equals(centros.autoId) : centros.autoId != null) return false;
        if (idCentro != null ? !idCentro.equals(centros.idCentro) : centros.idCentro != null) return false;
        if (nombre != null ? !nombre.equals(centros.nombre) : centros.nombre != null) return false;
        if (web != null ? !web.equals(centros.web) : centros.web != null) return false;
        if (contacto != null ? !contacto.equals(centros.contacto) : centros.contacto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (idCentro != null ? idCentro.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
        return result;
    }

    public CentrosDeProyecto getCentrosDeProyectoByIdCentro() {
        return centrosDeProyectoByIdCentro;
    }

    public void setCentrosDeProyectoByIdCentro(CentrosDeProyecto centrosDeProyectoByIdCentro) {
        this.centrosDeProyectoByIdCentro = centrosDeProyectoByIdCentro;
    }

    public Usuario getUsuarioByIdCentro() {
        return usuarioByIdCentro;
    }

    public void setUsuarioByIdCentro(Usuario usuarioByIdCentro) {
        this.usuarioByIdCentro = usuarioByIdCentro;
    }
}
