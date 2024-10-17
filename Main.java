import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    ArrayList<String> simbolos = new ArrayList<String>();

    public Main() {
        simbolos.add("else");
        simbolos.add("for");
        simbolos.add("while");
    }
    
    //leitura do arquivo
    public void leArquivo(){
        File file = new File("entrada1.txt");
        try (Scanner r = new Scanner(file)) {
            while (r.hasNext()) {
                for (Character c : r.next().toCharArray()) {
                    if (c == '>' | c == '<' | c == '=' | c == '!') simula_AFD_RELOPS();
                    else if(Character.isLetter(c))                 simula_AFD_IDS();
                    else if (Character.isDigit(c))                 simula_AFD_NUMS();
                    else                                           throwErro();

                }
            }
            r.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //identificação de token tipo identificador/palavra-chave
    public void simula_AFD_IDS() {

    }

    //identificação de token tipo numeral
    public void simula_AFD_NUMS() {

    }

    //identificação de token tipo operador-relacional
    public void simula_AFD_RELOPS() {

    }

    public void throwErro() {

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
