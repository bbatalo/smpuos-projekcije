var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	conn.getDB("userservice-" + sufix).dropDatabase();
	conn.getDB("cinemaservice-" + sufix).dropDatabase();
};

var createDatabasesAndCollections = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	userserviceDb.createCollection("user");
	var cinemaserviceDb = conn.getDB("cinemaservice-" + sufix);
	cinemaserviceDb.createCollection("cinema");
};

load("user.js");

var insertDefaultData = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	insertDefaultDataUser(userserviceDb);
};

dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dpy");
