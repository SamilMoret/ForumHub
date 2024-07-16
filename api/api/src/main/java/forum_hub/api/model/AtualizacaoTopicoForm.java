package forum_hub.api.model;

import jakarta.validation.constraints.NotBlank;

public class AtualizacaoTopicoForm {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    // Getters e Setters

    public String getTitulo () {
        return titulo;
    }

    public void setTitulo (String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem () {
        return mensagem;
    }

    public void setMensagem (String mensagem) {
        this.mensagem = mensagem;
    }
}

