package forum_hub.api.dto;

import java.time.LocalDateTime;

public record TopicoRequestDTO(
        Long autor_id,
        Long curso_id,
        LocalDateTime data_criacao,
        Long estado_id,
        String mensagem,
        String titulo
) {}
