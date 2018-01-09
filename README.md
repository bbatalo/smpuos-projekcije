# SMPUOS-Evidencija i upravljanje projekcija filmova po bioskopima
U ovom projektu realizuje se podsistem za evidenciju i upravljanje projekcijama filmova po bioskopima kao mikroservisna arhitektura. Potrebno je obezbediti sledeće servise kako bi podsistem bio zaokružena celina:
  1. Evidencija korisnika
  2. Evidencija bioskopa
  3. Evidencija filmova
  4. Evidencija i upravljanje projekcijama

Evidencija korisnika vodi računa o pristupu i registraciji na sistem, autorizaciji i administraciji korisnika. Evidencija bioskopa vodi računa o bioskopima, njihovim podacima i projekcionim salama, zauzeću i upravljanju resursa svakog bioskopa, kao i o prezentaciji podataka o svakom bioskopu (na osnovu geolokacije između ostalog). Servis za evidenciju filmova vodi računa o dostupnim filmovima, njihovim podacima, omogućuje korisnicima pregled filmova na različite načine i njihovo ocenjivanje. Servis za evidenciju i upravljanje projekcijama vodi računa o projekcijama filmova, pruža mogućnost izmena podataka o projekcijama kao i pregled projekcija po različitim kriterijumima.

# Za početak
U ovom odeljku opisani su svi softverski preduslovi kako bi projekat podesio na lokalnoj mašini u svrhe daljeg razvoja i testiranja. Za uputstva o podizanju projekta na udaljeni server, pogledati odeljak *Podizanje na server*

## Preduslovi
Potreban softver kako bi se pokrenuli svi servisi:
  - Java 1.8 JDK (ne rizikovati sa drugim verzijama)
  - MongoDB Community Edition 3.6 server
  - NodeJS + npm

## Instalacija
Da bi se konfigurisalo razvojno okruženje za mikroservise, potrebno je sprovesti sledece korake:
  - Preuzeti Java 1.8 JDK sa zvaničnog web sajta i instalirati
  - Preuzeti MongoDB Community Edition 3.6 server sa zvaničnog web sajta i instalirati (može i Linux i Windows)
  - Podesiti db/data folder (kreirati direktorijum u C:/ na Windowsu)
  - Dodati MongoDB u PATH promenljivu, kako bi se omogućio pristup *mongod* i *mongo* procesima iz konzole (bez pozicioniranja)
  
Da bi se pokrenula baza podataka, potrebno je pokrenuti sledeću komandu u komandnoj liniji:
```bash
mongod
```
Da bi se inicijalizovali test podaci, potrebno je pokrenuti sledeću komandu u komandnoj liniji (iz *scripts* direktorijuma u okviru projekta:
```bash
mongo db_scripts.js
```
Svaki servis je implementiran kao zaseban *Maven* projekat, koji je potrebno importovati u razvojno okruženje Spring Tool Suite.
Redosled pokretanja pojedinačnih servisa je sledeći (prva dva moraju u navedenom redosledu, ostali ne):
  1. Eureka
  2. Zuul
  3. UserService
  4. CinemaService
  5. MovieService
  6. ProjekcijeService
  - Baza podataka mora biti pokrenuta kako bi mikroservisi bili u stanju da se povežu na nju

# Pokretanje testova
U ovom odeljku opisan je postupak za pokretanje automatizovanih integracionih i jediničnih testova.

## Integracioni testovi
U izradi...

## Jedinični testovi
U izradi...

# Podizanje na server
U ovom odeljku opisani su koraci potrebni za podizanje aplikacije na udaljeni server i puštanje u produkciju.
Za sada u izradi...

# Alati korišćeni u razvoju
Za modelovanje slučajeva korišćenja i dijagrama klasa:
  - Powerdesigner 15.0
  
Za modelovanje BPMN:
  - Yaoqiang BPMN editor
  
Za implementaciju mikroservisne arhitekure i web aplikacije:
  - Spring Tool Suite 
  - MongoDB Community Edition 3.6
  - Maven
  - Postman
  - MongoDB Compass
  - Robo 3T (nekada RoboMongo)
  - Sublime
  - Visual Studio Code

# Autori
- Dušan Radisavljević
- Nevena Dovičin
- Jovan Ivanović
- Una Banjanin
- Miloš Siđi
- Bojan Batalo
