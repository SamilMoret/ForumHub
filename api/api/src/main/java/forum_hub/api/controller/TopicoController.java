package forum_hub.api.controller;

import forum_hub.api.model.Topico;
import forum_hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    @PostMapping
    @Transactional
    public Topico criar(@RequestBody Topico topico) {
        return topicoRepository.save(topico);
    }
}
