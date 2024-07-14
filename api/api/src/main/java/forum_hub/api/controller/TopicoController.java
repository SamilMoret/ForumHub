package forum_hub.api.controller;

import forum_hub.api.dto.TopicoRequestDTO;
import forum_hub.api.model.*;
import forum_hub.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    @PostMapping
    @Transactional
    public Topico criar(@RequestBody TopicoRequestDTO topicoRequestDTO) {
        Autor autor = autorRepository.findById(topicoRequestDTO.autor_id())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Curso curso = cursoRepository.findById(topicoRequestDTO.curso_id())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        Estado estado = estadoRepository.findById(topicoRequestDTO.estado_id())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        Topico topico = new Topico(topicoRequestDTO.titulo(),
                topicoRequestDTO.mensagem(),
                estado,
                autor,
                curso);

        if (topicoRequestDTO.data_criacao() != null) {
            topico.setDataCriacao(topicoRequestDTO.data_criacao());
        }

        return topicoRepository.save(topico);
    }
}
