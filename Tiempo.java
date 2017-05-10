package ejercicioscurso6;

import java.io.IOException;
import static java.time.DayOfWeek.*;//TUESDAY, siguienteMartes
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import static java.time.Month.DECEMBER;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.TemporalAdjusters.next;
import java.util.Locale;
import sun.security.krb5.internal.KDCOptions;

public class Tiempo {

    public Tiempo() {
        LocalDatesAndTimesClase();
        LocalTimeClase();
        localDateTimeClase();
        timeZonesClase();
        dateAndTimeAmounts();
    }

    public void LocalDatesAndTimesClase() {
        System.out.println("------------Local Dates CLASE---------------");
        LocalDate ahora, bDate, ahoraMasMes, siguienteMartes;
        ahora = LocalDate.now();//se asigno a la variable ahora el valor del dia en curso
        System.out.println(ahora);//el resultado: 2017-05-10
        bDate = LocalDate.of(1995, Month.MARCH, 23);//cumpleaños de java
        System.out.println("El cumpleaños de java es pasado? " + bDate.isBefore(ahora));//compara con la fecha 
        //y manda true si bDate es antes de ahora
        System.out.println(" Leap Year? " + bDate.isLeapYear()); //false
        System.out.println(" Dia de la semana " + bDate.getDayOfWeek());//manda el dia en ingles THURSDAY
        ahoraMasMes = ahora.plusMonths(1);
        System.out.println(" Mas un mes " + ahoraMasMes);//2017-06-10
        siguienteMartes = ahora.with(next(TUESDAY));
        System.out.println("SIGUIENTE MARTES: " + siguienteMartes);//2017-05-16

    }

    public void LocalTimeClase() {
        System.out.println("--------------------LOCAL TIME-------------------");
        //basado en un reloj de 24 horas (15 horas)
        //LocalTime mothods
        LocalTime ahora, ahoraMas, ahoraHorasMins, horaComida, horaDormir;
        ahora = LocalTime.now();
        System.out.println("Ahora: " + ahora);
        ahoraMas = ahora.plusHours(1).plusMinutes(15);
        System.out.println("Mas una hora mas 15 minutos: " + ahoraMas);
        ahoraHorasMins = (ahora.truncatedTo(MINUTES));
        System.out.println("TRUNCADO A MINUTOS: " + ahoraHorasMins);
        System.out.println("Es el: " + ahora.toSecondOfDay() / 60 + " minuto ");
        horaComida = LocalTime.of(1, 30);
        System.out.println("Esta la hora de comida en el futuro? " + horaComida.isAfter(ahora));
        long minutosParaLaComida = ahora.until(horaComida, MINUTES);
        System.out.println("Minutos para la comida: " + minutosParaLaComida);
        horaDormir = LocalTime.of(20, 0);
        long horasParaDormir = ahora.until(horaDormir, HOURS);
        System.out.println("Horas que faltan para dormir " + horasParaDormir);
    }

    public void localDateTimeClase() {
        System.out.println("------------LocalDateTimes CLASE---------------");
        LocalDateTime reunion, vuelo, cursoEmpieza, cursoTermina;
        reunion = LocalDateTime.of(2017, MARCH, 5, 12, 30);
        System.out.println("La reunion es: " + reunion);
        LocalDate fechaVuelo = LocalDate.of(2014, MARCH, 21);
        LocalTime tiempoVuelo = LocalTime.of(21, 45);
        vuelo = LocalDateTime.of(fechaVuelo, tiempoVuelo);
        System.out.println("El vuelo se va: " + vuelo);
        cursoEmpieza = LocalDateTime.of(2017, MARCH, 10, 9, 00);
        cursoTermina = cursoEmpieza.plusDays(5).plusHours(8).plusMinutes(5);
        System.out.println("El curso Empieza : " + cursoEmpieza);
        System.out.println("El curso termina " + cursoTermina);
        long horasCurso = (cursoTermina.getHour() - cursoEmpieza.getHour())
                * (cursoEmpieza.until(cursoTermina, DAYS) + 1);
        System.out.println(horasCurso);
    }

