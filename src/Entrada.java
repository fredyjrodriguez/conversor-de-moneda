public class Entrada {




    public boolean validarEntrada(String entrada){
        if (entrada == null || entrada.length() != 3) {
            return false;
        }
        for (int i = 0; i < entrada.length(); i++) {
            char currentChar = entrada.charAt(i);
            if (!Character.isLetter(currentChar)) {
                return false;
            }
        }
        return true;
    }
}
