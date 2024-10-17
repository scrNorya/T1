import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Token {
    public String tipo;
    public String valor;

    public Token(String tipo, String valor){
        this.tipo = tipo;
        this.valor = valor;
    }

    public Token(String tipo) {
        this.tipo = tipo;
    }
}

public class Main {
    ArrayList<String> simbolos = new ArrayList<String>();
    File file = new File("entrada1.txt");
    Scanner r;
    int line;
    int col;

    public Main() {
        simbolos.add("else");
        simbolos.add("for");
        simbolos.add("while");
        try {
            r = new Scanner(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while(r.hasNextLine()) {
            char[] c = r.nextLine().toCharArray();
            line++;
            col = 0;

            if (c[col] == '>' | c[col] == '<' | c[col] == '=' | c[col] ==  '!') 
                                                    simula_AFD_RELOPS();
             else if(Character.isLetter(c[col]))    simula_AFD_IDS();
             else if (Character.isDigit(c[col]))    simula_AFD_NUMS();
             else                                   throwErro();
        }
        r.close();
    }
    
    //identificação de token tipo operador-relacional
    public void simula_AFD_RELOPS() {

    }
    
    //identificação de token tipo identificador/palavra-chave
    public void simula_AFD_IDS() {

    }

    //identificação de token tipo numeral
    public void simula_AFD_NUMS() {
        
    }

    public void throwErro() {

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
