package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Manager.AbbonamentoDAO;
import Manager.AcquistiDAO;
import Manager.CartaDiCreditoDAO;
import Manager.FotoDAO;
import Manager.LikeDAO;
import Manager.PostDAO;
import Manager.StatisticheDAO;
import Manager.UtenteDAO;
import Model.AbbonamentoBean;
import Model.AcquistiBean;
import Model.CartaDiCreditoBean;
import Model.FotoBean;
import Model.LikeBean;
import Model.PostBean;
import Model.StatisticheBean;
import Model.UtenteBean;
import junit.framework.TestCase;

public class TestStatisticheDAO extends TestCase {
	private UtenteBean utenteBean = new UtenteBean();
	private UtenteBean utenteBean2 = new UtenteBean();
	private UtenteDAO utenteDAO = new UtenteDAO();
	
	private CartaDiCreditoBean cartaDiCreditoBean = new CartaDiCreditoBean();
	private CartaDiCreditoDAO cartaDiCreditoDAO = new CartaDiCreditoDAO();
	
	private AbbonamentoBean abbonamentoBean = new AbbonamentoBean();
	private AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO();
	
	private PostBean postBean = new PostBean();
	private PostDAO postDAO = new PostDAO();
	
	private LikeBean likeBean = new LikeBean();
	private LikeDAO likeDAO = new LikeDAO();
	
	private FotoBean fotoBean = new FotoBean();
	private FotoDAO fotoDAO = new FotoDAO();
	
	private AcquistiBean acquistiBean = new AcquistiBean();
	private AcquistiDAO acquistiDAO = new AcquistiDAO();
	
	private StatisticheBean statisticheBean = new StatisticheBean();
	private StatisticheDAO statisticheDAO = new StatisticheDAO();
	private ArrayList <StatisticheBean> listaStatisticheBean = new ArrayList<>();
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
		
		statisticheBean.setIdUtente("test");
		statisticheBean.setDataInizio(new GregorianCalendar(2012, 02, 12));
		statisticheBean.setDataFine(new GregorianCalendar(2012, 02, 19));
		statisticheBean.setNumeroVisualizzazioni(50);
		
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
		
		acquistiBean.setIdUtente("test");
		acquistiBean.setIdFoto(0);
		acquistiBean.setData(new GregorianCalendar(2019, 01, 02));
		
		likeBean.setIdUtente("testing");
		likeBean.setIdPost(0);
		likeBean.setIdUtentePost("test");
	}
	@After
	public void tearDown() throws SQLException {
		statisticheDAO.doDelete(statisticheBean);
		abbonamentoDAO.doDelete(utenteBean.getIdUtente());
		cartaDiCreditoDAO.doDelete(cartaDiCreditoBean.getNumeroCarta());
		utenteDAO.doDelete(utenteBean);
		likeDAO.doDelete(likeBean);
		utenteDAO.doDelete(utenteBean2);
		postDAO.doDelete(postBean);
		fotoDAO.doDelete(fotoBean);
	}
	@Test
	public void testDoSave() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		boolean result = statisticheDAO.doSave(statisticheBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		statisticheDAO.doSave(statisticheBean);
		StatisticheBean result = statisticheDAO.doRetrieveByKey(statisticheBean.getIdUtente(), statisticheBean.getDataInizio(), statisticheBean.getDataFine());
		assertEquals(statisticheBean.getIdUtente(), result.getIdUtente());
		assertEquals(statisticheBean.getStringDataInizio(), result.getStringDataInizio());
		assertEquals(statisticheBean.getStringDataFine(), result.getStringDataFine());
		assertEquals(statisticheBean.getNumeroVisualizzazioni(), result.getNumeroVisualizzazioni());
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		statisticheDAO.doSave(statisticheBean);
		statisticheBean.setNumeroVisualizzazioni(30);
		StatisticheBean result = statisticheDAO.doSaveOrUpdate(statisticheBean);
		assertEquals(statisticheBean.getIdUtente(), result.getIdUtente());
		assertEquals(statisticheBean.getStringDataInizio(), result.getStringDataInizio());
		assertEquals(statisticheBean.getStringDataFine(), result.getStringDataFine());
		assertEquals(statisticheBean.getNumeroVisualizzazioni(), result.getNumeroVisualizzazioni());
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		statisticheDAO.doSave(statisticheBean);
		listaStatisticheBean.add(statisticheBean);
		ArrayList<StatisticheBean> result = statisticheDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaStatisticheBean.get(i).getIdUtente(), result.get(i).getIdUtente());
			assertEquals(listaStatisticheBean.get(i).getStringDataInizio(), result.get(i).getStringDataInizio());
			assertEquals(listaStatisticheBean.get(i).getStringDataFine(), result.get(i).getStringDataFine());
			assertEquals(listaStatisticheBean.get(i).getNumeroVisualizzazioni(), result.get(i).getNumeroVisualizzazioni());
		}
	}
	@Test 
	public void testDoRetrieveByCondNLike() throws SQLException {
		utenteDAO.doSave(utenteBean);
		utenteDAO.doSave(utenteBean2);
		fotoDAO.doSave(fotoBean);
		postDAO.doSave(postBean);
		likeDAO.doSave(likeBean);
		String result = statisticheDAO.doRetrieveByCondNLike(likeBean.getIdUtentePost(), fotoBean.getIdFoto());
		assertEquals("0", result);
	}
	@Test 
	public void testDoRetrieveByCondNAcquisti() throws SQLException {
		fotoDAO.doSave(fotoBean);
		acquistiDAO.doSave(acquistiBean);
		String result = statisticheDAO.doRetrieveByCondNLike(likeBean.getIdUtentePost(), fotoBean.getIdFoto());
		assertEquals("0", result);
	}
	@Test 
	public void testDoDelete() throws SQLException {
		utenteDAO.doSave(utenteBean);
		cartaDiCreditoDAO.doSave(cartaDiCreditoBean);
		abbonamentoDAO.doSave(abbonamentoBean);
		statisticheDAO.doSave(statisticheBean);
		boolean result = statisticheDAO.doDelete(statisticheBean);
		assertEquals(true, result);
	}
}
