Aplikacija je implementirana uz pomoc <b>Java Spring MVC</b> na backend-u a frontend je odradjen pomocu <b>Thymeleaf-a</b>. Podaci su perzistirani u <b>MySQL</b> bazi podataka.
U pitanju je web aplikacija za rad <b>Knjizare</b> koju mogu da koriste prijavljeni i neprijavljeni korisnici.<br>
Medju prijavljene korisnike ubrajamo <b>kupce</b> i <b>administratore</b>.

<hr>

<b>Specifikacija i funkcionalnosti projekta:</b><br/>

Pri registraciji, uloga se ne unosi, a korisnik dobija ulogu običnog korisnika. Administratori se ne
registruju, dodaju se programski (kroz bazu podataka).
Svaka knjiga može da pripada više nego jednom žanru, a svakom žanru pripada jedna ili više
knjiga.
Modelovati klijentsku korpu (shopping cart) kao objekat gde bi se čuvale sve odabrane knjige za
kupovinu. Korisnik prilikom svake kupovine ima mogućnost da kupi jednu ili više knjiga.
Potrebno je voditi računa o broju primeraka knjige koju knjižara poseduje.

<b>Knjige</b><br/>
Na podrazumevanoj, glavnoj stranici aplikacije potrebno je svim korisnicima omogućiti pregled
svih dostupnih knjiga. Ime knjige treba da bude realizovano kao link, gde će korisnik klikom na njega preći na stranicu za
pregled jedne knjige. Prethodno pomenuta stranica za prikaz pojedinačne knjige, treba da prikaže sve podatke o
knjizi. Ukoliko se stranica prikazuje običnom korisniku (kupac) nije potrebno prikazati podatak o
broju preostalih primeraka knjige, dok je sa druge strane potrebno ovo obeležje uz ostale
prikazati administratorima. Takođe, običnim korisnicima na ovoj stranici omogućiti dodavanje
knjige u korpu za kupovinu (shopping cart) sa unosom proizvoljne količine primeraka. Pored ove
opcije, na stranici pojedinačne knjige, omogućiti korisniku i dodavanje knjige u ličnu listu želja
(lista koju korisnik čuva kako bi vodio računa o nekim knjigama koje želi da kupi u budućnosti).
Na stranici za prikaz pojedinačne knjige, administratorima omogućiti izmenu podataka o knjizi,
pri čemu je moguće promeniti sve osim ISBN. 

Na stranici pojedinačne knjige, potrebno je prikazati i postavljene komentare o knjizi.
Administratorima je potrebno omogućiti funkcionalnost za dodavanje nove knjige.
Omogućiti administratorima opciju pretrage knjiga po ISBN-u. Ova pretraga bi trebalo da omogući administratoru da pretraži i knjige
koje nisu na stanju. Nakon izbora pretražene knjige i odlaska na prethodno opisanu stranicu za
prikaz pojedinačne knjige ima opciju ažuriranja podataka o istoj.
Na stranici za prikaz pojedinačne knjige, omogućiti administratoru poručivanje konkretne knjige,
sa definisanjem proizvoljne količine tj. željenim brojem primeraka. Nakon potvrde o poručivanju
knjiga, nova količina se pamti u bazi (podrazumeva se da se naružbina knjiga odmah dešava).


<b>Korisnička korpa (Shopping cart)</b><br/>
Omogućiti korisniku pristup posebnoj stranici za prikaz sadržaja korisničke korpe. Za svaku od
stavki u korpi navesti naziv knjige, autora, količinu primeraka, jediničnu i ukupnu cenu. Pružiti
korisniku mogućnost izbacivanja stavke iz korpe.
Na ovoj stranici korisniku omogućiti i funkcionalnost završetka kupovine, gde će korisnik
izborom akcije (Klikom na dugme) kupiti izabrane proizvode.
Prilikom kupovine, na loyalty kartici se dodaju bodovi. Na svakih potrošenih 1000 dinara korisnik dobija 1 bod. 1 bod donosi popust
od 5%. Korisnik po registraciji ne dobija loyalty karticu, ali može da zatraži njeno kreiranje.

