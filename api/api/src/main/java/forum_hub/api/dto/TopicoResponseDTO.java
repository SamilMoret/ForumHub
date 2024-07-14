package forum_hub.api.dto;

import forum_hub.api.model.Autor;
import forum_hub.api.model.Curso;
import forum_hub.api.model.Estado;

import java.time.LocalDateTime;

public record TopicoResponseDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Estado estado,
        Autor autor,
        Curso curso) {
}


