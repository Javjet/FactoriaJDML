package Clases;

import jakarta.persistence.*;

@Entity
public class Tags {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "Proyecto_ID", nullable = false)
    private Integer proyectoId;
    @Basic
    @Column(name = "Tag", nullable = true, length = 20)
    private String tag;

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

        Tags tags = (Tags) o;

        if (autoId != null ? !autoId.equals(tags.autoId) : tags.autoId != null) return false;
        if (proyectoId != null ? !proyectoId.equals(tags.proyectoId) : tags.proyectoId != null) return false;
        if (tag != null ? !tag.equals(tags.tag) : tags.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (proyectoId != null ? proyectoId.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
