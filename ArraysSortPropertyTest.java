package deliverable_4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Arrays;

public class ArraysSortPropertyTest {

	int arrays[][];
	
	@Before
	public void setUp() throws Exception {
		
		arrays = new int[100][];
		Random gen = new Random(1337);
		
		for (int i = 0; i < arrays.length; i++) {
			
			arrays[i] = new int[gen.nextInt(100)];
			
			for (int j = 0; j < arrays[i].length; j++) {
				
				arrays[i][j] = gen.nextInt();
			}
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/*
	 * Test that the size of each array remains unchanged after calling Arrays.sort()
	 * 
	 */
	public void testArraySizeIsUnchanged() {
		
		int len;
		
		for (int i = 0; i < arrays.length; i++) {
			
			len = arrays[i].length;
			
			Arrays.sort(arrays[i]);
			
			assertEquals(len, arrays[i].length);
		}
	}
	
	@Test
	/*
	 * Test that the Arrays.sort() method is pure -- that running it twice on identical
	 *   input arrays should yield identical output arrays.
	 */
	public void testPurity() {
		
		int copy[];
		
		for (int i = 0; i < arrays.length; i++) {
			
			copy = Arrays.copyOf(arrays[i], arrays[i].length);
			
			Arrays.sort(arrays[i]);
			Arrays.sort(copy);
			
			for (int j = 0; j < arrays[i].length; j++) {
				
				assertEquals(copy[j], arrays[i][j]);
			}
		}
	}
	
	@Test
	/*
	 * Test that the Arrays.sort() method is idempotent
	 * 
	 */
	public void testIdempotence() {
		
		int copy[];
		
		for (int i = 0; i < arrays.length; i++) {
			
			Arrays.sort(arrays[i]);
			
			copy = Arrays.copyOf(arrays[i], arrays[i].length);
			
			Arrays.sort(copy);
			
			for (int j = 0; j < arrays[i].length; j++) {
				
				assertEquals(copy[j], arrays[i][j]);
			}
		}
	}
	
	@Test
	/*
	 * Test that the output array contains no sequence of decreasing values
	 * 
	 */
	public void testNoDecreasingValues() {
		
		int previous;
		
		for (int i = 0; i < arrays.length; i++) {
			
			Arrays.sort(arrays[i]);
			
			previous = Integer.MIN_VALUE;
						
			for (int j = 0; j < arrays[i].length; j++) {
				
				assertTrue(arrays[i][j] >= previous);
				
				previous = arrays[i][j];
			}
		}
	}

}
