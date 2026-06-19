package com.example.catalogo.service;

import com.example.catalogo.dto.CategoriaRequest;
import com.example.catalogo.exception.RecursoNaoEncontradoException;
import com.example.catalogo.model.Categoria;
import com.example.catalogo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));
    }

    public Categoria criar(CategoriaRequest request) {
        return repository.save(new Categoria(request.getNome()));
    }

    public Categoria atualizar(Long id, CategoriaRequest request) {
        Categoria categoria = buscarPorId(id);
        categoria.setNome(request.getNome());
        return repository.save(categoria);
    }

    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);
        repository.delete(categoria);
    }
}
