public class DallorCalculator implements  ICalculator{

    private int price = 1;

    private MarketApi marketApi;

    public DallorCalculator(MarketApi marketApi){
        this.marketApi = marketApi;
    }

    public void init(){
        this.price = marketApi.connect();
    }

    // 따로따로 계산해주기위해 크래스를 판다.
//    public void init(){
//        this.price = connect();
//    }
//
//    public int connect(){
//
//        // naver
//
//        // kakao
//
//        return 1100;
//
//    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x - y;
    }
}
