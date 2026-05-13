package br.com.fiap.projectreviewer.dto;

public class CorrecaoRequest {

    private String nomeAluno;
    private String rm;
    private String descricaoTrabalho;

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public String getDescricaoTrabalho() {
        return descricaoTrabalho;
    }

    public void setDescricaoTrabalho(String descricaoTrabalho) {
        this.descricaoTrabalho = descricaoTrabalho;
    }
}