package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Manager.AcquistiDAO;
import Manager.FotoDAO;
import Manager.UtenteDAO;
import Model.AcquistiBean;
import Model.FotoBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestFotoDAO extends TestCase {
	private FotoBean fotoBean = new FotoBean();
	private FotoDAO fotoDAO = new FotoDAO();
	private AcquistiBean acquistiBean = new AcquistiBean();
	private AcquistiDAO acquistiDAO = new AcquistiDAO();
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO();
	private ArrayList<FotoBean> listaFotoBean = new ArrayList<>();
	@Before
	public void setUp() {
		fotoBean.setIdFoto(0);
		fotoBean.setPath("pathFoto");
		fotoBean.setPrezzo(2.00);
		
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
		
		acquistiBean.setIdUtente("test");
		acquistiBean.setIdFoto(0);
		acquistiBean.setData(new GregorianCalendar(2019, 01, 02));
	}
	@After
	public void tearDown() {
		fotoDAO.doDelete(fotoBean);
		acquistiDAO.doDelete(acquistiBean);
	}
	@Test
	public void testDoSave() {
		boolean result = fotoDAO.doSave(fotoBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		fotoDAO.doSave(fotoBean);
		FotoBean result = fotoDAO.doRetrieveByKey(fotoBean.getIdFoto());
		assertEquals(fotoBean.getIdFoto(), result.getIdFoto());
		assertEquals(fotoBean.getPath(), result.getPath());
		assertEquals(fotoBean.getPrezzo(), result.getPrezzo());
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		fotoDAO.doSave(fotoBean);
		fotoBean.setPath("fotoPath");
		FotoBean result = fotoDAO.doSaveOrUpdate(fotoBean);
		assertEquals(fotoBean.getIdFoto(), result.getIdFoto());
		assertEquals(fotoBean.getPath(), result.getPath());
		assertEquals(fotoBean.getPrezzo(), result.getPrezzo());
	}
	@Test
	public void testDoRetrieveMaxId() throws SQLException {
		fotoDAO.doSave(fotoBean);
		int result = fotoDAO.doRetrieveMaxId();
		assertEquals(0, result);
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		fotoDAO.doSave(fotoBean);
		listaFotoBean.add(fotoBean);
		ArrayList<FotoBean> result = fotoDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaFotoBean.get(i).getIdFoto(), result.get(i).getIdFoto());
			assertEquals(listaFotoBean.get(i).getPath(), result.get(i).getPath());
			assertEquals(listaFotoBean.get(i).getPrezzo(), result.get(i).getPrezzo());
		}
	}
	@Test
	public void testDoRetrieveByCond() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		acquistiDAO.doSave(acquistiBean);
		listaFotoBean.add(fotoBean);
		FotoBean result = fotoDAO.doRetrieveByCond(acquistiBean.getIdUtente(), acquistiBean.getIdFoto());
		assertEquals(fotoBean.getPath(), result.getPath());
	}
	@Test
	public void testDoRetrieveByCondFoto() throws SQLException {
		fotoDAO.doSave(fotoBean);
		listaFotoBean.add(fotoBean);
		ArrayList<FotoBean> result = fotoDAO.doRetrieveByCondFoto(utenteBean.getIdUtente());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(fotoBean.getIdFoto(), result.get(i).getIdFoto());
			assertEquals(fotoBean.getPath(), result.get(i).getPath());
		}
	}
	@Test 
	public void testDoDelete() throws SQLException {
		fotoDAO.doSave(fotoBean);
		boolean result = fotoDAO.doDelete(fotoBean);
		assertEquals(true, result);
		
	}
}
