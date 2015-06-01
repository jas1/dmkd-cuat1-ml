package ml.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.Debug.Random;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class PruebaWekaRefactor {

    
    /**
     * esto es un comentario de documentacion.
     *
     * este metodo es utilizado para crear un archivo arff a partir de un txt
     * aplicandole los filtros de como son las variables
     * configuro el j48 y lo ejecuto
     * guardo los resultados en un archivo externo
     *
     * --- este metodo esta armado a modo 'choclo de codigo' para poder hacer un seguimiento derecho para abajo
     * --- cuando se programa de mejor manera se parten los 'chocolos de codigo' en metodos por funcionalidad
     * --- dejando un codigo mas ordenado y pudiendo reciclar partes, como por ejemplo:
     * repetir correr el algoritmo, guardar varios archivos de resultados... etc.
     * --- esto est
     */
    @Test
    public void pruebaJ48() {
        //    esto es un comentario.  
        //    esto es para configurar si los archivos estan guardados en la codificacion UTF-8
        //  esto tiene que ver con acentos y simbolos no utilizados comunmente por
        //  codificacion inglesa
        final String encode = "UTF-8";
        // esto es para configurar el encpoding
        System.setProperty("file.encoding", encode);

        // donde voy a tener los archivos
        String pathDatos ="C:/TP1/carpeta_donde_esta_el_archivo/";
        // nombre del archivo que quiero levantar
        String datosName = "archivo_depurado_preparado_para_correr_j48.txt";

        // nombre del archivo de weka resultante
        String outReal = "nombre_archivo_de_weka_resultante.arff";

        // nombre archivo de exportacion de la corrida del j48
        String exportResult = "export_corrida.txt";
        
        try {
            File outFile2 = arffFromCsv(pathDatos, datosName, outReal);

            // PARTE 2 --------------------- carga del arff y ejecucion del j48
            ArffLoader loaderArff = new ArffLoader();
            loaderArff.setSource(outFile2);
            Instances dataArff = loaderArff.getDataSet();
            
            // confianza del 0.025
            BigDecimal intervalo = new BigDecimal(0.025);
            
            //redondeo igualado con 3 digitos 
            // ( igualado: 1.1234 > va a redondear a 1.123 , 1.1236 > va a redondear a 1.124 ) 
            intervalo.setScale(3, RoundingMode.HALF_EVEN);

            
            // creo lav ariable de texto donde se van a guardar los resultados
            StringBuilder builder = new StringBuilder();
            
            //llamo a la ejecucion del j48 multiples veces hasta que sea menor igual que 0.5
            
            while (intervalo.doubleValue() <= 0.5) {
				// ejecuto el j48
            	configurarEjecutarJ48(dataArff, builder, intervalo);
            	// aumento el valor del intervalo de a 0.025
            	intervalo = intervalo.add(new BigDecimal(0.025));
			}
            

            //guardo el archivo con los resultados
            archivoDeResultados(encode, pathDatos, exportResult, builder);
            
            
            // con esta instruccion digo: si se ejecuto todo bien, doy el test por OK
            Assert.assertTrue(true);
        } catch (Exception e) {
            // aca dice toda la cadena del error desde donde viene en caso de explotar
            e.printStackTrace();
            // aca digo que el test ESTA MAL, y digo el mensaje de la excepcion
            Assert.fail("revento : "+e.getMessage());
        }
        
        
    }

	/**
	 * para guardar el archivo de resultados
	 * @param encode para que guarde con acentos
	 * @param pathDatos ruta de la carpeta donde se va a guardar el resultado
	 * @param exportResult nombre del archivo que se va a guardar
	 * @param builder acumulador de los resultados que fueron existiendo
	 */
	private void archivoDeResultados(final String encode, String pathDatos,
			String exportResult, StringBuilder builder) {
		// -------------- parte 3 escribir el archivo de output
		
		// declaro un 'escribidor'
		Writer writer = null;
		try {
		    // fecha generada en el momento ( da la fecha en el momento de la corrida )
		    Date defaultDate = new Date();
		    
		    // formato fecha anio, mes , dia, hora , minuto, segundo,
		    // esto lo agregue para tener multiples corridas que no pisen los archivos
		    String defaulFormat = "yyyyMMdd_HHmmss_";

		    // esto es un formateador de fechas, para que lo muestre de fecha a formato de 'palabras''
		    SimpleDateFormat lSDF = new SimpleDateFormat(defaulFormat);
		    // esto da la fecha formateada
		    String fechaAlMomento = lSDF.format(defaultDate);

		    // armo donde va a guardar el archivo siendo:
		    // carpeta pathDatos, y nombre de archivo compuesto por fechaAlMomento mas exportResult
		    String result = pathDatos + fechaAlMomento    + exportResult;

		    // creo el escribidor
		    // que va a ser un 'escribidor a archivo'
		    // para el archivo que esta en la carpeta y con nombre result
		    // y va a estar con codificacion encode
		    writer = new BufferedWriter(new OutputStreamWriter(
		            new FileOutputStream(result), encode));
		    
		    // escribo al archivo, todas las salidas que vine acumulando en el string
		    writer.write(builder.toString());

		    
		    // muestro por consola la ubicacion del archiov creado
		    System.out.println(result);
		    
		    // en caso de que explote el proceso
		} catch (Exception e) {
		    // impimo que fue lo que exploto
		    e.printStackTrace();
		    // si revienta al crear el archivo el test da como que no funciono
		    Assert.fail("revento al crear el archivo por:" + e.getMessage());
		} finally {
		    // si se llego a crear el escribidor
		    if (writer != null) {
		        try {
		            // le digo al escribidor que cierre el archivo
		            writer.close();
		        } catch (IOException e) {
		            // imprimo donde ocurrio el error
		            e.printStackTrace();
		            // imprimo el mensaje
		            Assert.fail("revento al cerrar el archivo por:" + e.getMessage());
		        }
		    }
		}
	}

    /**
     * recibe una configuracion minima y va escribiendo los resultados en el parametro builder.
     * 
     * @param dataArff datos arff
     * @param builder acumulador de resultados
     * @param intervalo intervalo de confianza
     * @throws Exception en caso de que exista un error tira exception
     */
	private void configurarEjecutarJ48(Instances dataArff,
			StringBuilder builder, BigDecimal intervalo) throws Exception {
		// formato que voy a mostrar los valores
		DecimalFormat formater = new DecimalFormat("#.###");

		// crecion de una instancia del j48
		J48 j48 = new J48();

		// config base como la de weka
		j48.setBinarySplits(false);
		j48.setDebug(false);
		j48.setMinNumObj(2);
		j48.setNumFolds(3);
		j48.setReducedErrorPruning(false);
		j48.setSaveInstanceData(false);
		j48.setSeed(1);
		j48.setSubtreeRaising(true);
		j48.setUnpruned(false);
		j48.setUseLaplace(false);

		// escribo en la salida
		// 2 enter , un titulo y que intervalo estoy analizando
		builder.append("\n\n").append("--- j48 para confianza: ").append(formater.format(intervalo)).append("\n");

		// pongo la confianza en valor de punto flotante que es lo que acepta el algoritmo como entrada
		float tmpFloat = intervalo.floatValue();
		// pongo la confianza
		j48.setConfidenceFactor(tmpFloat);
		
		// armo el j48 segun la configuraciona nterior para los datos que levante
		j48.buildClassifier(dataArff);

		// agrego el resultado que muestra el j48
		builder.append(j48).append("\n");
      
		// evaluo los datos del dataSet
		Evaluation eval = new Evaluation(dataArff);
		// en este caso pongo con cross validation
		Random rand = new Random(1); // seed de 1: parametro del crossvalidation
		eval.crossValidateModel(j48, dataArff, 10, rand);
		
		// armo los mensajes: enter, estadisticas, enter
		builder.append("\n").append("--- estadisticas --- ").append("\n");
		// agrego los resultados estadisticos
		builder.append(eval.toSummaryString());
		// armo los mensajes: enter, matriz, enter
		builder.append("\n").append("--- matrix --- ").append("\n");
		// agrego la matriz de confusion
		builder.append(eval.toMatrixString());
		// armo los mensajes: enter, detalles, enter
		builder.append("\n").append("--- detalles --- ").append("\n");
		// agrego mmas detalles
		builder.append(eval.toClassDetailsString());
	}

	/**
	 * este metodo sirve para transformar un csv armar un arff, y devolver el archivo creado
	 * @param pathDatos ruta a la carpeta donde se abren y guadan los archivos
	 * @param datosName nombre del archivo de donde se sacan los datos
	 * @param outReal
	 * @return el archivo arff creado
	 * @throws IOException
	 * @throws Exception
	 */
	private File arffFromCsv(String pathDatos, String datosName, 
			String outReal) throws IOException, Exception {
        // esto solo es un archivo que no sirve para nada
        // pero si no se lo pone tira algunos errores
        String dummy = "dummy.arff";
        
		// levantar el csv original
		CSVLoader loader = new CSVLoader();
		// qeu esta ubicado en la carpeta pathDatos y de nombre datosName
		loader.setSource(new File(pathDatos + datosName));
		// creo una instancia de dataset , respecto de lo cargado
		Instances oldData = loader.getDataSet();

		// le aplico un filtro para convertir numericos a nominales
		// de las columnas especificadas
		// filtro de numericos
		// creo el filtro
		NumericToNominal numToNom = new NumericToNominal();
		// lo seteo con las variables que aparecen cuando selecciono
		// el filtro en el weka
		numToNom.setDebug(false);
		numToNom.setInvertSelection(false);
		// aca especifico los campso que van a ser afectados,
		// que son interpretados como numericos pero , en realidad son categoricos
		int[] indeces =  {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,16};
		// aca le paso como parametro los indices especificados anteriormente
		numToNom.setAttributeIndicesArray(indeces);
		// aca especifico a que set de datos voy a aplicarle el filtro.
		numToNom.setInputFormat(oldData);

		// creo una nueva instancia del dataset respecto de
		// el viejo dataset con el filtro aplicado
		Instances data = Filter.useFilter(oldData, numToNom);
		
		
		// para guardar el archivo arff bien filtrado
		// creo el 'guardador'
		ArffSaver saver = new ArffSaver();
		// le paso que datos voy a guaradr
		saver.setInstances(data);
		
		
		// Especifico enq ue archivos voy a guardar las cosas
		// agregado el output del dummy , en este bno se va a escribir nada
		// agregado por el error de " Cannot create a new output file. Standard out is used."
		File outFile = new File(pathDatos+dummy);
		
		// agrego el archivo donde se va a guardar
		// en el path con pathDatos nombre outReal
		File outFile2 = new File(pathDatos+outReal);

		// le digo en que archivo va a guardar el guardador> esto me dio algun error
		// por eso puse 2 archivos
		saver.setFile(outFile);
		// en este se van a guardar las cosas
		saver.setDestination(outFile2);
		// esta es la instruccion para guardar todo
		saver.writeBatch();
		return outFile2;
	}
    
}