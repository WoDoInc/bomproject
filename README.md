You can run the application by using the following command:

    mvn clean spring-boot:run

You can also package the application by using the follow command:

    mvn clean package
    
Both create an executable JAR. (Some slight changes are needed to create a deployable WAR instead).

Unit tests will be executed for both cases, however they can be run independently if needed. Unit tests were created to validate the different components of the application - model, controllers, and services.

To use live data, create a mongodb instance (the default 'test' database will suffice). Use mongoimport to import the respective parts CSVs.

    mongoimport -d test -c part --type csv --file Capacitors.csv --headerline
    mongoimport -d test -c part --type csv --file Diodes.csv --headerline
    mongoimport -d test -c part --type csv --file Resistors.csv --headerline

Why MongoDB? The project database of choice uses a noSQL schema to serve up versioned documents. Unlike a traditional database, where joins would be needed between BillsOfMaterials and Parts (pending Parts was not split into three categorical tables as in the CSVs - capacitors, diodes, resistors), we can consolidate the information into an easily accessible collection as a lookup. Also, the schema can evolve quite rapidly, allowing us to extend our database as new information is needed.

Two options are available at this point, either simply embedding the collection within the bill of materials object, as seen below, or going one step further and doing the join on the client-side. Basically, off-loading the join on the client distributes an expensive join from the server to the client - which is great if there are say, millions of clients using this API and if more data joining was needed.

In the end, we have a well-scaling, flexible database. AngularJS would be a great additional example for the client-side view and controller (and user contexts tied to Spring Security), plus the Twitter Bootstrap for styling, but I was not able to finish that portion of the project in time.

The Restful API uses JSON as the structure for the data, with POJOs as the intermediary objects and DTOs.

The bill of materials data is structured as follows. id is the guaranteed unique primary key for the object, on the front-end we would prepend "BOM" to the ID after fetching the document we seek. Alternatively we could enforce another unique identifier as separate field. This is why we do not have a "BOM1234" key explicitly created.
{
    "_id": ObjectId("..."),
    "description": "A Description",
    "partslist": [{
        "count": "42",
        "part": ObjectId("3f56309f7625576ade5a1500")
    }]
}

Each part is its own collection:
{
    "_id": ObjectId("..."),
    "type": "Capacitor",
    "datasheet": "http://industrial.panasonic.com/www-cgi/jvcr13pz.cgi?E+PZ+3+ABA0012+ECA1HM100I+7+WW",
    ...
}

The API can be accessed on http://localhost:8080

    http://localhost:8080/api/billofmaterials -> (CRUD)
    http://localhost:8080/api/partslist -> (CRUD)
    http://localhost:8080/api/part -> (read only)
    
Your restful client of choice should work, or curl if you prefer a CLI.

    curl -v http://localhost:8080/billofmaterials
    curl -v -H "Accept: application/json" -X POST -d {"description":"a descrption","parts":[{"count":"42","part":"..."}]} http://localhost:8080/api/billofmaterials

Objects and collections can also be referenced within the database via mongo's command line interface.

    db.part.find({ "_id":ObjectId("5591db46301fb0df082fc6e4")})
    
    >{ "_id" : ObjectId("5591db46301fb0df082fc6e4"), "type" : "Capacitor", "datasheets" : "http://industrial.panasonic.com/www-data/pdf/ABA0000/ABA0000CE2.pdf", "image" : "http://media.digikey.com/photos/Panasonic%20Photos/VS%20SERIES%206.5H,5.0D,HA%20SERIES%205.5H,5.0D.jpg", "corporatePartNumber" : "PCE3890TR-ND", "manufacturerPartNumber" : "EEE-1CA470WR", "manufacturer" : "Panasonic Electronic Components", "description" : "CAP ALUM 47UF 20% 16V SMD", "unitPrice" : 0.07733, "packaging" : "Tape & Reel (TR)", "series" : "S", "capacitance" : "47µF", "tolerance" : "±20%", "voltageRating" : "16V", "lifetimeTemp" : "1000 Hrs @ 85°C", "operatingTemperature" : "-40°C ~ 85°C", "capacitorType" : "-", "applications" : "Automotive", "ripple current" : "39mA", "impedance" : "-", "sizeDimension" : "0.197\" Dia (5.00mm)", "heightSeated" : "0.213\" (5.40mm)", "surfaceMountLandSize" : "0.209\" L x 0.209\" W (5.30mm x 5.30mm)", "mountingType" : "Surface Mount", "packageCase" : "Radial, Can - SMD" }