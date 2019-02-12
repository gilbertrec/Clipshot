package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestUtenteDAO.class, TestCartaDiCreditoDAO.class, TestFotoDAO.class, 
	TestOperatoreDAO.class, TestStatisticheDAO.class, TestSeguiDAO.class,
	TestLikeDAO.class, TestSegnalazioneDAO.class, TestPostDAO.class,
	TestAbbonamentoDAO.class, TestAcquistiDAO.class
})
public class AllTest { 

}
