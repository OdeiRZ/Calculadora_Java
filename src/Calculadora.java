import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * Clase Calculadora que extiende de JFrame.
 * Se encarga de generar los elementos multimedia utilizados para implementar
 * la interfaz gráfica y los métodos y gestión de nuestra aplicación.
 *
 * @author Odei
 * @version 25.11.2014
 */
public class Calculadora extends JFrame {
    /**
     * Variable booleana que habilita o no las nuevas operaciones.
     */
    private boolean nuevaOperacion;
    
    /**
     * Variable real que almacena los resultados de las operaciones.
     */
    private double resultado;
    
    /**
     * Variable de tipo cadena que almacena las operaciones a realizar.
     */
    private String operacion;
    
    /**
     * Variable de tipo panel que almacena los botones operacionales.
     */
    private final JPanel panelOperaciones = new JPanel();
    
    /**
     * Variable de tipo panel que almacena los botones numéricos.
     */
    private final JPanel panelNumeros = new JPanel();
    
    /**
     * Variable etiqueta usada para mostrar la pantalla de valores de 
     * nuestra calculadora durante la ejecución de la misma.
     */
    private final JTextField pantalla = new JTextField("0.", 20);
    
    /**
     * Constructor principal de la aplicación.
     * Inicializa la Interfaz y los elementos multimedia utilizados
     * para visualizar de forma interactiva la ejecución del programa.
     */
    protected Calculadora() {
        setSize(350, 300);
        setResizable(false);
        setTitle("Calculadora");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().createImage("src/recursos/logo.png"));
        
        JPanel panel = (JPanel) this.getContentPane();
        
        pantalla.setFont(new Font("Arial", Font.BOLD, 20));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setBackground(Color.yellow);
        pantalla.setEditable(false);  
        panel.add("North", pantalla);
 
        panelNumeros.setLayout(new GridLayout(4, 3, 15, 15));
        panelNumeros.setBorder(new EmptyBorder(5, 10, 10, 10));
 
        for(int i = 9; i >= 0; i--) 
            nuevoBotonNumerico("" + i);
        nuevoBotonNumerico(".");
        nuevaOperacion = true;
 
        panel.add("Center", panelNumeros);
 
        panelOperaciones.setLayout(new GridLayout(4, 2, 15, 15));
        panelOperaciones.setBorder(new EmptyBorder(5, 40, 10, 10));
 
        nuevoBotonOperacion("C");
        nuevoBotonOperacion("CE");
        nuevoBotonOperacion("+");
        nuevoBotonOperacion("-");
        nuevoBotonOperacion("*");
        nuevoBotonOperacion("/");
        nuevoBotonOperacion("=");
        nuevoBotonOperacion("%");
        
        panel.add("East", panelOperaciones);
    }
    
    /**
     * Método usado para introducir y asignar botones numéricos en 
     * nuestra calculadora.
     * 
     * @param digito String: cadena que identifica el botón a añadir.
     */
    protected void nuevoBotonNumerico(String digito) {
        JButton btn = new JButton(digito);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton)evt.getSource();
                numeroPulsado(btn.getText());
            }
        });
        panelNumeros.add(btn);
    }
    
    /**
     * Método usado para introducir y asignar botones operacionales en 
     * nuestra calculadora.
     * 
     * @param operacion String: cadena que identifica el botón a añadir.
     */
    protected void nuevoBotonOperacion(String operacion) {
        JButton btn=new JButton(operacion);
        btn.setForeground(Color.RED);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn=(JButton) evt.getSource();
                operacionPulsado(btn.getText());
            }
        });
        panelOperaciones.add(btn);
    }
    
    /**
     * Método usado para simular la lógica del pulsamiento de dígitos,
     * procesando la petición correspondiente en nuestra calculadora.
     * 
     * @param digito String: cadena que identifica el dígito pulsado.
     */
    protected void numeroPulsado(String digito) {
        if(pantalla.getText().equals("0") || nuevaOperacion)
            pantalla.setText(digito);
        else
            pantalla.setText(pantalla.getText() + digito);
        nuevaOperacion = false;
    }
    
    /**
     * Método usado para simular la lógica del pulsamiento de operaciones,
     * procesando la petición correspondiente en nuestra calculadora.
     * 
     * @param tecla String: cadena que identifica la tecla pulsada.
     */
    protected void operacionPulsado(String tecla) {
        switch (tecla) {
            case "=":
                calcularResultado();
                break;
            case "CE":
                resultado = 0;
                pantalla.setText("0.");
                nuevaOperacion = true;
                break;
            case "C":
                resultado = 0;
                pantalla.setText("0.");
                nuevaOperacion = true;
                break;
            default:
                operacion = tecla;
                if ((resultado > 0) && !nuevaOperacion)
                    calcularResultado();
                else
                    resultado = new Double(pantalla.getText());
                break;
        }
        nuevaOperacion = true;
    }
    
    /**
     * Método utilizado para calcular el resultado de la operación realizada. 
     * Sobrescribe el valor del resultado con el obtenido tras la operación.
     */
    protected void calcularResultado() {
        switch (operacion) {
            case "+":   resultado += new Double(pantalla.getText());    break;
            case "-":   resultado -= new Double(pantalla.getText());    break;
            case "/":   resultado /= new Double(pantalla.getText());    break;
            case "*":   resultado *= new Double(pantalla.getText());    break;
            default:    break;
        }
        pantalla.setText("" + resultado);
        operacion = "";
    }
    
    /**
     * Genera una Interfaz que controla y visualiza la ejecución del programa.
     *
     * @param args String[]: argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        new Calculadora().setVisible(true);
    }
}