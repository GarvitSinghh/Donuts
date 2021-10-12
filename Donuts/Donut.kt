import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.math.cos
import kotlin.math.sin

internal object Donut {
    var A = 1.0
    var B = 1.0
    @Throws(InterruptedException::class, IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        while (true) {
            val isWindows = System.getProperty("os.name").contains("Windows");
            if(isWindows){
                ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()
            }else{
                ProcessBuilder("clear").inheritIO().start().waitFor()
            }
            A += 0.07
            B += 0.03
            val cA = cos(A)
            val sA = sin(A)
            val cB = cos(B)
            val sB = sin(B)
            val b = CharArray(1760)
            val z = DoubleArray(1760)
            for (k in 0..1759) {
                b[k] = if (k % 80 == 79) '\n' else ' '
                z[k] = 0.0
            }
            var j = 0.0
            while (j < 6.28) {

                // j -> theta
                val ct = cos(j)
                val st = sin(j)
                var i = 0.0
                while (i < 6.28) {

                    // i -> phi
                    val sp = sin(i)
                    val cp = cos(i)
                    val h = ct + 2

                    val D = 1 / (sp * h * sA + st * cA + 5)

                    val t = sp * h * cA - st * sA
                    val x = (40 + 30 * D * (cp * h * cB - t * sB)).toInt()
                    val y = (12 + 15 * D * (cp * h * sB + t * cB)).toInt()
                    val N = (8 *
                            ((st * sA - sp * ct * cA) * cB - sp * ct * sA - st * cA - cp * ct * sB)).toInt()
                    val o = x + 80 * y
                    if (y in 0..21 && x >= 0 && x < 79 && D > z[o]) {
                        z[o] = D
                        b[o] = ".,-~:;=!*#$@"[N.coerceAtLeast(0)]
                    }
                    i += 0.02
                }
                j += 0.07
            }
            println(String(b))
            TimeUnit.MILLISECONDS.sleep(50)
        }
    }
}
