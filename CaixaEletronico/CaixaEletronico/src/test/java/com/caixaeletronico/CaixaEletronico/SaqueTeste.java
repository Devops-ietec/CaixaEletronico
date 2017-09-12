import org.junit.Test;
import org.junit.Assert;
import 

public class SaqueTeste {

    public ContaBancaria contaBancaria = new ContaBancaria();

    @Test
    public void saqueValido(){
        boolean sacou =  contaBancaria.sacar(30);      
        Assert.assertTrue(sacou);
    }

     @Test
    public void saqueValido(){
        boolean sacou =  contaBancaria.sacar(30);      
        Assert.assertTrue(sacou);
    }

    @Test
    public void saqueInvalido(){
        boolean sacou =  contaBancaria.sacar(1200);      
        Assert.assertTrue(sacou);
    }

    @Test
    public void testeInvalido(){
        
    }
}