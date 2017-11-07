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
	private int count=0;
	
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
	
	public int Count(String countLines,String filename) throws IOException {
		int file_Length=0;
		File file=new File(filename);
		Scanner inputFile=new Scanner(file);
		// to see how many lines there is in the file
		while(inputFile.hasNext()) {
			countLines=inputFile.nextLine();
			if(!(countLines.isEmpty())) {
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
		String countLines="";
		String timeStamp="";
		double closingPrice=0;
		double highestPrice=0;
		double lowestPrice=0;
		double openingPrice=0;
		double stockVolume=0;
		int file_length=Count(countLines,filename);
		
		File file=new File(filename);
		Scanner inputFile=new Scanner(file);
		ArrayList<Stock> stock=new ArrayList<Stock>(file_length);
		
		inputFile.nextLine();
		//to add stocks from file into arraylist
			for(int i=0;i<file_length-1;i++) {
			String fileLine=inputFile.nextLine();
			if( fileLine.length()>0 && !(fileLine.isEmpty())) {
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
	public boolean EntryStrategy() {
		boolean buy=false;
		if(count==BUY_SIGNAL_THREASHOLD) {
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
	public boolean ExitStrategy(double changeInPercent) {
		boolean sell=false;
		if(changeInPercent>=SELL_SIGNAL_THREASHOLD || changeInPercent<=-SELL_SIGNAL_THREASHOLD){
			sell=true;
			
		}//end bracket of if
		else {
			sell=false;
		}//end bracket of else
		return sell;
		
	}//end bracket of the exit strategy
	public void Run(String nameOfFile) throws IOException{
		
		boolean entryStrategy=false;
		boolean exitStrategy=false;
	
		ArrayList<Stock> stock_=new ArrayList<Stock>();
		stock_=ReadInputData(nameOfFile);
		

		double previousPrice=stock_.get(0).getClosingPrice();
		System.out.println("TIMESTAMP,CURRENT_PRICE,SHARES,P/L_PERCENT,PROFIT/LOSS,REALIZED_PROFIT/LOSS,HOLD/NONE,PURCHASE/SELL_PRICE,PURCHASE_COST");
		
		setHoldStatus("NONE");
		setShares(0);
		for(int i=0;i<stock_.size();i++) {
			
			
			double currentPrice=stock_.get(i).getClosingPrice();
			if( getShares()==0) {
				
				if(currentPrice>previousPrice) {
					
					count++;
					entryStrategy=EntryStrategy();
					if(entryStrategy==true) {
						
						setShares(10000);
						setHoldStatus("HOLD");
						setPurchaseOrSellPrice(getCurrentPrice());
						setActualProfitLoss(getActualProfitLoss()+getProfitLoss());
						setProfitLoss((getPurchaseOrSellPrice()*10000)-getPurchaseCost());
						setPurchaseCost(10000*getCurrentPrice());
						count=0;
						
						
					}//end bracket of inside if 
					currentPrice=previousPrice;
				}//end bracket of outside if
				
				
			}
			
				
			if(getShares() ==10000) {
				
				exitStrategy=ExitStrategy(getPercentage());
				if(exitStrategy=true) {
				
						setHoldStatus("NONE");
						setPurchaseOrSellPrice(getCurrentPrice());
						setPercentage((getCurrentPrice()-getPurchaseOrSellPrice())/getPurchaseOrSellPrice());
						setPurchaseCost(10000*stock_.get(i).getClosingPrice());
						setShares(0);
						setProfitLoss(getPercentage()*getPurchaseCost());
						setActualProfitLoss(0);
						
						
					}//end bracket of if
					else if(exitStrategy==false) {
						System.out.print("Here0000");
						setHoldStatus("HOLD");
						setProfitLoss(0);
						setShares(10000);
						setPurchaseOrSellPrice(getCurrentPrice());
						setPurchaseCost(10000*stock_.get(i).getClosingPrice());
						setPercentage((stock_.get(i).getClosingPrice()-getPurchaseOrSellPrice())/getPurchaseOrSellPrice());
						setActualProfitLoss((getPurchaseOrSellPrice()*10000)-getPurchaseCost());
						
						
						
					}
			
					else if(stock_.size()==i && getShares()==10000) {
						setHoldStatus("NONE");
						setPurchaseOrSellPrice(getCurrentPrice());
						setPercentage((stock_.get(i).getClosingPrice()-getPurchaseOrSellPrice())/getPurchaseOrSellPrice());
						setPurchaseCost(10000*stock_.get(i).getClosingPrice());
						setShares(0);
						setProfitLoss(0);
						setActualProfitLoss(getPurchaseCost()-(getPurchaseOrSellPrice()*10000)+getActualProfitLoss());
						 
					}
				
				}//end bracket of second outside if		
			System.out.printf("%s,\t%.3f\t,%d\t,%.2f\t,%.2f\t,%.2f\t,%s\t,%.2f\t,%.2f\r\n", stock_.get(i).getTimeStamp(),getCurrentPrice(),getShares(),getPercentage(),getProfitLoss(),getActualProfitLoss(),getHoldStatus(),getPurchaseOrSellPrice(),getPurchaseCost());
			setCurrentPrice(stock_.get(i).getClosingPrice());	
			
			
			 
		}//end bracket of the for loop
	}//end of Run method
}//end bracket for class