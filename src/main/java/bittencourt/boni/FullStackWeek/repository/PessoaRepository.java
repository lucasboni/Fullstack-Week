/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bittencourt.boni.FullStackWeek.repository;

import bittencourt.boni.FullStackWeek.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lucas
 */
public interface PessoaRepository  extends JpaRepository<Pessoa,Long>{
    
}
