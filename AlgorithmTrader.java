import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

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
	private int count=0;
	ArrayList<Stock> stock_=new ArrayList<Stock>();
	
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
	
	/**
	 * @param filename
	 * @return stock
	 * @throws IOException
	 */
	public ArrayList<Stock> ReadInputData(String filename) throws IOException{
		
		String timeStamp="";
		double closingPrice=0;
		double highestPrice=0;
		double lowestPrice=0;
		double openingPrice=0;
		double stockVolume=0;
		
		File file=new File(filename);
		Scanner inputFile=new Scanner(file);
		ArrayList<Stock> stock=new ArrayList<Stock>();
		
		inputFile.nextLine();
		//to add stocks from file into arraylist
			while(inputFile.hasNext()) {
			String fileLine=inputFile.nextLine();
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
	 * @param num
	 * @return buy
	 */
	public boolean EntryStrategy(int num) {
		boolean buy=false;
		
		if(num!=0 && getShares()==0) {
			double previous=stock_.get(num-1).getClosingPrice();
			double currentPrice=stock_.get(num).getClosingPrice();
		
			if(currentPrice>previous ) {
				buy=true;
			
			}//end bracket of if
			else {
				buy=false;
			}//end bracket of else 
		}//end bracket of outside if 
		
		return buy;
			
	}//end bracket of EntryStrategy method
	/**
	 * @return sell
	 */
	public boolean ExitStrategy(double percentChange, int shares) {
		boolean sell=false;
		if(percentChange>SELL_SIGNAL_THREASHOLD || percentChange<-SELL_SIGNAL_THREASHOLD && shares==10000) {
			sell=true;
			
		}
		else {
			sell=false;
		}
		return sell;
		
	}//end bracket of the exit strategy
	
	/**	
	 * @param nameOfFile
	 * @throws IOException
	 */
	public void Run(String nameOfFile) throws IOException{
		PrintWriter output=new PrintWriter("tradingSummary.csv");
		boolean entryStrategy;
		boolean exitStrategy;
	
		setShares(0);
		stock_=ReadInputData(nameOfFile);
		
		output.println("TIMESTAMP,CURRENT_PRICE,SHARES,P/L_PERCENT,PROFIT/LOSS,REALIZED_PROFIT/LOSS,HOLD/NONE,PURCHASE/SELL_PRICE,PURCHASE_COST");
		
		for(int i=0;i<stock_.size();i++) {
			setCurrentPrice(stock_.get(i).getClosingPrice());
			if(getHoldStatus().equals("NONE")) {
				
				setProfitLoss(0);
				//just changed
				setPercentage(getPercentage());
				
			}//end bracket of if 
			else {
				setPercentage((stock_.get(i).getClosingPrice()-getPurchaseOrSellPrice())/getPurchaseOrSellPrice());
				setProfitLoss((getCurrentPrice()-getPurchaseOrSellPrice())*10000);
				
			}//end bracket of else 
			
			
			entryStrategy=EntryStrategy(i);
			//if current Price is greater than previous
			if(entryStrategy==true) {
				count++;
				//you buy stock
				if(count==BUY_SIGNAL_THREASHOLD) {
					
					setShares(10000);
					setPurchaseCost(10000*getCurrentPrice());
					setHoldStatus("HOLD");
					setPurchaseOrSellPrice(getCurrentPrice());
					//this is what last got changed
					setPercentage((stock_.get(i).getClosingPrice()-getPurchaseOrSellPrice())/getPurchaseOrSellPrice());
					
					//setActualProfitLoss(0);
					
					
					count=0;		
				}//end bracket of inside if
				
			}//end bracket of outside if
			else if(entryStrategy==false) {
				count=0;
			}//end bracket of else if
			
			exitStrategy = ExitStrategy(getPercentage(),getShares());	
				// only if percent is either less than or greater than .0012 and shares are 10000
			if(exitStrategy == true) {
				
					setHoldStatus("NONE");
					setPurchaseOrSellPrice(getCurrentPrice());
					
					setShares(0);
					setActualProfitLoss((getPercentage()*getPurchaseCost())+getActualProfitLoss());
					setProfitLoss(0);
					
				
			}//end bracket of if 
			if(stock_.size()==i && getHoldStatus().equals("HOLD")) {
				exitStrategy=true;
			}//end bracket of if

			
			//output results to csv file
			output.printf("%s,\t%.4f\t,%d\t,%.8f\t,$%.2f\t,$%.2f\t,%s\t,%.4f\t,$%.2f\r\n", stock_.get(i).getTimeStamp(),getCurrentPrice(),getShares(),getPercentage(),getProfitLoss(),getActualProfitLoss(),getHoldStatus(),getPurchaseOrSellPrice(),getPurchaseCost());
		}//end bracket of the for loop
		//close file
		output.close();
	}//end of Run method
}//end bracket for class