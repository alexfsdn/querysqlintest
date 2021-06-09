package com.testplusquerysql.querysqlintest;

import com.testplusquerysql.querysqlintest.model.Product;
import com.testplusquerysql.querysqlintest.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void cleanAll(){
        productRepository.deleteAll();
    }

    @Test
    @Sql({"/insert1.sql", "/insert2.sql"})
    // ALTERNATIVA INVÉS DE USAR O método cleanAll construído acima @Sql(value = "/clean_table.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFileSql() {
        //Arrange
        final int TAMANHO_ESPERADO = 2;

        //Action
        List<Product> productList = productRepository.findAll();
        int tamanhoDaLista = productList.size();


        //Assert
        Assertions.assertEquals(TAMANHO_ESPERADO, tamanhoDaLista);
    }

    @Test
    @Sql(statements = "insert into product (name, price)  values ('Microphone', 15)")
    public void test() {
        //Arrange
        final int TAMANHO_ESPERADO = 1;
        final String NOME_ESPERADO = "Microphone";
        final Integer PRECO_ESPERADO = 15;

        //Action
        List<Product> productList = productRepository.findAll();
        int tamanhoDaLista = productList.size();
        String nomeAtual = productList.get(0).getName();
        Integer precoAtual = productList.get(0).getPrice();

        //Assert
        Assertions.assertEquals(TAMANHO_ESPERADO, tamanhoDaLista);
        Assertions.assertEquals(NOME_ESPERADO, nomeAtual);
        Assertions.assertEquals(PRECO_ESPERADO, precoAtual);
    }


    @Test
    @Sql({"/insert1.sql", "/insert2.sql"})
    @Sql(statements = "insert into product (name, price)  values ('Microphone', 15)")
    public void testFileAndStatements() {
        //Arrange
        final int TAMANHO_ESPERADO = 3;

        //Action
        List<Product> productList = productRepository.findAll();
        int tamanhoDaLista = productList.size();

        //Assert
        Assertions.assertEquals(TAMANHO_ESPERADO, tamanhoDaLista);
    }
}
