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

public class TestAcquistiDAO extends TestCase{
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO();
	
	private FotoBean fotoBean = new FotoBean();
	private FotoDAO fotoDAO = new FotoDAO();
	
	private AcquistiBean acquistiBean = new AcquistiBean();
	private AcquistiDAO acquistiDAO = new AcquistiDAO();
	private ArrayList<AcquistiBean> listaFotoAcquistateBean = new ArrayList<>();
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

		fotoBean.setIdFoto(0);
		fotoBean.setPath("pathFoto");
		fotoBean.setPrezzo(2.00);
		
		acquistiBean.setIdUtente("test");
		acquistiBean.setIdFoto(0);
		acquistiBean.setData(new GregorianCalendar(2019, 01, 02));
	}
	@After
	public void tearDown() throws SQLException {
		acquistiDAO.doDelete(acquistiBean);
		fotoDAO.doDelete(fotoBean);
		utenteDAO.doDelete(utenteBean);
	}
	@Test
	public void testDoSave() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		boolean result = acquistiDAO.doSave(acquistiBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		acquistiDAO.doSave(acquistiBean);
		AcquistiBean result = acquistiDAO.doRetrieveByKey(acquistiBean.getIdUtente(), acquistiBean.getIdFoto());
		assertEquals(acquistiBean.getIdUtente(), result.getIdUtente());
		assertEquals(acquistiBean.getIdFoto(), result.getIdFoto());
		assertEquals(acquistiBean.getStringData(), result.getStringData());
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		acquistiDAO.doSave(acquistiBean);
		acquistiBean.setData(new GregorianCalendar(2004, 12, 12));
		AcquistiBean result = acquistiDAO.doSaveOrUpdate(acquistiBean);
		assertEquals(acquistiBean.getIdUtente(), result.getIdUtente());
		assertEquals(acquistiBean.getIdFoto(), result.getIdFoto());
		assertEquals(acquistiBean.getStringData(), result.getStringData());
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		acquistiDAO.doSave(acquistiBean);
		listaFotoAcquistateBean.add(acquistiBean);
		ArrayList<AcquistiBean> result = acquistiDAO.doRetrieveByAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaFotoAcquistateBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaFotoAcquistateBean.get(i).getIdFoto(), result.get(i).getIdFoto());
			assertEquals(listaFotoAcquistateBean.get(i).getStringData(), result.get(i).getStringData());
		}
	}
	@Test 
	public void testDoRetrieveByCond() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		acquistiDAO.doSave(acquistiBean);
		listaFotoAcquistateBean.add(acquistiBean);
		ArrayList<AcquistiBean> result = acquistiDAO.doRetrieveByCond(acquistiBean.getIdUtente());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaFotoAcquistateBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaFotoAcquistateBean.get(i).getIdFoto(), result.get(i).getIdFoto());
			assertEquals(listaFotoAcquistateBean.get(i).getStringData(), result.get(i).getStringData());
		}
	}
	@Test 
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		acquistiDAO.doSave(acquistiBean);
		boolean result = acquistiDAO.doDelete(acquistiBean);
		assertEquals(true, result);
	}
}
