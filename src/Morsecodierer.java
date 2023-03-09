import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Morsecodierer {

    public BinaryTree<Character> baum;

    public static void main(String[] args){
        Morsecodierer program = new Morsecodierer();
        program.morseLaden();
        program.zeigeBaum();

    }

    public Morsecodierer(){
        baum = new BinaryTree<Character>(' ');
    }

    public String encodiereText(String pText){
        return "";
    }

    public String decodiereText(String pText){
        return "";
    }

    public void zeigeBaum(){
        BaumZeichner zeichner = new BaumZeichner(1280,960, baum);
    }

    private void morseLaden() {
        String  line;
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File("C:\\IntelliJ Workspace\\Morsecodierer\\src\\morse.csv")));
            while ((line = in.readLine()) != null) {
                verarbeiteZeile(line);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Dateifehler "+ e.getMessage());
        }
    }

    private void verarbeiteZeile(String line) {
        Character z = line.charAt(0);
        String code = line.substring(2);
        fuegeEin(z, code);
    }

    private void fuegeEin(Character pZeichen, String pCode) {
        baum = fuegeEin(pZeichen, pCode, baum);
    }

    private BinaryTree<Character> fuegeEin(Character pZeichen, String pCode, BinaryTree<Character> pTree){
        Character z = pZeichen;
        if (pTree.isEmpty()) {
            pTree = new BinaryTree<Character>(z);
        }
        if (pCode.equals("")) {
            pTree.setContent(z);
        } else {
            if (pCode.charAt(0)== '.') {
                pTree.setLeftTree(fuegeEin(pZeichen, pCode.substring(1), pTree.getLeftTree()));
            } else {
                if (pCode.charAt(0) == '-') {
                    pTree.setRightTree(fuegeEin(pZeichen, pCode.substring(1), pTree.getRightTree()));
                }
                else {
                    System.out.println("Fehler!");
                }
            }
        }
        return pTree;
    }


}
