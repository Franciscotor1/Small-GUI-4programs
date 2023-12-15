import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamenNominasApp extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtNumeroRecibo;
    private JTextField txtNombre;
    private JComboBox<String> cmbPuesto;
    private JComboBox<String> cmbTipoContrato;
    private JTextField txtDiasTrabajados;
    private JTextArea txtAreaResultados;

    public ExamenNominasApp() {
        setTitle("Examen Nominas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(6, 2));
        add(panelFormulario, BorderLayout.NORTH);

        JLabel lblNumeroRecibo = new JLabel("Número de recibo:");
        txtNumeroRecibo = new JTextField();
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblPuesto = new JLabel("Puesto:");
        cmbPuesto = new JComboBox<>(new String[]{"Auxiliar", "Albañil", "Ing. de Obra"});
        JLabel lblTipoContrato = new JLabel("Tipo de contrato:");
        cmbTipoContrato = new JComboBox<>(new String[]{"Base", "Eventual"});
        JLabel lblDiasTrabajados = new JLabel("Días trabajados:");
        txtDiasTrabajados = new JTextField();

        panelFormulario.add(lblNumeroRecibo);
        panelFormulario.add(txtNumeroRecibo);
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblPuesto);
        panelFormulario.add(cmbPuesto);
        panelFormulario.add(lblTipoContrato);
        panelFormulario.add(cmbTipoContrato);
        panelFormulario.add(lblDiasTrabajados);
        panelFormulario.add(txtDiasTrabajados);

        JButton buttonCalcular = new JButton("Calcular y Mostrar Recibo");
        buttonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularMostrarRecibo();
            }
        });

        txtAreaResultados = new JTextArea();
        txtAreaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaResultados);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton buttonSalir = new JButton("Salir");
        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelBotones.add(buttonCalcular);
        panelBotones.add(buttonSalir);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void calcularMostrarRecibo() {
        try {
            int numeroRecibo = Integer.parseInt(txtNumeroRecibo.getText());
            String nombre = txtNombre.getText();
            int puesto = cmbPuesto.getSelectedIndex() + 1; // índice base 0
            int tipoContrato = cmbTipoContrato.getSelectedIndex() + 1; // índice base 0
            int diasTrabajados = Integer.parseInt(txtDiasTrabajados.getText());

            double pago = calcularPago(puesto, diasTrabajados);
            double impuesto = calcularImpuesto(tipoContrato, pago);
            double totalPagar = pago - impuesto;

            String resultado = "Recibo de Nómina\n" +
                    "Num. Recibo: " + numeroRecibo + " Nombre: " + nombre + "\n" +
                    "Puesto: " + puesto + " Tipo Contrato: " + tipoContrato + " Días Trabajados: " + diasTrabajados + "\n" +
                    "Calcular Pago: " + pago + "\n" +
                    "Calculo Impuesto: " + impuesto + "\n" +
                    "Total a Pagar: " + totalPagar + "\n\n";

            txtAreaResultados.append(resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos para los campos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calcularPago(int puesto, int diasTrabajados) {
        double pago = 0;
        switch (puesto) {
            case 1:
                pago = 100 * diasTrabajados; // Auxiliar
                break;
            case 2:
                pago = 200 * diasTrabajados; // Albañil
                break;
            case 3:
                pago = 300 * diasTrabajados; // Ing. de Obra
                break;
        }
        return pago;
    }

    private double calcularImpuesto(int tipoContrato, double pago) {
        double impuesto = 0;
        switch (tipoContrato) {
            case 1:
                impuesto = pago * 0.03; // Eventual
                break;
            case 2:
                impuesto = pago * 0.05; // Base
                break;
        }
        return impuesto;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExamenNominasApp().setVisible(true);
        });
    }
}
