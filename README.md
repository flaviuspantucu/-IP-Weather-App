# WeatherApp :card_index_dividers:

## Content
- [Descriere aplicație](#Introducere)
- [Configuratie](#Config)
- [Limbaj de programare](#Limbaj)
- [Realizator](#Realizator)


## Introducere
Acest proiect conține o aplicație ce se ocupă aflarea informațiilor despre vremea dintr-un oraș. Aplicația conține două liste: lista cu țările disponibile căutării și lista cu orașele disponibile căutării din țara respectiv aleasă. Rezultatul va fi afișat in aplicație și va consta în informații legate de vreme în orașul ales în ziua curentă.


## Parametrii
[X] Country - reprezintă țara in care vom cauta informații legate de vremea unui oraș
[X] City - reprezintă orașul pe care îl vom cauta

## Config
Aplicația va conține un fișier de configurare care conține pe fiecare linie următoarele:
[X] ID - reprezintă ID-ul orașului
[X] nm - reprezintă numele orașului 
[X] lat & lon - reprezintă coordonatele geografice ale orașului
[X] countryCode - reprezintă numele țarii in format prescurtat

Config.cfg

```json
ID				nm			lat				lon			countryCode
819827	        Razvilka	55.591667       37.740833	RU
524901	        Moscow	    55.752220       37.615555	RU
2973393	        Tarascon	43.805828       4.660280	FR
2986678	        Ploufragan	48.491409       -2.794580	FR
```

## Limbaj
:memo: Java -> SDK 15

## Echipa

:man: Panțucu Flavius-Marian
<br />
:school: Academia Tehnică Militară "Ferdinand I", București, România