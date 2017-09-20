package br.com.devops.CaixaEletronicoDevops;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ContaBancaria {
	private double totalSaques;
	private int qtdSaques;

	static ArrayList qtdNotas;

	public ContaBancaria() {
		qtdNotas = new ArrayList();
		repor(100, 200);
		repor(50, 500);
		repor(20, 500);
		repor(10, 5000);
	}

	public int[] sacar(double quantia) throws Exception {
		if(limite(quantia)) {            
			if (sacando(quantia) == false) {
				throw new Exception("Nao existem notas suficientes no Caixa para esta quantia!\n");
			}
			else {
				return quantNotas10_20_50_100();
			}
		}
		else {
			throw new Exception("O valor está acima do limite!\n");
		}
	}

	private boolean limite(double quantia)	{
		if(quantia < 500) {
			return true;
		}
		else {			
			if((hora() >= 18 && quantia <=500) || (hora() < 18 && quantia <=5000))	{
				return true; 
			}
			else {
				return false;
			}

		}

	}

	public int hora(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	private boolean sacando(double quantia) {
		if ((quantia <= 0 || quantia > consultarSaldo())) {
			return false;
		}
		else if (quantia % 5 != 0) {
			return false;
		}
		else {
			ArrayList notas = new ArrayList();
			double resto = quantia;
			double valor = 0;
			Collections.sort(qtdNotas);

			while (resto > 0) {
				valor = resto - ((Integer) qtdNotas.get(qtdNotas.size() - 1)).intValue();

				if (valor < 0) {
					notas.add(qtdNotas.get(qtdNotas.size() - 1));
					qtdNotas.remove(qtdNotas.size() - 1);
					Collections.sort(qtdNotas);
				}
				else {
					qtdNotas.remove(qtdNotas.size() - 1);
					resto = valor;
				}
			}

			for (int i = 0; i < notas.size(); i++) {
				qtdNotas.add(notas.get(i));
			}

			totalSaques = totalSaques + quantia;
			qtdSaques = qtdSaques + 1;

			return true;
		}
	}

	public void repor(int nota, int qtdDeNotas) {
		for (int i = 0; i < qtdDeNotas; i++) {
			qtdNotas.add(nota);
		}
	}

	public double consultarSaldo() {
		double somaNotas = 0;

		if (!qtdNotas.isEmpty()) {
			for (int i = 0; i < qtdNotas.size(); i++) {
				somaNotas = somaNotas + ((Integer) qtdNotas.get(i)).intValue();
			}
		}
		return somaNotas;
	}

	public int mostraQtdTipoNotas(int nota) {
		int qtdDeNotas = 0;
		for (int i = 0; i < qtdNotas.size(); i++) {
			if (qtdNotas.get(i).equals(nota)) {
				qtdDeNotas = qtdDeNotas + 1;
			}
		}
		return qtdDeNotas;
	}

	public double getTotalSaques() {
		return totalSaques;
	}

	public void setTotalSaques(double totalSaques) {
		this.totalSaques = totalSaques;
	}

	public int getQtdSaques() {
		return qtdSaques;
	}

	public void setQtdSaques(int qtdSaques) {
		this.qtdSaques = qtdSaques;
	}	
	
	public int[] quantNotas10_20_50_100()
	{
		int quantNotas10_20_50_100[] = new int[4];

		quantNotas10_20_50_100[0] = mostraQtdTipoNotas(10);
		quantNotas10_20_50_100[1] = mostraQtdTipoNotas(20);
		quantNotas10_20_50_100[2] = mostraQtdTipoNotas(50);
		quantNotas10_20_50_100[3] = mostraQtdTipoNotas(100);
		
		return quantNotas10_20_50_100;
	}
}
