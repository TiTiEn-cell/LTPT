package demo;

import com.mongodb.reactivestreams.client.MongoDatabase;

import dao.ZipsDao;
import utils.DBConnection;

public class ZipsDemo {

	public static void main(String[] args) {
		DBConnection conn = new DBConnection();
		MongoDatabase db = conn.getInstance().getDatabase();
//		Loc loc= new Loc(111, 222);
		ZipsDao zipsDAO = new ZipsDao(db);
		
//	Address product = new Address("long anaaaaa", "11111",loc,12345,"phong");
		//productDAO.addProduct(product);
//		zipsDAO.findAll("AZ");
//		zipsDAO.findAllByStateAndCity("AZ", "PHOENIX");
//		zipsDAO.findInRangeAndMaxValue(3500, 4000);
		zipsDAO.updateProduct1("NguyenTanTai", "AL");
//			productDAO.findInRange(3062, 3063);
//	productDAO.updateProduct("35014", "Alo");
//	
//	productDAO.find("35014");

	}

}
