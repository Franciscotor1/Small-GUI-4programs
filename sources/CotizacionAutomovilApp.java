import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CotizacionAutomovilApp {
    private int numCotizacion;
    private String descripcion;
    private double precio;
    private double porcentajePagoInicial;
    private int plazo;

    private JTextField txtNumCotizacion;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtPorcentajePagoInicial;
    private JTextField txtPlazo;
    private JButton btnMostrarCotizacion;

    public CotizacionAutomovilApp() {
        JFrame frame = new JFrame("Cotización Automóvil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        txtNumCotizacion = new JTextField(10);
        txtDescripcion = new JTextField(10);
        txtPrecio = new JTextField(10);
        txtPorcentajePagoInicial = new JTextField(10);
        txtPlazo = new JTextField(10);
        btnMostrarCotizacion = new JButton("Mostrar Cotización");

        btnMostrarCotizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCotizacion();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Num Cotizacion:"));
        panel.add(txtNumCotizacion);
        panel.add(new JLabel("Descripcion:"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Precio:"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Porcentaje Pago Inicial:"));
        panel.add(txtPorcentajePagoInicial);
        panel.add(new JLabel("Plazo:"));
        panel.add(txtPlazo);

        frame.add(panel);
        frame.add(btnMostrarCotizacion);

        frame.setVisible(true);
    }

    private void mostrarCotizacion() {
        try {
            numCotizacion = Integer.parseInt(txtNumCotizacion.getText());
            descripcion = txtDescripcion.getText();
            precio = Double.parseDouble(txtPrecio.getText());
            porcentajePagoInicial = Double.parseDouble(txtPorcentajePagoInicial.getText());
            plazo = Integer.parseInt(txtPlazo.getText());

            if (numCotizacion <= 0 || precio <= 0 || porcentajePagoInicial < 0 || porcentajePagoInicial > 100 || plazo <= 0) {
                throw new NumberFormatException();
            }

            CotizacionAutomovil cotizacion = new CotizacionAutomovil(numCotizacion, descripcion, precio, porcentajePagoInicial, plazo);
            cotizacion.mostrarCotizacion();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese valores válidos y positivos para los campos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CotizacionAutomovilApp();
            }
        });
    }

    private class CotizacionAutomovil {
        private int numCotizacion;
        private String descripcion;
        private double precio;
        private double porcentajePagoInicial;
        private int plazo;

        public CotizacionAutomovil(int numCotizacion, String descripcion, double precio, double porcentajePagoInicial, int plazo) {
            this.numCotizacion = numCotizacion;
            this.descripcion = descripcion;
            this.precio = precio;
            this.porcentajePagoInicial = porcentajePagoInicial;
            this.plazo = plazo;
        }

        public void mostrarCotizacion() {
            double totalFinanciar = precio - (precio * (porcentajePagoInicial / 100));
            double pagoMensual = totalFinanciar / plazo;

            String mensaje = "Num Cotizacion: " + numCotizacion + "\n" +
                    "Descripcion: " + descripcion + "\n" +
                    "Precio: " + precio + "\n" +
                    "Porcentaje Pago Inicial: " + porcentajePagoInicial + "%\n" +
                    "Plazo: " + plazo + " meses\n\n" +
                    "Calculos:\n" +
                    "Total a financiar: " + totalFinanciar + "\n" +
                    "Pago Mensual: " + pagoMensual;

            JOptionPane.showMessageDialog(null, mensaje);
        }
    }
}
