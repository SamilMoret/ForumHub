package forum_hub.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Topico {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titulo;
        private String mensagem;

        @ManyToOne
        private Estado estado;

        @ManyToOne
        private Autor autor;

        @ManyToOne
        private Curso curso;

        @Column(name = "data_criacao", nullable = false)
        private LocalDateTime dataCriacao;

        // Construtores, getters e setters
        public Topico() {
                this.dataCriacao = LocalDateTime.now(); // Preenchendo a data de criação automaticamente
        }

        public Topico(String titulo, String mensagem, Estado estado, Autor autor, Curso curso) {
                this.titulo = titulo;
                this.mensagem = mensagem;
                this.estado = estado;
                this.autor = autor;
                this.curso = curso;
                this.dataCriacao = LocalDateTime.now(); // Preenchendo a data de criação automaticamente
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public String getMensagem() {
                return mensagem;
        }

        public void setMensagem(String mensagem) {
                this.mensagem = mensagem;
        }

        public Estado getEstado() {
                return estado;
        }

        public void setEstado(Estado estado) {
                this.estado = estado;
        }

        public Autor getAutor() {
                return autor;
        }

        public void setAutor(Autor autor) {
                this.autor = autor;
        }

        public Curso getCurso() {
                return curso;
        }

        public void setCurso(Curso curso) {
                this.curso = curso;
        }

        public LocalDateTime getDataCriacao() {
                return dataCriacao;
        }

        public void setDataCriacao(LocalDateTime dataCriacao) {
                this.dataCriacao = dataCriacao;
        }
}
