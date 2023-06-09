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

public class ModificarProducto implements WindowListener, ActionListener{
	Frame ventana = new Frame("Editar Producto");
	Dialog dlgEdicion = new Dialog(ventana, "Edición", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	
	Label lblElegir = new Label("Elegir el producto a editar:");
	
	Choice choProductos = new Choice();
	
	Button btnEditar = new Button("Editar");

	Conexion conexion = new Conexion();
	
	Label lblTitulo = new Label("------- Edición de Producto -------");
	Label lblNombreProducto = new Label("Nombre:");
	Label lblTipo = new Label("Tipo:      ");
	Label lblStock = new Label("Stock:    ");
	Label lblPrecio = new Label("Precio:  ");
	Label lblMensaje = new Label("Modificación de Producto Correcta");
	
	TextField txtNombreProducto = new TextField(10);
	TextField txtTipo = new TextField(10);
	TextField txtStock = new TextField(10);
	TextField txtPrecio = new TextField(10);
	
	Button btnModificar = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");

	String idProducto = "";
	
	ModificarProducto()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220,220);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice
		conexion.rellenarChoiceProducto(choProductos);
		ventana.add(choProductos);
		btnEditar.addActionListener(this);
		ventana.add(btnEditar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgEdicion.isActive())
		{
			dlgEdicion.setVisible(false);
			ventana.setVisible(false);
		}
		else if (dlgMensaje.isActive())
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
		if(e.getSource().equals(btnEditar))
		{
			if(choProductos.getSelectedIndex()!=0)
			{
				dlgEdicion.setLayout(new FlowLayout());
				dlgEdicion.setSize(220,220);
				dlgEdicion.addWindowListener(this);

				dlgEdicion.add(lblTitulo);
				dlgEdicion.add(lblNombreProducto);
				dlgEdicion.add(txtNombreProducto);
				dlgEdicion.add(lblTipo);
				dlgEdicion.add(txtTipo);
				dlgEdicion.add(lblStock);
				dlgEdicion.add(txtStock);
				dlgEdicion.add(lblPrecio);
				dlgEdicion.add(txtPrecio);

				btnModificar.addActionListener(this);
				btnCancelar.addActionListener(this);

				dlgEdicion.add(btnModificar);
				dlgEdicion.add(btnCancelar);

				dlgEdicion.setResizable(false);
				dlgEdicion.setLocationRelativeTo(null);

				String tabla[] = choProductos.getSelectedItem().split("-");
				String resultado = conexion.getDatosEdicion(tabla[0]);

				String datos[] = resultado.split("-");
				idProducto = datos[0];
				txtNombreProducto.setText("");
				txtTipo.setText("");
				txtStock.setText("");
				txtPrecio.setText("");

				dlgEdicion.setVisible(true);
			}
		}
		
		else if(e.getSource().equals(btnModificar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(200,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombreProducto.getText().length()==0||txtTipo.getText().length()==0||txtStock.getText().length()==0||txtPrecio.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			// Comprobar las claves
			else
			{
				// Modificar
				String sentencia = "UPDATE producto SET nombreProducto ='"+txtNombreProducto.getText()+"', tipoProducto = '"+txtTipo.getText()+"', stockProducto = '"+txtStock.getText()+"', precioProducto = '"+txtPrecio.getText()+"' WHERE idProducto="+idProducto+";";
				int respuesta = conexion.modificarProducto(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Modificación");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if (e.getSource().equals(btnCancelar))
		{
			dlgEdicion.setVisible(false);
		}
	}
}
