package com.example.sistema_estoque.controller;

import com.example.sistema_estoque.model.MovimentacaoEstoque;
import com.example.sistema_estoque.service.MovimentacaoEstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoestoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    public MovimentacaoestoqueController(MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @GetMapping
    public List<MovimentacaoEstoque> listar() {
        return movimentacaoEstoqueService.listar();
    }

    @GetMapping("/{id}")
    public MovimentacaoEstoque buscarPorId(@PathVariable Long id) {
        return movimentacaoEstoqueService.buscar(id);
    }
}
