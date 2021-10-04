import 'dart:io';

import 'dart:math';

void main() {
  double a = 0;
  double b = 0;

  const int height = 24;
  const int width = 80;

  print("\x1B[2J\x1B[0;0H");

  while (true) {
    List<double> z = List.filled(4 * height * width, 0.0);
    List<String> screen = List.filled(height * width, ' ');

    for (double j = 0; j < 6.28; j += 0.07) {
      for (double i = 0; i < 6.28; i += 0.02) {
        double sinA = sin(a);
        double cosA = cos(a);
        double cosB = cos(b);
        double sinB = sin(b);

        double sinI = sin(i);
        double cosI = cos(i);
        double cosJ = cos(j);
        double sinJ = sin(j);

        double cosJ2 = cosJ + 2;

        double mess = 1 / (sinI * cosJ2 * sinA + sinA * cosA + 5);
        double t = sinI * cosJ2 * cosA - sinJ * sinA;

        int x = (40 + 30 * mess * (cosI * cosJ2 * cosB - t * sinB)).toInt();

        int y = (11 + 15 * mess * (cosI * cosJ2 * sinB + t * cosB)).toInt();

        int o = (x + width * y).toInt();

        int N = (8 *
                ((sinJ * sinA - sinI * cosJ * cosA) * cosB -
                    sinI * cosJ * sinA -
                    sinJ * cosA -
                    cosI * cosJ * sinB))
            .toInt();

        if (y > 0 && y < height && x > 0 && x < width && z[o] < mess) {
          z[o] = mess;
          screen[o] = [
            '.',
            ',',
            '-',
            '~',
            ':',
            ';',
            '=',
            '!',
            '*',
            '#',
            '\$',
            '@'
          ][N > 0 ? N : 0];
        }
      }
    }
    print("\x1B[2J\x1B[0;0H");

    screen.asMap().forEach((index, value) {
      if (index % width == 0) {
        stdout.write("\n");
      } else {
        stdout.write(value);
      }
    });

    a += 0.02;
    b += 0.01;
  }
}
