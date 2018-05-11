# Arkkitehtuurikuvaus

## Rakenne

Ohjelmalla on kolme pakkausta. Yksi on omistettu kaikille luokille jotka muodostavat käyttöliittymät. Yksi luokille jotka toimivat meidän databasen kanssa. Sekä yksi missä on kaikki pelin toimivuudelle oleelliset luokat.

## Käyttöliittymä

Meillä on yhteensä 5 eri käyttöliittymänäkymää. Ensimmäisenä on käyttöliittymä missä on valikko josta pääsee peliin sekä katsomaan näppäimet ja highscoret. Seuraavana meillä on ikkunat highscore ja controls, missä on vain tekstiä joka kertoo käyttäjälle ketkä ovat valloittaneet ranking listat ja mitä näppäimiä peli käyttää. 

Sitten meillä on meidän tärkein käyttöliittymä, tetrispeli. Kun peli kuitenkin joskus päättyy avautuu meidän viimeinen ikkuna, josta pelaaja voi tallettaa pisteensä tietokantaan.

## Luokka/pakkauskaavio

<img src="https://github.com/willmana/otm-harjoitusty-/blob/master/dokumentointi/Images/32260495_10209006580322326_2236448638496669696_n.jpg">

## Toiminnallisuus

Esimerkki kuinka yksi pala löytää tiensä syntymisestä ruudun pohjalle.
Ensin luodaan pala kutsumalla komentoa newPiece(). Tämä metodi hakee luokasta Shape komennot setRandomShape() joka käyttää saman luokan komentoa setShape() kun on generoinut randomilla minkä palan luo. Sen jälkeen peli kutsuu metodia actionPerformed() joka katsoo onko pala alhaalla. Koska nyt meillä on uusi pala niin siirtää tämä metodi palaa alaspäin käyttämällä luokan Board oneLineDown() metodia. Peli toistaa tätä kunnes pala kohtaa pohjan. Sen jälkeen kun peli kutsuu metodia actionPerformed() ja sen sisällä metodia oneLineDown() tutkii oneLineDown() metodilla tryMove että liikkuuko pala enään minnekkään. Jos ei, niin silloin ilmoitetaan pieceDropped() joka poistaa täydet rivit ja muodostaa uuden palan.

## Ohjelman heikkouksia

Huomasin viimeisen pushin jälkeen että tetrispelin ikkuna ei sulkeudukkaan kun peli päättyy ja submit ruutu aukeaa. Valitettavasti en kerennyt töihin lähdön takia debuggaamaan tätä virhettä. Ohjelman olisi voinut tehdä tietenkin käyttäen vähän nykyaikaisempaa JavaFX:ää mutta päätin käyttää tähän swingiä koska sen yksinkertaisuus viehätti. Ohjelman melkein kaikki päätoiminnallisuudet on ohjelmoitu yhteen luokkaan joka on... aika iso tästä syystä. Jälkeenpäin hieman kadutti etten pilkkonut toiminnallisuuksia useampaan luokkaan.

