public class Money {

  private long amount;

  public Money(long amount){
    this.amount = amount;
  }

  public void add(long amount){
    this.amount += amount;
  }

  public long getAmount(){
    return this.amount;
  }

}
