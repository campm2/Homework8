public class Stock{
	private double closingPrice_=0;
	private double highestPrice_=0;
	private double lowestPrice_=0;
	private double openingPrice_=0;
	private double stockVolume_=0;
	private String timeStamp_="";
	
	/**
	 * 
	 */
	public Stock() {
		openingPrice_=0;
		closingPrice_=0;
		highestPrice_=0;
		lowestPrice_=0;
		stockVolume_=0;
		timeStamp_="00:00";
	}//end bracket of stock constructor
	/**
	 * @param timeStamp
	 * @param closingPrice
	 * @param highestPrice
	 * @param lowestPrice
	 * @param openingPrice
	 * @param stockVolume
	 */
	public Stock(String timeStamp,double closingPrice, double highestPrice, double lowestPrice,double openingPrice,double stockVolume) {
		timeStamp_=timeStamp;
		closingPrice_=closingPrice;
		highestPrice_=highestPrice;
		lowestPrice_=lowestPrice;
		openingPrice_=openingPrice;
		stockVolume_=stockVolume;
	}//end of the constructor that has arguements

	//setter and getter for closingPrice
	/**
	 * @param closingPrice
	 */
	public void setClosingPrice(double closingPrice) {
		closingPrice_=closingPrice;
	}//end bracket of setter for closingPrice
	/**
	 * @return closingPrice_
	 */
	public double getClosingPrice() {
		return closingPrice_;
	}//end bracket for getter of closingPrice
	
	//setter and getter for highestPrice
	/**
	 * @param highestPrice
	 */
	public void setHighestPrice(double highestPrice) {
		highestPrice_=highestPrice;	
	}//end bracket of setter for highestPrice
	/**
	 * @return highestPrice_;
	 */
	public double getHighestPrice() {
		return highestPrice_;
	}//end bracket of getter for highestPrice
	
	//setter and getter for lowestPrice
	/**
	 * @param lowestPrice
	 */
	public void setLowestPrice(double lowestPrice) {
		lowestPrice_=lowestPrice;
	}//end bracket for setter for lowestPrice
	/**
	 * @return lowestPrice_
	 */
	public double getLowestPrice() {
		return lowestPrice_;
	}//end bracket for getter for lowestPrice
	
	//setter and getter for openingPrice
	/**
	 * @param openingPrice
	 */
	public void setOpeningPrice(double openingPrice) {
		openingPrice_=openingPrice;
	}//end bracket for setter for openingPrice
	/**
	 * 
	 * @return openingPrice_
	 */
	 public double getOpeningPrice() {
		 return openingPrice_;
	 }//end bracket for getter for openingPrice
	 
	 //setter and getter for stockVolume
	 /**
	  * @param stockVolume
	  */
	 public void setStockVolume(double stockVolume) {
		 stockVolume_=stockVolume;
	 }//end bracket for setter for stockVolume
	 /**
	  * @return stockVolume_
	  */
	 public double getStockVolume() {
		 return stockVolume_;
	 }//end bracket for getter for stockVolume
	 
	 //setter and getter for timeStamp
	 /**
	  * @param timeStamp
	  */
	 public void setTimeStamp(String timeStamp) {
		 timeStamp_=timeStamp;
	 }//end bracket for setter for timeStamp
	 /**
	  * @return timeStamp_
	  */
	 public String getTimeStamp() {
		 return timeStamp_;
	 }//end bracket for getter for timeStamp
	 
	
}