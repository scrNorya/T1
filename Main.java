import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Token {
    private String tipo;
    private String valor;

    public Token(String tipo, String valor){
        this.tipo = tipo;
        this.valor = valor;
    }

    public Token(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTipoEValor() {
        return tipo + " | " + valor;
    }
}

class Lexer {
    ArrayList<String> simbolos = new ArrayList<String>();
    
    File file;
    Scanner r;
    
    char[] c;
    
    int line;
    int col;
    
    ArrayList<String> tokens = new ArrayList<>();

    public Lexer(String pathname) {
        simbolos.add("else");
        simbolos.add("for");
        simbolos.add("while");

        file  = new File(pathname);
    }

    public void scan() {
        try {
            r = new Scanner(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while(r.hasNextLine()) {
            c = r.nextLine().toCharArray();
            col = 0;
            while(c.length > col) {
                if (c[col] == '>' | c[col] == '<' | c[col] == '=') 
                tokens.add(simula_AFD_RELOPS().getTipo());
                else if (Character.isLetter(c[col]))    tokens.add(simula_AFD_IDS().getTipoEValor());
                else if (Character.isDigit(c[col]))     tokens.add(simula_AFD_NUMS().getTipoEValor());
                else if (Character.isWhitespace(c[col])) col++;
                else                                   throwErro();
                line++;
            }
        }
        r.close();
        tokens.forEach((s) -> System.out.println(s));

    }

    //identificação de token tipo operador-relacional
    public Token simula_AFD_RELOPS() {
        int state = 0;
        Token t = new Token("");
        boolean aceita = false;
        while (!aceita) {
            switch (state) {
                //primeiro char
                case 0:
                    switch (c[col]) {
                        case '<': if(c.length > col + 1) {col++; state = 1;} else {col++; state = 4;} break;
                        case '=': col++; state = 5; break;
                        case '>': if(c.length > col + 1) {col++; state = 6;} else {col++; state = 8;} break;
                        default:                                                                      break;
                    }
                    break;
                // segundo char caso <
                case 1: 
                    switch (c[col]) {
                        case '=': col++; state = 2; break;
                        case '>': col++; state = 3; break;
                        default:         state = 4; break;
                    }
                    break;
                // segundo char caso >
                case 6: 
                    switch (c[col]) {
                        case '=': col++; state = 7; break;
                        default:         state = 8; break;
                    }        
                    break; 
                
                //definicao de token
                case 7: t = new Token("maiorIgual"); aceita = true; break;
                case 8: t = new Token("maiorQue");   aceita = true; break;  
                case 2: t = new Token("menorIgual"); aceita = true; break;
                case 4: t = new Token("menorQue");   aceita = true; break;  
                case 5: t = new Token("igual");      aceita = true; break;     
                case 3: t = new Token("notIgual");   aceita = true; break;  
            }
        }
        return t;
    }
    
    //identificação de token tipo identificador/palavra-chave
    public Token simula_AFD_IDS() {
        return null;
    }

    //identificação de token tipo numeral
    public Token simula_AFD_NUMS() {
        return null;
    }

    public void throwErro() {

    }
}

public class Main {
    

    public static void main(String[] args) throws Exception {
        Lexer lex = new Lexer("entrada1.txt");
        lex.scan();
    }
}
