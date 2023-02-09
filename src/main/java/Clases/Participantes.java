package Clases;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Participantes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "Proyecto_id", nullable = false)
    private Integer proyectoId;
    @Basic
    @Column(name = "id_Usuario", nullable = false)
    private Integer idUsuario;
    @Basic
    @Column(name = "Cordinador", nullable = true, length = 20)
    private String cordinador;
    @Basic
    @Column(name = "Fec_Ini", nullable = true)
    private Date fecIni;
    @Basic
    @Column(name = "Fec_Fin", nullable = true)
    private Date fecFin;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCordinador() {
        return cordinador;
    }

    public void setCordinador(String cordinador) {
        this.cordinador = cordinador;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participantes that = (Participantes) o;

        if (autoId != null ? !autoId.equals(that.autoId) : that.autoId != null) return false;
        if (proyectoId != null ? !proyectoId.equals(that.proyectoId) : that.proyectoId != null) return false;
        if (idUsuario != null ? !idUsuario.equals(that.idUsuario) : that.idUsuario != null) return false;
        if (cordinador != null ? !cordinador.equals(that.cordinador) : that.cordinador != null) return false;
        if (fecIni != null ? !fecIni.equals(that.fecIni) : that.fecIni != null) return false;
        if (fecFin != null ? !fecFin.equals(that.fecFin) : that.fecFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (proyectoId != null ? proyectoId.hashCode() : 0);
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        result = 31 * result + (cordinador != null ? cordinador.hashCode() : 0);
        result = 31 * result + (fecIni != null ? fecIni.hashCode() : 0);
        result = 31 * result + (fecFin != null ? fecFin.hashCode() : 0);
        return result;
    }
}
