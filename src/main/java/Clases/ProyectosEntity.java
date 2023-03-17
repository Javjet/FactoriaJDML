package Clases;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Proyectos", schema = "FactoriaProyectos", catalog = "")
public class ProyectosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AUTO_ID", nullable = false)
    private int autoId;
    @Basic
    @Column(name = "PROYECTO_ID", nullable = false)
    private int proyectoId;
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
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_ID", nullable = false, updatable = false, insertable = false)
    private CentrosDeProyectoEntity centrosDeProyectoByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_id", nullable = false, updatable = false, insertable = false)
    private ComentariosEntity comentariosByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_ID", nullable = false, updatable = false, insertable = false)
    private FamiliaProfesionalImplicadaEntity familiaProfesionalImplicadaByProyectoId;
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "Proyecto_id", nullable = false, updatable = false, insertable = false)
    private ParticipantesEntity participantesByProyectoId;

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
        ProyectosEntity that = (ProyectosEntity) o;
        return autoId == that.autoId && proyectoId == that.proyectoId && Objects.equals(titulo, that.titulo) && Objects.equals(descripcion, that.descripcion) && Objects.equals(coordinador, that.coordinador) && Objects.equals(estado, that.estado) && Objects.equals(visibilidad, that.visibilidad) && Objects.equals(visitas, that.visitas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoId, proyectoId, titulo, descripcion, coordinador, estado, visibilidad, visitas);
    }

    public CentrosDeProyectoEntity getCentrosDeProyectoByProyectoId() {
        return centrosDeProyectoByProyectoId;
    }

    public void setCentrosDeProyectoByProyectoId(CentrosDeProyectoEntity centrosDeProyectoByProyectoId) {
        this.centrosDeProyectoByProyectoId = centrosDeProyectoByProyectoId;
    }

    public ComentariosEntity getComentariosByProyectoId() {
        return comentariosByProyectoId;
    }

    public void setComentariosByProyectoId(ComentariosEntity comentariosByProyectoId) {
        this.comentariosByProyectoId = comentariosByProyectoId;
    }

    public FamiliaProfesionalImplicadaEntity getFamiliaProfesionalImplicadaByProyectoId() {
        return familiaProfesionalImplicadaByProyectoId;
    }

    public void setFamiliaProfesionalImplicadaByProyectoId(FamiliaProfesionalImplicadaEntity familiaProfesionalImplicadaByProyectoId) {
        this.familiaProfesionalImplicadaByProyectoId = familiaProfesionalImplicadaByProyectoId;
    }

    public ParticipantesEntity getParticipantesByProyectoId() {
        return participantesByProyectoId;
    }

    public void setParticipantesByProyectoId(ParticipantesEntity participantesByProyectoId) {
        this.participantesByProyectoId = participantesByProyectoId;
    }
}
