package br.com.fiap.projectreviewer.service;

import br.com.fiap.projectreviewer.dto.CorrecaoRequest;
import br.com.fiap.projectreviewer.model.Correcao;
import br.com.fiap.projectreviewer.repository.CorrecaoRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CorrecaoService {

    private final CorrecaoRepository correcaoRepository;
    private final ChatClient chatClient;

    public CorrecaoService(CorrecaoRepository correcaoRepository, ChatClient chatClient) {
        this.correcaoRepository = correcaoRepository;
        this.chatClient = chatClient;
    }

    public Correcao corrigirTrabalho(CorrecaoRequest request) {

        String prompt = """
                Voce e um agente de IA chamado Project Reviewer.
                Sua funcao e corrigir trabalhos de alunos de Java e Spring Boot.

                Avalie o trabalho do aluno com uma nota de 0 a 100.

                Criterios:
                - Atendimento aos requisitos
                - Qualidade do codigo
                - Organizacao do projeto
                - Uso correto de Java e Spring Boot
                - Uso de banco de dados
                - Clareza da documentacao

                Dados do aluno:
                Nome: %s
                RM: %s

                Trabalho enviado:
                %s

                IMPORTANTE:
                Responda apenas uma vez.
                Nao crie varias notas.
                Nao repita o feedback.
                Nao use markdown.
                Nao use lista.

                Responda exatamente neste formato:

                NOTA: numero de 0 a 100
                FEEDBACK: texto curto com no maximo 3 frases
                """.formatted(
                request.getNomeAluno(),
                request.getRm(),
                request.getDescricaoTrabalho()
        );

        String respostaIa = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        Double nota = extrairNota(respostaIa);

        Correcao correcao = new Correcao();
        correcao.setNomeAluno(request.getNomeAluno());
        correcao.setRm(request.getRm());
        correcao.setDescricaoTrabalho(request.getDescricaoTrabalho());
        correcao.setNota(nota);
        correcao.setFeedback(respostaIa);
        correcao.setDataCorrecao(LocalDateTime.now());

        return correcaoRepository.save(correcao);
    }

    public List<Correcao> listarCorrecoes() {
        return correcaoRepository.findAll();
    }

    public Correcao buscarPorId(Long id) {
        return correcaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Correcao nao encontrada"));
    }

    private Double extrairNota(String respostaIa) {
        try {
            String[] linhas = respostaIa.split("\\n");

            for (String linha : linhas) {
                if (linha.toUpperCase().contains("NOTA")) {
                    String numero = linha.replaceAll("[^0-9.]", "");

                    if (!numero.isBlank()) {
                        return Double.parseDouble(numero);
                    }
                }
            }

            return 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}