package br.com.rvrsmo.view;


import java.awt.GridLayout;

import javax.swing.JPanel;

import br.com.rvrsmo.model.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {
	
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getQtdeLinhas(), 
				tabuleiro.getQtdeColunas()));
		
		//reconhecer os campos do tabuleiro como JButtons:
		tabuleiro.paraCada(c -> add(new BotaoCampo(c)));
		
		tabuleiro.registrarObservador(e -> {
			// TODO mostrar resultado para o usuário
		});
	}
	

}
