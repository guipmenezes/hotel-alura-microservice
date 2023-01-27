package com.hotelalura.hospede;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HospedeServiceTest {
    @Mock
    private HospedeRepository hospedeRepository;
    @Mock
    private RestTemplate restTemplate;
    private HospedeService underTest;

    @BeforeEach
    void setUp() {
        underTest = new HospedeService(hospedeRepository, restTemplate);
    }

    @Test
    void canFindHospedes() {
        //when
        underTest.findHospede();
        //then
        verify(hospedeRepository).findAll();
    }

    @Test
    void canDeleteHospede() {
        //given
        Integer id = 1;

        //when
        underTest.deleteHospede(1);

        //then
        verify(hospedeRepository).deleteById(id);

    }

    @Test
    void canRegisterHospede() {
        long milis = System.currentTimeMillis();
        HospedeRegistrationRequest request = new HospedeRegistrationRequest(
                "Guilherme",
                "Menezes",
                new Date(milis),
                "brasileiro",
                "6127812718"
        );

        Hospede hospede = Hospede.builder()
                .hospedeId(1)
                .nome(request.nome())
                .sobrenome(request.sobrenome())
                .dataNascimento(request.dataNascimento())
                .nacionalidade(request.nacionalidade())
                .telefone(request.telefone())
                .reservasId(1)
                .build();

        //when
        Mockito.when(restTemplate.postForObject("http://FRAUDE/fraude-check/{hospedeId}",
                        request,
                        ResponseEntity.class,
                        hospede.getHospedeId())).thenReturn(new ResponseEntity(HttpStatus.OK));

        underTest.registerHospede(request);

        //then
        ArgumentCaptor<Hospede> hospedeArgumentCaptor = ArgumentCaptor.forClass(Hospede.class);
        verify(hospedeRepository).saveAndFlush(hospedeArgumentCaptor.capture());
        Hospede capturedHospede = hospedeArgumentCaptor.getValue();

        assertThat(capturedHospede).isEqualTo(hospede);
    }

    @Test
    @Disabled
    void getHospedeWithReserva() {
    }
}