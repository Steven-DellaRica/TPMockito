package dev.service;

import dev.dao.IPlatDao;
import dev.entite.Plat;
import dev.exception.PlatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class PlatServiceVersion1Test {

    @Test
    void testListerPlats() {
        IPlatDao platDao = Mockito.mock(IPlatDao.class);

        Mockito.when(platDao.listerPlats()).thenReturn(Arrays.asList(
                new Plat("Couscous", 51890),
                new Plat("Tartiflette", 1605789)
        ));

        PlatServiceVersion1 platService = new PlatServiceVersion1(platDao);

        List<Plat> plats = platService.listerPlats();

        Assertions.assertEquals(2, plats.size());
        Assertions.assertEquals("COUSCOUS", plats.get(0).getNom());
        Assertions.assertEquals("TARTIFLETTE", plats.get(1).getNom());

        Mockito.verify(platDao, Mockito.times(1)).listerPlats();
    }

    @Test
    void testListerPlatsException() {
        IPlatDao platDao = Mockito.mock(IPlatDao.class);

        Mockito.when(platDao.listerPlats()).thenThrow(new NullPointerException());

        PlatServiceVersion1 platService = new PlatServiceVersion1(platDao);

        PlatException thrownException = Assertions.assertThrows(PlatException.class, () -> {
            platService.listerPlats();
        });

        Assertions.assertTrue(thrownException.getCause() instanceof NullPointerException);
    }
  
}