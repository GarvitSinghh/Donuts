import std.stdio;
import std.math;
import std.array;
import core.thread;

void main() {
    float A = 0;
    float B = 0;
    float[1760] z;
    char[1760] b;
    
    while (true) {
        b[] = ' ';
        z[] = 0.0;

        for(float j = 0.0f; j < 6.28f; j += 0.07f) {
            for(float i = 0.0f; i < 6.28f; i += 0.02f) {
                float c = sin(i);
                float d = cos(j);
                float e = sin(A);
                float f = sin(j);
                float g = cos(A);
                float h = d + 2;
                float D = 1.0 / (c * h * e + f * g + 5);
                float l = cos(i);
                float m = cos(B);
                float n = sin(B);
                float t = c * h * g - f * e;
                int x = cast(int)(40 + 30 * D * (l * h * m - t * n));
                int y = cast(int)(12 + 15 * D * (l * h * n + t * m));
                int o = x + 80 * y;
                int N = cast(int)(8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n));
                if (y < 22 && y > 0 && x > 0 && x < 80 && D > z[o]) {
                    z[o] = D;
                    b[o] = ".,-~:;=!*#$@"[N > 0 ? N : 0];
                }
            }
        }

	write("\x1b[H");
	for (size_t i = 0; i < b.length; ++i) {
	    write(b[i]);
	    if (i % 80 == 79) {
	        writeln();
	    }
	}

        A += 0.04;
        B += 0.02;

        Thread.sleep(dur!"msecs"(33));
    }
}
