package br.com.rvrsmo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador {
	
	private int QtdeLinhas;
	private int QtdeColunas;
	private int QtdeMinas;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int qtdeLinhas, int qtdeColunas, int qtdeMinas) {
		QtdeLinhas = qtdeLinhas;
		QtdeColunas = qtdeColunas;
		QtdeMinas = qtdeMinas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
		
	}
	
	protected Tabuleiro(int qtdeLinhas, int qtdeColunas) {
		QtdeLinhas = qtdeLinhas;
		QtdeColunas = qtdeColunas;
		
		gerarCampos();
		associarVizinhos(); 
		
	}
	
	public boolean isCampoAberto(int linha, int coluna) {
		return campos.parallelStream()
				.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
				.anyMatch(c -> c.isAberto()); 
	}
	
	public boolean isCampoFechado(int linha, int coluna) {
		return campos.parallelStream()
				.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
				.anyMatch(c -> c.isFechado()); 
	}
	
	
	public void abrirCampo(int linha, int coluna) {
		try {
			campos.parallelStream()
			.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
			.findFirst()
			.ifPresent(c -> c.abrir());
			
		} catch (Exception e) {
			//FIXME Ajustar a implementação do método abrir
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
		
	}
	
	public boolean isCampoMarcado(int linha, int coluna) {
		return campos.parallelStream()
				.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
				.anyMatch(c -> c.isMarcado());
	}
	
	public void marcarCampo(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMacacao());
		
	}
	
	public boolean isCampoMinado(int linha, int coluna) {
		return campos.parallelStream()
		.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
		.anyMatch(c -> c.isMinado());
	}
	
	public void minarCampo(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
		.findFirst()
		.ifPresent(c -> c.setMinado());
		
	}


	private void gerarCampos() {
		
		for (int i = 0; i < QtdeLinhas; i++) {
			for(int j = 0; j < QtdeColunas; j++) {
				campos.add(new Campo(i, j));
			}
			
		}
		
	}
	
	
	private void associarVizinhos() {
		
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2); 		
			}	
		}
		
	}
	
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		
		do {
			
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).setMinado();
			minasArmadas = campos.stream().filter(minado).count();
			
		} while (minasArmadas < QtdeMinas); 

	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.isLimpo());
	}

	public void reiniciarTabuleiro() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	@Override
	public void eventoOcorreu(Campo c, CampoEvento e) {
		if(e == CampoEvento.EXPLODIR) {
			System.out.println("Perdeu :(");
		} else if(objetivoAlcancado()) {
			System.out.println("Ganhou :)");
		}
	}

}