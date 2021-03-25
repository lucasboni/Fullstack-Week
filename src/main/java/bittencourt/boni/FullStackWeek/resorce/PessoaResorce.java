/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bittencourt.boni.FullStackWeek.resorce;

import bittencourt.boni.FullStackWeek.domain.Pessoa;
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
@RequestMapping("/pessoas")
public class PessoaResorce {
    
    
    @Autowired
    private PessoaRepository repository;
    
    
    @GetMapping
    public List<Pessoa> listarTodos(){
        return repository.findAll();
    }
    
    @PostMapping
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa){
        return repository.save(pessoa);
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity atualizar(@PathVariable("codigo") Long codigo,@RequestBody Pessoa pessoa){
        return repository.findById(codigo).map(record ->{
                   record.setCpf(pessoa.getCpf()); 
                   record.setDataNascimento(pessoa.getDataNascimento()); 
                   record.setEmail(pessoa.getEmail()); 
                   record.setIdade(pessoa.getIdade()); 
                   record.setTelefone(pessoa.getTelefone());
                   Pessoa pessoaAtualizada = repository.save(record);
                   return ResponseEntity.ok().body(pessoaAtualizada);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity buscarPessoa(@PathVariable("codigo") Long codigo){
        return repository.findById(codigo).map(
                record -> {
                    return ResponseEntity.ok().body(record);
                }
        ).orElse(ResponseEntity.notFound().build());
    }
            
}
