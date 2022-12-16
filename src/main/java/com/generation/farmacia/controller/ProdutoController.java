package com.generation.farmacia.controller;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.resitory.CategoriaRepository;
import com.generation.farmacia.resitory.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @GetMapping
    public ResponseEntity<List<Produto>> getAll (){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(titulo));
    }


    @PostMapping
    public ResponseEntity<Produto> postPostagem(@Valid @RequestBody Produto produtos){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtos));
    }

    @PutMapping
    public ResponseEntity<Produto> putPostagem (@Valid @RequestBody Produto produtos){
        return produtoRepository.findById((produtos.getId()))
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produtos)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        Optional<Produto> produtos = produtoRepository.findById(id);

        if(produtos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtoRepository.deleteById(id);
    }

}
