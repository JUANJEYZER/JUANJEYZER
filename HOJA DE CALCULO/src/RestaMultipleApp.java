import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaMultipleApp extends JFrame {

    private int numeroCasillas = 20; // Número de pares de casillas de resta
    private JTextField[] inputEntradaFields;
    private JTextField[] inputSalidaFields;
    private JTextField[] resultadoFields;
    private JTextField colectadoField;
    private JTextField premiosField;
    private JTextField retencionField;

    public RestaMultipleApp() {
        setTitle("Resta Múltiple");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JPanel casillasPanel = new JPanel();
        casillasPanel.setLayout(new BoxLayout(casillasPanel, BoxLayout.Y_AXIS));

        inputEntradaFields = new JTextField[numeroCasillas];
        inputSalidaFields = new JTextField[numeroCasillas];
        resultadoFields = new JTextField[numeroCasillas];

        for (int i = 0; i < numeroCasillas; i++) {
            JPanel casillaPanel = new JPanel();
            casillaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel tituloEntradaLabel = new JLabel("Entrada " + (i + 1) + ":");
            JTextField inputEntrada = new JTextField("0", 5);
            JLabel tituloSalidaLabel = new JLabel("Salida " + (i + 1) + ":");
            JTextField inputSalida = new JTextField("0", 5);
            JTextField resultado = new JTextField(10);

            inputEntradaFields[i] = inputEntrada;
            inputSalidaFields[i] = inputSalida;
            resultadoFields[i] = resultado;

            casillaPanel.add(tituloEntradaLabel);
            casillaPanel.add(inputEntrada);
            casillaPanel.add(tituloSalidaLabel);
            casillaPanel.add(inputSalida);
            casillaPanel.add(resultado);

            casillasPanel.add(casillaPanel);
        }

        colectadoField = new JTextField(10);
        colectadoField.setEditable(false);
        premiosField = new JTextField(10);
        premiosField.setEditable(false);
        retencionField = new JTextField(10);
        retencionField.setEditable(false);

        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        resultadosPanel.add(new JLabel("Colectado:"));
        resultadosPanel.add(colectadoField);
        resultadosPanel.add(new JLabel("Premios:"));
        resultadosPanel.add(premiosField);
        resultadosPanel.add(new JLabel("Retención:"));
        resultadosPanel.add(retencionField);

        JButton generarResultadosButton = new JButton("Generar Resultados");
        generarResultadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarResultados();
            }
        });

        add(casillasPanel);
        add(resultadosPanel);
        add(generarResultadosButton);

        pack();
        setLocationRelativeTo(null);
    }

    private void generarResultados() {
        double colectado = 0;
        double premios = 0;

        for (int i = 0; i < numeroCasillas; i++) {
            double entrada = Double.parseDouble(inputEntradaFields[i].getText());
            double salida = Double.parseDouble(inputSalidaFields[i].getText());
            double resta = entrada - salida;

            resultadoFields[i].setText(Double.toString(resta));

            colectado += entrada;
            premios += salida;
        }

        double retencion = premios * 0.055;

        colectadoField.setText(Double.toString(colectado));
        premiosField.setText(Double.toString(premios));
        retencionField.setText(Double.toString(retencion));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RestaMultipleApp().setVisible(true);
            }
        });
    }
}
