package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteDto;
import br.com.duxusdesafio.dto.TimeResponseDto;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ApiService apiService;

    @GetMapping("/time-da-data")
    public Time timeDaData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Time> times = timeRepository.findAll();
        return apiService.timeDaData(data, times);
    }

    @GetMapping("/integrantes-time-mais-recorrente")
    public Object integrantesMaisRecorrente(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){

        List<Time> times = timeRepository.findAll();
        return apiService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal, times);
    }

    @GetMapping("/integrante-mais-usado")
    public Object integranteMaisUsado(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        List<Time> times = timeRepository.findAll();

        Integrante integrante = apiService.integranteMaisUsado(dataInicial, dataFinal, times);

        if (integrante == null) return null;

        return new IntegranteDto(
                integrante.getId(),
                integrante.getNome(),
                integrante.getFuncao()
        );
    }

    @GetMapping("/funcao-mais-recorrente")
    public Object funcaoMaisRecorrente(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        List<Time> times = timeRepository.findAll();
        return apiService.funcaoMaisRecorrente(dataInicial, dataFinal, times);
    }

    @GetMapping("/clube-mais-recorrente")
    public Object clubeMaisRecorrente(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        List<Time> times = timeRepository.findAll();
        return apiService.clubeMaisRecorrente(dataInicial, dataFinal, times);
    }

    @GetMapping("/contagem-clubes")
    public Map<String, Long> contagemDeClubesNoPeriodo(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        List<Time> times = timeRepository.findAll();
        return apiService.contagemDeClubesNoPeriodo(dataInicial, dataFinal, times);
    }

    @GetMapping("/contagem-funcoes")
    public Map<String, Long> contagemPorFuncao(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        List<Time> times = timeRepository.findAll();
        return apiService.contagemPorFuncao(dataInicial, dataFinal, times);
    }

}
