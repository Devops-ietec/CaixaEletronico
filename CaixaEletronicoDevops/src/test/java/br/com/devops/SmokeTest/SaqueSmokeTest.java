package br.com.devops.SmokeTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.devops.CaixaEletronicoDevops.ContaBancaria;

public class SaqueSmokeTest {

	private ContaBancaria cb = new ContaBancaria();
	
	@Test
	public void valorSaqueZero() {
		boolean permissaoSaque = cb.sacando(0);
		Assert.assertFalse(permissaoSaque);
	}
	
	@Test
	public void valorSaqueNegativo() {
		boolean permissaoSaque = cb.sacando(-300);
		Assert.assertFalse(permissaoSaque);
	}
	
	@Test
	public void validaQtdadeNotas() throws Exception {
		int[] notasEsperadas = new int[1];
		int[] notas= cb.sacar(500);
		notasEsperadas[0] = 5;
		Assert.assertArrayEquals(notasEsperadas, notas);
	}
	
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
