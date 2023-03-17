package Clases;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CentrosDeProyecto", schema = "FactoriaProyectos", catalog = "")
public class CentrosDeProyectoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private int autoId;
    @Basic
    @Column(name = "Proyecto_ID", nullable = false)
    private int proyectoId;
    @Basic
    @Column(name = "ID_Centro", nullable = false)
    private int idCentro;

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentrosDeProyectoEntity that = (CentrosDeProyectoEntity) o;
        return autoId == that.autoId && proyectoId == that.proyectoId && idCentro == that.idCentro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoId, proyectoId, idCentro);
    }
}
