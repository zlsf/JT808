
package Temp;

public class Temp {

	public static void main(String[] args) {
		String a = "91,0,0,0,90,2,36,38,13,80,18,0,0,0,3,102,0,0,0,0,0,0,18,52,-62,-77,70,72,57,57,56,77,0,0,0,0,0,0,0,0,0,0,0,0,0,2,18,2,0,0,0,36,0,17,6,7,-29,7,43,5,7,63,95,-104,2,58,-82,-36,0,0,0,0,0,0,0,0,1,46,0,0,0,12,0,-61,0,0,0,0,-84,-10,93";
		String[] ss = a.split(",");
		byte[] bytes = new byte[100];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int p = bytes[i];
			if (p > 0) {
				sb.append(Integer.toHexString(p)).append(" ");
			}
			if (p == 0) {
				sb.append(" 00 ");
			}
			if (p < 0) {
				sb.append(Integer.toHexString(bytes[i] & 0XFF) + " ");
			}
		}
		System.out.print(sb.toString());
	}

}
