package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.IntegranteDto;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteService {

    @Autowired
    private IntegranteRepository integranteRepository;

    public Integrante salvar(Integrante integrante){
        return integranteRepository.save(integrante);
    }

    public List<Integrante> listar() {
        return integranteRepository.findAll();
    }

    public Integrante buscarPorId(Long id){
        return integranteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Integrante não encontrado com id: " + id));
    }

    public Integrante atualizar (Long id, Integrante integranteAtualizado){
        Integrante existente =  buscarPorId(id);

        if(integranteAtualizado.getNome() != null){
            existente.setNome(integranteAtualizado.getNome());
        }
        if(integranteAtualizado.getFuncao() != null){
            existente.setFuncao(integranteAtualizado.getFuncao());
        }
        return integranteRepository.save(existente);
    }

    public void deletar (Long id){
        buscarPorId(id);
        integranteRepository.deleteById(id);
    }

    public IntegranteDto converterParaDto(Integrante integrante) {

        IntegranteDto dto = new IntegranteDto();
        dto.setId(integrante.getId());
        dto.setNome(integrante.getNome());
        dto.setFuncao(integrante.getFuncao());

        return dto;
    }

}
