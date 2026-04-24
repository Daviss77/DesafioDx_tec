package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Time;
import com.tngtech.java.junit.dataprovider.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class ApiServiceTimeTest {

    private final static LocalDate data1993 = LocalDate.of(1993,1,1);
    private final static LocalDate data1995 = LocalDate.of(1995,1,1);

    @Spy
    private ApiService apiService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DataProvider
    public static Object[][] testTimeDaDataParams() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        return new Object[][]{
                { data1995, dados.getTodosOsTimes(), dados.getTimeChicagoBullsDe1995() },
                { data1993, dados.getTodosOsTimes(), dados.getTimeDetroidPistonsDe1993() }
        };
    }

    @Test
    @UseDataProvider("testTimeDaDataParams")
    public void testTimeDaData(LocalDate data, List<Time> lista, Time esperado) {
        assertEquals(esperado, apiService.timeDaData(data, lista));
    }

    @Test
    public void deveRetonarNuloQuandoDataNaoForEncontrada(){
        DadosParaTesteApiService dados = new DadosParaTesteApiService();
        assertNull(apiService.timeDaData(LocalDate.of(2000,1,1), dados.getTodosOsTimes()));
    }

    @Test
    public void deveRetornarNuloQuandoAListaForVazia(){
        assertNull(apiService.timeDaData(data1995, new ArrayList<>()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarExcecaoQuandoDataForNulo(){
        apiService.timeDaData(null, new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarExcecaoQuandoListaForNulo(){
        apiService.timeDaData(data1995, null);
    }
}