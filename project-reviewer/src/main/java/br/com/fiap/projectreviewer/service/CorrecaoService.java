package br.com.fiap.projectreviewer.service;

import br.com.fiap.projectreviewer.dto.CorrecaoRequest;
import br.com.fiap.projectreviewer.model.Correcao;
import br.com.fiap.projectreviewer.repository.CorrecaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CorrecaoService {

    private final CorrecaoRepository correcaoRepository;

    public CorrecaoService(CorrecaoRepository correcaoRepository) {
        this.correcaoRepository = correcaoRepository;
    }

    public Correcao corrigirTrabalho(CorrecaoRequest request) {

        Double nota = calcularNotaSimulada(request.getDescricaoTrabalho());
        String feedback = gerarFeedbackSimulado(request, nota);

        Correcao correcao = new Correcao();
        correcao.setNomeAluno(request.getNomeAluno());
        correcao.setRm(request.getRm());
        correcao.setDescricaoTrabalho(request.getDescricaoTrabalho());
        correcao.setNota(nota);
        correcao.setFeedback(feedback);
        correcao.setDataCorrecao(LocalDateTime.now());

        return correcaoRepository.save(correcao);
    }

    public List<Correcao> listarCorrecoes() {
        return correcaoRepository.findAll();
    }

    public Correcao buscarPorId(Long id) {
        return correcaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Correção não encontrada"));
    }

    private Double calcularNotaSimulada(String descricaoTrabalho) {
        double nota = 50.0;

        if (descricaoTrabalho == null || descricaoTrabalho.isBlank()) {
            return 0.0;
        }

        String texto = descricaoTrabalho.toLowerCase();

        if (texto.contains("spring")) {
            nota += 10;
        }

        if (texto.contains("controller")) {
            nota += 10;
        }

        if (texto.contains("service")) {
            nota += 10;
        }

        if (texto.contains("repository")) {
            nota += 10;
        }

        if (texto.contains("banco") || texto.contains("h2") || texto.contains("database")) {
            nota += 10;
        }

        if (texto.contains("readme") || texto.contains("documentação") || texto.contains("documentacao")) {
            nota += 5;
        }

        if (nota > 100) {
            nota = 100;
        }

        return nota;
    }

    private String gerarFeedbackSimulado(CorrecaoRequest request, Double nota) {
        String situacao;

        if (nota >= 90) {
            situacao = "Excelente entrega. O trabalho atende muito bem aos requisitos.";
        } else if (nota >= 70) {
            situacao = "Boa entrega. O trabalho atende boa parte dos requisitos, mas ainda pode melhorar.";
        } else if (nota >= 50) {
            situacao = "Entrega mediana. O projeto possui alguns pontos importantes, mas faltam melhorias.";
        } else {
            situacao = "Entrega insuficiente. O trabalho precisa de mais requisitos implementados.";
        }

        return """
                NOTA: %.1f
                FEEDBACK: %s

                Aluno: %s
                RM: %s

                Pontos avaliados:
                - Atendimento aos requisitos
                - Organização do projeto
                - Uso de Java e Spring Boot
                - Presença de camadas como controller, service e repository
                - Integração com banco de dados
                - Documentação do projeto

                Observação: esta correção está em modo demo, simulando o comportamento de um agente de IA com critérios automáticos.
                """.formatted(
                nota,
                situacao,
                request.getNomeAluno(),
                request.getRm()
        );
    }
}