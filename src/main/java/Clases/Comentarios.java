package Clases;

import jakarta.persistence.*;

@Entity
public class Comentarios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "Escritor", nullable = false)
    private Integer escritor;
    @Basic
    @Column(name = "Proyecto_id", nullable = false)
    private Integer proyectoId;
    @Basic
    @Column(name = "Contenido", nullable = true, length = 20)
    private String contenido;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getEscritor() {
        return escritor;
    }

    public void setEscritor(Integer escritor) {
        this.escritor = escritor;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comentarios that = (Comentarios) o;

        if (autoId != null ? !autoId.equals(that.autoId) : that.autoId != null) return false;
        if (escritor != null ? !escritor.equals(that.escritor) : that.escritor != null) return false;
        if (proyectoId != null ? !proyectoId.equals(that.proyectoId) : that.proyectoId != null) return false;
        if (contenido != null ? !contenido.equals(that.contenido) : that.contenido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (escritor != null ? escritor.hashCode() : 0);
        result = 31 * result + (proyectoId != null ? proyectoId.hashCode() : 0);
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        return result;
    }
}
