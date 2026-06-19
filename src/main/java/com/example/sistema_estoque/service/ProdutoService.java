package com.example.sistema_estoque.service;

import com.example.sistema_estoque.model.Produto;
import com.example.sistema_estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    public ProdutoService(ProdutoRepository produtoRepository, MovimentacaoEstoqueService movimentacaoEstoqueService, MovimentacaoEstoqueService estoqueService, MovimentacaoEstoqueService movimentacaoEstoqueService1) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoEstoqueService = movimentacaoEstoqueService1;
    }

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);

    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscar(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não existe produtos com este ID!"));
    }

    public Produto atualizar(Long id, Produto produtoNovo) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não existe produtos para serem atualizados com este ID!"));

        produtoExistente.setNome(produtoNovo.getNome());
        produtoExistente.setPreco(produtoNovo.getPreco());
        produtoExistente.setQuantidade(produtoNovo.getQuantidade());

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        produtoRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Não existe produtos para serem deletados com este ID!"));

        produtoRepository.deleteById(id);
    }

    public Produto entradaEstoque(Long id, Integer quantidade) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        if (quantidade <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero!");
        }

        produto.setQuantidade(produto.getQuantidade() + quantidade);

        produto = produtoRepository.save(produto);

        movimentacaoEstoqueService.criarEntrada(
                "ENTRADA",
                quantidade,
                produto
        );

        return produto;
    }

    public Produto saidaEstoque(Long id, Integer quantidade) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado!"));

        if (quantidade > produto.getQuantidade()) {
            throw new RuntimeException("Quantidade insuficiente em estoque!");
        }
        if (quantidade <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero!");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);

        produto = produtoRepository.save(produto);

        movimentacaoEstoqueService.criarSaida(
                "SAIDA",
                quantidade,
                produto
        );

        return produto;
    }
}
