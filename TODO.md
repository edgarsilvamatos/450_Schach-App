# TODO – Projekt „abgeändertes Schach“ (TDD)

## Projekt & Infrastruktur

- [x] Privates Git-Repository erstellen  
- [x] Herrmann als Collaborator hinzufügen  
- [x] Java-/Build-Umgebung einrichten (JDK, Build-Tool)  
- [x] CI/CD-Pipeline einrichten 
- [x] CI so konfigurieren, dass Build **on push** und **on pull request** läuft  
- [x] JaCoCo ins Projekt einbinden  
- [x] JaCoCo im CI-Build konfigurieren  
- [x] JaCoCo-Report als CI-Artefakt hochladen  
- [x] `TODO.md` (oder `TODO.txt`) im Root-Verzeichnis anlegen  

## Prozess / Arbeitsweise

- [x] TDD-Workflow festlegen und konsequent anwenden (Rot–Grün–Refactor)  
- [x] Regeln für Pflege des TODO-Files definieren  
  - [x] TODO-File nie komplett leer (ausser ganz am Ende)  
- [ ] Commit-Regel: **mindestens ein Commit pro Stunde**  
- [ ] CI-Regel: Build **mindestens einmal pro Doppelstunde** (idealerweise pro Feature)  
- [x] Branch-Strategie definieren (zB Feature-Branches, `main` baut immer)  

## Domäne & Grundarchitektur

- [x] Repräsentation des Spielzustands (Spieler am Zug, Status Schach, ...)  
- [x] Schnittstelle für **Eingabe von Zügen** definieren  
- [x] Schnittstelle für **Ausgabe des Spielstands** definieren (Textboard mit Unicode-Symbolen)  

## Regelanpassungen modellieren

- [x] Startaufstellung für 10x10-Feld definieren (links-rechts-zentriert, oben/unten am Rand)  

### Bewegungsregeln

- [x] König: normale König-Bewegung  
- [x] Lover: wie der König
- [x] Dame: wie normal, explizit bis zu 10 Felder in alle Richtungen  
- [x] Läufer: max. **6 Felder** diagonal pro Zug  
- [x] Springer: neu **3 vorwärts / 1 zur Seite** (z.B. A1 -> B4)  
- [x] Turm: normale Turm-Bewegung inkl. zusätzlichem Turm  
- [x] Bauern: normale Bauernregeln (Startzug/Sonderregeln festlegen)  

## Muss-Features (mit TDD implementieren)

- [x] **Spielsetup (neues Spiel)**  
  - [x] Tests für initialen Zustand  
  - [x] Implementierung der Startaufstellung  
  - [x] Lover implementieren

- [x] **Spielstatus tracken/anzeigen**  
  - [x] Textuelle Darstellung des Bretts  
  - [x] Anzeige, welche Farbe am Zug ist  

- [x] **Eingabe für Spielzug**  
  - [x] Eingabeformat definieren (mit Schachnotation)  
  - [x] Parser mit Tests implementieren  

- [x] **Überprüfung, ob ein Spielzug gültig ist**  
  - [x] Gültigkeitsprüfung pro Figurentyp  
  - [x] Brettgrenzen (10x10) prüfen  
  - [x] Eigene Figuren nicht schlagen  
  - [x] Züge verbieten, die eigenen König ins Schach setzen  

- [x] **Automatische Schach-Erkennung**  
  - [x] Bedrohung des Königs erkennen (Lover auslassen!)
  - [x] Tests für verschiedene Schach-Szenarien  

- [x] **Entfernung geschlagener Figuren**  
  - [x] Beim gültigen Zug gegnerische Figuren entfernen  
  - [x] Liste/Status der geschlagenen Figuren aktualisieren  

## Tests & Qualität

- [x] Teststruktur planen (Unit-, Integrations-, End-to-End-Tests)  
- [x] Parametrisierte Tests für wiederkehrende Muster (zB Bewegungen)  
- [x] Regelmässiges Refactoring einplanen  
- [x] JaCoCo-Report kontrollieren und **>= 80% Line- und Branch-Coverage** erreichen  
- [x] Clean Code sicherstellen (auch in Tests)  

## Orga & Abgabe

- [x] Sicherstellen, dass Projekt bis Freitag **2026-01-16 18:00** im Git-Repository vorliegt  
