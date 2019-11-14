# Quarkus JPA by Philipp Auinger
Erläuterung: Ein Supermarkt wird realisiert.

### Struktur:
* Cashier - Kassier
* Customer - Kunde der ein Produkt kaufen kann
* Product - Produkt
* Activity - Aktivität (Verbindungstabelle zwischen Cashier, Product, Customer)
* Storage - Lager (mehrere Produkte die abgespeichert werden, Lagerort wird abgespeichert)

## Docker Command (DB)
    docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=passme -d postgres --rm

## Supermarket CLD
![CLD](quarkusJPA/CLD.png)     

## Supermarket ERD
![ERD](quarkusJPA/ERD.png)
