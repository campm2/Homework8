import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class AlgorithmTrader{
	private double currentPrice_=0;
	private int shares_=0;
	private double percentage_=0;
	private double profit_Loss_=0;
	private double actualProfit_Loss_=0;
	private String holdStatus_="";
	private double purchaseCost_=0;
	private double purchaseOrSellPrice_=0;
	private final int BUY_SIGNAL_THREASHOLD=5;
	private final double SELL_SIGNAL_THREASHOLD=.0012;
	
	//empty constructor 
	/**
	 * 
	 */
	AlgorithmTrader(){
		currentPrice_=0;
		shares_=0;
		percentage_=0;
		profit_Loss_=0;
		actualProfit_Loss_=0;
		holdStatus_="NONE";
		purchaseCost_=0;
		purchaseOrSellPrice_=0;
	}//end bracket of empty constructor
	
	//setter and getter of current Price
	/**
	 * @param currentPrice
	 */
	public void setCurrentPrice(double currentPrice) {
		currentPrice_=currentPrice;
	}//end bracket of setter for currentPrice
	/**
	 * @return currentPrice_
	 */
	public double getCurrentPrice() {
		return currentPrice_;
	}//getter for getter for currentPrice
	
	//setter and getter for shares
	/**
	 * @param shares
	 */
	public void setShares(int shares) {
		shares_=shares;
	}//end bracket for setter for shares
	/**
	 * @return shares_
	 */
	public int getShares() {
		return shares_;
	}//end bracket for getter for shares
	
	//setter and getter for percentage
	/**
	 * @param percentage
	 */
	public void setPercentage(double percentage) {
		percentage_=percentage;
	}//end bracket for setter for percentage
	/**
	 * @return percentage
	 */
	public double getPercentage() {
		return percentage_;
	}//end bracket for getter for percentage
	
	//setter and getter for profit_Loss
	/**
	 * @param profit_Loss
	 */
	public void setProfitLoss(double profit_Loss) {
		profit_Loss_=profit_Loss;
	}//end bracket for setter for profit_Loss
	/**
	 * @return profit_Loss_
	 */
	public double getProfitLoss() {
		return profit_Loss_;
	}//end bracket for getter for profit_Loss
	
	//setter and getter for actual profit loss
	/**
	 * @param actualProfit_Loss
	 */
	public void setActualProfitLoss(double actualProfit_Loss) {
		actualProfit_Loss_=actualProfit_Loss;
	}//end bracket for setter for actual profit loss
	/**
	 * @return actualProfit_Loss_
	 */
	public double getActualProfitLoss() {
		return actualProfit_Loss_;
	}//end bracket for getter for actual profit loss
	
	//setter and getter for hold Status
	/**
	 * @param holdStatus
	 */
	public void setHoldStatus(String holdStatus) {
		holdStatus_=holdStatus;
	}//end bracket for setter for hold Status
	/**
	 * @return holdStatus_
	 */
	public String getHoldStatus() {
		return holdStatus_;
	}//end bracket for getter for hold Status
	
	//setter and getter for purchase cost
	/**
	 * @param purchaseCost
	 */
	public void setPurchaseCost(double purchaseCost) {
		purchaseCost_=purchaseCost;
	}//end bracket for setter for purhcase cost
	/**
	 * @return purchaseCost_;
	 */
	public double getPurchaseCost() {
		return purchaseCost_;
	}//end bracket for getter for  purchase cost
	
	//setter and getter for purchaseorSell price
	/**
	 * @param purchaseOrSellPrice
	 */
	public void setPurchaseOrSellPrice(double purchaseOrSellPrice) {
		purchaseOrSellPrice_=purchaseOrSellPrice;
	}//end bracket for setter for purchaseorSell price
	/**
	 * @return purchaseOrSellPrice_
	 */
	public double getPurchaseOrSellPrice() {
		return purchaseOrSellPrice_;
	}//end bracket for getter for purchaseorSell price
	public int Count(String count,String filename) throws IOException {
		int file_Length=0;
		File file=new File(filename);
		Scanner inputFile=new Scanner(file);
		// to see how many lines there is in the file
		while(inputFile.hasNext()) {
			count=inputFile.nextLine();
			if(!(count.isEmpty())) {
				file_Length++;
			}//end of if
		}//end of while statement
		inputFile.close();
		return file_Length;
	}

	/**
	 * @param filename
	 * @return stock
	 * @throws IOException
	 */
	public ArrayList<Stock> ReadInputData(String filename) throws IOException{
		String count="";
		String timeStamp="";
		double closingPrice=0;
		double highestPrice=0;
		double lowestPrice=0;
		double openingPrice=0;
		double stockVolume=0;
		int file_length=Count(count,filename);
		
		File file=new File(filename);
		Scanner inputFile=new Scanner(file);
		ArrayList<Stock> stock=new ArrayList<Stock>(file_length);
		
		inputFile.nextLine();
		//to add stocks from file into arraylist
			while(inputFile.hasNext()) {
			String fileLine=inputFile.nextLine().trim();
			if((fileLine!=null) && fileLine.length()>0) {
				String [] splitFile=fileLine.split(",");
				timeStamp=splitFile[0];
				
				closingPrice=(Double.parseDouble(splitFile[1]));
				highestPrice=(Double.parseDouble(splitFile[2]));
				lowestPrice=(Double.parseDouble(splitFile[3]));
				openingPrice=(Double.parseDouble(splitFile[4]));
				stockVolume=(Double.parseDouble(splitFile[5]));
				stock.add(new Stock (timeStamp,closingPrice,highestPrice,lowestPrice,openingPrice,stockVolume));
			
			}//end of if
			
		}//end of while loop
		inputFile.close();
		return stock;
		
	}//end of Read inputData method
	/**
	 * @param counter
	 * @return buy
	 */
	public boolean EntryStrategy(int counter) {
		boolean buy=false;
		if(counter==BUY_SIGNAL_THREASHOLD) {
			buy=true;
			
		}
		else{
			buy=false;
			
		}
		return buy;
			
	}//end bracket of EntryStrategy method
	/**
	 * @return sell
	 */
	public boolean ExitStrategy() {
		boolean sell=false;
		if(getPercentage()>=SELL_SIGNAL_THREASHOLD || getPercentage()<=-SELL_SIGNAL_THREASHOLD){
			sell=true;
			
		}//end bracket of if
		else {
			sell=false;
		}//end bracket of else
		return sell;
		
	}//end bracket of the exit strategy
	public void Run(String nameOfFile) throws IOException{
		int count=0;
		boolean entryStrategy=false;
		boolean exitStrategy=false;
		ArrayList<Stock> stock_=new ArrayList<Stock>();
		stock_=ReadInputData(nameOfFile);
		for(int i=0;i<stock_.size();i++) {
			setPercentage((getPurchaseOrSellPrice()-stock_.get(i).getClosingPrice())/getPurchaseOrSellPrice());
			if(i!=0) {
				if(stock_.get(i).getClosingPrice()>stock_.get(i-1).getClosingPrice()&& getShares()==0 ) {
					
				
				count++;
				entryStrategy=EntryStrategy(count);
				
					if(entryStrategy==true) {
					
						setShares(10000);
						setProfitLoss(getPercentage()*getPurchaseCost());
						setPercentage(getPercentage());
						setHoldStatus("HOLD");
						setPurchaseOrSellPrice(stock_.get(i).getClosingPrice());
						setCurrentPrice(10000*stock_.get(i).getClosingPrice());
						setPurchaseCost(10000*stock_.get(i).getClosingPrice());
						count=0;
					
					
					}//end bracket of inside if 
					else if(entryStrategy==false){
						count=0;
					
					}//end bracket of else if
				}//end bracket of outside if
			}
			
			
			else if(getShares()==10000) {
				exitStrategy=ExitStrategy();
				if(exitStrategy==true) {
					
					setHoldStatus("NONE");
					setPurchaseOrSellPrice(stock_.get(i).getClosingPrice());
					setPercentage(getPercentage());
					setPurchaseCost(0);
					setShares(0);
					setProfitLoss(0);
					setActualProfitLoss(getPurchaseCost()-(getPurchaseOrSellPrice()*10000)+getActualProfitLoss());
				}//end bracket of if
				
			}//end bracket of second outside if
			else if(stock_.size()==i&& getHoldStatus().equals("HOLD")) {
				 entryStrategy=EntryStrategy(count);
				 
				 
			 }
			
			 System.out.printf("%s,%.3f,%d,%.2f,%.2f,%.2f,%s,%.2f,%.2f\r\n", stock_.get(i).getTimeStamp(),getCurrentPrice(),getShares(),getPercentage(),getProfitLoss(),getActualProfitLoss(),getHoldStatus(),getPurchaseOrSellPrice(),getPurchaseCost());
		}//end bracket of the for loop
		
		
	}//end of Run method
}//end bracket for class