    public void timeZonesClase() {
        //utc y esas cosas
        //ZONEID especifica locacion para utc
        //ZoneOffset extiende de zoneid especifica la diferencia del tiempo actual
        //ZoneRules se usa para especificar zoneoffset LEER REGLAS
        System.out.println("------------TIME ZONES-------------------------");
        ZoneId NYTZ = ZoneId.of("America/New_York");
        ZoneOffset USEast = ZoneOffset.ofHoursMinutes(5, 45);
        ZoneId EST = ZoneId.ofOffset("UTC", USEast);
        System.out.println("NYTZ: " + NYTZ);
        System.out.println("USEast: " + USEast);
        System.out.println("EST: " + EST);
        //ZONEDDATETIME
        LocalDate fechaJunta = LocalDate.of(2017, MARCH, 8);
        LocalTime tiempoJunta = LocalTime.of(16, 00);
        ZonedDateTime junta = ZonedDateTime.of(fechaJunta, tiempoJunta, USEast);
        System.out.println("La junta es: " + junta);
        ZonedDateTime nuevaJunta = junta.plusDays(1);
        System.out.println("La nueva junta es: " + nuevaJunta);
        //iana zone database, international org.
    }

    public void dateAndTimeAmounts() {
        System.out.println("-------------DATE TIME AMOUNTS----------------");
        try {
            //definir y crear timestamps, periodos, dureciones
            //como formatear la fecha
            Instant ahora = Instant.now();
            Thread.sleep(0, 1);//long milisegundos, int nanosegundos
            Instant despues = Instant.now();
            System.out.println("Ahora es mas terde que despues? " + ahora.isBefore(despues));
            System.out.println(ahora);
            System.out.println(despues);
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }

        //Instant es un punto instantanea en la linea del tiempo
        //debe de ser usado para grabar timestamps en las aplicaciones
        //CLASE PERIOD
        System.out.println("Period");
        Period unDia = Period.ofDays(1);
        System.out.println("Periodo del dia: " + unDia);
        LocalDateTime antesDST = LocalDateTime.of(2014, MARCH, 18, 12, 00);
        ZonedDateTime nuevaYork = ZonedDateTime.of(antesDST, ZoneId.of("America/New_York"));
        System.out.println("Despues: " + nuevaYork);
        System.out.println("Antes: " + nuevaYork.plus(unDia));
        //DURATION
        System.out.println("Duration");
        Duration unDiaD = Duration.ofDays(1);
        System.out.println("Periodo del dia: " + unDiaD);
        LocalDateTime antesDSTD = LocalDateTime.of(2014, MARCH, 18, 12, 00);
        ZonedDateTime nuevaYorkD = ZonedDateTime.of(antesDSTD, ZoneId.of("America/New_York"));
        System.out.println("Despues: " + nuevaYorkD);
        System.out.println("Antes: " + nuevaYorkD.plus(unDiaD));

        //calculating BETWEEN DATES con el LocalDate
        LocalDate navidad = LocalDate.of(2017, DECEMBER, 25);
        LocalDate hoy = LocalDate.now();
        long dias = DAYS.between(hoy, navidad);//con el Period tambien se puede usar el between
        System.out.println("Dias entre navidad y el dia de hoy: " + dias);

        //MAKE DATES PRETTY USING DATETMEFORMATTER
        ZonedDateTime ahora = ZonedDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE;
        System.out.println("---FORMATTER---");
        System.out.println("ISO_LOCAL_DATE: " + formato);
        DateTimeFormatter formato2 = DateTimeFormatter.ISO_ORDINAL_DATE;
        System.out.println("ISO_ORDINAL_DATE: " + formato2);
        DateTimeFormatter formato3 = DateTimeFormatter.ofPattern("EEEE, MMMM, YYYY G, HH:MM AVV");
        System.out.println("ofPattern: " + formato3);
        DateTimeFormatter formato4 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("ofLocalizedDateTime: " + formato4);

        //FLUENT NOTATION JSR-310 MAKE API FLUENT
        System.out.println("FLUENT NOTATION");
        LocalDate miCumple = LocalDate.of(07, JULY, 1993);
        miCumple = Year.of(1993).atMonth(JULY).atDay(22);
        LocalDateTime meeting = LocalDateTime.of(1993, JULY, 22, 01, 01);
        System.out.println(miCumple);
        System.out.println(meeting);

    }

    public static void main(String... args) {
        new Tiempo();
    }
}
