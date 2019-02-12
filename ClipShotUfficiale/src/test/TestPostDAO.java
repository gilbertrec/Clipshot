package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Manager.FotoDAO;
import Manager.PostDAO;
import Manager.SeguiDAO;
import Manager.UtenteDAO;
import Model.FotoBean;
import Model.PostBean;
import Model.SeguiBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestPostDAO extends TestCase{
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteBean utenteBean2 = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO(); 
	
	private SeguiBean seguiBean = new SeguiBean();
	private SeguiDAO seguiDAO = new SeguiDAO();
	
	private FotoBean fotoBean = new FotoBean();
	private FotoDAO fotoDAO = new FotoDAO();
	
	private PostBean postBean = new PostBean();
	private PostDAO postDAO = new PostDAO();
	private ArrayList<PostBean> listaPostBean = new ArrayList<>();
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
		
		fotoBean.setIdFoto(0);
		fotoBean.setPath("pathFoto");
		fotoBean.setPrezzo(2.00);
		
		postBean.setIdPost(0);
		postBean.setIdUtente("test");
		postBean.setIdFoto(0);
		postBean.setDescrizione("descrizionePost");
		postBean.setData(new GregorianCalendar(2019, 01, 02));
		postBean.setOra(new GregorianCalendar());
		postBean.setStato("FREE");
		
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
	public void tearDown() throws SQLException {
		postDAO.doDelete(postBean);
		fotoDAO.doDelete(fotoBean); 
		seguiDAO.doDelete(seguiBean);
		utenteDAO.doDelete(utenteBean);
		utenteDAO.doDelete(utenteBean2);
	}
	@Test
	public void testDoSave() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		boolean result = postDAO.doSave(postBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		postBean.setDescrizione("post update");
		PostBean result = postDAO.doSaveOrUpdate(postBean);
		assertEquals(postBean.getIdPost(), result.getIdPost());
		assertEquals(postBean.getIdUtente(), result.getIdUtente());
		assertEquals(postBean.getIdFoto(), result.getIdFoto());
		assertEquals(postBean.getDescrizione(), result.getDescrizione());
		assertEquals(postBean.getData(), result.getData());
		assertEquals(postBean.getOra(), result.getOra());
		assertEquals(postBean.getStato(), result.getStato());
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException{
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		PostBean result = postDAO.doRetrieveByKey(postBean.getIdFoto(), postBean.getIdUtente());
		assertEquals(postBean.getIdPost(), result.getIdPost());
		assertEquals(postBean.getIdUtente(), result.getIdUtente());
		assertEquals(postBean.getIdFoto(), result.getIdFoto());
		assertEquals(postBean.getDescrizione(), result.getDescrizione());
		assertEquals(postBean.getStringData(), result.getStringData());
		assertEquals(postBean.getStringOra(), result.getStringOra());
		assertEquals(postBean.getStato(), result.getStato());
	}
	@Test
	public void testDoRetrieveAll() throws SQLException{
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		listaPostBean.add(postBean);
		ArrayList<PostBean> result = postDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaPostBean.get(i).getIdPost(), result.get(i).getIdPost());
			assertEquals(listaPostBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaPostBean.get(i).getIdFoto(), result.get(i).getIdFoto());
			assertEquals(listaPostBean.get(i).getDescrizione(), result.get(i).getDescrizione());
			assertEquals(listaPostBean.get(i).getStringData(), result.get(i).getStringData());
			assertEquals(listaPostBean.get(i).getStringOra(), result.get(i).getStringOra());
			assertEquals(listaPostBean.get(i).getStato(), result.get(i).getStato());
		}
	}
	@Test
	public void testDoRetrieveByCond() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		listaPostBean.add(postBean);
		PostBean result = postDAO.doRetrieveByCond(postBean.getIdFoto(), postBean.getIdUtente());
		assertEquals(postBean.getDescrizione(), result.getDescrizione());
	}
	@Test
	public void testDoRetrieveMaxId() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		int result = postDAO.doRetrieveMaxId(utenteBean.getIdUtente());
		assertEquals(0, result);
	}
	@Test
	public void testDoRetrievePostOfFollowing() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		seguiDAO.doSave(seguiBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		listaPostBean.add(postBean);
		ArrayList<PostBean> result = postDAO.doRetrievePostOfFollowing(seguiBean.getIdFollower());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaPostBean.get(i).getIdPost(), result.get(i).getIdPost());
			assertEquals(listaPostBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaPostBean.get(i).getIdFoto(), result.get(i).getIdFoto());
			assertEquals(listaPostBean.get(i).getDescrizione(), result.get(i).getDescrizione());
			assertEquals(listaPostBean.get(i).getStringData(), result.get(i).getStringData());
			assertEquals(listaPostBean.get(i).getStringOra(), result.get(i).getStringOra());
			assertEquals(listaPostBean.get(i).getStato(), result.get(i).getStato());
		}
	}
	@Test
	public void testDoRetrievePostByIdUtente () throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		listaPostBean.add(postBean);
		ArrayList<PostBean> result = postDAO.doRetrievePostByIdUtente(utenteBean.getIdUtente());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaPostBean.get(i).getIdPost(), result.get(i).getIdPost());
			assertEquals(listaPostBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaPostBean.get(i).getIdFoto(), result.get(i).getIdFoto());
			assertEquals(listaPostBean.get(i).getDescrizione(), result.get(i).getDescrizione());
			assertEquals(listaPostBean.get(i).getStringData(), result.get(i).getStringData());
			assertEquals(listaPostBean.get(i).getStringOra(), result.get(i).getStringOra());
			assertEquals(listaPostBean.get(i).getStato(), result.get(i).getStato());
		}
	}
	@Test
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		boolean result = postDAO.doDelete(postBean);
		assertEquals(true, result);
	}
}
