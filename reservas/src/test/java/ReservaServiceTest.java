import com.hotelalura.Reservas;
import com.hotelalura.ReservasRegistrationRequest;
import com.hotelalura.ReservasRepository;
import com.hotelalura.ReservasService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

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
    void canFindReservaById() {
        //given

        //when
        underTest.findReservasById(1);

        //then
        verify(reservasRepository).findById(1);
    }
}
