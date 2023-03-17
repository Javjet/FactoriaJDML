package Clases;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Centros", schema = "FactoriaProyectos", catalog = "")
public class CentrosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private int autoId;
    @Basic
    @Column(name = "ID_CENTRO", nullable = false)
    private int idCentro;
    @Basic
    @Column(name = "Nombre", nullable = true, length = 20)
    private String nombre;
    @Basic
    @Column(name = "Web", nullable = true, length = 20)
    private String web;
    @Basic
    @Column(name = "Contacto", nullable = true, length = 20)
    private String contacto;
    @Basic
    @Column(name = "Activo", nullable = true)
    private Byte activo;

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
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

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentrosEntity that = (CentrosEntity) o;
        return autoId == that.autoId && idCentro == that.idCentro && Objects.equals(nombre, that.nombre) && Objects.equals(web, that.web) && Objects.equals(contacto, that.contacto) && Objects.equals(activo, that.activo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoId, idCentro, nombre, web, contacto, activo);
    }
}
