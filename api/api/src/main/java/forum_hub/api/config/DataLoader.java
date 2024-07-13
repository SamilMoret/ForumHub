package forum_hub.api.config;

import forum_hub.api.model.*;
import forum_hub.api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(AutorRepository autorRepository, CursoRepository cursoRepository, EstadoRepository estadoRepository, TopicoRepository topicoRepository) {
        return args -> {
            Autor autor = new Autor(null, "Autor Exemplo", "autor@example.com");
            autorRepository.save(autor);

            Curso curso = new Curso(null, "Curso Exemplo", "Descrição do Curso Exemplo");
            cursoRepository.save(curso);

            Estado estado = new Estado(null, "Aberto");
            estadoRepository.save(estado);

            Topico topico = new Topico("Título do Tópico", "Mensagem do Tópico", estado, autor, curso);
            topicoRepository.save(topico);
        };
    }
}
