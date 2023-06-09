package es.studium.Programa_Gestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoProducto implements WindowListener, ActionListener{
	Frame ventana = new Frame("Nuevo Producto");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	
	Label lblTitulo = new Label("         Alta de Producto        ");
	Label lblNombreProducto = new Label("Nombre:");
	Label lblTipo = new Label("Tipo:      ");
	Label lblStock = new Label("Stock:    ");
	Label lblPrecio = new Label("Precio:  ");
	Label lblMensaje = new Label("Alta de Producto Correcta");
	
	TextField txtNombre = new TextField(10);
	TextField txtTipo = new TextField(10);
	TextField txtStock = new TextField(10);
	TextField txtPrecio = new TextField(10);
	
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	
	Conexion conexion = new Conexion();
	
	NuevoProducto(){
		
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220,220);
		ventana.addWindowListener(this);
		
		ventana.add(lblTitulo);
		ventana.add(lblNombreProducto);
		ventana.add(txtNombre);
		ventana.add(lblTipo);
		ventana.add(txtTipo);
		ventana.add(lblStock);
		ventana.add(txtStock);
		ventana.add(lblPrecio);
		ventana.add(txtPrecio);
		
		
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
			dlgMensaje.setSize(170,100);
			dlgMensaje.addWindowListener(this);
			if(txtNombre.getText().length()==0||txtTipo.getText().length()==0||txtStock.getText().length()==0||txtPrecio.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			else
			{
				// Dar de alta
				String sentencia = "INSERT INTO producto (idProducto, nombreProducto, tipoProducto, stockProducto, precioProducto) VALUES (null, '"+txtNombre.getText()+"','"+txtTipo.getText()+"','"+txtStock.getText()+"','"+txtPrecio.getText()+"');";
				int respuesta = conexion.altaProducto(sentencia);
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
			txtNombre.setText("");
			txtTipo.setText("");
			txtStock.setText("");
			txtPrecio.setText("");
			txtNombre.requestFocus();
		}
	}
}
