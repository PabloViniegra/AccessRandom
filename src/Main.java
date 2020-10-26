import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File fichero = new File("ejemplo.txt");
        escribe(fichero);
        leer(fichero);

    }

    public static void escribe (File fichero) throws IOException {
        /*Random Access File*/
        /*long getFilePointer() --> devuelve la posición actual del puntero*/
        /*void seek (long posicion) --> coloca el puntero del fichero en una posicion detemrinada desde el comienzo del mismo*/
        /*long length() --> devuelve el tamaño del fichero en bytes. La posición length() marca el final del fichero*/
        /*int skipBytes (int desplazamiento) --> Desplaza el puntero desde la posición actual el número de bytes indicado en desplazmaiento*/

        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        String[] nombre = {"pablo", "pepe", "juan", "mariano"};
        int[] dep = {10, 20, 10, 30};
        int[] salario = {1000, 2000, 2500, 1200};

        StringBuffer buffer;
        for (int i = 0; i < nombre.length; i++) {
            try {
                file.writeInt(i + 1);
                buffer = new StringBuffer(nombre[i]);
                buffer.setLength(10);
                String aux = buffer.toString();
                file.writeChars(aux);
                file.writeInt(dep[i]);
                file.writeInt(salario[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*Un char ocupa dos bytes, un short ocupa dos bytes, int y float ocupan 4 bytes. 8 bytes ocupan long y double*/
        }
        file.close();
        /*Vamos a leer el fichero*/
    }

    public static void leer (File fichero) {
        try (RandomAccessFile f = new RandomAccessFile(fichero, "r")) {
            int id, dep, posicion = 0;
            int salario;
            char[] nombre = new char[10];
            char aux;

            while (f.getFilePointer() != f.length()) {
                f.seek(posicion);
                id = f.readInt();

                for (int i = 0; i < nombre.length; i++) {
                    aux = f.readChar();
                    nombre[i] = aux;
                }
                String nombres = new String(nombre);
                dep = f.readInt();
                salario = f.readInt();

                System.out.println("ID: " + id + ", nombre: " + nombres + ", Departamento: " + dep + ", salario: " + salario);
                posicion += 32;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
