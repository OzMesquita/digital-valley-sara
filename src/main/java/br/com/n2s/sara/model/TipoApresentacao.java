package br.com.n2s.sara.model;

public enum TipoApresentacao {

	POSTER("Pôster"), ORAL("Oral");

	private String label;

	private TipoApresentacao(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
