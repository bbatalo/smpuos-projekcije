var insertDefaultDataProjekcije = function(db){
    db.projekcije.insert([
        {
            "date" : ISODate("2018-01-10T00:10:00.000Z"),
            "type" : "PREMIERE",
            "status" : "INACTIVE",
            "cinemaId" : "1",
			"cinemaName" : "Cinema SRB",
            "hallId" : "11",
			"hallLabel" : "Sala11",
            "movieId" : "100",
			"movieName" : "Биће боље - aб"
		},
        {
            "date" : ISODate("2018-01-13T13:00:00.000Z"),
            "type" : "PREMIERE",
            "status" : "ACTIVE",
            "cinemaId" : "2",
			"cinemaName" : "Cinema SRB",
            "hallId" : "22",
			"hallLabel" : "Sala22",
            "movieId" : "200",
			"movieName" : "Brže, jače, bolje 4"
        },
        {
			"date" : ISODate("2018-01-15T15:00:00.000Z"),
            "type" : "REGULAR",
            "status" : "ACTIVE",
            "cinemaId" : "5a5169492e1dcff9ec8f1b70",
			"cinemaName" : "Arena",
            "hallId" : "5a5169492e1dcff9ec8f1b6c",
			"hallLabel" : "KLASIKANERSKA",
            "movieId" : "5a5169492e1dcff9ec8f1b68",
			"movieName" : "The Notebook"
        }		
    ]);

};
