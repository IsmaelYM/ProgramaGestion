package es.studium.Programa_Gestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoProveedor implements WindowListener, ActionListener {
	Frame ventana = new Frame("Nuevo Proveedor");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTitulo = new Label("        Alta de Proveedor        ");
	Label lblNombreProveedor = new Label("Nombre:               ");
	Label lblPrimerApellidoProveedor = new Label("Primer Apellido:   ");
	Label lblSegundoApellidoProveedor = new Label("Segundo Apellido:");
	Label lblTlf = new Label("Tlf:                        ");
	Choice choProductos = new Choice();
	Label lblMensaje = new Label("Alta de Proveedor Correcta");

	TextField txtNombreProveedor = new TextField(10);
	TextField txtPrimerApellidoProveedor = new TextField(10);
	TextField txtSegundoApellidoProveedor = new TextField(10);
	TextField txtTlf = new TextField(10);
	TextField txtStock = new TextField(10);
	

	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	
	
	String idProducto = "";
	Conexion conexion = new Conexion();

	NuevoProveedor() {

		ventana.setLayout(new FlowLayout());
		ventana.setSize(190, 350);
		ventana.addWindowListener(this);

		ventana.add(lblTitulo);
		ventana.add(lblNombreProveedor);
		ventana.add(txtNombreProveedor);
		ventana.add(lblPrimerApellidoProveedor);
		ventana.add(txtPrimerApellidoProveedor);
		ventana.add(lblSegundoApellidoProveedor);
		ventana.add(txtSegundoApellidoProveedor);
		ventana.add(lblTlf);
		ventana.add(txtTlf);
		
		conexion.rellenarChoiceProducto(choProductos);
		ventana.add(choProductos);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			ventana.setVisible(false);
		}
	}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Nuevo Producto
		if(e.getSource().equals(btnAceptar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(200,100);
			dlgMensaje.addWindowListener(this);
			
			String tabla[] = choProductos.getSelectedItem().split("-");
			//String resultado = conexion.getDatosEdicionProducto(tabla[0]);
			//String datos[] = resultado.split("-");
			idProducto = tabla[0];
			//TextField txtNombre = new TextField();
			//txtNombre.setText(datos[1]);
			
			if(txtNombreProveedor.getText().length()==0||txtTlf.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			else
			{
				
				// Dar de alta
				String sentencia = "INSERT INTO proveedor (idProveedor, nombreProveedor, primerApellidoProveedor, segundoApellidoProveedor, telefonoProveedor, idProductoFK) VALUES (null, '"+txtNombreProveedor.getText()+"', '"+txtPrimerApellidoProveedor.getText()+"','"+txtSegundoApellidoProveedor.getText()+"','"+txtTlf.getText()+"', "+idProducto+");";

				int respuesta = conexion.altaProveedor(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtNombreProveedor.setText("");
			txtPrimerApellidoProveedor.setText("");
			txtSegundoApellidoProveedor.setText("");
			txtTlf.setText("");
			txtNombreProveedor.requestFocus();
		}
	}
	
}