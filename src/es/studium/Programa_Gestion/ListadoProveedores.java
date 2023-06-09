package es.studium.Programa_Gestion;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ListadoProveedores implements WindowListener, ActionListener{
	
	Frame ventana = new Frame("Listado Proveedores");
	
	Label lblNombreProveedor = new Label("Nombre");
	Label lblTelefonoProveedor = new Label("Tlf");
	Label lblProductoProveedor = new Label("Proveedor");
	TextArea txaListadoProveedor = new TextArea(6, 40);
	Button btnPdf = new Button("PDF");
	
	Conexion conexion = new Conexion();
	
	ListadoProveedores(){
		ventana.setLayout(new FlowLayout());
		ventana.setSize(400,230);
		ventana.addWindowListener(this);
		
		// Rellenar el TextArea
		conexion.rellenarListadoProveedor(txaListadoProveedor);
				
		ventana.add(lblNombreProveedor);
		ventana.add(lblTelefonoProveedor);
		ventana.add(lblProductoProveedor);
		ventana.add(txaListadoProveedor);
		ventana.add(btnPdf);
		btnPdf.addActionListener(this);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnPdf)) {
			try
			{
			//Initialize PDF writer
			PdfWriter writer = new PdfWriter("imprimir.pdf");
			//Initialize PDF document
			PdfDocument pdf = new PdfDocument(writer);
			// Initialize document
			Document document = new Document(pdf);
			//Add paragraph to the document
			document.add(new Paragraph("Proveedor\n"+"Nombre  -  "+"Tlf  -  "+"Producto \n"+ txaListadoProveedor.getText()));
			//Close document
			document.close();
			// Open the new PDF document just created
			Desktop.getDesktop().open(new File("imprimir.pdf"));
			}
			catch(IOException | java.io.IOException ioe) {}
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventana.setVisible(false);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
