package models;


public class Calculator {
    private int chiffre1;
    private int chiffre2;
    private int result;

    // Constructeur par défaut (OBLIGATOIRE pour JAXB/XML)
    public Calculator() {
    }

    public Calculator(int chiffre1, int chiffre2, int result) {
        this.chiffre1 = chiffre1;
        this.chiffre2 = chiffre2;
        this.result = result;
    }

    public Calculator add(int chiffre1, int chiffre2) {
        int result = chiffre1 + chiffre2;
        return new Calculator(chiffre1, chiffre2, result);
    }

    public Calculator multiply(int chiffre1, int chiffre2) {
        int result = chiffre1 * chiffre2;
        return new Calculator(chiffre1, chiffre2, result);
    }
    
    public Calculator divise(int chiffre1, int chiffre2) {
        int result = chiffre1 / chiffre2;   // On suppose ici que denominateur != 0
        return new Calculator(chiffre1, chiffre2, result);
    }

    // Getters et Setters
    public int getChiffre1() { return chiffre1; }
    public int getChiffre2() { return chiffre2; }
    public int getResult() { return result; }

    public void setChiffre1(int chiffre1) { this.chiffre1 = chiffre1; }
    public void setChiffre2(int chiffre2) { this.chiffre2 = chiffre2; }
    public void setResult(int result) { this.result = result; }
}