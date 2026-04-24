package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Integrante;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class ApiServiceIntegranteTest {

    private final static LocalDate data1993 = LocalDate.of(1993,1,1);
    private final static LocalDate data1995 = LocalDate.of(1995,1,1);

    @Spy
    private ApiService apiService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @DataProvider
    public static Object[][] integranteMaisUsadoParams() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();
        List<Time> todos = dados.getTodosOsTimes();

        return new Object[][]{
                //periodo completo de datas
                {data1993, data1995, todos, dados.getDenis_rodman()},
                //apenas 1993
                {data1993, data1993, todos, dados.getDenis_rodman()}
        };
    }

    @Test
    @UseDataProvider("integranteMaisUsadoParams")
    public void deveRetornarIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Integrante esperado){

        Integrante resultado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, resultado);
    }
    //Lista Vazia
    @Test
    public void deveRetornarNullQuandoListaVazia(){
        Integrante resultado = apiService.integranteMaisUsado(data1993, data1995, new ArrayList<>());

        assertNull(resultado);
    }

    //Periodo sem times
    @Test
    public void deveRetornarNullQuandoNaoHaTimesNoPeriodo(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        Integrante resultado = apiService.integranteMaisUsado(
                LocalDate.of(2001,1,1),
                LocalDate.of(2001,1,1),
                dados.getTodosOsTimes()
        );
        assertNull(resultado);
    }

    //Exceções
    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExececoesQuandoDataInicialForNula(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        apiService.integranteMaisUsado(null, data1995, dados.getTodosOsTimes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExececoesQuandoDataFinalForNula(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        apiService.integranteMaisUsado(data1993, null, dados.getTodosOsTimes());
    }

    //========================
    // TESTE - integrantesDoTimeMaisRecorrente
    // =======================

    @DataProvider
    public static Object[][] integrantesDoTimeMaisRecorrenteParams(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();
        List<Time> todos = dados.getTodosOsTimes();

        List<String> chigagoEsperado = Arrays.asList(
                dados.getDenis_rodman().getNome(),
                dados.getMichael_jordan().getNome(),
                dados.getScottie_pippen().getNome()
        );

        List<String> detroitEsperado = Arrays.asList(
                dados.getDenis_rodman().getNome()
        );

        return new Object[][]{
                {data1993, data1995, todos, chigagoEsperado},
                {LocalDate.of(1994,1,1), data1995, todos, chigagoEsperado},
                {data1993, data1993, todos, detroitEsperado},
                {null, data1995, todos, chigagoEsperado},
                {data1993, null, todos, chigagoEsperado},
                {null, null, todos, chigagoEsperado},
                {data1993, data1995, new ArrayList<>(), null},
                {LocalDate.of(2000,1,1),LocalDate.of(2001,1,1), todos, null}
        };
    }

    @Test
    @UseDataProvider("integrantesDoTimeMaisRecorrenteParams")
    public void deveRetornarIntegrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, List<String> esperado){

        List<String> resultado = apiService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);

        if(resultado != null){
            Collections.sort(resultado);
        }
        if (esperado != null){
            Collections.sort(esperado);
        }

        assertEquals(esperado, resultado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoListaForNula_TimeMaisRecorrente(){
        apiService.integrantesDoTimeMaisRecorrente(data1993, data1995, null);
    }
}
