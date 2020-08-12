package service;

public abstract class DaoFactory {
	
	public final static int MY_SQL = 1;
	
	public static DaoFactory getInstance(int sourceType) {
		switch (sourceType) {
		case MY_SQL:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}
	
	public abstract ProductDao getProductDao();
	
	public abstract UserDao getUserDao();

}
