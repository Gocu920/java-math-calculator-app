package com.example.demo;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.*;

import static java.lang.Integer.parseInt;

public class MathCalculator {
    int boardWidth=800;
    int boardHeight=800;

    JFrame frame=new JFrame("Math Calculator");
    JLabel textLabel=new JLabel();
    JPanel textPanel=new JPanel();
    JPanel boardPanel=new JPanel();
    JPanel toppanel=new JPanel();
    JPanel bottompanel=new JPanel();
    JTextField calculations=new JTextField(40);
    MathCalculator(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.white);
        textLabel.setForeground(Color.black);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("Arial",Font.BOLD,40));
        textLabel.setText("Math Calculator");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPanel.setLayout(new BorderLayout());
        toppanel.setLayout(new GridLayout(1,2));
        bottompanel.setLayout(new GridLayout(4,4));
        JButton deleteButton = new JButton("Delete");
        JButton equalButton = new JButton("=");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 30));
        equalButton.setFont(new Font("Arial", Font.BOLD, 30));
        deleteButton.setBackground(Color.white);
        deleteButton.setForeground(Color.black);
        equalButton.setBackground(Color.white);
        deleteButton.setForeground(Color.black);
        toppanel.add(deleteButton);
        toppanel.add(equalButton);
        String calculation="";
        int length=0;
//        deleteButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                calculations.getText().length();
//              //  deleteButton.setText();
//            }
//        });
        deleteButton.addActionListener(e -> {
            if (e.getSource() == deleteButton) {
                calculations.setText(result(calculations.getText()));
            }
        });
        equalButton.addActionListener(e->{
            if(e.getSource()==equalButton){
                System.out.println(calculations.getText());
                calculations.setText(calculations.getText()+equalButton.getText());
                calculations.setText(mathResult(calculations.getText()));
            }
        });

        String[] StringLabels={"1","2","3","x","4","5","6","+","7","8","9","-","+/-","0",",","/"};
        for(String label:StringLabels){
            JButton button=new JButton(label);
            button.setForeground(Color.black);
            button.setBackground(Color.white);
            button.setFont(new Font("Arial",Font.BOLD,30));
            button.addActionListener(e->{
                String buttonText=button.getText();
                if(button.getText() != "+/-" && button.getText() != ","){
                    calculations.setText(calculations.getText()+buttonText);
                    System.out.println(calculations);
                }
                if(button.getText()==","){
                    calculations.setText(calculations.getText()+",");
                }
            });
            bottompanel.add(button);

        }
        boardPanel.add(toppanel,BorderLayout.SOUTH);
        boardPanel.add(bottompanel,BorderLayout.CENTER);
        calculations.setBackground(Color.white);
        calculations.setFont(new Font("Arial",Font.BOLD,25));
        calculations.setForeground(Color.black);
        calculations.setHorizontalAlignment(JTextField.RIGHT);
        boardPanel.add(calculations,BorderLayout.NORTH);
        frame.add(boardPanel,BorderLayout.CENTER);

        calculations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
    String result(String text){
        int length=text.length();
        String after_deleted_character="";
        for(int i=0;i<length-1;i++){
            after_deleted_character=after_deleted_character+text.charAt(i);
        }
        System.out.println(after_deleted_character);
        return after_deleted_character;
    }
    String mathResult(String text){
        int number=0;
        String math_result="";
        double calculate_num=0;
        String num="";
        char lastchar='a';
        int length=text.length();
        System.out.println(text);
        List<Object> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();
        String value="";
        for(int i=0;i<length;i++){

            if(text.charAt(i)=='+' || text.charAt(i)=='-' || text.charAt(i)=='x' || text.charAt(i)=='/' || text.charAt(i)=='='){
                numbers.add(Double.parseDouble(value));
                value="";
                if(text.charAt(i)!='=') {
                    operators.add(text.charAt(i));
                }
            }else{
                //numbers.add(text.charAt(i));
                if(text.charAt(i)==','){
                    value+='.';
                }else {
                    value += String.valueOf(text.charAt(i));
                }
            }
        }
        List<Object> newnumbers = new ArrayList<>();
        ArrayList<Character> newoperators = new ArrayList<>();
        double temp=(Double)numbers.get(0);
        for(int i=0;i<operators.size();i++) {
            //  System.out.println(i);
            temp = (Double) numbers.get(i);
            if (i == numbers.size() - 1) {
                newnumbers.add(temp);
            } else {
                if (operators.get(i) == '+') {
                    newoperators.add('+');
                    newnumbers.add((Double) numbers.get(i));
                }
                if (operators.get(i) == '-') {
                    newoperators.add('-');
                    newnumbers.add((Double) numbers.get(i));
                }
                if (operators.get(i) == '/') {
                    newoperators.add('/');
                    newnumbers.add((Double) numbers.get(i));
                }
            }
            if (operators.get(i) != '+' && operators.get(i) != '-' && operators.get(i) != '/') {
                while (i <= operators.size()) {
                    System.out.println(i);
                    if (i == operators.size()) {
                        newnumbers.add(temp);
                        break;
                    }
                    if (operators.get(i) == 'x') {
                        temp *= (Double) numbers.get(i + 1);
                        i++;
                    } else {

                        newnumbers.add(temp);
                        newoperators.add(operators.get(i));
                        break;
                        //      }

                    }
                }

            }
            if(i==operators.size()-1){
                if(operators.get(i)!='x' ){//&& operators.get(i)!='/')
                    newnumbers.add(numbers.get(i+1));
                }
            }
        }
        System.out.println(newnumbers);
        System.out.println("Newnumbers:");
        System.out.println(newoperators);
        List<Object> newnumbers2 = new ArrayList<>();
        ArrayList<Character> newoperators2 = new ArrayList<>();
        for(int i=0;i<newoperators.size();i++) {
            //  System.out.println(i);
            temp = (Double) newnumbers.get(i);
            if (i == newnumbers.size() - 1) {
                newnumbers2.add(temp);
            } else {
                if (newoperators.get(i) == '+') {
                    newoperators2.add('+');
                    newnumbers2.add((Double) newnumbers.get(i));
                }
                if (newoperators.get(i) == '-') {
                    newoperators2.add('-');
                    newnumbers2.add((Double) newnumbers.get(i));
                }
            }
            if (newoperators.get(i) != '+' && newoperators.get(i) != '-') { //&& newoperators.get(i) != '/'
                while (i <= newoperators.size()) {
                    System.out.println(i);
                    if (i == newoperators.size()) {
                        newnumbers2.add(temp);
                        break;
                    }
                    if (newoperators.get(i) == '/') {
                        temp /= (Double) newnumbers.get(i + 1);
                        i++;
                    } else {

                        newnumbers2.add(temp);
                        newoperators2.add(newoperators.get(i));
                        break;

                    }
                }

            }
            if(i==newoperators.size()-1){
                if(newoperators.get(i)!='x' && newoperators.get(i)!='/'){
                    newnumbers2.add(newnumbers.get(i+1));
                }
            }
        }
        calculate_num=(Double) newnumbers2.get(0);
        for(int i=0;i<newoperators2.size();i++){
            if(newoperators2.get(i)=='+')
            {
                calculate_num+=(Double)newnumbers2.get(i+1);
            }else{
                calculate_num-= (Double)newnumbers2.get(i+1);
            }

        }

        return String.valueOf(calculate_num);
    }
}
