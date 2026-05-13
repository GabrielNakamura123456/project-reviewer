package br.com.fiap.projectreviewer.controller;

import br.com.fiap.projectreviewer.dto.CorrecaoRequest;
import br.com.fiap.projectreviewer.model.Correcao;
import br.com.fiap.projectreviewer.service.CorrecaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correcoes")
public class CorrecaoController {

    private final CorrecaoService correcaoService;

    public CorrecaoController(CorrecaoService correcaoService) {
        this.correcaoService = correcaoService;
    }

    @PostMapping
    public Correcao corrigirTrabalho(@RequestBody CorrecaoRequest request) {
        return correcaoService.corrigirTrabalho(request);
    }

    @GetMapping
    public List<Correcao> listarCorrecoes() {
        return correcaoService.listarCorrecoes();
    }

    @GetMapping("/{id}")
    public Correcao buscarPorId(@PathVariable Long id) {
        return correcaoService.buscarPorId(id);
    }
}