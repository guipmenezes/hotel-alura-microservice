import com.hotelalura.Model.Reservas;
import com.hotelalura.Controller.ReservasRegistrationRequest;
import com.hotelalura.Model.ReservasRepository;
import com.hotelalura.Service.ReservasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservasRepository reservasRepository;

    private ReservasService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ReservasService(reservasRepository);
    }

    @Test
    void canFindReserva() {
        //when
        underTest.findReserva();
        //then
        verify(reservasRepository).findAll();
    }

    @Test
    void canDeleteReservaById() {
        //given
        Date date1 = new Date(2022-12-25);
        Date date2 = new Date(2023-01-01);
        Reservas reserva = new Reservas(1, date1, date2, 200, "À vista");

        //when
        underTest.deleteReserva(reserva.getReservasId());

        //then
        verify(reservasRepository).deleteById(reserva.getReservasId());
    }

    @Test
    void canRegisterReservation() {
        //given
        long milis = System.currentTimeMillis();
        ReservasRegistrationRequest request = new ReservasRegistrationRequest(
                new Date(milis),
                new Date(milis),
                200,
                "Cartão de crédito"
        );
        Reservas reservas = Reservas.builder()
                .dataEntrada(request.dataEntrada())
                .dataSaida(request.dataSaida())
                .valor(request.valor())
                .formaPagamento(request.formaPagamento())
                .build();

        //when
        underTest.registerReserva(request);

        //then
        ArgumentCaptor<Reservas> hospedeArgumentCaptor = ArgumentCaptor.forClass(Reservas.class);
        verify(reservasRepository).saveAndFlush(hospedeArgumentCaptor.capture());
        Reservas capturedReserva = hospedeArgumentCaptor.getValue();

        assertThat(capturedReserva).isEqualTo(reservas);
    }

    @Test
    void canRegisterReserva() {
        //given
        long milis = System.currentTimeMillis();
        ReservasRegistrationRequest request = new ReservasRegistrationRequest(
                new Date(milis),
                new Date(milis),
                200,
                "Dinheiro"
        );

        Reservas reserva = Reservas.builder()
                .reservasId(1)
                .dataEntrada(request.dataEntrada())
                .dataSaida(request.dataSaida())
                .valor(request.valor())
                .formaPagamento(request.formaPagamento())
                .build();

        //when
        reservasRepository.saveAndFlush(reserva);

        //then
        ArgumentCaptor<Reservas> reservasArgumentCaptor = ArgumentCaptor.forClass(Reservas.class);
        verify(reservasRepository).saveAndFlush(reservasArgumentCaptor.capture());
        Reservas reservasCaptured = reservasArgumentCaptor.getValue();

        assertThat(reservasCaptured).isEqualTo(reserva);
    }

    @Test
    void canFindReservasById() {
        //given
        Integer reservasId = 1;
        Reservas reserva = new Reservas();

        //when
        when(reservasRepository.findByReservasId(reservasId)).thenReturn(reserva);
        Reservas result = reservasRepository.findByReservasId(reservasId);

        //then
        assertThat(reserva).isEqualTo(result);
    }
}
