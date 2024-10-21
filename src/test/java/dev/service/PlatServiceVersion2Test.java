package dev.service;

import dev.dao.IPlatDao;
import dev.exception.PlatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PlatServiceVersion2Test {

    @Mock
    private IPlatDao platDao;

    @InjectMocks
    private PlatServiceVersion2 platService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterPlatNomInvalide() {
        String nomPlat = "Pizza";
        int prix = 1500;

        platService.ajouterPlat(nomPlat, prix);
    }

    @Test
    void testAjouterPlatNomPrixValide() {
        String nomPlat = "Lasagnes";
        int prix = 1200;

        platService.ajouterPlat(nomPlat, prix);

        verify(platDao, times(1)).ajouterPlat(nomPlat, prix);
    }

}