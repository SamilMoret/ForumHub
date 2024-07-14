package forum_hub.api.repository;

import forum_hub.api.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome AND FUNCTION('YEAR', t.dataCriacao) = :ano")
    List<Topico> findByCursoNomeAndAno(String cursoNome, int ano);

    Page<Topico> findAll(Pageable pageable);
}
