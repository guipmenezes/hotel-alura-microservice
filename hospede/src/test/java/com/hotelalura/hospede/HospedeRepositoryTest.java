package com.hotelalura.hospede;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HospedeRepositoryTest {

    @Autowired
    private HospedeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindHospedeById() {
        //given
        long milis = System.currentTimeMillis();

        Hospede hospede = new Hospede(
                1,
                "Guilherme",
                "Menezes",
                new java.sql.Date(milis),
                "brasileiro",
                "61983212365"
        );

        underTest.save(hospede);

        //when
        Hospede expected = underTest.findByHospedeId(1);

        //then
        assertThat(expected).isEqualTo(hospede);
    }

    @Test
    void itShouldNotFindHospedeById() {
        //given
        Integer id = 1;

        //when
        Hospede expected = underTest.findByHospedeId(1);

        //then
        assertThat(expected).isNotEqualTo(id);
    }
}