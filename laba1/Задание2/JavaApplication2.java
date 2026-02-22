public class JavaApplication2 {

    public static void main(String[] args) {
        Stock sber = new Stock("SBER", "PAO Sber");
        sber.previousClosingPrice = 281.50;
        sber.currentPrice = 282.87;
        
   
        System.out.println(sber);
        System.out.println();
        
        // Создаем массив акций различных компаний
        Stock[] stocks = new Stock[5];
        
        stocks[0] = new Stock("GAZP", "PAO Gazprom");
        stocks[0].previousClosingPrice = 165.40;
        stocks[0].currentPrice = 167.25;
        
        stocks[1] = new Stock("LKOH", "PAO Lukoil");
        stocks[1].previousClosingPrice = 7450.00;
        stocks[1].currentPrice = 7520.50;
        
        stocks[2] = new Stock("YNDX", "Yendex");
        stocks[2].previousClosingPrice = 2890.00;
        stocks[2].currentPrice = 2850.75;
        
        stocks[3] = new Stock("TCSG", "TCS Group Holding PLC");
        stocks[3].previousClosingPrice = 3520.00;
        stocks[3].currentPrice = 3495.50;
        
        stocks[4] = new Stock("GMKN", "PAO GMK Norilski Nikel");
        stocks[4].previousClosingPrice = 15680.00;
        stocks[4].currentPrice = 15820.00;
        
     
        for(int i = 0; i < stocks.length; i++){
            System.out.println(stocks[i]);
        }
        
    }
     
}
