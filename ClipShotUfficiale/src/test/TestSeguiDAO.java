package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Manager.SeguiDAO;
import Manager.UtenteDAO;
import Model.SeguiBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestSeguiDAO extends TestCase {
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteBean utenteBean2 = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO(); 
	
	private SeguiBean seguiBean = new SeguiBean();
	private SeguiDAO seguiDAO = new SeguiDAO();
	private ArrayList<SeguiBean> listaSeguaci = new ArrayList<>();
	@Before
	public void setUp() {
		utenteBean.setIdUtente("test");
		utenteBean.setPassword("test123");
		utenteBean.setEmail("test@testo.com");
		utenteBean.setNome("test");
		utenteBean.setCognome("test");
		utenteBean.setDataNascita(new GregorianCalendar(1980, 12, 12));
		utenteBean.setSesso("M");
		utenteBean.setIndirizzo("test via");
		utenteBean.setStato("FREE");
		utenteBean.setTipo("BASE");
		utenteBean.setFotoProfilo("pathFoto");
		
		utenteBean2.setIdUtente("testing");
		utenteBean2.setPassword("test123");
		utenteBean2.setEmail("test@test.com");
		utenteBean2.setNome("test");
		utenteBean2.setCognome("test");
		utenteBean2.setDataNascita(new GregorianCalendar(1980, 12, 12));
		utenteBean2.setSesso("M");
		utenteBean2.setIndirizzo("test via");
		utenteBean2.setStato("FREE");
		utenteBean2.setTipo("BASE");
		utenteBean2.setFotoProfilo("pathFoto");
		
		seguiBean.setIdFollower("test");
		seguiBean.setIdFollowing("testing");
	}
	@After
	public void tearDown() {
		seguiDAO.doDelete(seguiBean);
		utenteDAO.doDelete(utenteBean);
		utenteDAO.doDelete(utenteBean2);
	}
	@Test
	public void testDoSave() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		boolean result = seguiDAO.doSave(seguiBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		seguiDAO.doSave(seguiBean);
		SeguiBean result = seguiDAO.doRetrieveByKey(seguiBean.getIdFollower(), seguiBean.getIdFollowing());
		assertEquals(seguiBean.getIdFollower(), result.getIdFollower());
		assertEquals(seguiBean.getIdFollowing(), result.getIdFollowing());
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		seguiDAO.doSave(seguiBean);
		listaSeguaci.add(seguiBean);
		ArrayList<SeguiBean> result = seguiDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaSeguaci.get(i).getIdFollower(), result.get(i).getIdFollower());
			assertEquals(listaSeguaci.get(i).getIdFollowing(), result.get(i).getIdFollowing());
		}
	}
	@Test 
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		seguiDAO.doSave(seguiBean);
		boolean result = seguiDAO.doDelete(seguiBean);
		assertEquals(true, result);
		
	}
}
