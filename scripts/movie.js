var insertDefaultDataMovie = function(db){
    db.movie.insert([
        {
            "name" : "The Notebook",
            "description": "A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom, but they are soon separated because of their social differences.",
            "length": 123,
            "director": {
						"firstName": "Nick",
						"lastName": "Cassavetes"
			},
            "actors": [
			{
				"firstName": "Tim",
				"lastName": "Ivey"
			},
			{
				"firstName": "Gena",
				"lastName": "Rowlands"
			},
			{
				"firstName": "Starletta",
				"lastName": "DuPois"
			},
			{
				"firstName": "James",
				"lastName": "Garner"
			},
			{
				"firstName": "Anthony-Michael Q.",
				"lastName": "Thomas"
			},
			{
				"firstName": "Ed",
				"lastName": "Grady"
			},
			{
				"firstName": "Renee",
				"lastName": "Amber"
			},
			{
				"firstName": "Jennifer",
				"lastName": "Echols"
			},
			{
				"firstName": "Geoffrey",
				"lastName": "Knight"
			},
			{
				"firstName": "Kevin",
				"lastName": "Connolly"
			},
			{
				"firstName": "Ryan",
				"lastName": "Gosling"
			},
			{
				"firstName": "Heather",
				"lastName": "Wahlquist"
			},
			{
				"firstName": "Rachel",
				"lastName": "McAdams"
			},
			{
				"firstName": "Andrew",
				"lastName": "Schaff"
			},
			{
				"firstName": "Matt",
				"lastName": "Shelly"
			}
			],
            "category": "ROMANCE",
            "ratings": [],
            "ratingAvg": 0,
            "premiere": ISODate("2004-06-25")
        },
        {
            "name" : "The Silence of the Lambs",
            "description": "A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.",
            "length": 118,
            "director": {
						"firstName": "Jonathan",
						"lastName": "Demme"
			},
            "actors": [
			{
				"firstName": "Jodie",
				"lastName": "Foster"
			},
			{
				"firstName": "Lawrence A.",
				"lastName": "Bonney"
			},
			{
				"firstName": "Kasi",
				"lastName": "Lemmons"
			},
			{
				"firstName": "Lawrence T.",
				"lastName": "Wrentz"
			},
			{
				"firstName": "Scott",
				"lastName": "Glenn"
			},
			{
				"firstName": "Anthony",
				"lastName": "Heald"
			},
			{
				"firstName": "Frankie",
				"lastName": "Faison"
			},
			{
				"firstName": "Don",
				"lastName": "Brockett"
			},
			{
				"firstName": "Frank Jr.",
				"lastName": "Seals"
			},
			{
				"firstName": "Stuart",
				"lastName": "Rudin"
			},
			{
				"firstName": "Anthony",
				"lastName": "Hopkins"
			},
			{
				"firstName": "Maria",
				"lastName": "Skorobogatov"
			},
			{
				"firstName": "Jeffrie",
				"lastName": "Lane"
			},
			{
				"firstName": "Lieb",
				"lastName": "Lensky"
			},
			{
				"firstName": "George",
				"lastName": "Schwartz"
			}
			],
            "category": "THRILLER",
            "ratings": [],
            "ratingAvg": 0,
            "premiere": ISODate("1991-01-30")
        },
		{
            "name" : "Alien",
            "description": "After a space merchant vessel perceives an unknown transmission as a distress call, its landing on the source moon finds one of the crew attacked by a mysterious lifeform, and they soon realize that its life cycle has merely begun.",
            "length": 117,
            "director": {
						"firstName": "Ridley",
						"lastName": "Scott"
			},
            "actors": [
			{
				"firstName": "Tom",
				"lastName": "Skerritt"
			},
			{
				"firstName": "Sigourney",
				"lastName": "Weaver"
			},
			{
				"firstName": "Veronica",
				"lastName": "Cartwright"
			},
			{
				"firstName": "Harry",
				"lastName": "Dean Stanton"
			},
			{
				"firstName": "John",
				"lastName": "Hurt"
			},
			{
				"firstName": "Ian",
				"lastName": "Holm"
			},
			{
				"firstName": "Yaphet",
				"lastName": "Kotto"
			},
			{
				"firstName": "Bolaji",
				"lastName": "Badejo"
			},
			{
				"firstName": "Helen",
				"lastName": "Horton"
			}
			],
            "category": "HORROR",
            "ratings": [],
            "ratingAvg": 0,
            "premiere": ISODate("1979-06-22")
        },
		{
            "name" : "Escape from L.A.",
            "description": "Snake Plissken is once again called in by the United States government to recover a potential doomsday device from Los Angeles, now an autonomous island where undesirables are deported.",
            "length": 101,
            "director": {
						"firstName": "John",
						"lastName": "Carpenter"
			},
            "actors": [
			{
				"firstName": "Kurt",
				"lastName": "Russell"
			},
			{
				"firstName": "Steve",
				"lastName": "Buscemi"
			},
			{
				"firstName": "Peter",
				"lastName": "Fonda"
			},
			{
				"firstName": "Cliff",
				"lastName": "Robertson"
			},
			{
				"firstName": "Valeria",
				"lastName": "Golino"
			},
			{
				"firstName": "Stacy",
				"lastName": "Keach"
			},
			{
				"firstName": "Pam",
				"lastName": "Grier"
			},
			{
				"firstName": "Bruce",
				"lastName": "Campbell"
			},
			{
				"firstName": "Georges",
				"lastName": "Corraface"
			},
			{
				"firstName": "Michelle",
				"lastName": "Forbes"
			},
			{
				"firstName": "A.J.",
				"lastName": "Langer"
			},
			{
				"firstName": "Ina",
				"lastName": "Romeo"
			},
			{
				"firstName": "Peter",
				"lastName": "Jason"
			},
			{
				"firstName": "Jordan",
				"lastName": "Baker"
			},
			{
				"firstName": "Caroleen",
				"lastName": "Feeney"
			}
			],
            "category": "ADVENTURE",
            "ratings": [],
            "ratingAvg": 0,
            "premiere": ISODate("1996-08-09")
        }
    ]);

};