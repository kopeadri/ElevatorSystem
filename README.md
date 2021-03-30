# ElevatorSystem

System kontroli wind w budynku. 

- Java 11

## Opis 
### Elevator
Klasa reprezentująca windę, jej najważniejszymi zmiennymi składowymi są:
- id - identyfikator 
- currentFloor - numer piętra, na którym winda obecnie się znajduje
- destination - piętro docelowe
Trójka ta określa status windy.

Kierunek windy określany jest na podstawie różnicy jej obecnego i docelowego piętra. Oznaczany jest przez: '+' - w górę, '-' - w dół, '0' - spoczynek.
Kolejny krok symulacji oznacza przemieszczenie się windy o 1 piętro zgodnie z jej kierunkiem.


### ElevatorSystem
Klasa reprezentująca system wind, której najważniejszymi zmiennymi składowymi są:
- elevatorsNumber - liczba wind 
- floorsNumber - liczba pięter
- elevators - lista wind (obiektów Elevator)

Przywołanie windy reprezentowane jest przez trójkę:
- piętro zgłoszenia
- kierunek: + góra, - dół
- piętro docelowe

Działanie systemu polega na pojedynczym przetwarzaniu kolejnych przywołań windy. Do obsługi danego wywołania w pierwszej kolejności przydzielana jest winda znajdująca się najbliżej piętra zgłoszenia, która znajduje się w spoczynku, bądź kierunek do piętra zgłoszenia jest zgodny w stosunku do kierunku jej ruchu.


### ElevatorSystemSimulation


## Uruchomienie programu
Aby uruchomić program, należy wywołać polecenie
```
> java ElevatorSystemSimulation elevatorsNumber floorsNumber pickupNumber randomInitialState
```
w głównym katalogu projektu.
Kolejne argumenty programu to:
- int elevatorsNumber - liczba wind (domyślnie 3)
- int floorsNumber - liczba pięter (domyślnie 5)
- int pickupNumber - liczba zgłoszeń (domyślnie 8)
- boolean randomInitialState - początkowy stan wind: true - losowy, false - parter (domyślnie false)
