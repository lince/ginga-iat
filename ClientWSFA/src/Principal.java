
import java.io.FileInputStream;
import java.io.IOException;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
public class Principal {

    public static void main(String[] args) {
        byte[] vet = loadBytes("C:\\Documents and Settings\\Administrador\\Desktop\\PSAI\\PSAI\\interaction.xml");
        //    retornaXML(vet);

        org.apache.ws.axis2.Principal hello = new org.apache.ws.axis2.Principal();
        System.out.println(hello.getPrincipalHttpSoap11Endpoint().retornaXML(vet));
    }

    private static byte[] loadBytes(String name) {
        FileInputStream in = null;

        try {
            in = new FileInputStream(name);
            java.io.ByteArrayOutputStream byteArrayStream/* buffer */ = new java.io.ByteArrayOutputStream();

            int bytesread = 0;
            byte[] tbuff = new byte[1024];
            while ((bytesread = in.read(tbuff)) != -1) {
                byteArrayStream.write(tbuff, 0, bytesread);
            }
            return byteArrayStream.toByteArray();
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e2) {
                }
            }
            return null;
        }
    }
}
