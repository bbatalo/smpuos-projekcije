var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	conn.getDB("userservice-" + sufix).dropDatabase();
	conn.getDB("projekcijeservice-" + sufix).dropDatabase();	
};

var createDatabasesAndCollections = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	userserviceDb.createCollection("user");
	
	var projekcijeserviceDb = conn.getDB("projekcijeservice-" + sufix);
	projekcijeserviceDb.createCollection("projekcije");	
};

load("E:/AA Github/smpuos-projekcije/scripts/user.js");
load("E:/AA Github/smpuos-projekcije/scripts/projekcije.js");


var insertDefaultData = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	insertDefaultDataUser(userserviceDb);
	
	var projekcijeserviceDb = conn.getDB("projekcijeservice-" + sufix);
	insertDefaultDataProjekcije(projekcijeserviceDb);	
};

dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dpy");
