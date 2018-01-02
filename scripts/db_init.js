var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	conn.getDB("userservice-" + sufix).dropDatabase();
};

var createDatabasesAndCollections = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	userserviceDb.createCollection("user");
};

load("user.js");

var insertDefaultData = function(conn, sufix){
	var userserviceDb = conn.getDB("userservice-" + sufix);
	insertDefaultDataUser(userserviceDb);
};

dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dpy");
