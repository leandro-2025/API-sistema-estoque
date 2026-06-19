package com.example.sistema_estoque.controller;

import com.example.sistema_estoque.dto.MovimentacaoEstoqueDTO;
import com.example.sistema_estoque.model.Produto;
import com.example.sistema_estoque.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.criar(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return  produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscar(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProdutoPorId(@PathVariable Long id, @RequestBody Produto produtoNovo) {
        return produtoService.atualizar(id, produtoNovo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProdutoPorId(@PathVariable Long id){
        produtoService.deletar(id);

        return ResponseEntity.ok("Produto deletado com sucesso!");
    }

    @PostMapping("/{id}/entrada")
    public Produto entradaDeEstoque(@PathVariable Long id, @RequestBody MovimentacaoEstoqueDTO dto) {
        return produtoService.entradaEstoque(id,dto.getQuantidade());
    }

    @PostMapping("/{id}/saida")
    public Produto saidaDeEstoque(@PathVariable Long id, @RequestBody MovimentacaoEstoqueDTO dto) {
        return produtoService.saidaEstoque(id, dto.getQuantidade());
    }
 }
