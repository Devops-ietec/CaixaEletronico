package br.com.devops.Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.devops.CaixaEletronicoDevops.ContaBancaria;

public class SaqueTeste {
	private ContaBancaria cb = new ContaBancaria();

	public int [] QuantCedulasAntesDeSacar() {
		return cb.quantNotas10_20_50_100();
	}
	
	public double valorSacadoPorQuantCedulas (int QuantCedulasAntesDeSacar[], int QuantCedulasSacadas []) {
		return ((QuantCedulasAntesDeSacar[0] - QuantCedulasSacadas[0])* 10)+
				((QuantCedulasAntesDeSacar[1] - QuantCedulasSacadas[1])* 20)+
				((QuantCedulasAntesDeSacar[2] - QuantCedulasSacadas[2])* 50)+
				((QuantCedulasAntesDeSacar[3] - QuantCedulasSacadas[3])* 100);
	}

	@Test
	public void saqueValido() throws Exception {
		
		int QuantCedulasAntesDeSacar [] = QuantCedulasAntesDeSacar();
		int QuantCedulasSacadas [] = cb.sacar(30);

		double valorSacado = valorSacadoPorQuantCedulas (QuantCedulasAntesDeSacar, QuantCedulasSacadas);

		assertEquals(30, valorSacado, 0.1);
	}
	
	@Test
	public void saqueValidoLimite() throws Exception {
		
		int QuantCedulasAntesDeSacar [] = QuantCedulasAntesDeSacar();
		
		if(cb.hora() >= 18 ) {		
			
			int QuantCedulasSacadas [] = cb.sacar(500);
	
			double valorSacado = valorSacadoPorQuantCedulas (QuantCedulasAntesDeSacar, QuantCedulasSacadas);
	
			assertEquals(500, valorSacado, 0.1);
		}
		else if(cb.hora() < 18) {
			
			int QuantCedulasSacadas [] = cb.sacar(5000);

			double valorSacado = valorSacadoPorQuantCedulas (QuantCedulasAntesDeSacar, QuantCedulasSacadas);

			assertEquals(5000, valorSacado, 0.1);
		}
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
}

