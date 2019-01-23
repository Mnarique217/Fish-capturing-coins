package juego.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class UtilidadesXML {

    // <editor-fold defaultstate="collapsed" desc="Constructores">
    private UtilidadesXML() {
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Métodos XML">
    public static Document crearDocumento(String elementoRaiz)
            throws ParserConfigurationException {
        try {
            DocumentBuilder b = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = b.newDocument();
            if (elementoRaiz != null) {
                doc.appendChild(doc.createElement(elementoRaiz));
            }
            return doc;
        } catch (ParserConfigurationException e) {
            throw e;
        }
    }

    public static Document crearDocumento()
            throws ParserConfigurationException {
        return UtilidadesXML.crearDocumento(null);
    }

    public static Element crearNodo(Document doc, String etiqueta, String dato) {
        Element r = doc.createElement(etiqueta);
        r.appendChild(doc.createTextNode(dato));
        return r;
    }
    
    public static void guardarArchivoXML(Document doc, String nombreArchivo) {
        try {
            Source origen = new DOMSource(doc);

            File archivo = new File(nombreArchivo);
            Result r = new StreamResult(archivo);

            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(origen, r);
        } catch (TransformerConfigurationException e) {
        } catch (TransformerException e) {
        }
    }   
    
        public static Document crearXMLDocumento(File file){
        DocumentBuilderFactory constructores = null;
        DocumentBuilder constructor = null;
        Document fileXML = null;        
        try {
            constructores = DocumentBuilderFactory.newInstance();
            constructor = constructores.newDocumentBuilder();
            if(file == null){
                fileXML = constructor.newDocument();
                System.out.println("El archivo no existe");
            } else {
                System.out.println("El archivo ya existe");
                fileXML =  constructor.parse(file);
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.err.println("Error crearDocumento..."+ex.getMessage());
        }
        return fileXML;
            
    }  
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static UtilidadesXML obtenerInstancia() {
        if (instancia == null) {
            instancia = new UtilidadesXML();
        }
        return instancia;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributos (instancia)">
    private static UtilidadesXML instancia = null;

    // </editor-fold>
}

//
//   public void guardarXml() {
//   
//        Document d;
//   try {
//          d = UtilidadesXML.crearDocumento();
//      
//          Node r = d.createElement("Informacion_Jugadores");
//          for(int i=0; i <this.cantidad(); i++)
//             r.appendChild(recuperar(i).toXML(d)); 
//               
//          
//            d.appendChild(r);
//            UtilidadesXML.guardarArchivoXML(d, "jugadores.xml"); 
//            
//            System.out.println("Programa finalizado..");
//         } catch (ParserConfigurationException ex) {
//        Logger.getLogger(Jugadores.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//     public void leerXML(Node nodo) {
//        NodeList arbolEtiquetas = nodo.getChildNodes();
//        int numJugadores = arbolEtiquetas.getLength();
//        for (int i = 0; i < numJugadores; i++) {
//            Node etiquetaJugador = arbolEtiquetas.item(i);
//            Jugador p = new Jugador();
//            p.leerXML(etiquetaJugador);
//            Jugadores.add(p);            
//        }
//    }
//    public void leerXML(){
//        File file = new File("jugadores.xml");
//        Document documentoXML = UtilidadesXML.crearXMLDocumento(file);
//        Node raiz = documentoXML.getDocumentElement();
//        jugadores.leerXML(raiz);
//        tabla.setChanged();
//        refrescar();
//     }
