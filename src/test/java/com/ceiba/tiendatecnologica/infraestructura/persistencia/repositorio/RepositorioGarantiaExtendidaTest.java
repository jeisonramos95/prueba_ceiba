package com.ceiba.tiendatecnologica.infraestructura.persistencia.repositorio;

import com.ceiba.tiendatecnologica.aplicacion.comando.ComandoProducto;
import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.Producto;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioProducto;
import com.ceiba.tiendatecnologica.dominio.servicio.vendedor.ServicioVendedor;
import com.ceiba.tiendatecnologica.testdatabuilder.ProductoTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RepositorioGarantiaExtendidaTest {

    @Mock
    private RepositorioGarantiaPersistente repositorioGarantia;
    private EntityManager entityManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ProductoPrecioMyorTest(){

        //arrange
        ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
        Producto producto = productoestDataBuilder.build();
        // EntityManager managerMock = Mockito.mock(EntityManager.class);
        // Mockito.when(repositorioGarantia.)

        ComandoProducto comandoProducto = new ComandoProducto("FEU789", "Tv LG", 600000.00);

        // RepositorioGarantiaPersistente repositorioGarantia = mock(RepositorioGarantiaPersistente.class);
        // RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);

        //act
         //result = repositorioGarantia.agregar("codigo","nombre",comandoProducto);
        // ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);
        // servicioVendedor.generarGarantia(comandoProducto.getCodigo(), "Jeison", comandoProducto);

        // GarantiaExtendida garantia = repositorioGarantia.obtener(comandoProducto.getCodigo());

        //assert
       // assertEquals("", result);

    }


}
