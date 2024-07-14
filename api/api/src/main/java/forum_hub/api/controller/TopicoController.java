package forum_hub.api.controller;

import forum_hub.api.dto.TopicoRequestDTO;
import forum_hub.api.dto.TopicoResponseDTO;
import forum_hub.api.model.Autor;
import forum_hub.api.model.Curso;
import forum_hub.api.model.Estado;
import forum_hub.api.model.Topico;
import forum_hub.api.repository.TopicoRepository;
import forum_hub.api.repository.AutorRepository;
import forum_hub.api.repository.CursoRepository;
import forum_hub.api.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TopicoResponseDTO> listar() {
        return topicoRepository.findAll().stream()
                .map(topico -> new TopicoResponseDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getDataCriacao(),
                        topico.getEstado(),
                        topico.getAutor(),
                        topico.getCurso()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/ordenados")
    public List<TopicoResponseDTO> listarOrdenados() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("dataCriacao").ascending());
        return topicoRepository.findAll(pageable).stream()
                .map(topico -> new TopicoResponseDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getDataCriacao(),
                        topico.getEstado(),
                        topico.getAutor(),
                        topico.getCurso()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/busca")
    public List<TopicoResponseDTO> buscarPorCursoEAno(@RequestParam String cursoNome, @RequestParam int ano) {
        return topicoRepository.findByCursoNomeAndAno(cursoNome, ano)
                .stream()
                .map(topico -> new TopicoResponseDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getDataCriacao(),
                        topico.getEstado(),
                        topico.getAutor(),
                        topico.getCurso()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/paginado")
    public Page<TopicoResponseDTO> listarPaginado(@PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {
        return topicoRepository.findAll(pageable)
                .map(topico -> new TopicoResponseDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getDataCriacao(),
                        topico.getEstado(),
                        topico.getAutor(),
                        topico.getCurso()
                ));
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
