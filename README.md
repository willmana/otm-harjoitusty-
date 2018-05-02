# Tetris

Kaikille tuttu tetris peli kurssille **Ohjelmistotekniikan menetelmät**.

## Tämänhetkinen toiminnallisuus
Peliä itsessään pystyy jo pelaamaan. Pelin nopeus kiihtyy aina joka neljännen rivin katoamisen jälkeen. Pelistä löytyy päävalikko, mistä voi katsoa mitä näppäimiä voi käyttää pelissä ja pääsee itse peliin. Database on kohta tulossa jolloin saadaan myös Highscore -napillekkin jotain toimintaa.

## Dokumentaatio
- [Suunnitelma](https://github.com/willmana/otm-harjoitusty-/blob/master/dokumentointi/m%C3%A4%C3%A4rittelydokumentti.md)
- [Aikakirjanpito](https://github.com/willmana/otm-harjoitusty-/blob/master/dokumentointi/Ty%C3%B6aikakirjanpito.md)
- [Arkkitehtuuri](https://github.com/willmana/otm-harjoitusty-/blob/master/dokumentointi/arkkitehtuuri.md)
- [Release](https://github.com/willmana/otm-harjoitusty-/releases/tag/viikko5)


## Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```
Jar- tiedoston saa komennolla 

```
mvn package
```

Checkstylen voi generoida komennolla 

```
mvn jxr:jxr checkstyle:checkstyle
```
