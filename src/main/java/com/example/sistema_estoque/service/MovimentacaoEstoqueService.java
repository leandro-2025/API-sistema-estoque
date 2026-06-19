package com.example.sistema_estoque.service;

import com.example.sistema_estoque.model.MovimentacaoEstoque;
import com.example.sistema_estoque.model.Produto;
import com.example.sistema_estoque.repository.MovimentacaoEstoqueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository repository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository repository) {
        this.repository = repository;
    }

    public MovimentacaoEstoque criarEntrada(String tipo, Integer quantidade, Produto produto) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setTipo("ENTRADA");
        movimentacao.setQuantidade(quantidade);
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setProduto(produto);

        return repository.save(movimentacao);
    }

    public MovimentacaoEstoque criarSaida(String tipo, Integer quantidade, Produto produto) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setTipo("SAIDA");
        movimentacao.setQuantidade(quantidade);
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setProduto(produto);

        return repository.save(movimentacao);
    }

    public List<MovimentacaoEstoque> listar() {
        return repository.findAll();
    }

    public MovimentacaoEstoque buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Nenhuma movimentação com este ID!"));
    }
}
