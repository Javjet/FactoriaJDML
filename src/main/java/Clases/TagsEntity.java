package Clases;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Tags", schema = "FactoriaProyectos", catalog = "")
public class TagsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private int autoId;
    @Basic
    @Column(name = "Proyecto_ID", nullable = false)
    private int proyectoId;
    @Basic
    @Column(name = "Tag", nullable = true, length = 20)
    private String tag;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagsEntity that = (TagsEntity) o;
        return autoId == that.autoId && proyectoId == that.proyectoId && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoId, proyectoId, tag);
    }
}
