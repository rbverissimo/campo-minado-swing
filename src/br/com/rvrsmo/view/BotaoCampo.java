package br.com.rvrsmo.view;

import javax.swing.JButton;

import br.com.rvrsmo.model.Campo;
import br.com.rvrsmo.model.CampoEvento;
import br.com.rvrsmo.model.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador {
	
	/*
	 * essa classe implementa graficamente as logicas do Campo (Model)
	 */
	
	public BotaoCampo(Campo c) {
		c.registrarObservador(this); 
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		default:
			aplicarEstiloPadrao();
			//Não é necessário criar o desmarcar porque ele volta ao estilo padrão; 
		}
		
	}

	private void aplicarEstiloPadrao() {
		// TODO Auto-generated method stub
		
	}

	private void aplicarEstiloExplodir() {
		// TODO Auto-generated method stub
		
	}

	private void aplicarEstiloMarcar() {
		// TODO Auto-generated method stub
		
	}

	private void aplicarEstiloAbrir() {
		// TODO Auto-generated method stub
		
	}

}
