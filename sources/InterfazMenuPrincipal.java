import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class InterfazMenuPrincipal extends JFrame {
    private Map<String, JFrame> programasAbiertos;

    public InterfazMenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        programasAbiertos = new HashMap<>();

        // Mensaje de bienvenida mejorado
        JButton btnBienvenida = new JButton("<html><div style='text-align: center; color: #2ecc71; font-size: 24pt;'>Bienvenido, Soy Francisco Torres</div></html>");
        configurarBoton(btnBienvenida);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();

        // Pestaña "Proyectos" con flecha
        JButton btnProyectos = new JButton("<html><div style='text-align: center; color: #ffffff; font-size: 16pt;'>Ejecutar &#9658;</div></html>");
        configurarBotonProyectos(btnProyectos);
        btnProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expandirMenu(btnProyectos);
            }
        });
        menuBar.add(btnProyectos);

        // Crear elementos del menú
        JMenuItem itemCotizacion = new JMenuItem("CotizacionAutomovilApp");
        JMenuItem itemProductos = new JMenuItem("ProductosApp");
        JMenuItem itemTerreno = new JMenuItem("TerrenoInterface");
        JMenuItem itemNominas = new JMenuItem("ExamenNominasApp");

        // Agregar elementos al menú
        menuBar.add(itemCotizacion);
        menuBar.add(itemProductos);
        menuBar.add(itemTerreno);
        menuBar.add(itemNominas);

        // Botón "Sobre la app"
        JButton btnSobreLaApp = new JButton("<html><div style='text-align: center; color: #ffffff; font-size: 16pt;'>Sobre la app</div></html>");
        configurarBoton(btnSobreLaApp);
        btnSobreLaApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacionApp();
            }
        });

        // Configuración de colores
        menuBar.setBackground(new Color(41, 128, 185)); // Azul claro
        btnSobreLaApp.setBackground(new Color(52, 152, 219)); // Azul más claro

        // Configurar el diseño del formulario
        setLayout(new GridLayout(3, 1));

        // Agregar componentes al formulario
        add(btnBienvenida);
        add(menuBar);
        add(btnSobreLaApp);
    }

    private void expandirMenu(Component source) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemCotizacion = new JMenuItem("CotizacionAutomovilApp");
        JMenuItem itemProductos = new JMenuItem("ProductosApp");
        JMenuItem itemTerreno = new JMenuItem("TerrenoInterface");
        JMenuItem itemNominas = new JMenuItem("ExamenNominasApp");

        // Configurar eventos de los elementos del menú
        itemCotizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarPrograma("CotizacionAutomovilApp");
            }
        });

        itemProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarPrograma("ProductosApp");
            }
        });

        itemTerreno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarPrograma("TerrenoInterface");
            }
        });

        itemNominas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarPrograma("ExamenNominasApp");
            }
        });

        menu.add(itemCotizacion);
        menu.add(itemProductos);
        menu.add(itemTerreno);
        menu.add(itemNominas);

        menu.show(source, 0, source.getHeight());
    }

    private void ejecutarPrograma(String nombrePrograma) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    if (programasAbiertos.containsKey(nombrePrograma)) {
                        // Si el programa ya está abierto, simplemente hazlo visible
                        programasAbiertos.get(nombrePrograma).setVisible(true);
                    } else {
                        // Si el programa no está abierto, créalo y guárdalo en el mapa
                        Class<?> programaClass = Class.forName(nombrePrograma);
                        JFrame programaInstance = (JFrame) programaClass.getDeclaredConstructor().newInstance();
                        programasAbiertos.put(nombrePrograma, programaInstance);

                        programaInstance.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                // Ocultar la ventana en lugar de cerrarla
                                programaInstance.setVisible(false);
                            }
                        });

                        programaInstance.setVisible(true);
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                        NoSuchMethodException | InvocationTargetException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Funciona correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void mostrarInformacionApp() {
        JOptionPane.showMessageDialog(this,
                "Esta aplicación de menú proporciona acceso a varios proyectos.\n"
                        + "Haga clic en 'Ejecutar' para ver la lista y seleccionar un proyecto.\n"
                        + "El botón 'Sobre la app' muestra información adicional sobre la aplicación.",
                "Sobre la App", JOptionPane.INFORMATION_MESSAGE);
    }

    private void configurarBoton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void configurarBotonProyectos(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setOpaque(true);
        button.setBackground(new Color(41, 128, 185)); // Azul claro
        button.setForeground(Color.white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazMenuPrincipal().setVisible(true);
            }
        });
    }
}
