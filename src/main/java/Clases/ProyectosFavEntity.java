package Clases;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ProyectosFav", schema = "FactoriaProyectos", catalog = "")
public class ProyectosFavEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private int autoId;
    @Basic
    @Column(name = "Proyecto_ID", nullable = false)
    private int proyectoId;
    @Basic
    @Column(name = "ID_USUARIO", nullable = false)
    private int idUsuario;

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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectosFavEntity that = (ProyectosFavEntity) o;
        return autoId == that.autoId && proyectoId == that.proyectoId && idUsuario == that.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoId, proyectoId, idUsuario);
    }
}
