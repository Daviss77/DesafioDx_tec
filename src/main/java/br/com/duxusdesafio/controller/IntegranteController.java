package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteDto;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.service.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/integrantes")
public class IntegranteController {

    @Autowired
    private IntegranteService integranteService;

    @PostMapping
    public Integrante salvar(@RequestBody Integrante integrante){
        return integranteService.salvar(integrante);
    }

    @GetMapping
    public List<Integrante> listar() {
        return integranteService.listar();
    }

    @GetMapping("/{id}")
    public IntegranteDto buscar(@PathVariable Long id){
        return integranteService.converterParaDto(
                integranteService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public Integrante atualizar(@PathVariable Long id, @RequestBody Integrante integrante){
        return integranteService.atualizar(id, integrante);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        buscar(id);
        integranteService.deletar(id);
    }


}
