package forum_hub.api.controller;

import forum_hub.api.dto.AtualizacaoTopicoForm;
import forum_hub.api.dto.TopicoResponseDTO;
import forum_hub.api.model.Topico;
import forum_hub.api.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/{id}")
    @Transactional
    public TopicoResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            throw new ResponseStatusException ( HttpStatus.NOT_FOUND, "Tópico não encontrado");
        }

        Topico topico = optionalTopico.get();
        topico.setTitulo(form.getTitulo());
        topico.setMensagem(form.getMensagem());
        // Atualizar outros campos conforme necessário (estado, autor, curso, etc.)

        return new TopicoResponseDTO(topicoRepository.save(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tópico não encontrado");
        }
    }
}
