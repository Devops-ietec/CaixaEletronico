package br.com.devops.SmokeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.devops.CaixaEletronicoDevops.ContaBancaria;

public class SaqueSmokeTest {

	private ContaBancaria cb = new ContaBancaria();
	
	
	@Rule
	    public ExpectedException thrown = ExpectedException.none();
	@Test
	public void saqueInvalidoLimite() throws Exception {		
		
		if(cb.hora() >= 18 ) {		
			
			thrown.expect(Exception.class);
			thrown.expectMessage("O valor está acima do limite!");
	        cb.sacar(501);
	        
		}
		else if(cb.hora() < 18) {
			thrown.expect(Exception.class);
			thrown.expectMessage("O valor está acima do limite!");
	        cb.sacar(5001);
		}
	}
}
