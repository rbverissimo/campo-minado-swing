package br.com.rvrsmo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Campo {
	
	private final int LINHA;
	private final int  COLUNA;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado; 
	
	private List<Campo> vizinhos = new ArrayList<>();
	private Set<CampoObservador> observadores = new HashSet<>();
	
	Campo(int linha, int coluna){  
		this.LINHA = linha;
		this.COLUNA = coluna;
	}
	
	public void registrarObservador(CampoObservador observador) {
		observadores.add(observador); 
	}
	
	private void notificarObservadores(CampoEvento e) {
		observadores.stream().forEach(o -> o.eventoOcorreu(this, e));
	}
	
	
	boolean adicionarVizinho(Campo vizinho) {
		
		/* IMPLEMENTAÇÃO DA LÓGICA: 
		 * vizinho é o campo que
		 *  tem uma diferença de 1 ou 2 de coluna + linha
		 * porque:
		 * os vizinhos na diagonal tem uma linha e uma coluna de diferença
		 * em relação ao objeto campo atual;
		 * se o campo é 2,2, um vizinho possível é o 1,1 na diagonal, 
		 * 1,2 na vertical e 1,3 também na diagonal
		 */
		
		//DEFINIÇÃO DO QUE É OU NÃO DIAGONAL
		boolean linhaDiferente = LINHA != vizinho.LINHA;
		boolean colunaDiferente = COLUNA != vizinho.COLUNA;
		boolean isDiagonal = linhaDiferente && colunaDiferente;
		
		//DEFINIÇÃO DOS VIZINHOS PELA DIFERENÇA DAS LINHAS E COLUNAS
		int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
		int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA); 
		int deltaSoma = deltaLinha + deltaColuna; 
		
		//ADICIONAR OS VIZINHOS DE ACORDO COM A LÓGICA ESCRITA ACIMA; 
		if((deltaSoma == 1 && !isDiagonal) ||
				(deltaSoma == 2 && isDiagonal)) {
			vizinhos.add(vizinho); 
			return true;
		} else return false;

	}
	
	
	void alternarMacacao() { 
		if(!aberto) {
			marcado = !marcado; 
			
			if(marcado) {
				notificarObservadores(CampoEvento.MARCAR);
			} else notificarObservadores(CampoEvento.DESMARCAR);
		
		}
	}
	
	
	boolean abrir() {
		if(!marcado && !aberto) {
			aberto = true;
			
			if(minado) {
				//TODO implementar nova versão
			}
			
			if(vizinhoSeguro()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		}
			
		return false;
	}
	
	
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}


	public void setVizinhos(List<Campo> vizinhos) {
		this.vizinhos = vizinhos;
	}


	boolean vizinhoSeguro() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	void setMinado() {
		minado = true;
	}


	public int getLINHA() {
		return LINHA;
	}


	public int getCOLUNA() {
		return COLUNA;
	}
	
	
	boolean isLimpo() {
		/*
		 * Função que define se o campo está classificado como aberto e sem minas ou marcado;
		 * O objetivo geral do jogo é conseguir todos os campos do tabuleiro "limpos";
		 */
		boolean naoMarcadoAberto = !marcado && aberto;
		boolean marcadoFechado = marcado && !aberto; 
		
		return naoMarcadoAberto || marcadoFechado;
		
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream()
				.filter(v -> v.minado)
				.count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	

	
	public boolean isMinado() {
		return minado;
	}
	
	
}
