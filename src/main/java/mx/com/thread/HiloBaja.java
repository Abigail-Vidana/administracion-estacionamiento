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
import mx.com.dao.RegistroDAO;
import mx.com.model.Registro;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import mx.com.dao.CajonDAO;
import mx.com.model.Cajon;
import mx.com.model.Usuario;
import mx.com.object.IngresoFrame;
import mx.com.util.UtilidadSession;

/**
 *
 * @author abigail
 */
//clase para especificar el Hilo para la baja de autos
public class HiloBaja implements Runnable{

    private Registro registro;//objeto Registro
    RegistroDAO registroDao = new RegistroDAO();//objeto para usar los query de Registro
    private JFileChooser jFileChooser1 = new javax.swing.JFileChooser();//componente para elegir una ruta
    
    public HiloBaja(){}//constructor vacio
    
    public HiloBaja(Registro registro){//constructor para establecer el objeto de registro al de la clase
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
        documento.add(new Paragraph("Baja de auto").setTextAlignment(TextAlignment.CENTER));
        documento.add(new Paragraph("No. Boleto: "+registro.getId()));
        documento.add(new Paragraph("Marca: "+registro.getMarca()));
        documento.add(new Paragraph("Modelo: "+registro.getModelo()));
        documento.add(new Paragraph("Placas: "+registro.getPlacas()));
        documento.add(new Paragraph("Color: "+registro.getColor()));
        documento.add(new Paragraph("Propietario: "+ registro.getPropietario()));
        documento.add(new Paragraph("Cajon: "+registro.getCajon()));
        documento.add(new Paragraph("Hora de entrada: "+registro.getEntrada()));
        documento.add(new Paragraph("Hora de salida: "+registro.getSalida()));
        documento.add(new Paragraph("Total a pagar: "+registro.getTotal()));
        Usuario usuario = UtilidadSession.getInstance().getUsuario();
        documento.add(new Paragraph("Acomodador: "+usuario.getNombre()+" "+usuario.getApellido() ));
        documento.add(new Paragraph("¡Gracias por su preferencia!").setTextAlignment(TextAlignment.CENTER));
       
        documento.close();
        
        JOptionPane.showMessageDialog(null, "Pdf creado correctamente");//se informa que se ha creado el documento
    }
    
    //metodo sobreeescrito que se implementa de Runnable para correr el Hilo
    @Override
    public void run() {
        Cajon cajonObj = new Cajon();//objeto Cajon
        cajonObj.setId(registro.getCajon());//se especifica el cajon que vamos a modificar
        cajonObj.setEstado(false);//cambia el estado de cajon a desocupado
        CajonDAO cajonDao = new CajonDAO();//objeto para usar los query de cajon
        cajonDao.actualizarCajon(cajonObj);//se actualiza el estado del cajon
        int filasAfectadas = registroDao.actualizar(registro);//se actualiza el registro o boleto, se da de baja
        if(filasAfectadas > 0){//si se actualiza correctamente
            //se le informa al usuario
           JOptionPane.showMessageDialog(null,"Se ha dado de baja un auto","Informacion",JOptionPane.INFORMATION_MESSAGE); //mensaje de guardado exitoso       
           
           try {
               generarPdf(registro);//enseguida creamos el documento PDF para el boleto de alta
           } catch (IOException ex) {
               Logger.getLogger(IngresoFrame.class.getName()).log(Level.SEVERE, null, ex);
           } 
        }else{//si no hubo una actualizacion
            //se informa al usuario
           JOptionPane.showMessageDialog(null,"Hubo un error al dar de baja los datos","Alerta",JOptionPane.WARNING_MESSAGE); 
        } 
    }
}