
# Zgłoszenie usterki

## Opis aplikacji
Aplikacja umożliwia zgłaszanie usterek za pomocą formularza.
Składa się z dwóch ekranów: formularza oraz podsumowania zgłoszenia.

## Ekran 1 – Formularz
Użytkownik może:
- Wybrać kategorię usterki.
- Wprowadzić opis problemu.
- Wybrać priorytet.
- Zaznaczyć, czy usterka jest pilna.

## Ekran 2 – Podsumowanie
Aplikacja wyświetla wprowadzone dane.
Użytkownik może:
- Potwierdzić zgłoszenie.
- Anulować zgłoszenie.
- Udostępnić zgłoszenie innym aplikacjom.

## Zastosowane mechanizmy
- Intent – przekazywanie danych między ekranami.
- ActivityResultLauncher – odbieranie wyników.
- public static final – stałe klucze danych.
- Walidacja formularza.
- Niejawna intencja ACTION_SEND.
- ActivityNotFoundException – obsługa braku aplikacji.
- parentActivityName – powiązanie ekranów.

## Przekazywane dane
1. category – kategoria (String).
2. description – opis (String).
3. priority – priorytet (int).
4. urgent – pilność (boolean).
5. date – data zgłoszenia (long).

## Wyniki działania
- RESULT_OK – zgłoszenie potwierdzone.
- RESULT_CANCELED – rezygnacja ze zgłoszenia.

## Technologie
- Java
- Android Studio
- Android SDK
