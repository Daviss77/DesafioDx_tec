package br.com.duxusdesafio.service;
import br.com.duxusdesafio.model.Time;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class ApiServiceClubeTest {

    private final static LocalDate data1993 = LocalDate.of(1993, 1, 1);
    private final static LocalDate data1994 = LocalDate.of(1994, 1, 1);
    private final static LocalDate data1995 = LocalDate.of(1995, 1, 1);

    @Spy
    private ApiService apiService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DataProvider
    public static Object[][] testClubeMaisRecorrenteParams() {

        DadosParaTesteApiService dados = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dados.getTodosOsTimes();

        return new Object[][]{

                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        dados.getClubeChicagoBulls()
                }
        };
    }
    
    @Test
    @UseDataProvider("testClubeMaisRecorrenteParams")
    public void deveRetornarClubeMaisRecorrente(
            LocalDate dataInical,
            LocalDate dataFinal,
            List<Time> todosOsTimes,
            String esperado
    ){
        String resultado = apiService.clubeMaisRecorrente(dataInical, dataFinal, todosOsTimes);

        assertEquals(esperado, resultado);
    }

    @Test
    public void deveRetornarNullQuandoNaoExistiremTimesNoPeriodo(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        String resultado = apiService.clubeMaisRecorrente(
                LocalDate.of(2000,1,1),
                LocalDate.of(2000,1,1),
                dados.getTodosOsTimes()
        );

        assertNull(resultado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoListaForNula(){
        apiService.clubeMaisRecorrente(
                data1993,
                data1995,
                null
        );
    }

    @DataProvider
    public static Object[][] testContagemDeClubesParams(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dados.getTodosOsTimes();

        Map<String, Long> esperado1 = new HashMap<>();
        esperado1.put(dados.getClubeDetroitPistons(), 1L);
        esperado1.put(dados.getClubeChicagoBulls(), 2L);

        Map<String, Long> esperado2 = new HashMap<>();
        esperado2.put(dados.getClubeChicagoBulls(), 2L);

        return new Object[][]{
                {
                    data1993,
                    data1995,
                    todosOsTimes,
                    esperado1
                },
                {
                    data1994,
                    data1995,
                    todosOsTimes,
                    esperado2
                }
        };
    }

    @Test
    @UseDataProvider("testContagemDeClubesParams")
    public void deveRetornarContagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> resultado = apiService.contagemDeClubesNoPeriodo(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, resultado);
    }

    @Test
    public void deveRetornarMapaVazioQuandoListaForVazia(){
        Map<String, Long> resultado = apiService.contagemDeClubesNoPeriodo(data1993, data1995, new ArrayList<>());

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void deveRetornarMapaVazioQuandoNaoExistiremTimesNoPeriodo(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();
        Map<String, Long> resultado = apiService.contagemDeClubesNoPeriodo(
                LocalDate.of(2000,1,1),
                LocalDate.of(2001,1,1),
                dados.getTodosOsTimes()
        );
        assertTrue(resultado.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoListaClubeForNula(){
        apiService.clubeMaisRecorrente(
                data1993,
                data1995,
                null
        );
    }
}

