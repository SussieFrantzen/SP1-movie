## Analyse af Opgaven:
Jeg skal finde ud af hvilke entiteter der skal bruges:
Her ser jeg i opgave formuleringen - Backend'en skal kunne gemme og hente information om film, genrer, skuespillere og instruktører.
Dette føre mig frem til 4 entiteter:
<br>
•	Actors - name, id og movie database Id
<br>
•	Director - name, id og movie database Id
<br>
•	Genre - name, id og movie database Id
<br>
•	Movie - titlen, udgivelsesdatoen, gennemsnitlige vurdering

### Metoder der kræves:
Data skal gemmes på lokal database.
<br>
Udvælg danske film fra di sidste 5 år
<br>
Vis liste fra database
<br>
Vis liste over skuespiller og instruktør fra databaselisten.
<br>
Vis genre liste
<br>
Vis liste af film med en bestemt genre
<br>
CRUD metoder
<br>
Søg efter film ved titel - skal kunne tage imod store og små bogstaver.
<br>
Samlede gennemsnitlige vurdering af alle film i databasen, de 10 lavest og højest bedømte film og de 10 mest populære film.

#### Tanker:
Ud fra dette kan jeg se der skal bruges flere klasser
Jeg skal kommunikere til database det bruger vi Hibernate klasserne til.
Men for at kommunikere til dem skal jeg bruge DAO klasser

De 4 entiteter skal have en DAO hver her tænker jeg det kan være given at bruge interface klasser:
<br>
•	ActorsDAO
<br>
•	DirectorDAO
<br>
•	GenreDAO
<br>
•	MovieDAO

Men de fleste skal jo have de samme metoder i sig:
<br>
•	Create
<br>
•	findById
<br>
•	List
<br>
•	Update
<br>
•	Remove

Så jeg tænker her kunne være et godt sted at bruge Generisk metode og lade de andre extends en hoved DAO der bruger Generiske metoder.
Men jeg skal så have klasser der bruger vores interface så jeg skal have en række klasser jeg kalder for:
<br>
•	ActorsDAOImpl
<br>
•	DirectorDAOImpl
<br>
•	GenreDAOImpl
<br>
•	MovieDAOImpl
<br>

Da de implementer kontrakten for interface og jeg så kan lave metoderne derinde fra.
Denne sammenkædning med en DAO interface og de fire andre Interface gør at jeg kan tildele dem de fælles de bære 1 sted og tilføje de ekstra der tilhøre dem hver især.

Men jeg skal også have et transportlag, så jeg skal bruge 4 DTO’er
<br>
•	ActorsDTO
<br>
•	DirectorDTO
<br>
•	GenreDTO
<br>
•	MovieDTO

Vi skal så have et lag der håndtere om omdanner til og fra DTO så her vælger jeg at kalde det Service.
Igen de fire entiteter men jeg tænker jeg får brug for 1 mere her. Da jeg mangler noget der snakker med api’en i forhold til hente film.
<br>
•	ActorsService
<br>
•	DirectorService
<br>
•	GenreService
<br>
•	MovieService
<br>
•	MdbImportService

Til sidste skal der også være et sted der modtager resultaterne eller anvender dem
<br>
•	MdbClient

Dette giver mig et overblik til at få et klassediagram skabt til at kunne arbejde ud fra.




