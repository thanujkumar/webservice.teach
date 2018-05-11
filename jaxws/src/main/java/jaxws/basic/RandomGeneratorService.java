package jaxws.basic;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

//SEI - Service Endpoint interface
//SIB - Service Implementation Bean
//Both in a single file
@WebService
public class RandomGeneratorService {

	private static final int maxRandoms = 16;

	@WebMethod // optional but helpful annotation as by default all public methods are
			   // webservice operations
	public int nextOne() {
		return new Random().nextInt();
	}

	@WebMethod
	public int[] nextN(final int size) {
		int k = (size > maxRandoms) ? maxRandoms : Math.abs(size);
		int rands[] = new int[k];
		Random r = new Random();
		for (int i = 0; i < rands.length; i++) {
			rands[i] = r.nextInt();
		}
		return rands;
	}
}
