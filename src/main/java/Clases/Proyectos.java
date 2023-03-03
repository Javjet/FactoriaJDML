package Clases;

import jakarta.persistence.*;

@Entity
public class Proyectos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private Integer autoId;
    @Basic
    @Column(name = "PROYECTO_ID", nullable = false)
    private Integer proyectoId;
    @Basic
    @Column(name = "Titulo", nullable = true, length = 20)
    private String titulo;
    @Basic
    @Column(name = "Descripcion", nullable = true, length = 120)
    private String descripcion;
    @Basic
    @Column(name = "Coordinador", nullable = true, length = 20)
    private String coordinador;
    @Basic
    @Column(name = "Estado", nullable = true, length = 20)
    private String estado;
    @Basic
    @Column(name = "Visibilidad", nullable = true, length = 20)
    private String visibilidad;
    @Basic
    @Column(name = "Visitas", nullable = true)
    private Integer visitas;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_ID", nullable = false)
    private CentrosDeProyecto centrosDeProyectoByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_id", nullable = false)
    private Comentarios comentariosByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_ID", nullable = false)
    private FamiliaProfesionalImplicada familiaProfesionalImplicadaByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_id", nullable = false)
    private Participantes participantesByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_ID", nullable = false)
    private ProyectosFav proyectosFavByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_ID", nullable = false)
    private Tags tagsByProyectoId;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proyectos proyectos = (Proyectos) o;

        if (autoId != null ? !autoId.equals(proyectos.autoId) : proyectos.autoId != null) return false;
        if (proyectoId != null ? !proyectoId.equals(proyectos.proyectoId) : proyectos.proyectoId != null) return false;
        if (titulo != null ? !titulo.equals(proyectos.titulo) : proyectos.titulo != null) return false;
        if (descripcion != null ? !descripcion.equals(proyectos.descripcion) : proyectos.descripcion != null)
            return false;
        if (coordinador != null ? !coordinador.equals(proyectos.coordinador) : proyectos.coordinador != null)
            return false;
        if (estado != null ? !estado.equals(proyectos.estado) : proyectos.estado != null) return false;
        if (visibilidad != null ? !visibilidad.equals(proyectos.visibilidad) : proyectos.visibilidad != null)
            return false;
        if (visitas != null ? !visitas.equals(proyectos.visitas) : proyectos.visitas != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId != null ? autoId.hashCode() : 0;
        result = 31 * result + (proyectoId != null ? proyectoId.hashCode() : 0);
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (coordinador != null ? coordinador.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (visibilidad != null ? visibilidad.hashCode() : 0);
        result = 31 * result + (visitas != null ? visitas.hashCode() : 0);
        return result;
    }

    public CentrosDeProyecto getCentrosDeProyectoByProyectoId() {
        return centrosDeProyectoByProyectoId;
    }

    public void setCentrosDeProyectoByProyectoId(CentrosDeProyecto centrosDeProyectoByProyectoId) {
        this.centrosDeProyectoByProyectoId = centrosDeProyectoByProyectoId;
    }

    public Comentarios getComentariosByProyectoId() {
        return comentariosByProyectoId;
    }

    public void setComentariosByProyectoId(Comentarios comentariosByProyectoId) {
        this.comentariosByProyectoId = comentariosByProyectoId;
    }

    public FamiliaProfesionalImplicada getFamiliaProfesionalImplicadaByProyectoId() {
        return familiaProfesionalImplicadaByProyectoId;
    }

    public void setFamiliaProfesionalImplicadaByProyectoId(FamiliaProfesionalImplicada familiaProfesionalImplicadaByProyectoId) {
        this.familiaProfesionalImplicadaByProyectoId = familiaProfesionalImplicadaByProyectoId;
    }

    public Participantes getParticipantesByProyectoId() {
        return participantesByProyectoId;
    }

    public void setParticipantesByProyectoId(Participantes participantesByProyectoId) {
        this.participantesByProyectoId = participantesByProyectoId;
    }

    public ProyectosFav getProyectosFavByProyectoId() {
        return proyectosFavByProyectoId;
    }

    public void setProyectosFavByProyectoId(ProyectosFav proyectosFavByProyectoId) {
        this.proyectosFavByProyectoId = proyectosFavByProyectoId;
    }

    public Tags getTagsByProyectoId() {
        return tagsByProyectoId;
    }

    public void setTagsByProyectoId(Tags tagsByProyectoId) {
        this.tagsByProyectoId = tagsByProyectoId;
    }
}
