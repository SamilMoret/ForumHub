package forum_hub.api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoRequestDTO(
        @NotNull String titulo,
        @NotNull String mensagem,
        @NotNull Long autor_id,
        @NotNull Long curso_id,
        @NotNull Long estado_id,
        LocalDateTime data_criacao) {
}

