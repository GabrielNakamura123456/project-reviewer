package br.com.fiap.projectreviewer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "correcoes")
public class Correcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAluno;

    private String rm;

    @Column(columnDefinition = "TEXT")
    private String descricaoTrabalho;

    private Double nota;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    private LocalDateTime dataCorrecao;

    public Correcao() {
    }

    public Correcao(Long id, String nomeAluno, String rm, String descricaoTrabalho, Double nota, String feedback, LocalDateTime dataCorrecao) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.rm = rm;
        this.descricaoTrabalho = descricaoTrabalho;
        this.nota = nota;
        this.feedback = feedback;
        this.dataCorrecao = dataCorrecao;
    }

    public Long getId() {
        return id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getRm() {
        return rm;
    }

    public String getDescricaoTrabalho() {
        return descricaoTrabalho;
    }

    public Double getNota() {
        return nota;
    }

    public String getFeedback() {
        return feedback;
    }

    public LocalDateTime getDataCorrecao() {
        return dataCorrecao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public void setDescricaoTrabalho(String descricaoTrabalho) {
        this.descricaoTrabalho = descricaoTrabalho;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setDataCorrecao(LocalDateTime dataCorrecao) {
        this.dataCorrecao = dataCorrecao;
    }
}