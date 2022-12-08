package com.expertsystem.expertsystem;

import java.util.Scanner;

public class UserDialogue implements Dialogue {
	
	public UserDialogue() {}
	
	public String ask (ID questionId, String question) {
		System.out.print(questionId + " : " + question);
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		return answer;
	}
	
	public void say(String text) {
		System.out.print(text + "\n");
	}
	
	public String askChoice(ID questionId, String question, String choices) {
		
		System.out.print(question);
		System.out.print("A: The answer A;\n");
		System.out.print("B: The answer B;\n");
		System.out.print("C: The answer C;\n");
		System.out.print("D: The answer D;\n");

		
		Scanner keyboard = new Scanner(System.in);
        System.out.println("Select your answer by pressing A, B, C, or D.");
        String input = keyboard.nextLine();

        return input;
	}
	
	public String askAttributeValue(ID questionId, Object attribute) {
		System.out.print(questionId + " : " + "what is " + attribute);
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		return answer;
	}
}