package br.com.n2s.sara.model;

public enum NivelUsuario {
	AUTOR("Autor"),
	AVALIADOR("Avaliador"),
	ADMINISTRADOR("Administrador"),
	COORDENADOR_TRILHA("Coordenador de Trilha"),
	COORDENADOR_EVENTO("Coordenador de Evento"),
	USUARIO("Usuário");
	
	private String label;

	private NivelUsuario(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
