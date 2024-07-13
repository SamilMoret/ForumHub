package forum_hub.api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public record Curso(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String nome,
        String descricao
) {}

