
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuk
 */
public class index extends Window {

    public void redirecionar(String pagina) {
        Executions.getCurrent().sendRedirect(pagina + ".zul");
    }
    public void redirecionarIndex() {
        Executions.getCurrent().sendRedirect("index.zul");
    }
   

}
