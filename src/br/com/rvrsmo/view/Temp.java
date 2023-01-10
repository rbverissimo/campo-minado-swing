package br.com.rvrsmo.view;

import br.com.rvrsmo.model.Tabuleiro;

public class Temp {
	
	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);
		
		// tabuleiro.abrirCampo(2, 2);
		
		
		tabuleiro.marcarCampo(0, 0);
		tabuleiro.marcarCampo(0, 1);
		tabuleiro.marcarCampo(0, 2);
		tabuleiro.marcarCampo(1, 0);
		tabuleiro.marcarCampo(1, 1);
		tabuleiro.marcarCampo(1, 2);
		tabuleiro.marcarCampo(2, 0);
		tabuleiro.marcarCampo(2, 1);
		tabuleiro.marcarCampo(2, 2);
		
		
	}

}
