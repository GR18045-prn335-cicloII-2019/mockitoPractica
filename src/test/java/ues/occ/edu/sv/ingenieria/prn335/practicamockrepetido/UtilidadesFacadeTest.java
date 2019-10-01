/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.practicamockrepetido;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Clasificacion;

/**
 *
 * @author marlon
 */
@ExtendWith(MockitoExtension.class)
public class UtilidadesFacadeTest {
    //todas las entidades
    EntityTransaction mockTr = Mockito.mock(EntityTransaction.class);
    EntityManager mockEm = Mockito.mock(EntityManager.class);
    EntityManagerFactory mockEmf = Mockito.mock(EntityManagerFactory.class);
    UtilidadesFacade cut = new UtilidadesFacade();
    Clasificacion mockr = Mockito.mock(Clasificacion.class);
    List<Clasificacion> lista = Mockito.mock(ArrayList.class);

    public UtilidadesFacadeTest() {
    }

    /**
     * Test of iniciarEm method, of class UtilidadesFacade.
     */
    @Test
    public void testIniciarEm() throws Exception {
        cut.em = mockEm;
        Mockito.when(mockEmf.createEntityManager()).thenReturn(mockEm);
        cut.iniciarEm();
    }

    /**
     * Test of getTX method, of class UtilidadesFacade.
     */
    @Test
    public void testGetTX() throws Exception {
        //primero la excepcion
        cut.em = null;
        cut.getTX();
        //ahora sin la excepcion
        cut.em = mockEm;
        cut.getTX();

    }

    /**
     * Test of crear method, of class UtilidadesFacade.
     */
    @Test
    public void testCrear() throws PersistenceException {

        //ahora todo bien
        cut.em = mockEm;
        Mockito.when(mockEm.getTransaction()).thenReturn(mockTr);
        cut.crear(mockr);
        //devuelve error de un metodo void 
        try {
            Mockito.doThrow(PersistenceException.class).when(mockEm).persist(Mockito.any(Clasificacion.class));
            cut.crear(mockr);
        } catch (PersistenceException e) {
        }
    }

    /**
     * Test of modificar method, of class UtilidadesFacade.
     */
    @Test
    public void testModificar() throws PersistenceException {
       //ahora todo bien
        cut.em = mockEm;
        Mockito.when(mockEm.getTransaction()).thenReturn(mockTr);
        cut.modificar(mockr);
        //devuelve error de un metodo void 
        try {
            Mockito.doThrow(PersistenceException.class).when(mockEm).merge(Mockito.any(Clasificacion.class));
            cut.modificar(mockr);
        } catch (PersistenceException e) {
        }
    }

    /**
     * Test of eliminar method, of class UtilidadesFacade.
     */
    @Test
    public void testEliminar() throws PersistenceException {

        //ahora todo bien
        cut.em = mockEm;
        Mockito.when(mockEm.getTransaction()).thenReturn(mockTr);
        cut.eliminar(mockr);
        //devuelve error de un metodo void 
        try {
            Mockito.doThrow(PersistenceException.class).when(mockEm).remove(Mockito.any(Clasificacion.class));
            cut.eliminar(mockr);
        } catch (PersistenceException e) {
        }
    }

    /**
     * Test of getClasificacion method, of class UtilidadesFacade.
     */
    @Test
    public void testGetClasificacion() throws Exception {
        cut.em = mockEm;
        List<Clasificacion> lista = new ArrayList<Clasificacion>();
        Query mockQ = Mockito.mock(Query.class);
        Mockito.when(mockEm.createQuery(Mockito.anyString())).thenReturn(mockQ);
        Mockito.when(mockQ.getResultList()).thenReturn(lista);
        //todo va bien
        try {

            List<Clasificacion> resultado = cut.getClasificacion();
        } catch (Exception e) {

            //devuelve la query null para que genere la ecepcion
        }
        try {

            Mockito.when(mockEm.createQuery(Mockito.anyString())).thenReturn(null);
            List<Clasificacion> resultado = cut.getClasificacion();
        } catch (Exception e) {
        }
    }

    /**
     * Test of handler method, of class UtilidadesFacade.
     */
    @Test
    public void testHandler() throws Exception {

        //mock todo el metodo get lista
        Clasificacion clasi = Mockito.mock(Clasificacion.class);
        Query mockQ = Mockito.mock(Query.class);
        Mockito.when(mockEm.createQuery(Mockito.anyString())).thenReturn(mockQ);
        Mockito.when(mockQ.getResultList()).thenReturn(lista);
        //mock get id clasificacion
        Mockito.when(lista.get(Mockito.anyInt())).thenReturn(clasi);
        Mockito.when(clasi.getIdClasificacion()).thenReturn(10);
        //que devuelva tamaño mayor a 0
        Mockito.when(lista.size()).thenReturn(10);
        try {
            cut.handler();
        } catch (Exception e) {
        }
        //que devuelva un id=0
        try {
            Mockito.when(clasi.getIdClasificacion()).thenReturn(0);
            cut.handler();
        } catch (Exception e) {
        }
        //que devuelva tamaño igual a 0
        try {
            Mockito.when(lista.size()).thenReturn(-1);
            cut.handler();
        } catch (Exception e) {
        }
    }

}
