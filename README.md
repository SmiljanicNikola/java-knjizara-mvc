Application is implemented using <b> Java SPRING MVC </b> on BACKEND and <b> Thymeleaf </b> on FRONTEND. Data are persisted in MySQL. This is Book Store web aplication that can be used by registered and unregistered users. There are two registered types of users: Admins and Customers.
<br/><br/>
<b> Project Specification </b><br/><br/>
When registering, the role is not entered, and the user gets customer role. An administrator is a predefined user who arleady exists in the system.
Each book can belong to more than one genre, and each genre belongs to one or more books.
Model a shopping cart as an object where all selected books for
shopping should be stored. The user has the opportunity to buy one or more books with each purchase.
It is necessary to pay attention to the number of copies of the book that the bookstore owns.

<b>Knjige</b><br/>
By default, the main page of the application needs to allow all users to view all available books. The name of the book should be realized as a link, where the user will click on it to go to the page for reviewing one book. The previously mentioned page for displaying an individual book should show all the information about the book. If the page is displayed to an ordinary user (customer), it is not necessary to display information on the number of remaining copies of the book, while on the other hand, this feature should be shown to administrators.
Also on this page, customers should be able to add books in the shopping cart and specify how many books they want to buy. In addition to this option, on the individual book page, allow the user to add the book to the personal wish list (a list that the user keeps to keep track of some books he wants to buy in the future).
Allow administrators to modify book information, making it possible to change anything but the ISBN.

On the page of an individual book, comments about this book should be displayed. Administrators should be able to add a new book.
Enable administrators to search for books by ISBN. This search should allow the administrator to search for books which are not in stock(Remaining quantity is 0). After selecting the searched book and going to the previously described page to view an individual book, admin should be able to update all attributes beside ISBN.
On the same page, admin should be able to order a specific book and he needs to enter desired number of copies. After the confirmation of ordering books, the new quantity is stored in the database (it is assumed that the order of books happens immediately).

<b> Shopping cart </b><br/>
Allow the user to access a special page so he can see content of his Shopping cart. For every book in the basket, display name of the book, the author, the number of copies, the unit and the total price. Give the user the ability to delete book from cart. On this page, the user will also be provided with the functionality of completing the purchase, where the user will select the action (Click on the button) to purchase the selected books.
When buying, points are added to the loyalty card. On every spent 1000 dinars, the user receives 1 point. 1 point brings a discount
of 5%. The user does not receive a loyalty card after registration, but can request its creation.

<b> Loyalty Card </b><br/>
The previous section describes how to use a loyalty card if the user has one. If he does not own it, the user can submit request to create a loyalty card. On the other hand, the administrator can see all requests and he can approve or reject them. When the request is approved, a card is created for a specific user with an initial 20% discount and 4 points. If the administrator decline request, the user can resubmit it. After the request is approved, the user can use his card in accordance with the method described in the previous section.

<b> Comments </b><br/>
Customers should have option to leave comments on specific book. He needs to enter comment and rating (1 to 5), with both fields required. When the customer leaves a comment, he will not be publicly visible, ie. other users will not see it as long as admin does not approve his comment. Provide administrators link to comment management on the homepage, which displays all comments waiting to be approved. The administrator can approve or reject comment. If admin approve, it will be public and visible on the page of a specific book, and if he reject it, it will not be visible, but it does not need to be physically deleted from the database and it does not need to be displayed next time when he enters comment management page.

<b> Features with Users </b><br/>
Not logged in user can register on system. On home page they should have links that redirects to register and login pages. After registration, user is being redirected to login page. Logged in users have links towards their profile page and they can logout. Admins has feature for user management. When it comes to user management, admin should see all registered users displayed with their personal data including their username(Which needs to serve as link, where if username is clicked, it leads admin on that user's profile.
Admin can update user's attributes and he can block/unblock. Blocked users do not have the ability to log in and use the application.
Users whose page is viewed and admins can see table of purchases of that user with the total price of that purchase and the date and time of purchase (date and time should be implemented as a link to the page of that specific purchase). Regular customers on their own profiles should see their own wish list, where customer can also remove unwanted books from list.

<b> Reports </b><br/>
Admins need to have access to a separate reporting page. For the entered range of dates, it is necessary to display table of all the books sold in that specific period. For each book it should display:
1. Their name
2. Author name
3. Number of copies sold 
4. Final price (quantity*unit price)

<b>The bilingualism of the application has not been implemented in detail, for now it is still superficial.</b>

<b>SQL Queries for creating and populating entity tables will be published inside data.sql file</b>

<hr><br/>
<b>Specifikacija na srpskom jeziku:</b>
<br/><br/>

Aplikacija je implementirana uz pomoc <b>Java Spring MVC</b> na backend-u a frontend je odradjen pomocu <b>Thymeleaf-a</b>. Podaci su perzistirani u <b>MySQL</b> bazi podataka.
U pitanju je web aplikacija za rad <b>Knjizare</b> koju mogu da koriste prijavljeni i neprijavljeni korisnici.<br>
Medju prijavljene korisnike ubrajamo <b>kupce</b> i <b>administratore</b>.


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
