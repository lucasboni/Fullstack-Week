/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bittencourt.boni.FullStackWeek.resorce;

import bittencourt.boni.FullStackWeek.domain.GruposPrioridades;
import bittencourt.boni.FullStackWeek.domain.Pessoa;
import bittencourt.boni.FullStackWeek.repository.GruposPrioridadeRepository;
import bittencourt.boni.FullStackWeek.repository.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas
 */
@RestController
@RequestMapping("/gruposprioridades")
public class GruposPrioridadesResorce {
     @Autowired
    private GruposPrioridadeRepository repository;
    
    
    @GetMapping
    public List<GruposPrioridades> listarTodos(){
        return repository.findAll();
    }
    
    @PostMapping
    public GruposPrioridades cadastrar(@RequestBody GruposPrioridades pessoa){
        return repository.save(pessoa);
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity buscar(@PathVariable("codigo") Long codigo){
        return repository.findById(codigo).map(
                record -> {
                    return ResponseEntity.ok().body(record);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
