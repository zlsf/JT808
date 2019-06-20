
package Temp;

public class Temp {

	public static void main(String[] args) {
		String a = "91,0,0,0,90,2,36,42,111,75,18,0,0,0,3,102,0,0,0,0,0,0,18,52,-62,-77,70,80,50,51,48,56,0,0,0,0,0,0,0,0,0,0,0,0,0,2,18,2,0,0,0,36,0,17,6,7,-29,8,24,0,7,50,-107,-41,2,57,38,-113,0,96,0,0,0,0,0,0,0,-71,0,0,0,8,0,3,64,0,32,0,1,-69,93";
		String[] ss = a.split(",");
		String[] mm = new String[ss.length];
		for (int i = 0; i < ss.length; i++) {
			Integer p = (Integer.parseInt(ss[i]));
			if (p > 0) {
				mm[i] = Integer.toHexString(p);
			}
			if (p == 0) {
				mm[i] = "00";
			}
			if (p < 0) {
				mm[i] = Integer.toHexString(p.byteValue() & 0XFF);
			}
			System.out.print(mm[i] + " ");
		}
	}

}
