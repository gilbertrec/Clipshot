package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Manager.FotoDAO;
import Manager.LikeDAO;
import Manager.PostDAO;
import Manager.UtenteDAO;
import Model.FotoBean;
import Model.LikeBean;
import Model.PostBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestLikeDAO extends TestCase{
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteBean utenteBean2 = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO();
	
	private PostBean postBean = new PostBean();
	private PostDAO postDAO = new PostDAO();
	
	private FotoBean fotoBean = new FotoBean();
	private FotoDAO fotoDAO = new FotoDAO();
	
	private LikeBean likeBean = new LikeBean();
	private LikeDAO likeDAO = new LikeDAO();
	private ArrayList<LikeBean> listaLikeBean = new ArrayList<>();
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
		
		likeBean.setIdUtente("testing");
		likeBean.setIdPost(0);
		likeBean.setIdUtentePost("test");
	}
	@After
	public void tearDown() throws SQLException {
		likeDAO.doDelete(likeBean);
		postDAO.doDelete(postBean);
		fotoDAO.doDelete(fotoBean); 
		utenteDAO.doDelete(utenteBean);
		utenteDAO.doDelete(utenteBean2);
	}
	@Test
	public void testDoSave() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		boolean result = likeDAO.doSave(likeBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		likeDAO.doSave(likeBean);
		LikeBean result = likeDAO.doRetrieveByKey(likeBean.getIdUtente(), postBean.getIdPost(), likeBean.getIdUtentePost());
		assertEquals(likeBean.getIdUtente(), result.getIdUtente());
		assertEquals(likeBean.getIdPost(), result.getIdPost());
		assertEquals(likeBean.getIdUtentePost(), result.getIdUtentePost());
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		likeDAO.doSave(likeBean);
		listaLikeBean.add(likeBean);
		ArrayList<LikeBean> result = likeDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaLikeBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaLikeBean.get(i).getIdPost(), result.get(i).getIdPost());
			assertEquals(listaLikeBean.get(i).getIdUtentePost(), result.get(i).getIdUtentePost());
		}
	}
	@Test 
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		likeDAO.doSave(likeBean);
		boolean result = likeDAO.doDelete(likeBean);
		assertEquals(true, result);
	}
}
