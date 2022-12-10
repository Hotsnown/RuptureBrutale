package com.example.application.controllers;

import org.salve.drools.controller.DroolInterface;

public class DroolController implements DroolInterface {
  
    public String answer;

    public void respond(String text) {
        answer = text;
    }
}
