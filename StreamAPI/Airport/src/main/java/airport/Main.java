package airport;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        //TODO Метод должен вернуть количество самолетов указанной модели.
        // подходят те самолеты, у которых name начинается со строки model
        return airport.getAllAircrafts().stream()
                .filter(a -> a.getModel().startsWith(model)) // Фильтруем нужные самолёты
                .count();                                   // Подсчитываем их количество
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов в каждом терминале.
        Map<String,Integer> infoOfParkedAircraft = airport.getTerminals().stream()
                .collect(Collectors.toMap(
                        Terminal::getName,                   // Название терминала
                        t -> t.getParkedAircrafts().size()    // Количество припаркованных самолётов
                ));

        Map<String, Integer> infoOfParkedAircraftSortedByName = infoOfParkedAircraft.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Сортировка по ключу (имени терминала)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1, // конфликт разрешаем первым значением (редко бывает актуально)
                        LinkedHashMap::new // Поддерживаем порядок сортировки
                ));

        infoOfParkedAircraftSortedByName.forEach((terminal, aircraftCount) ->
                System.out.println(terminal + ": " + aircraftCount));

        return infoOfParkedAircraftSortedByName;
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.
        return Collections.emptyList();
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        //TODO Найти ближайший прилет в указанный терминал.
        return Optional.empty();
    }
}