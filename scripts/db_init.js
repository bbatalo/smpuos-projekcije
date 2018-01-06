var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	//users
	conn.getDB("userservice-" + sufix).dropDatabase();
	//cinemas
	conn.getDB("cinemaservice-" + sufix).dropDatabase();
};

var createDatabasesAndCollections = function(conn, sufix){
	//users
	var userserviceDb = conn.getDB("userservice-" + sufix);
	userserviceDb.createCollection("user");
	//cinemas
	var cinemaserviceDb = conn.getDB("cinemaservice-" + sufix);
	cinemaserviceDb.createCollection("cinema");
};

load("user.js");
load("cinema.js")

var insertDefaultData = function(conn, sufix){
	//users
	var userserviceDb = conn.getDB("userservice-" + sufix);
	insertDefaultDataUser(userserviceDb);
	//cinemas
	var cinemaserviceDb = conn.getDB("cinemaservice-" + sufix);
	insertDefaultDataCinema(cinemaserviceDb);
};

dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dpy");
