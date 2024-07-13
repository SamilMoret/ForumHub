package forum_hub.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public record Topico(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        @ManyToOne @JoinColumn(name = "estado_id") Estado estado,
        @ManyToOne @JoinColumn(name = "autor_id") Autor autor,
        @ManyToOne @JoinColumn(name = "curso_id") Curso curso
) {
    public Topico(String titulo, String mensagem, Estado estado, Autor autor, Curso curso) {
        this(null, titulo, mensagem, LocalDateTime.now(), estado, autor, curso);
    }
}

