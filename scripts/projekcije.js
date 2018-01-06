var insertDefaultDataProjekcije = function(db){
    db.projekcije.insert([
        {
            "date" : ISODate("1991-01-01T00:00:00.000Z"),
            "type": "REGULAR",
            "status": "ACTIVE",
            "cinemaId": 1,
            "hallId": 1,
            "movieId": 1
		},
        {
            "date" : ISODate("1991-01-01T00:00:00.000Z"),
            "type": "PREMIERE",
            "status": "ACTIVE",
            "cinemaId": 2,
            "hallId": 1,
            "movieId": 2
        }
    ]);

};
