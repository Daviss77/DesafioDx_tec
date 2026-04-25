package br.com.duxusdesafio.controller;

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

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ApiService apiService;

    @GetMapping("/time-da-data")
    public Object timeDaData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate data) {
        List<Time> times = timeRepository.findAll();
        return apiService.timeDaData(data, times);
    }

    @GetMapping("/integrantes-time-mais-recorrente")
    public Object integrantesMaisRecorrente()

}
