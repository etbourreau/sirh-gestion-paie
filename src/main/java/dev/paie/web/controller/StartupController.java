package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import dev.paie.service.InitialiserDonneesService;

@Controller
public class StartupController {
	
	@Autowired
	InitialiserDonneesService initService;

  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() {
	  initService.initialiser();
  }
}