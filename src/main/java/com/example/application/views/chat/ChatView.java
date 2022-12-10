package com.example.application.views.chat;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.javatuples.Triplet;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.salve.drools.GameStart;
import org.salve.drools.UnparsedUserUtterance;
import org.salve.drools.controller.DroolInterface;
import org.salve.drools.model.Scene;
import org.salve.drools.model.db.PatternCollection;
import org.salve.drools.model.db.SymbolDatabase;
import org.salve.personality.model.Believes;
import org.vaadin.artur.Avataaar;

import com.example.application.controllers.DroolController;
import com.example.application.views.MainLayout;
import com.example.application.views.MessageList;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.function.Function;

import nl.jappieklooster.ymlbot.YapFactory;

@PageTitle("chat")
@Route(value = "Chat", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ChatView extends VerticalLayout {

    private final UI ui;
    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private final ScheduledExecutorService executorService;
    private final DroolController controller;

    public ChatView(KieSession kSession, DroolController controller, ScheduledExecutorService executorService) {

        this.executorService = executorService;
        this.controller = controller;

        ui = UI.getCurrent();

        message.setPlaceholder("Enter a message...");
        message.setSizeFull();

        Button send = new Button(VaadinIcon.ENTER.create(), event -> sendMessage(kSession));
        send.addClickShortcut(Key.ENTER);
        
        HorizontalLayout inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");
        add(messageList, inputLayout);
        expand(messageList);
        setSizeFull();

    }

    private void sendMessage(KieSession kSession) {

        String text = message.getValue();
        kSession.insert(new UnparsedUserUtterance(text));
        kSession.fireAllRules();

        messageList.addMessage("You", new Avataaar("Name"), text, true);

        message.clear();
            executorService.schedule(() -> {
                ui.access(() -> messageList.addMessage(
                        "Alice", new Avataaar("Alice2"), controller.answer, false));
            },new Random().ints(1000, 3000).findFirst().getAsInt(),
                                    TimeUnit.MILLISECONDS);

    }

}
