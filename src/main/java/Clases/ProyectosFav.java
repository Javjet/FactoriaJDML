package Clases;

import jakarta.persistence.*;

@Entity
public class ProyectosFav {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "Proyecto_ID", nullable = false)
    private Integer proyectoId;
    @Basic
    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProyectosFav that = (ProyectosFav) o;

        if (autoId != null ? !autoId.equals(that.autoId) : that.autoId != null) return false;
        if (proyectoId != null ? !proyectoId.equals(that.proyectoId) : that.proyectoId != null) return false;
        if (idUsuario != null ? !idUsuario.equals(that.idUsuario) : that.idUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (proyectoId != null ? proyectoId.hashCode() : 0);
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        return result;
    }
}
