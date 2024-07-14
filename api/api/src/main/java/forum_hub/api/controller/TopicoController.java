package forum_hub.api.controller;

import forum_hub.api.dto.TopicoResponseDTO;
import forum_hub.api.model.Topico;
import forum_hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public List<TopicoResponseDTO> listar() {
        return topicoRepository.findAll().stream()
                .map(TopicoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Transactional
    public Topico criar(@RequestBody Topico topico) {
        return topicoRepository.save(topico);
    }

    @GetMapping("/{id}")
    public TopicoResponseDTO detalhar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        return topicoOptional.map(TopicoResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
    }

    @GetMapping("/ordenados")
    public List<TopicoResponseDTO> listarOrdenados() {
        return topicoRepository.findAll(Sort.by(Sort.Direction.ASC, "dataCriacao")).stream()
                .map(TopicoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/busca")
    public List<TopicoResponseDTO> buscarPorCursoENome(@RequestParam String cursoNome, @RequestParam int ano) {
        return topicoRepository.findByCursoNomeAndAno(cursoNome, ano).stream()
                .map(TopicoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/paginado")
    public List<TopicoResponseDTO> listarPaginado(@PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable) {
        return topicoRepository.findAll(pageable).stream()
                .map(TopicoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
