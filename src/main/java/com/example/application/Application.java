package com.example.application;

import com.example.application.controllers.DroolController;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import nl.jappieklooster.ymlbot.YapFactory;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.javatuples.Triplet;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.salve.drools.GameStart;
import org.salve.drools.model.db.PatternCollection;
import org.salve.drools.model.db.SymbolDatabase;
import org.salve.personality.model.Believes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.application.controllers.DroolController;

@SpringBootApplication
@Theme(value = "myapp")
@PWA(name = "My App", shortName = "My App", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
@NpmPackage(value = "@vaadin-component-factory/vcf-nav", version = "1.0.6")
public class Application implements AppShellConfigurator
{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final DroolController controller = new DroolController();

    @Bean
    public DroolController controller() {
        return this.controller;
    }
    @Bean
    public KieSession kSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        KieSession kSession = kieContainer.newKieSession("anamnesi_ksession");

        kSession.setGlobal("random", new Random());
        kSession.setGlobal("log", Logger.getLogger("kie"));

        // the controller global is used by drools to interact with the 
        // outside world
        kSession.setGlobal("controller", controller);

        URL   resourceurl = ClassLoader.getSystemClassLoader().getResource("bots/agnese2/yml");
        org.apache.commons.vfs2.FileObject folder;
        try {
            folder = VFS.getManager().resolveFile(resourceurl);
        } catch (FileSystemException e) {
            folder = null;  
            e.printStackTrace();
        }
        Triplet<Believes, SymbolDatabase, PatternCollection> result = new YapFactory().create(folder);
        
        kSession.insert(result.getValue(0));
        kSession.insert(result.getValue(1));
        kSession.insert(result.getValue(2));

        GameStart start = new GameStart();
        kSession.insert(start);

        return kSession;
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(2);
    }

}
