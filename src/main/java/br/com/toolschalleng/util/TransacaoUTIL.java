package br.com.toolschalleng.util;


import java.util.Random;

import org.springframework.util.ObjectUtils;

public class TransacaoUTIL {
	
	private static Long nsu = 1L;
	
	public String esconderNumCartao(String num) {
		if (ObjectUtils.isEmpty(num)) {
			return null;
		}
		String numNovo = "";
		for (int x = 0; x < num.length() - 4; x++) {
			numNovo += "*";
		}
		return numNovo + num.substring(num.length() - 4);
	}	
				
	public  String getProximoNsu() {	
        return String.valueOf(nsu++);
    }
	
	public  String getCodigoAutorizacao() {
	    StringBuilder text = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < 9; i++) {
	        text.append(random.nextInt(10)); // gerar um número aleatório entre 0 e 9 com 9 digitos
	    }
	    return text.toString();
	}
	
}
