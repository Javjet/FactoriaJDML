package Clases;

import jakarta.persistence.*;

@Entity
public class FamiliaProfesionalImplicada {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "Proyecto_ID", nullable = false)
    private Integer proyectoId;
    @Basic
    @Column(name = "FAMILIA_PROFESIONAL_ID", nullable = false)
    private Integer familiaProfesionalId;

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

    public Integer getFamiliaProfesionalId() {
        return familiaProfesionalId;
    }

    public void setFamiliaProfesionalId(Integer familiaProfesionalId) {
        this.familiaProfesionalId = familiaProfesionalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamiliaProfesionalImplicada that = (FamiliaProfesionalImplicada) o;

        if (autoId != null ? !autoId.equals(that.autoId) : that.autoId != null) return false;
        if (proyectoId != null ? !proyectoId.equals(that.proyectoId) : that.proyectoId != null) return false;
        if (familiaProfesionalId != null ? !familiaProfesionalId.equals(that.familiaProfesionalId) : that.familiaProfesionalId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (proyectoId != null ? proyectoId.hashCode() : 0);
        result = 31 * result + (familiaProfesionalId != null ? familiaProfesionalId.hashCode() : 0);
        return result;
    }
}