<b>Loyalty kartica</b><br/>
U prethodnom odeljku opisan je način upotrebe loyalty kartice ukoliko je korisnik poseduje.
Ukoliko je ne poseduje, korisnik ima pravo tj. mogućnost slanja zahteva za kreiranje loyalty
kartice. Sa druge strane administrator ima pregled svih zahteva za kreiranje loyalty kartica i ima
mogućnost da ih odobri ili odbije. Kada se zahtev odobri, kreira se kartica za konkretnog
korisnika sa početnih 20% popusta za dobrodošlicu tj. 4 boda. Ukoliko administrator odbije
zahtev, korisnik može ponovo da ga pošalje. Nakon što je zahtev odobren, korisnik može da koristi svoju karticu u skladu sa opisan načinom
u prethodnom odeljku.

<b>Komentari</b><br/>
Obični korisnici (kupci) imaju mogućnost komentarisanja knjiga koje su kupili. Korisniku na
stranici pojedinačne knjige omogućiti opciju komentarisanja (ukoliko je kupio), gde on navodi
komentar u slobodnoj formi i ostavlja ocenu (od 1 do 5), pri čemu su oba polja obavezna (i
ocena i komentar). Kada korisnik ostavi komentar on neće biti javno vidljiv tj. drugi korisnici ga
neće videti, dok god ga admin ne odobri.
Administratorima omogućiti na početnoj stranici link ka upravljanju komentarima, gde se
prikazuju svi komentari koji čekaju da budu odobreni. Administrator može da odobri ili odbije
komentar. Ukoliko ga odobri on će biti javan i vidljiv na stranici konkretne knjige, a ukoliko ga
odbije on neće biti vidljiv ali ga nije potrebno fizički obrisati iz baze i takođe ga nije potrebno
prikazivati sledeći put na stranici komentara koji čekaju odobrenje.

<b>Rad sa korisnicima</b><br/>
Neprijavljeni korisnik može da se registruje na sistem. Na glavnoj stranici je neprijavljenim
korisnicima potrebno omogućiti link ka stranici za registraciju i prijavu. Nakon registracije,
korisnik biva preusmeren na stranicu za prijavu. Prijavljenim korisnicima je na svim
stranicama potrebno omogućiti link ka stranici njihovog profila i link za odjavu.
Administratorima je potrebno omogućiti opciju za upravljanje korisnicima. Na toj stranici
je potrebno prikazati tabelarno sve korisnike sa korisničkim imenom (koje je potrebno prikazati
kao link, gde se klikom na njega prelazi na profil korisnika).
Administratorima je na profilu nekog korisnika, potrebno dozvoliti samo izmenu uloge za nekog
korisnika (omogućiti odabir među ponuđenim vrednostima), kao i mogućnost blokiranja i
deblokiranja korisnika (moguće je blokirati samo obične korisnike, ali ne i administratore).
Blokiran korisnik, nema mogućnosti prijave na sistem. Korisnicima čija se stranica posmatra i administratorima je potrebno prikazati tabelu kupovina
(tog korisnika) sa ukupnom cenom te kupovine i datumom i vremenom kupovine (datum i
vreme treba da bude implementirano kao link ka stranici te konkretne kupovine).
Običnom korisniku na profilu potrebno je prikazati tabelarno i sve što se trenutno nalazi u
njegovoj listi želja, gde se korisniku za svaku od knjiga na listi želja omogućuje da neku od
knjiga ukloni iz liste želja.

<b>Izvestavanje</b><br/>
Administratorima je potrebno omogućiti pristup posebnoj stranici za izveštavanje. Za unet opseg
datuma potrebno je prikazati tabelu svih knjiga koje su kupljene u zadatom periodu i za svaku:
1. naziv knjige (link ka njenoj stranici)
2. ime autora
3. broj prodatih primeraka te knjige
4. ukupnu cenu za tu knjigu (broj primeraka*jedinična cena knjige).

<b>Dvojezicnost aplikacije nije detaljno odradjena, za sada idalje povrsno.</b>

<b>Upiti za kreiranje i popunjavanje svih potrebnih tabela bice okaceni u data.sql fajlu.</b>
