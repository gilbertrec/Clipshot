package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Manager.UtenteDAO;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestUtenteDAO extends TestCase {
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO(); 
	private ArrayList<UtenteBean> utentiBean = new ArrayList<>();
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
	}
	@After
	public void tearDown() {
		utenteDAO.doDelete(utenteBean);
	}
	@Test
	public void testDoSave() {
		boolean result = utenteDAO.doSave(utenteBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		utenteDAO.doSave(utenteBean);
		UtenteBean result = utenteDAO.doRetrieveByKey(utenteBean.getIdUtente());
		assertEquals(utenteBean.getIdUtente(), result.getIdUtente());
		assertEquals(utenteBean.getPassword(), result.getPassword());
		assertEquals(utenteBean.getEmail(), result.getEmail());
		assertEquals(utenteBean.getNome(), result.getNome());
		assertEquals(utenteBean.getCognome(), result.getCognome());
		assertEquals(utenteBean.getDataNascita(), result.getDataNascita());
		assertEquals(utenteBean.getSesso(), result.getSesso());
		assertEquals(utenteBean.getIndirizzo(), result.getIndirizzo());
		assertEquals(utenteBean.getStato(), result.getStato());
		assertEquals(utenteBean.getTipo(), result.getTipo());
		assertEquals(utenteBean.getFotoProfilo(), result.getFotoProfilo());
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteBean.setIdUtente("porcodio");
		//utenteBean.setCognome("Marmora");
		UtenteBean result = utenteDAO.doSaveOrUpdate(utenteBean);
		assertEquals(utenteBean.getIdUtente(), result.getIdUtente());
		assertEquals(utenteBean.getPassword(), result.getPassword());
		assertEquals(utenteBean.getEmail(), result.getEmail());
		assertEquals(utenteBean.getNome(), result.getNome());
		assertEquals(utenteBean.getCognome(), result.getCognome());
		assertEquals(utenteBean.getDataNascita(), result.getDataNascita());
		assertEquals(utenteBean.getSesso(), result.getSesso());
		assertEquals(utenteBean.getIndirizzo(), result.getIndirizzo());
		assertEquals(utenteBean.getStato(), result.getStato());
		assertEquals(utenteBean.getTipo(), result.getTipo());
		assertEquals(utenteBean.getFotoProfilo(), result.getFotoProfilo());
	}
	@Test 
	public void testDoRetrieveByCond() throws SQLException {
		utenteDAO.doSave(utenteBean);
		UtenteBean utenteBean2 = new UtenteBean();
		utenteBean2.setCognome("Cruoglio");
		utentiBean = utenteDAO.doRetrieveByCond(utenteBean2);
		for (int i = 0; i < utentiBean.size(); i++) {
			assertEquals(utenteBean.getCognome(), utentiBean.get(i).getCognome());
		}
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utentiBean.add(utenteBean);
		ArrayList<UtenteBean> result = utenteDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(utentiBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(utentiBean.get(i).getPassword(), result.get(i).getPassword());
			assertEquals(utentiBean.get(i).getEmail(), result.get(i).getEmail());
			assertEquals(utentiBean.get(i).getNome(), result.get(i).getNome());
			assertEquals(utentiBean.get(i).getCognome(), result.get(i).getCognome());
			assertEquals(utentiBean.get(i).getDataNascita(), result.get(i).getDataNascita());
			assertEquals(utentiBean.get(i).getSesso(), result.get(i).getSesso());
			assertEquals(utentiBean.get(i).getIndirizzo(), result.get(i).getIndirizzo());
			assertEquals(utentiBean.get(i).getStato(), result.get(i).getStato());
			assertEquals(utentiBean.get(i).getTipo(), result.get(i).getTipo());
			assertEquals(utentiBean.get(i).getFotoProfilo(), result.get(i).getFotoProfilo());
		}
	}
	@Test
	public void testDoRetrieveByKeyOrNomeOrCognome1() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utentiBean.add(utenteBean);
		ArrayList<UtenteBean> result = utenteDAO.doRetrieveByKeyOrNomeOrCognome(utenteBean.getIdUtente());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(utentiBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(utentiBean.get(i).getPassword(), result.get(i).getPassword());
			assertEquals(utentiBean.get(i).getEmail(), result.get(i).getEmail());
			assertEquals(utentiBean.get(i).getNome(), result.get(i).getNome());
			assertEquals(utentiBean.get(i).getCognome(), result.get(i).getCognome());
			assertEquals(utentiBean.get(i).getDataNascita(), result.get(i).getDataNascita());
			assertEquals(utentiBean.get(i).getSesso(), result.get(i).getSesso());
			assertEquals(utentiBean.get(i).getIndirizzo(), result.get(i).getIndirizzo());
			assertEquals(utentiBean.get(i).getStato(), result.get(i).getStato());
			assertEquals(utentiBean.get(i).getTipo(), result.get(i).getTipo());
			assertEquals(utentiBean.get(i).getFotoProfilo(), result.get(i).getFotoProfilo());
		}
	}
	@Test
	public void testDoRetrieveByKeyOrNomeOrCognome2() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utentiBean.add(utenteBean);
		ArrayList<UtenteBean> result = utenteDAO.doRetrieveByKeyOrNomeOrCognome(utenteBean.getNome());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(utentiBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(utentiBean.get(i).getPassword(), result.get(i).getPassword());
			assertEquals(utentiBean.get(i).getEmail(), result.get(i).getEmail());
			assertEquals(utentiBean.get(i).getNome(), result.get(i).getNome());
			assertEquals(utentiBean.get(i).getCognome(), result.get(i).getCognome());
			assertEquals(utentiBean.get(i).getDataNascita(), result.get(i).getDataNascita());
			assertEquals(utentiBean.get(i).getSesso(), result.get(i).getSesso());
			assertEquals(utentiBean.get(i).getIndirizzo(), result.get(i).getIndirizzo());
			assertEquals(utentiBean.get(i).getStato(), result.get(i).getStato());
			assertEquals(utentiBean.get(i).getTipo(), result.get(i).getTipo());
			assertEquals(utentiBean.get(i).getFotoProfilo(), result.get(i).getFotoProfilo());
		}
	}
	@Test
	public void testDoRetrieveByKeyOrNomeOrCognome3() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utentiBean.add(utenteBean);
		ArrayList<UtenteBean> result = utenteDAO.doRetrieveByKeyOrNomeOrCognome(utenteBean.getCognome());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(utentiBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(utentiBean.get(i).getPassword(), result.get(i).getPassword());
			assertEquals(utentiBean.get(i).getEmail(), result.get(i).getEmail());
			assertEquals(utentiBean.get(i).getNome(), result.get(i).getNome());
			assertEquals(utentiBean.get(i).getCognome(), result.get(i).getCognome());
			assertEquals(utentiBean.get(i).getDataNascita(), result.get(i).getDataNascita());
			assertEquals(utentiBean.get(i).getSesso(), result.get(i).getSesso());
			assertEquals(utentiBean.get(i).getIndirizzo(), result.get(i).getIndirizzo());
			assertEquals(utentiBean.get(i).getStato(), result.get(i).getStato());
			assertEquals(utentiBean.get(i).getTipo(), result.get(i).getTipo());
			assertEquals(utentiBean.get(i).getFotoProfilo(), result.get(i).getFotoProfilo());
		}
	}
	@Test 
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		boolean result = utenteDAO.doDelete(utenteBean);
		assertEquals(true, result);
		
	}
}
