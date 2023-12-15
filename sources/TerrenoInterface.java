import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerrenoInterface extends JFrame {
    private JTextField anchoField;
    private JTextField largoField;
    private JButton calcularButton;

    public TerrenoInterface() {
        // Configuración de la interfaz
        setTitle("Calculadora de Terreno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Creación de componentes
        JLabel anchoLabel = new JLabel("Ancho:");
        JLabel largoLabel = new JLabel("Largo:");

        anchoField = new JTextField(10);
        largoField = new JTextField(10);

        calcularButton = new JButton("Calcular");

        // Configuración del diseño
        JPanel panel = new JPanel();
        panel.add(anchoLabel);
        panel.add(anchoField);
        panel.add(largoLabel);
        panel.add(largoField);
        panel.add(calcularButton);

        add(panel);

        // Configuración del evento del botón
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTerreno();
            }
        });
    }

    private void calcularTerreno() {
        try {
            double ancho = Double.parseDouble(anchoField.getText());
            double largo = Double.parseDouble(largoField.getText());

            if (ancho <= 0 || largo <= 0) {
                throw new NumberFormatException();
            }

            Terreno terreno = new Terreno(ancho, largo);

            JOptionPane.showMessageDialog(this,
                    "Perímetro del terreno: " + terreno.calcularPerimetro() + "\n" +
                    "Área del terreno: " + terreno.calcularArea() + "\n" +
                    "Cantidad de semilla necesaria: " + terreno.calcularCantidadSemilla(0.5),
                    "Resultados", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos y positivos para el ancho y el largo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TerrenoInterface().setVisible(true);
            }
        });
    }
}

class Terreno {
    private double ancho;
    private double largo;

    public Terreno(double ancho, double largo) {
        this.ancho = ancho;
        this.largo = largo;
    }

    public double calcularPerimetro() {
        return 2 * (ancho + largo);
    }

    public double calcularArea() {
        return ancho * largo;
    }

    public double calcularCantidadSemilla(double densidadSemilla) {
        return densidadSemilla * calcularArea();
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }
}
