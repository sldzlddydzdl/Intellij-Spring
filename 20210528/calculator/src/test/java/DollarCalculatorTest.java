import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    // 테스트가 실행 되기 이전에!
    @BeforeEach
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    // Test 코드는 메인에서 노는게 아니라 이렇게 따로 테스트 파일에서
    // 따로 구현해야 메인을 안더럽히고 테스트를 진행할수 있다.

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void dollarTest(){
        MarketApi marketApi = new MarketApi();
        DallorCalculator dallorCalculator = new DallorCalculator(marketApi);
        dallorCalculator.init();

        Calculator calculator = new Calculator(dallorCalculator);

        System.out.println(calculator.sum(10 , 10));

        Assertions.assertEquals(22000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.minus(10, 10));
    }


    @Test
    public void mockTest(){
        DallorCalculator dallorCalculator = new DallorCalculator(marketApi);
        dallorCalculator.init();

        Calculator calculator = new Calculator(dallorCalculator);

        System.out.println(calculator.sum(10 , 10));

        Assertions.assertEquals(60000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.minus(10, 10));
    }


}
