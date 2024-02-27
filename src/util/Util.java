package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

public class Util {

    public static final String FORMATO_DATA_CURTA = "dd/MM/yyyy";
    public static final String FORMATO_DATA_HORA_SEM_SEGUNDOS = "dd/MM/yyyy HH:mm";
    public static final String FORMATO_DATA_HORA = "dd/MM/yyyy HH:mm:ss";
    public static final Locale PT_BR = new Locale("pt", "BR");
    
    public static boolean ehStringVazia(final String valor) {
        return valor == null || valor.isEmpty();
    }

    public static boolean ehListaVazia(final List<?> lista) {
        return lista == null || lista.isEmpty();
    }

    public static boolean ehValorVazio(final BigDecimal valor) {
        return valor == null || valor.compareTo(BigDecimal.ZERO) <= 0 || valor.toString().isEmpty();
    }
    
    /**
     * @author s.rodrigues.da.rocha
     * @param valor
     * @since 21/7/2014
     * @return boolean
     * Retorna True se a string conter apenas numeros e False se conter outro tipo de caractere
     */
    public static boolean isStringNumerica (final String valor) {
        return valor.matches("^[0-9]*$");
    }
    
    /**
     * 
     * @param valor
     * @param valorRef
     * @param tipoCom
     * @return
     */
    public static boolean isValorMaior(final BigDecimal valor, final BigDecimal valorRef, int tipoCom){
    	
    	boolean retorno = false;
    	if(tipoCom == 1){
    		if(valor.compareTo(valorRef) == 0 || valor.compareTo(valorRef) == 1){
    			return true;
    		}
    	}else{
    		if(valor.compareTo(valorRef) == 0 || valor.compareTo(valorRef) == -1){
    			return true;
    		}
    	}
    	
    	return retorno;
    }
    
    public static Double calculaPercentagem(BigDecimal vlrTotal, BigDecimal vlrEntrada) {
        Double percentagem = 0d;
        if(vlrTotal != null && vlrEntrada != null && BigDecimal.ZERO.compareTo(vlrTotal) != 0) {
            BigDecimal porcentagem = vlrEntrada.divide(vlrTotal, MathContext.DECIMAL32);
            percentagem = Double.parseDouble(porcentagem.toString());
        }
        
        return percentagem;
    }
    
    public static String converteData(XMLGregorianCalendar date) {
        String out = null;
        if(date != null) {
            SimpleDateFormat mask = new SimpleDateFormat("dd/MM/yyyy");
            out = mask.format(date.toGregorianCalendar().getTime());
        }
        return out;
    }
    
    public static String converteDataExtenco(Date date) {
        String out = null;
        if(date != null) {
            SimpleDateFormat maskDay = new SimpleDateFormat("dd", PT_BR);
            SimpleDateFormat mask = new SimpleDateFormat("MMMM", PT_BR);
            SimpleDateFormat maskYear = new SimpleDateFormat("yyyy", PT_BR);
            out = maskDay.format(date) + " de " + mask.format(date) + " de " + maskYear.format(date); 
        }
        return out;
    }
    
    public static BigDecimal converteDataExtenco(List<BigDecimal> listValores) {
        BigDecimal out = new BigDecimal(BigInteger.ZERO);
        
        for(BigDecimal valor : listValores) {
            if(valor != null) {
                out = out.add(valor);
            }
        }
        return out;
    }
	
    /** Converte Date em String no formato desejado
     * @param data
     * @param formato
     * @return
     * @autor camila.dos.s.fraga
     * @since 12/11/2014
     */
    public static final String formatarData(final Date data, final String formato) {
        String dataFormatada = null;
    	
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
        dataFormatada = simpleDateFormat.format(data);

        return dataFormatada;
    	}
    	
    /** Converte os canais UA, IB e ATM para os dominios usados na tabela 
     * LOG_CREDITO_CANAL que é populada na function FNC_Contrata_Emprestimo
     * @param canal
     * @return 
     * @autor camila.dos.s.fraga
     * @since 12/11/2014
     */
    public static final String converteCanalToLogCreditoCanal(String canal){
        String canalConv = null;
        if(canal.equalsIgnoreCase("UA")){
            canalConv = "A";
        }else if(canal.equalsIgnoreCase("IB")){
            canalConv = "B";
        }else if(canal.equalsIgnoreCase("ATM")){
            canalConv = "C";
        }
        return canalConv;
    }
    	
    public static final int diferencaEntreDatas(final Date dataFinal, final Date dataInicial) {
    	final long diferenca =  dataFinal.getTime() - dataInicial.getTime();
        return new BigDecimal(diferenca/1000/60/60/24).intValueExact();
    }
    
    /**
     * Retorna um double com duas casas decimais
     *
     * @autor camila.dos.s.fraga
     * @param vlr
     * @return
     * @since 19/11/2014
     */
    public static Double doubleComDuasCasasDec(final Double vlr) {
        final DecimalFormat df = new DecimalFormat("0.##");
        final String sSaldo = df.format(vlr).replace(",", ".");
        return Double.parseDouble(sSaldo);
    }
    
    /**
     * Retorna um double com duas casas decimais
     *
     * @autor camila.dos.s.fraga
     * @param vlr
     * @return
     * @since 19/11/2014
     */
    public static BigDecimal BigDecimalComDuasCasasDec(final BigDecimal vlr) {
        final DecimalFormat df = new DecimalFormat("0.##");
        final String sSaldo = df.format(vlr).replace(",", ".");
        return BigDecimal.valueOf(Double.parseDouble(sSaldo));
    }


}
