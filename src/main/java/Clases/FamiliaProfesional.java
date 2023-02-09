package Clases;

import jakarta.persistence.*;

@Entity
public class FamiliaProfesional {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "FAMILIA_PROFESIONAL_ID", nullable = false)
    private Integer familiaProfesionalId;
    @Basic
    @Column(name = "Nombre_Familia", nullable = false, length = 60)
    private String nombreFamilia;
    @ManyToOne
    @JoinColumn(name = "FAMILIA_PROFESIONAL_ID", referencedColumnName = "FAMILIA_PROFESIONAL_ID", nullable = false)
    private FamiliaProfesionalImplicada familiaProfesionalImplicadaByFamiliaProfesionalId;
    @ManyToOne
    @JoinColumn(name = "FAMILIA_PROFESIONAL_ID", referencedColumnName = "Familia_Profesional", nullable = false)
    private Usuario usuarioByFamiliaProfesionalId;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getFamiliaProfesionalId() {
        return familiaProfesionalId;
    }

    public void setFamiliaProfesionalId(Integer familiaProfesionalId) {
        this.familiaProfesionalId = familiaProfesionalId;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamiliaProfesional that = (FamiliaProfesional) o;

        if (autoId != null ? !autoId.equals(that.autoId) : that.autoId != null) return false;
        if (familiaProfesionalId != null ? !familiaProfesionalId.equals(that.familiaProfesionalId) : that.familiaProfesionalId != null)
            return false;
        if (nombreFamilia != null ? !nombreFamilia.equals(that.nombreFamilia) : that.nombreFamilia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (familiaProfesionalId != null ? familiaProfesionalId.hashCode() : 0);
        result = 31 * result + (nombreFamilia != null ? nombreFamilia.hashCode() : 0);
        return result;
    }

    public FamiliaProfesionalImplicada getFamiliaProfesionalImplicadaByFamiliaProfesionalId() {
        return familiaProfesionalImplicadaByFamiliaProfesionalId;
    }

    public void setFamiliaProfesionalImplicadaByFamiliaProfesionalId(FamiliaProfesionalImplicada familiaProfesionalImplicadaByFamiliaProfesionalId) {
        this.familiaProfesionalImplicadaByFamiliaProfesionalId = familiaProfesionalImplicadaByFamiliaProfesionalId;
    }

    public Usuario getUsuarioByFamiliaProfesionalId() {
        return usuarioByFamiliaProfesionalId;
    }

    public void setUsuarioByFamiliaProfesionalId(Usuario usuarioByFamiliaProfesionalId) {
        this.usuarioByFamiliaProfesionalId = usuarioByFamiliaProfesionalId;
    }
}
