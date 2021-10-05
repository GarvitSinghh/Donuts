import java.io.IOException;
import java.util.concurrent.TimeUnit;

class Donut {
	static double A = 1;
	static double B = 1;

	public static void main(String[] args) throws InterruptedException, IOException {
		final boolean isWindows = System.getProperty("os.name").contains("Windows");
		while(true) {
			if (isWindows) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				Runtime.getRuntime().exec("clear");
			}

			A += 0.07;
			B += 0.03;

			double cA = Math.cos(A),
					sA = Math.sin(A),
					cB = Math.cos(B),
					sB = Math.sin(B);

			final char[] b = new char[1760];
			final double[] z = new double[1760];

			for (int k = 0; k < 1760; k++) {
				b[k] = k % 80 == 79 ? '\n' : ' ';
				z[k] = 0;
			}

			for (double j = 0; j < 6.28; j += 0.07) {
				// j <=> theta
				double ct = Math.cos(j),
						st = Math.sin(j);
				for (double i = 0; i < 6.28; i += 0.02) {
					// i <=> phi
					double sp = Math.sin(i),
							cp = Math.cos(i),
							h = ct + 2, // R1 + R2*cos(theta)
							D = 1 / (sp * h * sA + st * cA + 5), // this is 1/z
							t = sp * h * cA - st * sA; // this is a clever factoring of some of the terms in x' and y'

					int x = (int) (40 + 30 * D * (cp * h * cB - t * sB)),
							y = (int) (12 + 15 * D * (cp * h * sB + t * cB)),
							N =
									(int) (8 *
													((st * sA - sp * ct * cA) * cB -
															sp * ct * sA -
															st * cA -
															cp * ct * sB)),
							o = x + 80 * y;
					if (y < 22 && y >= 0 && x >= 0 && x < 79 && D > z[o]) {
						z[o] = D;
						b[o] = ".,-~:;=!*#$@".charAt(Math.max(N, 0));
					}
				}
			}
			System.out.println(String.valueOf(b));

			TimeUnit.MILLISECONDS.sleep(50);
		}
	}
}