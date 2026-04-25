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

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class ApiServiceFuncaoTest {

    private final static LocalDate data1993 = LocalDate.of(1993,1,1);
    private final static LocalDate data1994 = LocalDate.of(1994,1,1);
    private final static LocalDate data1995 = LocalDate.of(1995,1,1);

    @Spy
    private ApiService apiService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    //TESTES
    @DataProvider
    public static Object[][] funcaoMaisRecorrenteParams(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dados.getTodosOsTimes();

        return new Object[][]{
                {data1993, data1995, todosOsTimes, "ala"},
                {data1994, data1995, todosOsTimes, "ala"},
                {data1993, data1993, todosOsTimes, "ala-pivô"},
                {null, data1995, todosOsTimes, "ala"},
                {data1993, null, todosOsTimes, "ala"},
                {null, null, todosOsTimes, "ala"},
                {data1993, data1995, new ArrayList<>(), null},
                {
                    LocalDate.of(2000,1,1),
                    LocalDate.of(2000,1,1),
                    todosOsTimes,
                    null
                }
        };
    }

    @Test
    @UseDataProvider("funcaoMaisRecorrenteParams")
    public void deveRetornarFuncaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, String esperado){
        String resultado = apiService.funcaoMaisRecorrente(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, resultado);
    }

    //Excecao
    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoListaForNula(){
        apiService.funcaoMaisRecorrente(data1993, data1995, null);
    }

    @DataProvider
    public static Object[][] testContagemPorFuncaoParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Map<String, Long> esperado = new HashMap<>();
        esperado.put("ala", 4L);
        esperado.put("ala-pivô", 3L);

        Map<String, Long> esperadoParcial = new HashMap<>();
        esperadoParcial.put("ala", 4L);
        esperadoParcial.put("ala-pivô", 2L);

        Map<String, Long> esperadoNulo = new HashMap<>();
        esperadoNulo.put("ala", 4L);
        esperadoNulo.put("ala-pivô", 3L);

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        esperado
                },
                {
                        data1994,
                        data1995,
                        todosOsTimes,
                        esperadoParcial
                },

                // datas nulas
                {
                        null,
                        null,
                        todosOsTimes,
                        esperado
                },

                // lista vazia
                {
                        data1993,
                        data1995,
                        new ArrayList<>(),
                        null},
                // sem resultado
                {
                    LocalDate.of(2000,1,1), LocalDate.of(2001,1,1), todosOsTimes, null}
        };
    }

    @Test
    @UseDataProvider("testContagemPorFuncaoParams")
    public void testContagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, contagemPorFuncao);
    }
}