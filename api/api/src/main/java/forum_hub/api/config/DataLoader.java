package forum_hub.api.config;

import forum_hub.api.model.Autor;
import forum_hub.api.model.Curso;
import forum_hub.api.model.Estado;
import forum_hub.api.model.Topico;
import forum_hub.api.repository.AutorRepository;
import forum_hub.api.repository.CursoRepository;
import forum_hub.api.repository.EstadoRepository;
import forum_hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void run(String... args) throws Exception {
        Autor autor = new Autor("nome", "email@exemplo.com");
        autorRepository.save(autor);

        Curso curso = new Curso("Curso", "Descrição do Curso Exemplo");
        cursoRepository.save(curso);

        Estado estado = new Estado("Aberto");
        estadoRepository.save(estado);

        LocalDateTime dataAtual = LocalDateTime.now();

        Topico topico = new Topico("Título do Tópico", "Mensagem do Tópico", estado, autor, curso);
        topico.setDataCriacao(dataAtual); // Define a data de criação explicitamente
        topicoRepository.save(topico);
    }
}
