package mx.com.thread;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.com.dao.RegistroDAO;
import mx.com.model.Registro;
import mx.com.object.IngresoFrame;
import javax.swing.JFileChooser;
import mx.com.model.Usuario;
import mx.com.util.UtilidadSession;

/**
 *
 * @author abigail
 */
//clase para especificar el Hilo para el alta de autos
public class HiloAlta implements Runnable {
    
    private Registro registro;//objeto Registro
    RegistroDAO registroDao = new RegistroDAO();//objeto para usar los query de Registro
    private JFileChooser jFileChooser1 = new javax.swing.JFileChooser();//componente para elegir una ruta
    
    public HiloAlta(){}//constructor vacio
    
    public HiloAlta(Registro registro){//constructor para establecer el objeto de registro al de la clase
        this.registro = registro;
    }
    
    //con este metodo le damos al texto del documento, un tamaño a la letra, espacios y tipo de letra al texto que recibe
    private Paragraph getParrafo(float fntSize, float lineSpacing, String text, String letra) throws IOException{
        Paragraph  parrafo = new Paragraph(new Text(text)
        .setFontColor(ColorConstants.BLUE)
        .setFontSize(fntSize)
        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));
        parrafo.setTextAlignment(TextAlignment.CENTER);
        return parrafo;
    }
    
    //metodo para crear el documento en PDF
    private void generarPdf(Registro registro) throws  IOException{
        //le damos a elegir donde guardar el archivo
        jFileChooser1.showSaveDialog(null);
        File archivo =jFileChooser1.getSelectedFile(); //aqui se almacena el nombre del archivo
        if(archivo !=null){//si se solicitó guardar un archivo
            String suffix = ".pdf"; //variable para el sufijo
            //en caso de que no se asignó automáticamente el .pdf
            if(!jFileChooser1.getSelectedFile().getAbsolutePath().endsWith(suffix)){
                archivo = new File(jFileChooser1.getSelectedFile() + suffix); //se asigna a nuestro nombre el .pdf
            }
        }
        //FileOutputStream archivo = new FileOutputStream("hola.pdf");
        Document documento = new Document(new PdfDocument(new PdfWriter(new FileOutputStream(archivo))));
        
        //al titulo le ponemos un tipo de fuente y tamaño
        documento.add(getParrafo(18f,24f,"Estacionamiento Santa Fe",StandardFonts.HELVETICA_BOLDOBLIQUE));
        
        //lineas siguientes del documento (datos especificos del boleto)
        documento.add(new Paragraph("Alta de auto").setTextAlignment(TextAlignment.CENTER));
        documento.add(new Paragraph("No. Boleto: "+registro.getId()));
        documento.add(new Paragraph("Marca: "+registro.getMarca()));
        documento.add(new Paragraph("Modelo: "+registro.getModelo()));
        documento.add(new Paragraph("Placas: "+registro.getPlacas()));
        documento.add(new Paragraph("Color: "+registro.getColor()));
        documento.add(new Paragraph("Propietario: "+ registro.getPropietario()));
        documento.add(new Paragraph("Cajon: "+registro.getCajon()));
        documento.add(new Paragraph("Hora de entrada: "+registro.getEntrada()));
        documento.add(new Paragraph("Hora de salida: "));
        documento.add(new Paragraph("Total a pagar: "));
        Usuario usuario = UtilidadSession.getInstance().getUsuario();
        documento.add(new Paragraph("Acomodador: "+usuario.getNombre()+" "+usuario.getApellido() ));
        Paragraph leyenda = new Paragraph("Presentar este boleto para entregar su auto");
        leyenda.setTextAlignment(TextAlignment.CENTER);
        documento.add(leyenda);
       
        documento.close();
      
        JOptionPane.showMessageDialog(null, "Pdf creado correctamente");//se informa que se ha creado el documento
    }
    
    //metodo sobreeescrito que se implementa de Runnable para correr el Hilo
    @Override
    public void run() {
        int id = registroDao.registro(registro);//se realiza el registro de alta de auto
        registro.setId(id);
        if(id > 0){//si hubo un registro
            //se informa
           JOptionPane.showMessageDialog(null,"Se ha registrado un auto","Informacion",JOptionPane.INFORMATION_MESSAGE); //mensaje de guardado exitoso       
           
           try {
               generarPdf(registro);//enseguida creamos el documento PDF para el boleto de alta
           }  catch (IOException ex) {
               Logger.getLogger(IngresoFrame.class.getName()).log(Level.SEVERE, null, ex);
           } 
        }else{//si no hubo un registro
            //se informa al usuario
           JOptionPane.showMessageDialog(null,"Hubo un error al guardar los datos","Alerta",JOptionPane.WARNING_MESSAGE); //mensaje de guardado exitoso
        } 
    }
    
}