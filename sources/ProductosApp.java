import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductosApp {
    private JTextField txtCodigoProducto;
    private JTextField txtDescripcion;
    private JTextField txtUnidadMedida;
    private JTextField txtPrecioCompra;
    private JTextField txtPrecioVenta;
    private JTextField txtCantidad;

    private ProductoInventario producto;

    public ProductosApp() {
        JFrame frame = new JFrame("Ingreso de Producto al Inventario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 2));

        // Crear componentes
        JLabel lblCodigoProducto = new JLabel("Código Producto:");
        txtCodigoProducto = new JTextField();

        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField();

        JLabel lblUnidadMedida = new JLabel("Unidad de Medida:");
        txtUnidadMedida = new JTextField();

        JLabel lblPrecioCompra = new JLabel("Precio de Compra:");
        txtPrecioCompra = new JTextField();

        JLabel lblPrecioVenta = new JLabel("Precio de Venta:");
        txtPrecioVenta = new JTextField();

        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        JButton btnMostrar = new JButton("Mostrar");

        // Agregar componentes al panel
        frame.add(lblCodigoProducto);
        frame.add(txtCodigoProducto);

        frame.add(lblDescripcion);
        frame.add(txtDescripcion);

        frame.add(lblUnidadMedida);
        frame.add(txtUnidadMedida);

        frame.add(lblPrecioCompra);
        frame.add(txtPrecioCompra);

        frame.add(lblPrecioVenta);
        frame.add(txtPrecioVenta);

        frame.add(lblCantidad);
        frame.add(txtCantidad);

        frame.add(btnGuardar);
        frame.add(btnMostrar);

        // Configurar eventos de los botones
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProducto();
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private void guardarProducto() {
        try {
            int codigoProducto = Integer.parseInt(txtCodigoProducto.getText());
            String descripcion = txtDescripcion.getText();
            String unidadMedida = txtUnidadMedida.getText();
            double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
            double precioVenta = Double.parseDouble(txtPrecioVenta.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());

            // Crear una instancia de ProductoInventario
            producto = new ProductoInventario(codigoProducto, descripcion, unidadMedida, precioCompra, precioVenta, cantidad);
            producto.calcularPrecioVenta();
            producto.calcularPrecioCompra();
            producto.calcularGanancia();

            JOptionPane.showMessageDialog(null, "Producto guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese valores válidos para los campos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarProducto() {
        if (producto != null) {
            JOptionPane.showMessageDialog(null, "Código Producto: " + producto.getCodigoProducto() + "\n" +
                                                "Descripción: " + producto.getDescripcion() + "\n" +
                                                "Unidad de Medida: " + producto.getUnidadMedida() + "\n" +
                                                "Precio de Compra: $" + producto.getPrecioCompra() + "\n" +
                                                "Precio de Venta: $" + producto.getPrecioVenta() + "\n" +
                                                "Cantidad de Productos: " + producto.getCantidad() + "\n" +
                                                "Cálculo Precio Venta: $" + producto.getCalculoPrecioVenta() + "\n" +
                                                "Cálculo Precio Compra: $" + producto.getCalculoPrecioCompra() + "\n" +
                                                "Cálculo de Ganancia: $" + producto.getCalculoGanancia(), "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Primero debe guardar un producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductosApp();
            }
        });
    }
}

class ProductoInventario {
    private int codigoProducto;
    private String descripcion;
    private String unidadMedida;
    private double precioCompra;
    private double precioVenta;
    private int cantidad;
    private double calculoPrecioVenta;
    private double calculoPrecioCompra;
    private double calculoGanancia;

    public ProductoInventario(int codigo, String descripcion, String unidad, double precioCompra,
                              double precioVenta, int cantidad) {
        this.codigoProducto = codigo;
        this.descripcion = descripcion;
        this.unidadMedida = unidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
    }

    public void calcularPrecioVenta() {
        calculoPrecioVenta = precioVenta * cantidad;
    }

    public void calcularPrecioCompra() {
        calculoPrecioCompra = precioCompra * cantidad;
    }

    public void calcularGanancia() {
        calculoGanancia = calculoPrecioVenta - calculoPrecioCompra;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getCalculoPrecioVenta() {
        return calculoPrecioVenta;
    }

    public double getCalculoPrecioCompra() {
        return calculoPrecioCompra;
    }

    public double getCalculoGanancia() {
        return calculoGanancia;
    }
}
