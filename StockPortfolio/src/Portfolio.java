import java.util.ArrayList;



public class Portfolio {


private static Portfolio instance; 

private ArrayList<PortfolioEntries> EntryList; 


private Portfolio (){


}


public static Portfolio getInstance()

{

if (instance == null)

instance = new Portfolio();



return instance;

}


public void display() {


}


public void update(){


}


public boolean addStock(){

return false;


}


public boolean removeStock(){

return false;

}


public static void main(String[] args) {





}



}

