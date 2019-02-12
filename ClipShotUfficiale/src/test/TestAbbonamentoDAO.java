package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Manager.AbbonamentoDAO;
import Manager.CartaDiCreditoDAO;
import Manager.UtenteDAO;
import Model.AbbonamentoBean;
import Model.CartaDiCreditoBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestAbbonamentoDAO extends TestCase{
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO();
	
	private CartaDiCreditoBean cartaDiCreditoBean = new CartaDiCreditoBean();
	private CartaDiCreditoDAO cartaDiCreditoDAO = new CartaDiCreditoDAO();
	
	private AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
	private AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
	private ArrayList<AbbonamentoBean> listaAbbonamentiBean = new ArrayList<>();
	@Before
	public void setUp() {
		utenteBean.setIdUtente("test");
		utenteBean.setPassword("test123");
		utenteBean.setEmail("test@test.com");
		utenteBean.setNome("test");
		utenteBean.setCognome("test");
		utenteBean.setDataNascita(new GregorianCalendar(1980, 12, 12));
		utenteBean.setSesso("M");
		utenteBean.setIndirizzo("test via");
		utenteBean.setStato("FREE");
		utenteBean.setTipo("BASE");
		utenteBean.setFotoProfilo("pathFoto");
		
		cartaDiCreditoBean.setNumeroCarta("1234567890123456");
		cartaDiCreditoBean.setIdUtente("test");
		cartaDiCreditoBean.setIntestatario("testing");
		cartaDiCreditoBean.setDataScadenza(new GregorianCalendar(2022, 01, 01));
		cartaDiCreditoBean.setCvv("123");
		
		abbonamentoBean.setIdUtente("test");
		abbonamentoBean.setDataScadenza(new GregorianCalendar(2019, 02, 28));
		abbonamentoBean.setNumeroCarta("1234567890123456");
		abbonamentoBean.setStato("ATTIVO");
	}
	@After
	public void tearDown() throws SQLException {
		cartaDiCreditoDAO.doDelete(cartaDiCreditoBean.getNumeroCarta());
		abbonamentoDAO.doDelete(utenteBean.getIdUtente());
		utenteDAO.doDelete(utenteBean);
	}
	@Test
	public void testDoSave() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		boolean result = abbonamentoDAO.doSave(abbonamentoBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		AbbonamentoBean result = abbonamentoDAO.doRetrieveByKey(abbonamentoBean.getIdUtente());
		assertEquals(abbonamentoBean.getIdUtente(), result.getIdUtente());
		assertEquals(abbonamentoBean.getDataScadenza(), result.getDataScadenza());
		assertEquals(abbonamentoBean.getNumeroCarta(), result.getNumeroCarta());
		assertEquals(abbonamentoBean.getStato(), result.getStato());
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		abbonamentoBean.setDataScadenza(new GregorianCalendar(2011, 03, 17));
		AbbonamentoBean result = abbonamentoDAO.doSaveOrUpdate(abbonamentoBean);
		assertEquals(abbonamentoBean.getIdUtente(), result.getIdUtente());
		assertEquals(abbonamentoBean.getDataScadenza(), result.getDataScadenza());
		assertEquals(abbonamentoBean.getNumeroCarta(), result.getNumeroCarta());
		assertEquals(abbonamentoBean.getStato(), result.getStato());
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		listaAbbonamentiBean.add(abbonamentoBean);
		ArrayList<AbbonamentoBean> result = abbonamentoDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaAbbonamentiBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaAbbonamentiBean.get(i).getDataScadenza(), result.get(i).getDataScadenza());
			assertEquals(listaAbbonamentiBean.get(i).getNumeroCarta(), result.get(i).getNumeroCarta());
			assertEquals(listaAbbonamentiBean.get(i).getStato(), result.get(i).getStato());
		}
	}
	@Test 
	public void testDoRetrieveByCond() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		AbbonamentoBean result = abbonamentoDAO.doRetrieveByCond(utenteBean.getIdUtente());
		assertEquals(cartaDiCreditoBean.getNumeroCarta(), result.getNumeroCarta());
	}
	@Test 
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		boolean result = abbonamentoDAO.doDelete(utenteBean.getIdUtente());
		assertEquals(true, result);
	}
}
