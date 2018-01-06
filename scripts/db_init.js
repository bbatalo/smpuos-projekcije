
var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	conn.getDB("userservice-" + sufix).dropDatabase();
	conn.getDB("projekcijeservice-" + sufix).dropDatabase();	
	conn.getDB("movieservice-" + sufix).dropDatabase();
};

var createDatabasesAndCollections = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	userserviceDb.createCollection("user");
	
	var projekcijeserviceDb = conn.getDB("projekcijeservice-" + sufix);
	projekcijeserviceDb.createCollection("projekcije");	
	
	var movieserviceDb = conn.getDB("movieservice-" + sufix);
	movieserviceDb.createCollection("movie");
};

load("E:/AA Github/smpuos-projekcije/scripts/user.js");
load("E:/AA Github/smpuos-projekcije/scripts/projekcije.js");
load("movie.js");


var insertDefaultData = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	insertDefaultDataUser(userserviceDb);
	
	var projekcijeserviceDb = conn.getDB("projekcijeservice-" + sufix);
	insertDefaultDataProjekcije(projekcijeserviceDb);	
	
	var movieserviceDb = conn.getDB("movieservice-" + sufix);
	insertDefaultDataMovie(movieserviceDb);
};

dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dpy");
