package br.com.n2s.sara;

import br.com.n2s.sara.dao.DAOCriterio;
import br.com.n2s.sara.dao.DAOCriterioTrilha;
import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.dao.DAOTrilha;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Item;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.util.Constantes;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	@org.junit.Test
	public void testRemover() {

		DAOCriterio daoCriterio = new DAOCriterio();
		Criterio criterio = daoCriterio.getCriterio(107);

		DAOTrilha daoTrilha = new DAOTrilha();
		Trilha trilha = daoTrilha.getTrilha(97);

		DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
		
		daoCriterioTrilha.delete(criterio, trilha);
		
		System.out.println();

	}
	
	@org.junit.Test
	public void testEdidarItem() {
		
		try {
			DAOItem daoItem = new DAOItem();
			Item item = daoItem.getItem(135);
			
			int idItem = 135;
			String descricao = "Teste de funcionalidade";
			int peso = 3;

			item.setIdItem(idItem);
			item.setPeso(peso);
			item.setDescricao(descricao);
			
			daoItem.editar(item);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
