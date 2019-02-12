package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Manager.FotoDAO;
import Manager.PostDAO;
import Manager.SegnalazioneDAO;
import Manager.UtenteDAO;
import Model.FotoBean;
import Model.PostBean;
import Model.SegnalazioneBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestSegnalazioneDAO extends TestCase {
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteBean utenteBean2 = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO();
	
	private PostBean postBean = new PostBean();
	private PostDAO postDAO = new PostDAO();
	
	private FotoBean fotoBean = new FotoBean();
	private FotoDAO fotoDAO = new FotoDAO();
	
	private SegnalazioneBean segnalazioneBean = new SegnalazioneBean();
	private SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
	private ArrayList<SegnalazioneBean> listaSegnalazioniBean = new ArrayList<>();
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
		postBean.setIdUtente("testing");
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
		
		segnalazioneBean.setIdSegnalazione(0);
		segnalazioneBean.setIdUtente("test");
		segnalazioneBean.setIdPost(0);
		segnalazioneBean.setIdUtentePost("testing");
		segnalazioneBean.setCausa("spam");
		segnalazioneBean.setStato("in_attesa");
		segnalazioneBean.setData(new GregorianCalendar(2010, 11, 11));
		segnalazioneBean.setDescrizione("descrizione test");
	}
	@After
	public void tearDown() throws SQLException {
		segnalazioneDAO.doDelete(segnalazioneBean.getIdSegnalazione(), utenteBean.getIdUtente());
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
		boolean result = segnalazioneDAO.doSave(segnalazioneBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		segnalazioneDAO.doSave(segnalazioneBean);
		segnalazioneBean.setStato("completata");
		SegnalazioneBean result = segnalazioneDAO.doSaveOrUpdate(segnalazioneBean);
		assertEquals(segnalazioneBean.getIdSegnalazione(), result.getIdSegnalazione());
		assertEquals(segnalazioneBean.getIdUtente(), result.getIdUtente());
		assertEquals(segnalazioneBean.getIdPost(), result.getIdPost());
		assertEquals(segnalazioneBean.getIdUtentePost(), result.getIdUtentePost());
		assertEquals(segnalazioneBean.getCausa(), result.getCausa());
		assertEquals(segnalazioneBean.getStato(), result.getStato());
		assertEquals(segnalazioneBean.getData(), result.getData());
		assertEquals(segnalazioneBean.getDescrizione(), result.getDescrizione());
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException{
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		segnalazioneDAO.doSave(segnalazioneBean);
		SegnalazioneBean result = segnalazioneDAO.doRetrieveByKey(segnalazioneBean.getIdSegnalazione(), utenteBean.getIdUtente());
		assertEquals(segnalazioneBean.getIdSegnalazione(), result.getIdSegnalazione());
		assertEquals(segnalazioneBean.getIdUtente(), result.getIdUtente());
		assertEquals(segnalazioneBean.getIdPost(), result.getIdPost());
		assertEquals(segnalazioneBean.getIdUtentePost(), result.getIdUtentePost());
		assertEquals(segnalazioneBean.getCausa(), result.getCausa());
		assertEquals(segnalazioneBean.getStato(), result.getStato());
		assertEquals(segnalazioneBean.getData(), result.getData());
		assertEquals(segnalazioneBean.getDescrizione(), result.getDescrizione());
	}
	@Test
	public void testDoRetrieveAll() throws SQLException{
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		segnalazioneDAO.doSave(segnalazioneBean);
		listaSegnalazioniBean.add(segnalazioneBean);
		ArrayList<SegnalazioneBean> result = segnalazioneDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaSegnalazioniBean.get(i).getIdSegnalazione(), result.get(i).getIdSegnalazione());
			assertEquals(listaSegnalazioniBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaSegnalazioniBean.get(i).getIdPost(), result.get(i).getIdPost());
			assertEquals(listaSegnalazioniBean.get(i).getIdUtentePost(), result.get(i).getIdUtentePost());
			assertEquals(listaSegnalazioniBean.get(i).getCausa(), result.get(i).getCausa());
			assertEquals(listaSegnalazioniBean.get(i).getStato(), result.get(i).getStato());
			assertEquals(listaSegnalazioniBean.get(i).getData(), result.get(i).getData());
			assertEquals(listaSegnalazioniBean.get(i).getDescrizione(), result.get(i).getDescrizione());
		}
	}
	@Test
	public void testDoRetrieveByCondAttesa() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		segnalazioneDAO.doSave(segnalazioneBean);
		listaSegnalazioniBean.add(segnalazioneBean);
		ArrayList<SegnalazioneBean> result = segnalazioneDAO.doRetrieveByCond("in_attesa");
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaSegnalazioniBean.get(i).getIdSegnalazione(), result.get(i).getIdSegnalazione());
			assertEquals(listaSegnalazioniBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaSegnalazioniBean.get(i).getIdPost(), result.get(i).getIdPost());
			assertEquals(listaSegnalazioniBean.get(i).getIdUtentePost(), result.get(i).getIdUtentePost());
			assertEquals(listaSegnalazioniBean.get(i).getCausa(), result.get(i).getCausa());
			assertEquals(listaSegnalazioniBean.get(i).getStato(), result.get(i).getStato());
			assertEquals(listaSegnalazioniBean.get(i).getData(), result.get(i).getData());
			assertEquals(listaSegnalazioniBean.get(i).getDescrizione(), result.get(i).getDescrizione());
		}
	}
	@Test
	public void testDoRetrieveByCondCompletata() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		segnalazioneDAO.doSave(segnalazioneBean);
		listaSegnalazioniBean.add(segnalazioneBean);
		ArrayList<SegnalazioneBean> result = segnalazioneDAO.doRetrieveByCond("completata");
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaSegnalazioniBean.get(i).getIdSegnalazione(), result.get(i).getIdSegnalazione());
			assertEquals(listaSegnalazioniBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaSegnalazioniBean.get(i).getIdPost(), result.get(i).getIdPost());
			assertEquals(listaSegnalazioniBean.get(i).getIdUtentePost(), result.get(i).getIdUtentePost());
			assertEquals(listaSegnalazioniBean.get(i).getCausa(), result.get(i).getCausa());
			assertEquals(listaSegnalazioniBean.get(i).getStato(), result.get(i).getStato());
			assertEquals(listaSegnalazioniBean.get(i).getData(), result.get(i).getData());
			assertEquals(listaSegnalazioniBean.get(i).getDescrizione(), result.get(i).getDescrizione());
		}
	}
	@Test
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		boolean result = segnalazioneDAO.doDelete(segnalazioneBean.getIdSegnalazione(), utenteBean.getIdUtente());
		assertEquals(true, result);
	}
}
