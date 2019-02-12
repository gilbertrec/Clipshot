package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Manager.CartaDiCreditoDAO;
import Manager.UtenteDAO;
import Model.CartaDiCreditoBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestCartaDiCreditoDAO extends TestCase {
	private CartaDiCreditoBean cartaDiCreditoBean = new CartaDiCreditoBean();
	private CartaDiCreditoDAO cartaDiCreditoDAO = new CartaDiCreditoDAO();
	private ArrayList<CartaDiCreditoBean> listaCartaDiCreditoBean = new ArrayList<>();
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO();
	
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
	}
	@After
	public void tearDown() {
		cartaDiCreditoDAO.doDelete(cartaDiCreditoBean.getNumeroCarta());
	}
	@Test
	public void testDoSave() throws SQLException {
		utenteDAO.doSave(utenteBean);
		boolean result = cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		assertEquals(true, result);
	}
	@Test 
	public void testDoRetrieveByKey() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		CartaDiCreditoBean result = cartaDiCreditoDAO.doRetrieveByKey(cartaDiCreditoBean.getNumeroCarta());
		assertEquals(cartaDiCreditoBean.getIdUtente(), result.getIdUtente());
		assertEquals(cartaDiCreditoBean.getIntestatario(), result.getIntestatario());
		assertEquals(cartaDiCreditoBean.getDataScadenza(), result.getDataScadenza());
		assertEquals(cartaDiCreditoBean.getCvv(), result.getCvv());
		assertEquals(cartaDiCreditoBean.getNumeroCarta(), result.getNumeroCarta());
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		cartaDiCreditoBean.setCvv("321");
		CartaDiCreditoBean result = cartaDiCreditoDAO.doSaveOrUpdate(cartaDiCreditoBean);
		assertEquals(cartaDiCreditoBean.getIdUtente(), result.getIdUtente());
		assertEquals(cartaDiCreditoBean.getIntestatario(), result.getIntestatario());
		assertEquals(cartaDiCreditoBean.getDataScadenza(), result.getDataScadenza());
		assertEquals(cartaDiCreditoBean.getCvv(), result.getCvv());
		assertEquals(cartaDiCreditoBean.getNumeroCarta(), result.getNumeroCarta());
	}
	@Test 
	public void testDoRetrieveByCond() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		listaCartaDiCreditoBean.add(cartaDiCreditoBean);
		CartaDiCreditoBean result = cartaDiCreditoDAO.doRetrieveByCond(utenteBean.getIdUtente());
		assertEquals(cartaDiCreditoBean.getIdUtente(), result.getIdUtente());
		assertEquals(cartaDiCreditoBean.getIntestatario(), result.getIntestatario());
		assertEquals(cartaDiCreditoBean.getDataScadenza(), result.getDataScadenza());
		assertEquals(cartaDiCreditoBean.getCvv(), result.getCvv());
		assertEquals(cartaDiCreditoBean.getNumeroCarta(), result.getNumeroCarta());
	
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		listaCartaDiCreditoBean.add(cartaDiCreditoBean);
		ArrayList<CartaDiCreditoBean> result = cartaDiCreditoDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaCartaDiCreditoBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaCartaDiCreditoBean.get(i).getIntestatario(), result.get(i).getIntestatario());
			assertEquals(listaCartaDiCreditoBean.get(i).getDataScadenza(), result.get(i).getDataScadenza());
			assertEquals(listaCartaDiCreditoBean.get(i).getCvv(), result.get(i).getCvv());
			assertEquals(listaCartaDiCreditoBean.get(i).getNumeroCarta(), result.get(i).getNumeroCarta());
		}
	}
	@Test 
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		boolean result = cartaDiCreditoDAO.doDelete(cartaDiCreditoBean.getIdUtente());
		assertEquals(true, result);
		
	}
}
