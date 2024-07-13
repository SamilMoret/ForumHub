package forum_hub.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public record Estado(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String descricao
) {}

