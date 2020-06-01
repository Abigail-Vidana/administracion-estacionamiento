package mx.com.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import mx.com.model.Registro;

/**
 *
 * @author abigail
 */
public class MontoPago {//clase para obtener el total a pagar
    
    Registro registro;
    
    public MontoPago(Registro registro){
        this.registro = registro;
    }
    
    //metodo para obtener la diferencia de horas y saber cuanto tiempo utilizo el servicio un auto
    private long obtenrDiferenciaEnMinutos(LocalDateTime entrada, LocalDateTime salida){
        return entrada.until(salida, ChronoUnit.MINUTES);//obtiene en minutos la diferencia entre la entrada y salida
    }
    
    //metodo para calcular el total a pagar
    public long obtenerTotal(){
        //obtenemos los minutos utilizados del servicio
        long minutos = obtenrDiferenciaEnMinutos(registro.getEntrada().toLocalDateTime(),
                registro.getSalida().toLocalDateTime());
        long resultado = 35;//por default la primera hora se cobra a $35
        if(minutos > 60){//despues de una hora
            int espacios20 = (int)Math.ceil((minutos-60.0)/20.0);//cada 20 min extra
            resultado += espacios20 *5;//son $5 mas
        }
        return resultado;//se retorna el total a pagar
    }
    
}
