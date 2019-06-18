
package Temp;

public class Temp {

	public static void main(String[] args) {
		String a = "91,0,0,0,90,2,36,38,13,80,18,0,0,0,3,102,0,0,0,0,0,0,18,52,-62,-77,70,72,57,57,56,77,0,0,0,0,0,0,0,0,0,0,0,0,0,2,18,2,0,0,0,36,0,17,6,7,-29,7,43,5,7,63,95,-104,2,58,-82,-36,0,0,0,0,0,0,0,0,1,46,0,0,0,12,0,-61,0,0,0,0,-84,-10,93";
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
