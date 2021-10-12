<?php 
$A = $B = 0;

function asciiframe(){
    global $A,$B;

    $A += 0.07;
    $B += 0.03;

    $b = $z = [];

    $cA = cos($A);
    $sA = sin($A);
    $cB = cos($B);
    $sB = sin($B);

    for ($k = 0; $k < 1760; $k++) {
        $b[$k] = $k % 80 == 79 ? "\n" : " ";
        $z[$k] = 0;
      }

    for ($j = 0; $j < 6.28; $j += 0.07) {
    // j <=> theta
        $ct = cos($j);
        $st = sin($j);
        for ($i = 0; $i < 6.28; $i += 0.02) {
            // i <=> phi
            $sp = sin($i);
            $cp = cos($i);
            $h = $ct + 2; // R1 + R2*cos(theta)
            $D = 1 / ($sp * $h * $sA + $st * $cA + 5); // this is 1/z
            $t = $sp * $h * $cA - $st * $sA; // this is a clever factoring of some of the terms in x' and y'

            $x = 0 | (40 + 30 * $D * ($cp * $h * $cB - $t * $sB));
            $y = 0 | (12 + 15 * $D * ($cp * $h * $sB + $t * $cB));
            $o = $x + 80 * $y;
            $N = 0 |
                (8 *
                (($st * $sA - $sp * $ct * $cA) * $cB -
                    $sp * $ct * $sA -
                    $st * $cA -
                    $cp * $ct * $sB));
            if ($y < 22 && $y >= 0 && $x >= 0 && $x < 79 && $D > $z[$o]) {
                $z[$o] = $D;
                $b[$o] = ".,-~:;=!*#$@"[$N > 0 ? $N : 0];
            }   
        }
    }
    
    echo implode("",$b);

}

while(1){
    $i++;
    system('clear');
    asciiframe();
    usleep(50000); // In nanoseconds
}

?>