package com.hotelalura.hospede;

import com.hotelalura.hospede.Controller.HospedeRegistrationRequest;
import com.hotelalura.hospede.Model.Hospede;
import com.hotelalura.hospede.Model.HospedeRepository;
import com.hotelalura.hospede.Service.HospedeService;
import com.hotelalura.hospede.VO.Reservas;
import com.hotelalura.hospede.VO.ResponseTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HospedeServiceTest {
    @Mock
    private HospedeRepository hospedeRepository;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
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
                .nome(request.nome())
                .sobrenome(request.sobrenome())
                .dataNascimento(request.dataNascimento())
                .nacionalidade(request.nacionalidade())
                .telefone(request.telefone())
                .build();
        //when

        underTest.registerHospede(request);

        //then
        ArgumentCaptor<Hospede> hospedeArgumentCaptor = ArgumentCaptor.forClass(Hospede.class);
        verify(hospedeRepository).saveAndFlush(hospedeArgumentCaptor.capture());
        Hospede capturedHospede = hospedeArgumentCaptor.getValue();

        assertThat(capturedHospede).isEqualTo(hospede);
    }

    @Test
    void canGetHospedeWithReserva() {
        //given
        Integer id = 1;

        Hospede hospede = new Hospede();
        hospede.setHospedeId(id);
        when(hospedeRepository.findByHospedeId(id)).thenReturn(hospede);

        Reservas reservas = new Reservas();
        when(restTemplate.getForObject("http://RESERVA/reservas/" + hospede.getHospedeId(), Reservas.class))
                .thenReturn(reservas);

        //when
        ResponseTemplate result = underTest.getHospedeWithReserva(id);

        //then
        Assertions.assertEquals(hospede, result.getHospede());
        Assertions.assertEquals(reservas, result.getReservas());

    }
}