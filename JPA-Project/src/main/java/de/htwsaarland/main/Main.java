package de.htwsaarland.main;

import de.htwsaarland.dao.ArbeitsspeicherDao;
import de.htwsaarland.dao.BetriebssystemDao;
import de.htwsaarland.dao.NotebookDao;
import de.htwsaarland.model.Arbeitsspeicher;
import de.htwsaarland.model.Betriebssystem;
import de.htwsaarland.model.Notebook;

/**
 * @author Marc Koster
 *
 */
public class Main {

  public static void main(String[] args) {
	  NotebookDao ndao = new NotebookDao();
	  Notebook notebook = new Notebook("Lenovo yoga5");
//	  ndao.save(notebook);
//	  System.out.println(ndao.findAll());
//
////	  BS
	  BetriebssystemDao bdao = new BetriebssystemDao();
	  Betriebssystem betriebssystem = new Betriebssystem();
//	  betriebssystem.setName("windows 7");
//	  bdao.save(betriebssystem);
//	  System.out.println(bdao.findAll());
//	  
//	  //AS
	  ArbeitsspeicherDao adao = new ArbeitsspeicherDao();
	  Arbeitsspeicher arbei = new Arbeitsspeicher();
//	  arbei.setName("Kingston XSDE");
//	  adao.save(arbei);
//	  System.out.println(adao.find(((long)1)));
//	  
  }
}
