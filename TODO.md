# TODO – Projekt „abgeändertes Schach“ (TDD)

## Projekt & Infrastruktur

- [x] Privates Git-Repository erstellen  
- [x] Herrmann als Collaborator hinzufügen  
- [x] Java-/Build-Umgebung einrichten (JDK, Build-Tool)  
- [x] CI/CD-Pipeline einrichten 
- [x] CI so konfigurieren, dass Build **on push** und **on pull request** läuft  
- [ ] JaCoCo ins Projekt einbinden  
- [ ] JaCoCo im CI-Build konfigurieren  
- [ ] JaCoCo-Report als CI-Artefakt hochladen  
- [x] `TODO.md` (oder `TODO.txt`) im Root-Verzeichnis anlegen  

## Prozess / Arbeitsweise

- [ ] TDD-Workflow festlegen und konsequent anwenden (Rot–Grün–Refactor)  
- [ ] Regeln für Pflege des TODO-Files definieren  
  - [ ] Aktuelles Feature immer zuoberst  
  - [x] TODO-File nie komplett leer (ausser ganz am Ende)  
- [ ] Commit-Regel: **mindestens ein Commit pro Stunde**  
- [ ] CI-Regel: Build **mindestens einmal pro Doppelstunde** (idealerweise pro Feature)  
- [x] Branch-Strategie definieren (zB Feature-Branches, `main` baut immer)  

## Domäne & Grundarchitektur
 
- [ ] Repräsentation des Spielzustands (Spieler am Zug, Status Schach, ...)  
- [ ] Schnittstelle für **Eingabe von Zügen** definieren  
- [ ] Schnittstelle für **Ausgabe des Spielstands** definieren (Textboard mit Unicode-Symbolen)  

## Regelanpassungen modellieren

- [ ] Startaufstellung für 10x10-Feld definieren (links-rechts-zentriert, oben/unten am Rand)  

### Bewegungsregeln

- [ ] König: normale König-Bewegung  
- [ ] Lover: wie der König
- [ ] Dame: wie normal, explizit bis zu 10 Felder in alle Richtungen  
- [ ] Läufer: max. **6 Felder** diagonal pro Zug  
- [ ] Springer: neu **3 vorwärts / 1 zur Seite** (z.B. A1 -> B4)  
- [ ] Turm: normale Turm-Bewegung inkl. zusätzlichem Turm  
- [ ] Bauern: normale Bauernregeln (Startzug/Sonderregeln festlegen)  

## Muss-Features (mit TDD implementieren)

- [ ] **Spielsetup (neues Spiel)**  
  - [ ] Tests für initialen Zustand  
  - [ ] Implementierung der Startaufstellung  
  - [ ] Lover implementieren

- [ ] **Spielstatus tracken/anzeigen**  
  - [ ] Textuelle Darstellung des Bretts  
  - [ ] Anzeige, welche Farbe am Zug ist  

- [ ] **Eingabe für Spielzug**  
  - [ ] Eingabeformat definieren (mit Schachnotation)  
  - [ ] Parser mit Tests implementieren  

- [ ] **Überprüfung, ob ein Spielzug gültig ist**  
  - [ ] Gültigkeitsprüfung pro Figurentyp  
  - [ ] Brettgrenzen (10x10) prüfen  
  - [ ] Eigene Figuren nicht schlagen  
  - [ ] Züge verbieten, die eigenen König ins Schach setzen  

- [ ] **Automatische Schach-Erkennung**  
  - [ ] Bedrohung des Königs erkennen (Lover auslassen!)
  - [ ] Tests für verschiedene Schach-Szenarien  

- [ ] **Entfernung geschlagener Figuren**  
  - [ ] Beim gültigen Zug gegnerische Figuren entfernen  
  - [ ] Liste/Status der geschlagenen Figuren aktualisieren  

## Tests & Qualität

- [ ] Teststruktur planen (Unit-, Integrations-, End-to-End-Tests)  
- [ ] Parametrisierte Tests für wiederkehrende Muster (zB Bewegungen)  
- [ ] Regelmässiges Refactoring einplanen  
- [ ] JaCoCo-Report kontrollieren und **>= 80% Line- und Branch-Coverage** erreichen  
- [ ] Clean Code sicherstellen (auch in Tests)  

## Orga & Abgabe

- [ ] Anforderungen und Bewertungskriterien regelmässig prüfen  
- [ ] Finalen Stand vor Abgabe taggen (zB `v1.0`)  
- [ ] Sicherstellen, dass Projekt bis Freitag **2026-01-16 18:00** im Git-Repository vorliegt  
