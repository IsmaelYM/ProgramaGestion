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

public class ListadoCompras implements WindowListener, ActionListener{
	
	Frame ventana = new Frame("Detalles Factura");
	
	Label lblIdFactura = new Label("IdFactura");
	Label lblCliente = new Label("Cliente");
	Label lblFecha = new Label("Fecha");
	Label lblProducto = new Label("Producto");
	Label lblCantidad = new Label("Cantidad");
	Label lblPrecio = new Label("Precio");
	Label lblTotal = new Label("Total");
	TextArea txaListadoFactura = new TextArea(6, 50);
	
	Button btnPdf = new Button("PDF");
	
	Conexion conexion = new Conexion();
	
	ListadoCompras(){
		ventana.setLayout(new FlowLayout());
		ventana.setSize(450,230);
		ventana.addWindowListener(this);
		
		// Rellenar el TextArea
		conexion.rellenarListadoFactura(txaListadoFactura);
				
		ventana.add(lblIdFactura);
		ventana.add(lblCliente);
		ventana.add(lblFecha);
		ventana.add(lblProducto);
		ventana.add(lblCantidad);
		ventana.add(lblPrecio);
		ventana.add(lblTotal);
		ventana.add(txaListadoFactura);
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
			document.add(new Paragraph("Factura\n"+"idProducto  -  "+"Cliente  -  "+"Fecha -  "+"Producto  -  "+"Cantidad  -  "+"Precio  -  "+"Total\n"+ txaListadoFactura.getText()));
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