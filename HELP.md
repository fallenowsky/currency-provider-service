1 currency-provider
- aplikacja ma za zadanie cyklicznie (co 5 minut) pobierać informacje
  na temat kursów walut z zewnętrznego api (przykładowo NBP).
- po pobraniu informacji o kursach kupna i sprzedaży każdej waluty
  przekazuje na kolejkę (przykładowo RabbitMQ) wiadomości z danymi
  dla każdej waluty oddzielnie.

2 currency-persistance-service
- aplikacja ma zbierać z kolejki wiadomości z informacjami na temat
  kursów walut i zapisywać je do bazy danych.
- baza danych ma przechowywać tylko najbardziej aktualną informację
  o kursie kupna i sprzedaży dla danej waluty.

3 exchange-api
- aplikacja ma wystawiać endpointy, które pozwalają na uzyskanie
  informacji takich jak
    - wszystkie dostępne waluty (brak autentykacji)
    - informacje o kursach wskazanej waluty (brak autentykacji)
    - wymiana walut (wskazanie "z", "do", "kwota") (admin)
    - wymiana walut z potwierdzeniem (każdy użytkownik)
- aplikacja ma używać security i weryfikować użytkowników zgodnie
  z powyższą rozspiską usług.
- wysyłka na maila ma się odbywać na adres przypisany do użytkownika
  wysyłającego zapytanie i ma być wywoływana eventem.

4 mail-service
- ma przyjmować informację o wymianie walut i adresie użytkownika,
  oraz budować i wysyłać wiadomość mailową na wskazany adres.

docelowo wszystkie komponenety razem z kolejkami i bazą mają być
możliwe do uruchomienia przez wywołanie z docker-compose