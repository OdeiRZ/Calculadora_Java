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

public class Calculadora extends JFrame {

    private boolean nuevaOperacion;
    
    private double resultado;
    
    private String operacion;
    
    private final JPanel panelOperaciones = new JPanel();
    
    private final JPanel panelNumeros = new JPanel();
    
    private final JTextField pantalla = new JTextField("0.", 20);
    
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
    
    protected void numeroPulsado(String digito) {
        if(pantalla.getText().equals("0") || nuevaOperacion)
            pantalla.setText(digito);
        else
            pantalla.setText(pantalla.getText() + digito);
        nuevaOperacion = false;
    }
    
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
    
    public static void main(String[] args) {
        new Calculadora().setVisible(true);
    }
}