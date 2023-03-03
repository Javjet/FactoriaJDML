package Clases;

import jakarta.persistence.*;

@Entity
public class CentrosDeProyecto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "Proyecto_ID", nullable = false)
    private Integer proyectoId;
    @Basic
    @Column(name = "ID_Centro", nullable = false)
    private Integer idCentro;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Integer getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public void setIdCentro(Integer idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CentrosDeProyecto that = (CentrosDeProyecto) o;

        if (autoId != null ? !autoId.equals(that.autoId) : that.autoId != null) return false;
        if (proyectoId != null ? !proyectoId.equals(that.proyectoId) : that.proyectoId != null) return false;
        if (idCentro != null ? !idCentro.equals(that.idCentro) : that.idCentro != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (proyectoId != null ? proyectoId.hashCode() : 0);
        result = 31 * result + (idCentro != null ? idCentro.hashCode() : 0);
        return result;
    }
}
