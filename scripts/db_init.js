var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	conn.getDB("userservice-" + sufix).dropDatabase();
	conn.getDB("movieservice-" + sufix).dropDatabase();
};

var createDatabasesAndCollections = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	var movieserviceDb = conn.getDB("movieservice-" + sufix);
	userserviceDb.createCollection("user");
	movieserviceDb.createCollection("movie");
};

load("user.js");
load("movie.js");


var insertDefaultData = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	insertDefaultDataUser(userserviceDb);
	
	var movieserviceDb = conn.getDB("movieservice-" + sufix);
	insertDefaultDataMovie(movieserviceDb);

};

dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dpy");
