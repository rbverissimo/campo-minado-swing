package br.com.rvrsmo.model;

@FunctionalInterface
public interface CampoObservador {
	
	public void eventoOcorreu(Campo c, CampoEvento e); 

}
