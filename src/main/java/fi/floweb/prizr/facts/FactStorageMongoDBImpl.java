package fi.floweb.prizr.facts;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

import fi.floweb.prizr.beans.MultiplierBase;

public class FactStorageMongoDBImpl implements FactStorage {

	private static final String FACTCOLLECTION = "facts";
	private static final String DBNAME_DEFAULT = "prizr";
	
	private static MongoClient mongoClient = null;
	private static DB db = null;
	private static DBCollection coll = null;
	private static String currentDbName = null;
	
	private static void init() {
		if (mongoClient == null) {
			try {
				mongoClient = new MongoClient( "localhost:27017" );
			} catch (UnknownHostException e) {
				System.exit(1);
			}
			if(currentDbName == null) {
				db = mongoClient.getDB(DBNAME_DEFAULT);
			} else {
				db = mongoClient.getDB(currentDbName);
			}
			coll = db.getCollection(FACTCOLLECTION); 
		}
	}
	
	public FactStorageMongoDBImpl(String dbName) {
		currentDbName = dbName;
	}
	
	@Override
	public ArrayList<MultiplierBase> getFacts() {
		init();
		ArrayList<MultiplierBase> res = new ArrayList<MultiplierBase>();
		DBCursor facts = coll.find();
		while(facts.hasNext()) {
			MultiplierBase fact = DBObjectToMultiplierBase(facts.next());
			res.add(fact);
		}
		return res;
	}

	@Override
	public boolean storeFact(MultiplierBase fact) {
		init();
		DBObject dbfact = multiplierBaseToDBObject(fact);
		WriteResult res = coll.insert(dbfact,WriteConcern.SAFE);
		return true;
	}

	@Override
	public boolean deleteFact(String objectId) {
		init();
		ObjectId _id;
		try {
			_id = new ObjectId(objectId);
		} catch (IllegalArgumentException e) {
			System.out.println("Failed to convert to object ID item: "+objectId);
			return false;
		}
		DBObject toRemove = new BasicDBObject("_id", _id);
		coll.remove(toRemove);
		return true;
	}
	
	private DBObject multiplierBaseToDBObject(MultiplierBase mb) {
		DBObject res = new BasicDBObject();
		res.put("appliesToCategory", mb.getAppliesToCategory());
		res.put("appliesToLocation", mb.getAppliesToLocation());
		res.put("multiplier",mb.getMultiplier());
		res.put("multiplierBaseDescription", mb.getMultiplierBaseDescription());
		res.put("multiplierBaseName", mb.getMultiplierBaseName());
		res.put("appliesToShopCode", mb.getAppliesToShopCode());
		res.put("isDomestic",mb.isDomestic());
		res.put("countryCode", mb.getCountryCode());
		res.put("includesFreight", mb.getIncludesFreight());
		res.put("freightMultiplier", mb.getFreightMultiplier());
		return res;
		
	}

	private MultiplierBase DBObjectToMultiplierBase(DBObject db) {
		MultiplierBase res = new MultiplierBase();
		ObjectId uniqueId = (ObjectId)db.get("_id");
		res.setId(uniqueId.toString());
		res.setAppliesToCategory((String)db.get("appliesToCategory"));
		res.setAppliesToLocation((String)db.get("appliesToLocation"));
		res.setMultiplier((Double)db.get("multiplier"));
		res.setMultiplierBaseDescription((String)db.get("multiplierBaseDescription"));
		res.setMultiplierBaseName((String)db.get("multiplierBaseName"));
		res.setAppliesToShopCode((String)db.get("appliesToShopCode"));
		res.setDomestic((Boolean)db.get("isDomestic"));
		res.setCountryCode((String)db.get("countryCode"));
		res.setIncludesFreight((Double)db.get("includesFreight"));
		res.setFreightMultiplier((Double)db.get("freightMultiplier"));
		return res;
		
	}
	
}
