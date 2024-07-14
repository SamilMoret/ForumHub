package forum_hub.api.service;

import forum_hub.api.model.Topico;
import forum_hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico salvarTopico(Topico topico) {
        verificarDuplicidade(topico.getTitulo(), topico.getMensagem());
        topico.setDataCriacao( LocalDateTime.now());
        return topicoRepository.save(topico);
    }

    private void verificarDuplicidade(String titulo, String mensagem) {
        Optional<Topico> topicoExistente = topicoRepository.findByTituloAndMensagem(titulo, mensagem);
        if (topicoExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um tópico com o mesmo título e mensagem");
        }
    }
}
