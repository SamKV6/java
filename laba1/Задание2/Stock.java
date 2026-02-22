public class Stock {
    public String symbol;                  //тикер акции         
    public String name;                    //полное название компании              
    public double previousClosingPrice;    //цена закрытия вчера
    public double currentPrice;            //текущая цена
    

    public Stock(String _symbol, String _name) {
        symbol = _symbol;
        name = _name;
    }
    
    public double getChangePercent() {  //Вычисляет процент изменения цены:
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }

    public String toString(){
        return symbol +"| " + name + "| "+getChangePercent();
    }
}
