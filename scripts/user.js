var insertDefaultDataUser = function(db){
    db.user.insert([
        {
            "firstName" : "Test",
            "lastName": "Test",
            "userName": "admin",
            "password": "admin",
            "dateOfRegistration": ISODate("1991-01-01T00:00:00.000Z"),
            "dateOfBirth": ISODate("1991-01-01T00:00:00.000Z"),
            "userType": "ADMINISTRATOR",
            "userLocation": {
                "type": "Point",
                "coordinates": [45.0, 22.0]
            }
        }
    ]);

};
