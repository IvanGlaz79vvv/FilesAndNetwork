package airport;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        //TODO Метод должен вернуть количество самолетов указанной модели.
        // подходят те самолеты, у которых name начинается со строки model
        return airport.getAllAircrafts().stream()
                .filter(a -> a.getModel().startsWith(model))
                .count();
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов в каждом терминале.
        Map<String, Integer> infoOfParkedAircraft = airport.getTerminals().stream()
                .collect(Collectors.toMap(
                        Terminal::getName,
                        t -> t.getParkedAircrafts().size()
                ));

        Map<String, Integer> infoOfParkedAircraftSortedByName = infoOfParkedAircraft.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

        return infoOfParkedAircraftSortedByName;
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.
        List<Flight> flightsAll = new ArrayList<>();
        List<Flight> departingList = new ArrayList<>();

        for (Terminal terminal : airport.getTerminals()) {
            flightsAll.addAll(terminal.getFlights());
        }

        Instant currentDate = Instant.now();
        Instant plusTime = currentDate.plus(Duration.ofHours(hours));
        for (Flight flight : flightsAll) {
            if ((flight.getType() == Flight.Type.DEPARTURE) &&
                    flight.getDate().isAfter(currentDate) &&
                    flight.getDate().isBefore(plusTime)) {
                departingList.add(flight);
            }
        }

        return departingList;
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        //TODO Найти ближайший прилет в указанный терминал.

            List<Terminal> terminalArrayList = airport.getTerminals();
            List<Flight> arrivalFlights = new ArrayList<>();

            Map<String, Terminal> terminalMap = new HashMap<>();
            List<Flight> flightList = new ArrayList<>();

            for (Terminal t : terminalArrayList) {
                terminalMap.put(t.getName(), t);
                if (t.getName().equals(terminalName)) {
                    flightList.addAll(terminalMap.get(terminalName).getFlights());//terminalMap.get(terminalForProcessing).getFlights().forEach(f -> flightList.add(f));

                    arrivalFlights.addAll(flightList.stream()
                            .filter(f -> f.getType() == Flight.Type.ARRIVAL)
                            .toList());
                }
            }

            flightList.sort(Comparator.comparing(Flight::getDate));

        if (arrivalFlights.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(arrivalFlights.get(0));
        }
    }
}