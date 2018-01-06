var insertDefaultDataCinema = function(db){
    db.cinema.insert([
        {
            "name": "Arena",
            "address": {
                "address": "Novi Sad, Srbija",
                "location": {
                    "type": "Point",
                    "coordinates": [45.0, 22.0]
                }
            },
            "halls": [
                {
                    _id: ObjectId(),
                    "label": "KLASIKANERSKA",
                    "capacity": 100,
                    "rowCount": 10,
                    "colCount": 10,
                    "type": "ORDINARY"
                },
                {
                    _id: ObjectId(),
                    "label": "ELITISTICKA",
                    "capacity": 50,
                    "rowCount": 10,
                    "colCount": 5,
                    "type": "TD"
                }
            ]
        },
        {
            "name": "CinCity",
            "address": {
                "address": "Novi Sad, Srbija",
                "location": {
                    "type": "Point",
                    "coordinates": [44.8, 22.0]
                }
            },
            "halls": [
                {
                    _id: ObjectId(),
                    "label": "KLASIKANERSKA",
                    "capacity": 100,
                    "rowCount": 10,
                    "colCount": 10,
                    "type": "ORDINARY"
                },
                {
                    _id: ObjectId(),
                    "label": "ELITISTICKA",
                    "capacity": 50,
                    "rowCount": 10,
                    "colCount": 5,
                    "type": "TD"
                }
            ]
        }
    ]);
};
