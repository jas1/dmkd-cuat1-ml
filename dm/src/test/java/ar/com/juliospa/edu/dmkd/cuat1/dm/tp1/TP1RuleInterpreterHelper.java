package ar.com.juliospa.edu.dmkd.cuat1.dm.tp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * dado un input de reglas hay que buscar que es en la DB
 * 
 * @author julio
 *
 */
public class TP1RuleInterpreterHelper {

	final String ENCODE = "UTF-8";
	
	// con <- dbConnect(RMySQL::MySQL(), dbname = "dm-tp1", password
	// ='dmkd',user = 'dmkd',host='192.168.1.113')

	private StringBuilder leerReglasFile(String filePath) throws Exception {
		StringBuilder sb = new StringBuilder();
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "Cp1252")); 
		
	    try {
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	    
	    
		return sb;
	}
	
	private StringBuilder leerReglas() {
		StringBuilder build = new StringBuilder();
		// build.append("lhs               rhs                support confidence     lift\n");
		descGenconfianza06support009(build);
		return build;
	}

	

	private void confianza06support023(StringBuilder build) {
		build.append("1 {BTP4S79-75RC} => {BTP4S79-75BLC} 0.02417417  0.8341969 29.39551");
	}
	private void confianza06support01(StringBuilder build) {
		build.append("1  {BTP4S79-5BLQ}  => {BTP4S79-5RQ}   0.01186186  0.8681319 58.99753\n");
		build.append("2  {LCM1414BK}     => {LCM1414R}      0.01171171  0.7959184 55.79807\n");
		build.append("3  {LCM1406NB,LCM1406NG,LCM1406NR}     => {LCM1406NS}     0.01261261  0.8842105 54.02607\n");
		build.append("4  {LCM1406NR,LCM1406NS}     => {LCM1406NG}     0.01321321  0.8979592 53.39650\n");
		build.append("5  {LCM1406NB,LCM1406NS}     => {LCM1406NG}     0.01291291  0.8958333 53.27009\n");
		build.append("6  {WOLM002}       => {WOLM001}       0.01171171  0.8297872 52.13569\n");
		build.append("7  {LCM1406NB,LCM1406NS}     => {LCM1406NR}     0.01396396  0.9687500 51.61500\n");
		build.append("8  {BTP4S79-75BLQ} => {BTP4S79-75RQ}  0.01081081  0.8571429 50.51833\n");
		build.append("9  {LCM1406NS}     => {LCM1406NG}     0.01381381  0.8440367 50.19004\n");
		build.append("10 {LCM1406NB,LCM1406NG}     => {LCM1406NR}     0.01426426  0.9405941 50.11485\n");
		build.append("11 {BTP4S79-75SQ}  => {BTP4S79-75RQ}  0.01171171  0.8478261 49.96922\n");
		build.append("12 {LCM1406NG}     => {LCM1406NR}     0.01546547  0.9196429 48.99857\n");
		build.append("13 {BTP4S79-5RC,BTP4S79-75BLC,BTP4S79-75RC,BTP4S79-75SC}  => {BTP4S79-5SC}   0.01021021  0.9577465 48.32266\n");
		build.append("14 {LCM1406NS}     => {LCM1406NR}     0.01471471  0.8990826 47.90312\n");
		build.append("15 {BTP4S79-5RC,BTP4S79-75RC,BTP4S79-75SC}  => {BTP4S79-5SC}   0.01111111  0.9487179 47.86713\n");
		build.append("16 {LCM1406NR}     => {LCM1406NB}     0.01696697  0.9040000 47.78286\n");
		build.append("17 {LCM1406NG}     => {LCM1406NB}     0.01516517  0.9017857 47.66582\n");
		build.append("18 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75BLC,                                                  \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01021021  0.9444444 47.65152\n");
		build.append("19 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC} => {BTP4S79-5BLC}  0.01006006  0.9178082 47.38452\n");
		build.append("20 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75BLC,                                                  \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-5BLC}  0.01246246  0.9120879 47.08919\n");
		build.append("21 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01126126  0.9259259 46.71717\n");
		build.append("22 {WOLM001}       => {WOLM003}       0.01216216  0.7641509 46.69032\n");
		build.append("23 {LCM1406NS}     => {LCM1406NB}     0.01441441  0.8807339 46.55308\n");
		build.append("24 {WOLM002}       => {WOLM003}       0.01066066  0.7553191 46.15069\n");
		build.append("25 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75BLC} => {BTP4S79-5BLC}  0.01276276  0.8854167 45.71221\n");
		build.append("26 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-75RC,                                                   \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01006006  0.9054054 45.68182\n");
		build.append("27 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-5RC}   0.01036036  0.9452055 44.64588\n");
		build.append("28 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01036036  0.8846154 44.63287\n");
		build.append("29 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75RC,                                                   \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-5BLC}  0.01006006  0.8589744 44.34705\n");
		build.append("30 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-5BLC}  0.01036036  0.8518519 43.97933\n");
		build.append("31 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC,                                                  \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-5BLC}  0.01036036  0.8518519 43.97933\n");
		build.append("32 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-5SC}   => {BTP4S79-5RC}   0.01291291  0.9247312 43.67879\n");
		build.append("33 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-5BLC}  0.01291291  0.8431373 43.52941\n");
		build.append("34 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC} => {BTP4S79-5BLC}  0.01066066  0.8255814 42.62304\n");
		build.append("35 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC,                                                  \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-5RC}   0.01096096  0.9012346 42.56895\n");
		build.append("36 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-5RC}   0.01201201  0.8695652 41.07308\n");
		build.append("37 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-5BLC}  0.01096096  0.7934783 40.96562\n");
		build.append("38 {BTP4S79-5BLC}  => {BTP4S79-5RC}   0.01651652  0.8527132 40.27709\n");
		build.append("39 {TN4X30-1,                                                       \n");
		build.append("    TN4X30-4}      => {TN4X30-3}      0.01306306  0.9354839 40.19563\n");
		build.append("40 {F541}          => {F291}          0.01036036  0.6509434 40.14151\n");
		build.append("41 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC} => {BTP4S79-5RC}   0.01096096  0.8488372 40.09401\n");
		build.append("42 {TN4X30-4}      => {TN4X30-3}      0.01501502  0.8928571 38.36406\n");
		build.append("43 {BTP4S79-5SC}   => {BTP4S79-5RC}   0.01591592  0.8030303 37.93037\n");
		build.append("44 {MG1970}        => {MG1968}        0.01546547  0.7744361 37.92459\n");
		build.append("45 {BTP4S79-5SC}   => {BTP4S79-5BLC}  0.01396396  0.7045455 36.37421\n");
		build.append("46 {NAC0.50}       => {NAC0.40}       0.01171171  0.8387097 34.91129\n");
		build.append("47 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC,                                                  \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-75SC}  0.01081081  0.8888889 34.82353\n");
		build.append("48 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC} => {BTP4S79-75SC}  0.01126126  0.8720930 34.16553\n");
		build.append("49 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-75SC}  0.01201201  0.8695652 34.06650\n");
		build.append("50 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75BLC,                                                  \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01066066  0.9861111 34.02850\n");
		build.append("51 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-75RC,                                                   \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-75BLC} 0.01051051  0.9459459 33.33333\n");
		build.append("52 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01171171  0.9629630 33.22971\n");
		build.append("53 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-75BLC} 0.01096096  0.9358974 32.97924\n");
		build.append("54 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01111111  0.9487179 32.73814\n");
		build.append("55 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75BLC} => {BTP4S79-75RC}  0.01366366  0.9479167 32.71049\n");
		build.append("56 {BTP4S79-5BLC,                                                   \n");
		build.append("    BTP4S79-75RC}  => {BTP4S79-75BLC} 0.01336336  0.9270833 32.66865\n");
		build.append("57 {BTP4S79-5SC,                                                    \n");
		build.append("    BTP4S79-75BLC} => {BTP4S79-75RC}  0.01216216  0.9418605 32.50151\n");
		build.append("58 {BTP4S79-75BLC,                                                  \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01861862  0.9117647 31.46297\n");
		build.append("59 {BTP4S79-5RC,                                                    \n");
		build.append("    BTP4S79-75SC}  => {BTP4S79-75BLC} 0.01081081  0.8888889 31.32275\n");
		build.append("60 {NAC0.30}       => {NAC0.40}       0.01081081  0.7346939 30.58163\n");
		build.append("61 {TN4X30-1}      => {TN4X30-3}      0.01981982  0.6947368 29.85127\n");
		build.append("62 {BTP4S79-75RC}  => {BTP4S79-75BLC} 0.02417417  0.8341969 29.39551\n");
		build.append("63 {TN4X30-4}      => {TN4X30-1}      0.01396396  0.8303571 29.10620\n");
		build.append("64 {BTP4S79-75SC}  => {BTP4S79-75RC}  0.02147147  0.8411765 29.02713\n");
		build.append("65 {BTP4S79-75SC}  => {BTP4S79-75BLC} 0.02042042  0.8000000 28.19048\n");
		build.append("66 {TA1001P}       => {TA1000CC}      0.01711712  0.7450980 26.96931\n");
		build.append("67 {TA751B}        => {TA1000CC}      0.01036036  0.7419355 26.85484\n");
		build.append("68 {CAP501}        => {CAP502}        0.01246246  0.7685185 26.11395\n");
		build.append("69 {BTP4S79-5BLC}  => {BTP4S79-75BLC} 0.01426426  0.7364341 25.95054\n");
		build.append("70 {BTP4S79-5SC}   => {BTP4S79-75SC}  0.01306306  0.6590909 25.82086\n");
		build.append("71 {BTP4S79-5BLC}  => {BTP4S79-75RC}  0.01441441  0.7441860 25.68020\n");
		build.append("72 {BTP4S79-5RC}   => {BTP4S79-75RC}  0.01531532  0.7234043 24.96307\n");
		build.append("73 {BTP4S79-5SC}   => {BTP4S79-75RC}  0.01381381  0.6969697 24.05087\n");
		build.append("74 {BTP4S79-5RC}   => {BTP4S79-75BLC} 0.01441441  0.6808511 23.99189\n");
		build.append("75 {BTP4S79-75RQ}  => {BTP4S79-75SC}  0.01036036  0.6106195 23.92192\n");
		build.append("76 {BTP4S79-5BLC}  => {BTP4S79-75SC}  0.01171171  0.6046512 23.68810\n");
		build.append("77 {TA21000CC}     => {TA1001A}       0.01216216  0.7363636 23.35325\n");
		build.append("78 {BTP4S79-75RQ}  => {BTP4S79-75RC}  0.01141141  0.6725664 23.20877\n");
		build.append("79 {CAP503}        => {CAP502}        0.01291291  0.6825397 23.19242\n");
		build.append("80 {BTP4S79-5SC}   => {BTP4S79-75BLC} 0.01291291  0.6515152 22.95815\n");
		build.append("81 {BTP4S79-75RQ}  => {BTP4S79-75BLC} 0.01096096  0.6460177 22.76443\n");
		build.append("82 {TA21000CC}     => {TA1000CC}      0.01006006  0.6090909 22.04644\n");
		build.append("83 {CAP510}        => {CAP502}        0.01006006  0.6261682 21.27694\n");
		build.append("84 {WOLH18+2}      => {WOLH12+2}      0.01486486  0.6000000 21.25532\n");
	}

	private void confianza06support02(StringBuilder build) {
		build.append("1 {BTP4S79-75RC} => {BTP4S79-75BLC} 0.02417417  0.8341969 29.39551\n");
		build.append("2 {BTP4S79-75SC} => {BTP4S79-75RC}  0.02147147  0.8411765 29.02713\n");
		build.append("3 {BTP4S79-75SC} => {BTP4S79-75BLC} 0.02042042  0.8000000 28.19048\n");
	}

	private void descGenconfianza06support009(StringBuilder lbuilder) {
		lbuilder.append("1  {PEDERNAL PARA ENCENDER FUEGO} => {MANTA DE EMERGENCIA}       0.009459459  0.7159091 61.127622\n");
		lbuilder.append("2  {LÍNEA FLY SINKING BLACK}      => {LÍNEA FLY FLOATING ORANGE} 0.009159159  0.7530864 37.996633\n");
		lbuilder.append("3  {REEL CENTURY,                                                                                \n");
		lbuilder.append("    REEL HERMES}                  => {REEL CALYPSO}              0.009009009  0.6593407 24.395604\n");
		lbuilder.append("4  {REEL BELLUS,                                                                                 \n");
		lbuilder.append("    REEL CALYPSO}                 => {REEL CENTURY}              0.009309309  0.6666667 22.200000\n");
		lbuilder.append("5  {CAP CON VISERA,                                                                              \n");
		lbuilder.append("    GORRO TIPO PILUSO}            => {SOMBRERO DE ALA}           0.009909910  0.7096774 22.086222\n");
		lbuilder.append("6  {PASAHILOS}                    => {PUNTERA}                   0.014114114  0.6025641 19.109890\n");
		lbuilder.append("7  {MOCHILAS DSICOVERY}           => {MOCHILAS SUPER MOUNTAIN}   0.014264264  0.7661290 16.784274\n");
		lbuilder.append("8  {REEL BELLUS,                                                                                 \n");
		lbuilder.append("    REEL CALYPSO}                 => {REEL HERMES}               0.009009009  0.6451613 15.400624\n");
		lbuilder.append("9  {SOMBRERO DE ALA}              => {CAP CON VISERA}            0.024174174  0.7523364 13.727564\n");
		lbuilder.append("10 {GORRO TIPO PILUSO}            => {CAP CON VISERA}            0.013963964  0.7099237 12.953676\n");
		lbuilder.append("11 {ROBADOR}                      => {ANZUELO X MILLAR}          0.010360360  0.6052632  8.631804\n");
		lbuilder.append("12 {BOTELLA TÉRMICA}              => {TERMO}                     0.010810811  0.6990291  7.274272\n");
	}
	
	private void confianza085support01(StringBuilder builder) {
		builder.append("1  {BTP4S79-5BLQ}  => {BTP4S79-5RQ}   0.01186186  0.8681319 58.99753\n");
		builder.append("2  {LCM1406NB,                                                      \n");
		builder.append("    LCM1406NG,                                                      \n");
		builder.append("    LCM1406NR}     => {LCM1406NS}     0.01261261  0.8842105 54.02607\n");
		builder.append("3  {LCM1406NR,                                                      \n");
		builder.append("    LCM1406NS}     => {LCM1406NG}     0.01321321  0.8979592 53.39650\n");
		builder.append("4  {LCM1406NB,                                                      \n");
		builder.append("    LCM1406NS}     => {LCM1406NG}     0.01291291  0.8958333 53.27009\n");
		builder.append("5  {LCM1406NB,                                                      \n");
		builder.append("    LCM1406NS}     => {LCM1406NR}     0.01396396  0.9687500 51.61500\n");
		builder.append("6  {BTP4S79-75BLQ} => {BTP4S79-75RQ}  0.01081081  0.8571429 50.51833\n");
		builder.append("7  {LCM1406NB,                                                      \n");
		builder.append("    LCM1406NG}     => {LCM1406NR}     0.01426426  0.9405941 50.11485\n");
		builder.append("8  {LCM1406NG}     => {LCM1406NR}     0.01546547  0.9196429 48.99857\n");
		builder.append("9  {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75RC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01021021  0.9577465 48.32266\n");
		builder.append("10 {LCM1406NS}     => {LCM1406NR}     0.01471471  0.8990826 47.90312\n");
		builder.append("11 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75RC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01111111  0.9487179 47.86713\n");
		builder.append("12 {LCM1406NR}     => {LCM1406NB}     0.01696697  0.9040000 47.78286\n");
		builder.append("13 {LCM1406NG}     => {LCM1406NB}     0.01516517  0.9017857 47.66582\n");
		builder.append("14 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01021021  0.9444444 47.65152\n");
		builder.append("15 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75BLC} => {BTP4S79-5BLC}  0.01006006  0.9178082 47.38452\n");
		builder.append("16 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-5BLC}  0.01246246  0.9120879 47.08919\n");
		builder.append("17 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01126126  0.9259259 46.71717\n");
		builder.append("18 {LCM1406NS}     => {LCM1406NB}     0.01441441  0.8807339 46.55308\n");
		builder.append("19 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75BLC} => {BTP4S79-5BLC}  0.01276276  0.8854167 45.71221\n");
		builder.append("20 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-75RC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01006006  0.9054054 45.68182\n");
		builder.append("21 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-5RC}   0.01036036  0.9452055 44.64588\n");
		builder.append("22 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5SC}   0.01036036  0.8846154 44.63287\n");
		builder.append("23 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75RC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5BLC}  0.01006006  0.8589744 44.34705\n");
		builder.append("24 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-5BLC}  0.01036036  0.8518519 43.97933\n");
		builder.append("25 {BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-5BLC}  0.01036036  0.8518519 43.97933\n");
		builder.append("26 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-5SC}   => {BTP4S79-5RC}   0.01291291  0.9247312 43.67879\n");
		builder.append("27 {BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-5RC}   0.01096096  0.9012346 42.56895\n");
		builder.append("28 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-5RC}   0.01291291  0.8958333 42.31383\n");
		builder.append("29 {BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-5RC}   0.01201201  0.8695652 41.07308\n");
		builder.append("30 {BTP4S79-5BLC}  => {BTP4S79-5RC}   0.01651652  0.8527132 40.27709\n");
		builder.append("31 {TN4X30-1,                                                       \n");
		builder.append("    TN4X30-4}      => {TN4X30-3}      0.01306306  0.9354839 40.19563\n");
		builder.append("32 {TN4X30-4}      => {TN4X30-3}      0.01501502  0.8928571 38.36406\n");
		builder.append("33 {BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-75SC}  0.01081081  0.8888889 34.82353\n");
		builder.append("34 {BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75BLC} => {BTP4S79-75SC}  0.01126126  0.8720930 34.16553\n");
		builder.append("35 {BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-75SC}  0.01201201  0.8695652 34.06650\n");
		builder.append("36 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01066066  0.9861111 34.02850\n");
		builder.append("37 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-75RC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-75BLC} 0.01051051  0.9459459 33.33333\n");
		builder.append("38 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01171171  0.9629630 33.22971\n");
		builder.append("39 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-75BLC} 0.01096096  0.9358974 32.97924\n");
		builder.append("40 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01111111  0.9487179 32.73814\n");
		builder.append("41 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75BLC} => {BTP4S79-75RC}  0.01366366  0.9479167 32.71049\n");
		builder.append("42 {BTP4S79-5BLC,                                                   \n");
		builder.append("    BTP4S79-75RC}  => {BTP4S79-75BLC} 0.01336336  0.9270833 32.66865\n");
		builder.append("43 {BTP4S79-5SC,                                                    \n");
		builder.append("    BTP4S79-75BLC} => {BTP4S79-75RC}  0.01216216  0.9418605 32.50151\n");
		builder.append("44 {BTP4S79-75BLC,                                                  \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-75RC}  0.01861862  0.9117647 31.46297\n");
		builder.append("45 {BTP4S79-5RC,                                                    \n");
		builder.append("    BTP4S79-75SC}  => {BTP4S79-75BLC} 0.01081081  0.8888889 31.32275\n");
		builder.append("46 {TN4X30-3}      => {TN4X30-1}      0.01981982  0.8516129 29.85127\n");
		builder.append("47 {BTP4S79-75BLC} => {BTP4S79-75RC}  0.02417417  0.8518519 29.39551\n");
		
	}
	
	private void categoriaConfianza084support01(StringBuilder build) {
		build.append("1  {CAMPING ,                                                           \n");
		build.append("    INDUMENTARIA ,                                                      \n");
		build.append("    PESCA ,                                                             \n");
		build.append("    PESCA REELS}         => {PESCA CAÑAS} 0.01561562  0.8666667 3.835216\n");
		build.append("2  {CAMPING ,                                                           \n");
		build.append("    PESCA CAÑAS,                                                        \n");
		build.append("    PESCA REELS,                                                        \n");
		build.append("    TIRO Y DEFENSA }     => {PESCA }      0.01201201  0.8791209 2.701867\n");
		build.append("3  {CAMPING ,                                                           \n");
		build.append("    INDUMENTARIA PESCA ,                                                \n");
		build.append("    PESCA CAÑAS,                                                        \n");
		build.append("    PESCA REELS}         => {PESCA }      0.01606607  0.8425197 2.589378\n");
		build.append("4  {INDUMENTARIA ,                                                      \n");
		build.append("    PESCA ,                                                             \n");
		build.append("    TIRO Y DEFENSA }     => {CAMPING }    0.01246246  0.8736842 1.599433\n");
		build.append("5  {CUCHILLERÍA ,                                                       \n");
		build.append("    INDUMENTARIA }       => {CAMPING }    0.01051051  0.8641975 1.582066\n");
		build.append("6  {OPTICA ,                                                            \n");
		build.append("    PESCA ,                                                             \n");
		build.append("    TIRO Y DEFENSA }     => {CAMPING }    0.01126126  0.8620690 1.578169\n");
		build.append("7  {INDUMENTARIA ,                                                      \n");
		build.append("    TIRO Y DEFENSA }     => {CAMPING }    0.02012012  0.8589744 1.572504\n");
		build.append("8  {CUCHILLERÍA ,                                                       \n");
		build.append("    OPTICA }             => {CAMPING }    0.01246246  0.8556701 1.566455\n");
		build.append("9  {INDUMENTARIA PESCA ,                                                \n");
		build.append("    PESCA ,                                                             \n");
		build.append("    TIRO Y DEFENSA }     => {CAMPING }    0.01156156  0.8555556 1.566245\n");
		build.append("10 {INDUMENTARIA PESCA ,                                                \n");
		build.append("    TIRO Y DEFENSA }     => {CAMPING }    0.01486486  0.8461538 1.549034\n");
		build.append("11 {OPTICA ,                                                            \n");
		build.append("    TIRO Y DEFENSA }     => {CAMPING }    0.01966967  0.8451613 1.547217\n");
	}
	
	
	private List<Regla> parseReglas(StringBuilder build) throws IOException {
		List<Regla> reglas = parseReglasPorReglon(build);

		return reglas;
	}
	
	private List<Regla> parseReglasOld(StringBuilder build) throws IOException {

		// el problema es que no siempre vienen en 1 regla por reglon en ese
		// caso como hacemos ?
		// cada vez que es una nueva ergla tiene un nuevo id ,entonces

		StringBuilder newBuild = reglasSinEnter(build);

		List<Regla> reglas = parseReglasPorReglon(newBuild);

		return reglas;
	}

	/**
	 * @param build
	 * @return
	 * @throws IOException
	 */
	private List<Regla> parseReglasPorReglon(StringBuilder build)
			throws IOException {
		StringReader reader = new StringReader(build.toString());
		LineNumberReader lineReader = new LineNumberReader(reader);

		List<Regla> reglas = new ArrayList<Regla>();
		String line = lineReader.readLine();
		while (line != null) {

			reglas.add(new Regla(line));
			line = lineReader.readLine();
		}
		return reglas;
	}

	private StringBuilder reglasSinEnter(StringBuilder build)
			throws IOException {
		List<Regla> reglas = new ArrayList<Regla>();
		StringReader reader = new StringReader(build.toString());
		LineNumberReader lineReader = new LineNumberReader(reader);

		String line = lineReader.readLine();

		StringBuilder ret = new StringBuilder();

		String lineAnt = "";
		String reglaAcumulada = "";
		while (line != null) {
			// primera vez que aparece espacio, si no es el 1er caracter,
			// entonces es reglon regla,
			// sino hay que concatenar con anterior.
			if (line.indexOf(" ") != 0) {
				if (line.contains("=>")) {
					// regla entera
					ret.append(line).append("\n");;
				} else {
					// inicio regla partida
					reglaAcumulada = line.trim();
				}
			} else {
				// sigue regla partida
				if (line.contains("}")) {
					// fin regla partida

					// agrego parte final de regla
					reglaAcumulada += line.trim();
					// agrego la regla finalizada
					ret.append(reglaAcumulada).append("\n");
					// reseteo la regla acumulada
					reglaAcumulada = "";
				} else {
					// medio regla partida
					reglaAcumulada += line.trim();
				}
			}

			lineAnt = line;
			line = lineReader.readLine();

		}

		return ret;
	}

	@Test
	public void buscarSegunIdProducto() throws Exception {
		System.out.println("Ejecutando:");
		StringBuilder build = leerReglas();
		List<Regla> reglas = parseReglas(build);
		StringBuilder buildForFile = new StringBuilder();
		for (Regla regla : reglas) {
			buildForFile.append("\n\n#"+ regla.tipoExcel()).append("\n");
			buildForFile.append(consultaProductoPorIdsEnDB(regla.getAllIds()));
		}
		
		final String nombreArchivo = "_dm_rules_filter_buscarSegunIdProducto.txt";
		writeOutput(buildForFile,nombreArchivo);
	}
	
	@Test
	public void buscarSegunDescGenProducto() throws Exception {
		System.out.println("Ejecutando:");
		StringBuilder build = leerReglas();
		List<Regla> reglas = parseReglas(build);

		StringBuilder buildForFile = new StringBuilder();
		Connection con = getConnection();
		try {
			for (Regla regla : reglas) {
				buildForFile.append("\n\n#"+ regla.tipoExcel()).append("\n");
				buildForFile.append(consultaProductoPorDescGenEnDB(regla.getAllIds(), con));
			}
		} catch (Exception e) {
			if (con!=null) {
				con.close();
			}
		}
		
		
		final String nombreArchivo = "_dm_rules_filter_buscarSegunDescGenProducto.txt";
		writeOutput(buildForFile,nombreArchivo);
		
	}
	
	@Test
	public void buscarSegunCategoriaProducto() throws Exception {
		System.out.println("Ejecutando:");
		StringBuilder build = leerReglas();
		List<Regla> reglas = parseReglas(build);

		StringBuilder buildForFile = new StringBuilder();
		for (Regla regla : reglas) {
			buildForFile.append("\n\n#"+ regla.tipoExcel()).append("\n");
			buildForFile.append(consultaProductoPorCategoriaEnDB(regla.getAllIds()));
		}
		final String nombreArchivo = "_dm_rules_filter_buscarSegunCategoriaProducto.txt";
		writeOutput(buildForFile,nombreArchivo);
	}
	
	@Test
	public void buscarSegunSubCategoriaProducto() throws Exception {
		System.out.println("Ejecutando:");
		
		String name = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/resultados_apriori/resultado_tx_prod_sub_cat.txt"; 
		 
		StringBuilder build = leerReglasFile(name);
		List<Regla> reglas = parseReglas(build);

		StringBuilder buildForFile = new StringBuilder();
		for (Regla regla : reglas) {
			buildForFile.append("\n\n#"+ regla.tipoExcel()).append("\n");
			buildForFile.append(consultaProductoPorSubCategoriaEnDB(regla.getAllIds()));
		}
		final String nombreArchivo = "_dm_rules_filter_buscarSegunSubCategoriaProducto.txt";
		writeOutput(buildForFile,nombreArchivo);
	}
	
	
	@Test
	public void buscarSegunDescGenProductoPorMes() throws Exception {
		System.out.println("Ejecutando:");
		
		String nameDir = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/analisis";
		
		File directorio  = new File(nameDir);
		File[] archivos =  directorio.listFiles();
		Connection con = getConnection();
		try {
			for (File file : archivos) {
				if (file.getName().endsWith("_result.txt")) {
//				System.out.println(file.getAbsolutePath());
					StringBuilder build = leerReglasFile(file.getAbsolutePath());
					List<Regla> reglas = parseReglas(build);

					StringBuilder buildForFile = new StringBuilder();
					for (Regla regla : reglas) {
						buildForFile.append("\n\n#"+ regla.tipoExcel()).append("\n");
						buildForFile.append(consultaProductoPorDescGenEnDB(regla.getAllIds(), con));
					}
					
					final String nombreArchivo = file.getAbsolutePath().replace("_result.txt", "_reglas_y_productos.txt");
					//writeOutput(buildForFile,nombreArchivo);
					writeToFile(buildForFile.toString(),ENCODE,nombreArchivo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (con!=null) {
				con.close();
			}
		}
		
		 /*

		*/
	}
	
	
	
	@Test
	public void buscarSegunClienteProducto() throws Exception {
		System.out.println("Ejecutando:");
		
		String name = "C:/Users/julio/Dropbox/julio_box/educacion/maestria_explotacion_datos_uba/materias/cuat_1_data_mining/TP1/resultados_apriori/resultado_cli_prod_prod_id.txt"; 
		 
		StringBuilder build = leerReglasFile(name);
		List<Regla> reglas = parseReglas(build);

		StringBuilder buildForFile = new StringBuilder();
		for (Regla regla : reglas) {
			buildForFile.append("\n\n#"+ regla.tipoExcel()).append("\n");
			buildForFile.append(consultaProductoPorClienteEnDB(regla.getAllIds()));
		}
		final String nombreArchivo = "_dm_rules_filter_buscarSegunClienteProducto.txt";
		writeOutput(buildForFile,nombreArchivo);
	}
	
	public String consultaProductoPorClienteEnDB(Set<String> idsProducto)
			throws Exception {

		StringBuilder buildResult = new StringBuilder();
		
		Connection con = getConnection();

		String sql = "SELECT * from Prod_Planos P where P.Prod_ID IN(?)";

		StringBuilder build = new StringBuilder();
		for (String id : idsProducto) {
			build.append("'").append(id).append("',");
		}
		build.deleteCharAt(build.length() - 1);

		buildResult.append(sql.replace("?", build.toString())).append("\n");

		 PreparedStatement preparedStatementProductos =con.prepareStatement(sql.replace("?", build.toString()));
//		 preparedStatementProductos.setString(1, build.toString());
		
		 // Result set get the result of the SQL query
		 ResultSet resultSet = preparedStatementProductos.executeQuery();

		 buildResult.append(mostrarResultadoConsultaProductoPorIdsEnDB(resultSet)).append("\n");
		 return buildResult.toString();
	}


	public String consultaProductoPorSubCategoriaEnDB(Set<String> idsProducto)
			throws Exception {

		StringBuilder buildResult = new StringBuilder();
		
		Connection con = getConnection();

		String sql = "SELECT * from Prod_Planos P where P.subcategoria IN(?)";

		StringBuilder build = new StringBuilder();
		for (String id : idsProducto) {
			build.append("'").append(id).append("',");
		}
		build.deleteCharAt(build.length() - 1);

		buildResult.append(sql.replace("?", build.toString())).append("\n");

		 PreparedStatement preparedStatementProductos =con.prepareStatement(sql.replace("?", build.toString()));
//		 preparedStatementProductos.setString(1, build.toString());
		
		 // Result set get the result of the SQL query
		 ResultSet resultSet = preparedStatementProductos.executeQuery();

		 buildResult.append(mostrarResultadoConsultaProductoPorIdsEnDB(resultSet)).append("\n");
		 return buildResult.toString();
	}

	
	
	public String consultaProductoPorCategoriaEnDB(Set<String> idsProducto)
			throws Exception {

		StringBuilder buildResult = new StringBuilder();
		
		Connection con = getConnection();

		String sql = "SELECT * from Prod_Planos P where P.categoria IN(?)";

		StringBuilder build = new StringBuilder();
		for (String id : idsProducto) {
			build.append("'").append(id).append("',");
		}
		build.deleteCharAt(build.length() - 1);

		buildResult.append(sql.replace("?", build.toString())).append("\n");

		 PreparedStatement preparedStatementProductos =con.prepareStatement(sql.replace("?", build.toString()));
//		 preparedStatementProductos.setString(1, build.toString());
		
		 // Result set get the result of the SQL query
		 ResultSet resultSet = preparedStatementProductos.executeQuery();

		 buildResult.append(mostrarResultadoConsultaProductoPorIdsEnDB(resultSet)).append("\n");
		 return buildResult.toString();
	}

	
	public String consultaProductoPorDescGenEnDB(Set<String> idsProducto, Connection con)
			throws Exception {

		String sql = "SELECT * from Prod_Planos P where P.DescGen IN(?)";

		StringBuilder build = new StringBuilder();
		for (String id : idsProducto) {
			build.append("'").append(id).append("',");
		}
		build.deleteCharAt(build.length() - 1);

		System.out.println(sql.replace("?", build.toString()));

		 PreparedStatement preparedStatementProductos =con.prepareStatement(sql.replace("?", build.toString()));
//		 preparedStatementProductos.setString(1, build.toString());
		
		 // Result set get the result of the SQL query
		 ResultSet resultSet = preparedStatementProductos.executeQuery();

		 return mostrarResultadoConsultaProductoPorIdsEnDB(resultSet);

	}
	
	public String consultaProductoPorIdsEnDB(Set<String> idsProducto)
			throws Exception {

		Connection con = getConnection();

		String sql = "SELECT * from Prod_Planos P where P.Prod_ID IN(?)";

		StringBuilder build = new StringBuilder();
		for (String id : idsProducto) {
			build.append("'").append(id).append("',");
		}
		build.deleteCharAt(build.length() - 1);

//		System.out.println(sql.replace("?", build.toString()));

		 PreparedStatement preparedStatementProductos =con.prepareStatement(sql.replace("?", build.toString()));
//		 preparedStatementProductos.setString(1, build.toString());
		
		 // Result set get the result of the SQL query
		 ResultSet resultSet = preparedStatementProductos.executeQuery();

		 return mostrarResultadoConsultaProductoPorIdsEnDB(resultSet);

	}

	private String mostrarResultadoConsultaProductoPorIdsEnDB(ResultSet resultSet)
			throws SQLException {

		StringBuilder build = new StringBuilder();
		
		while (resultSet.next()) {
//			P.Prod_ID, P.DescGen, P.DescAdic, P.Marca, P.Proveedor, P.CantEnvase, CAT.Cat_Desc AS categoria, SC.SubCat_Desc as subcategoria , PS.Precio AS precio
			String Prod_ID = resultSet.getString("Prod_ID");
			String DescGen = resultSet.getString("DescGen");
			String DescAdic = resultSet.getString("DescAdic");
			String Marca = resultSet.getString("Marca");
			String Proveedor = resultSet.getString("Proveedor");
			String CantEnvase = resultSet.getString("CantEnvase");
			String categoria = resultSet.getString("categoria");
			String subcategoria  = resultSet.getString("subcategoria");
			String precio = resultSet.getString("precio");

			build.append(Prod_ID).append("\t").append(DescGen).append("\t").append(DescAdic).append("\t").append(Marca).
					append("\t").append(Proveedor).append("\t").append(CantEnvase).append("\t").append(categoria).
					append("\t").append(subcategoria ).append("\t").append(precio).append("\n");
			
			
		}
		System.out.println(build);
		return build.toString();
	}

	private Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			throw e;
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.113:3306/dm-tp1", "dmkd", "dmkd");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
		}
		if (connection != null) {
			return connection;
		} else {
			throw new Exception("Failed to make connection!");
		}
	}
	
	
private void writeToFile(String stringToWrite, final String encoding,String pathYNombre) {
		
        Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathYNombre), encoding));
			writer.write(stringToWrite);
			System.out.println("output en: "+pathYNombre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Assert.fail();
				}
			}
		}
	}
/**
 * @param buildForFile
 */
private void writeOutput(StringBuilder buildForFile,String nombreArchivo) {
	
	final String path = "C:/dev/20150615_dm/";
	
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
     
	String result = path + fechaAlMomento    + nombreArchivo;
	
	writeToFile(buildForFile.toString(),ENCODE,result);
}

}