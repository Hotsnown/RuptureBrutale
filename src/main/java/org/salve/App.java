package org.salve;

import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import javax.tools.FileObject;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.utll.xml.AnyAnnotationTypePermission;
import org.salve.drools.GameStart;
import org.salve.drools.UnparsedUserUtterance;
import org.salve.personality.model.Believes;
import nl.jappieklooster.ymlbot.YapFactory;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.javatuples.Triplet;
import org.salve.drools.model.db.SymbolDatabase;
import org.salve.drools.model.db.PatternCollection;
import org.salve.drools.controller.DroolInterface;

import java.util.logging.Logger;

public class App 
{
    public static void main( String[] args ) throws FileSystemException
    {

        String baseName = "rules2";
        String scenarioName = "agnese2";

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        KieSession kSession = kieContainer.newKieSession("anamnesi_ksession");


        kSession.setGlobal("random", new Random());
        kSession.setGlobal("log", Logger.getLogger("kie"));

        // the controller global is used by drools to interact with the 
        // outside world
        kSession.setGlobal("controller", new DroolInterface());

        URL   resourceurl = ClassLoader.getSystemClassLoader().getResource("bots/agnese2/yml");
        org.apache.commons.vfs2.FileObject folder = VFS.getManager().resolveFile(resourceurl);
        Triplet<Believes, SymbolDatabase, PatternCollection> result = new YapFactory().create(folder);
        
        kSession.insert(result.getValue(0));
        kSession.insert(result.getValue(1));
        kSession.insert(result.getValue(2));

        GameStart start = new GameStart();
        kSession.insert(start);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            chatWithBot(scanner.nextLine(), kSession);
            kSession.fireAllRules();
        }
    }

    private static void chatWithBot(String userMsg, KieSession kSession) {
    	kSession.insert(new UnparsedUserUtterance(userMsg));
    }
}
