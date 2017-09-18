package com.caixaeletronico.CaixaEletronico;
import java.util.Scanner;


public class Test {

     public static void main(String[] args) throws Exception 
     {
       
    	Scanner teclado = new Scanner(System.in);
        double quantia;
        
        ContaBancaria caixa = new ContaBancaria();
        
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("             CAIXA ELETRONICO - Saque");
                    System.out.println("-----------------------------------------------------------\n");
                    
                    System.out.println("Digite o valor a sacar: ");
                    quantia = teclado.nextDouble();
                    int quantCedulas[] = caixa.sacar(quantia);
                    

                        System.out.printf("Saque de R$ %.2f efetuado com sucesso!\n", quantia);
                        

                        System.out.printf("Notas de R$ 10       : %d\n", quantCedulas[0]);
                        System.out.printf("Notas de R$ 20       : %d\n", quantCedulas[1]);
                        System.out.printf("Notas de R$ 50       : %d\n", quantCedulas[2]);
                        System.out.printf("Notas de R$ 100      : %d\n", quantCedulas[3]);

   
        teclado.close();
    }
}
