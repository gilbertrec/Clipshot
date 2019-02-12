package test;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Manager.OperatoreDAO;
import Model.OperatoreBean;
import junit.framework.TestCase;

public class TestOperatoreDAO extends TestCase{
	private OperatoreBean operatoreBean = new OperatoreBean();
	private OperatoreDAO operatoreDAO = new OperatoreDAO();
	private ArrayList<OperatoreBean> listaOperatoriBean = new ArrayList<>();
	@Before
	public void setUp() {
		operatoreBean.setUsername("test");
		operatoreBean.setPassword("test");
		operatoreBean.setNome("test");
		operatoreBean.setCognome("ing");
		operatoreBean.setEmail("test@example.it");
		operatoreBean.setTipo("AMMINISTRATORE");
	}
	@After
	public void tearDown() {
		operatoreDAO.doDelete(operatoreBean.getUsername());
	}
	@Test
	public void testDoSave() {
		boolean result = operatoreDAO.doSave(operatoreBean);
		assertEquals(true, result);
	}
	@Test
	public void testDoRetrieveByKey() throws SQLException {
		operatoreDAO.doSave(operatoreBean);
		OperatoreBean result = operatoreDAO.doRetrieveByKey(operatoreBean.getUsername());
		assertEquals(operatoreBean.getUsername(), result.getUsername());
		assertEquals(operatoreBean.getPassword(), result.getPassword());
		assertEquals(operatoreBean.getNome(), result.getNome());
		assertEquals(operatoreBean.getCognome(), result.getCognome());
		assertEquals(operatoreBean.getEmail(), result.getEmail());
		assertEquals(operatoreBean.getTipo(), result.getTipo());
	}
	@Test
	public void testDoSaveOrUpdate() throws SQLException {
		operatoreDAO.doSave(operatoreBean);
		operatoreBean.setEmail("ciao@mitico.it");
		OperatoreBean result = operatoreDAO.doSaveOrUpdate(operatoreBean);
		assertEquals(operatoreBean.getUsername(), result.getUsername());
		assertEquals(operatoreBean.getPassword(), result.getPassword());
		assertEquals(operatoreBean.getNome(), result.getNome());
		assertEquals(operatoreBean.getCognome(), result.getCognome());
		assertEquals(operatoreBean.getEmail(), result.getEmail());
		assertEquals(operatoreBean.getTipo(), result.getTipo());
	}
	@Test
	public void testDoRetrieveByAll() throws SQLException {
		operatoreDAO.doSave(operatoreBean);
		listaOperatoriBean.add(operatoreBean);
		ArrayList<OperatoreBean> result = operatoreDAO.doRetrieveAll();
		for (int i = 0; i < result.size(); i++) {
			assertEquals(listaOperatoriBean.get(i).getUsername(), result.get(i).getUsername());
			assertEquals(listaOperatoriBean.get(i).getPassword(), result.get(i).getPassword());
			assertEquals(listaOperatoriBean.get(i).getNome(), result.get(i).getNome());
			assertEquals(listaOperatoriBean.get(i).getCognome(), result.get(i).getCognome());
			assertEquals(listaOperatoriBean.get(i).getEmail(), result.get(i).getEmail());
			assertEquals(listaOperatoriBean.get(i).getTipo(), result.get(i).getTipo());
		}
	}
	@Test 
	public void testDoDelete() throws SQLException {
		operatoreDAO.doSave(operatoreBean);
		boolean result = operatoreDAO.doDelete(operatoreBean.getUsername());
		assertEquals(true, result);
		
	}
}
