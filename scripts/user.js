var insertDefaultDataUser = function(db){
    db.user.insert([
        {
            "firstName" : "Test",
            "lastName": "Test",
            "username": "admin",
            "password": "admin",
            "dateOfRegistration": ISODate("1991-01-01T00:00:00.000Z"),
            "dateOfBirth": ISODate("1991-01-01T00:00:00.000Z"),
            "gender": "OTHER",
            "location": {
                "type": "Point",
                "coordinates": [45.0, 22.0]
            },
            "status": "ACTIVE",
            "type": "ADMINISTRATOR"
        },
        {
            "firstName" : "Test",
            "lastName": "Test",
            "username": "regular",
            "password": "regular",
            "dateOfRegistration": ISODate("1991-01-01T00:00:00.000Z"),
            "dateOfBirth": ISODate("1991-01-01T00:00:00.000Z"),
            "gender": "OTHER",
            "location": {
                "type": "Point",
                "coordinates": [-45.0, -22.0]
            },
            "status": "ACTIVE",
            "type": "REGISTERED"
        }
    ]);

};
