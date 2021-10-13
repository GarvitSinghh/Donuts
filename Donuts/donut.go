package main

import (
    "fmt"
    "math"
    "time"
    "os"
    "os/exec"
    "runtime"
)

var clear map[string]func()

func init() {
    clear = make(map[string]func())
    clear["linux"] = func() { 
        cmd := exec.Command("clear")
        cmd.Stdout = os.Stdout
        cmd.Run()
    }
    clear["windows"] = func() {
        cmd := exec.Command("cmd", "/c", "cls")
        cmd.Stdout = os.Stdout
        cmd.Run()
    }
}

func CallClear() {
    value, ok := clear[runtime.GOOS]
    if ok { 
        value()
    } else {
        panic("Your platform is unsupported! I can't clear terminal screen :(")
    }
}

func main(){
	var  A, B float64 = 0.0 , 0.0
    var b [1760]byte
    var z [1760]float64
    CallClear()
    for ; ; {
    	for i := range b {
	    	b[i] = 32
	    }
	    for i := range z {
	    	z[i] = 0
	    }
        for j := 0.0; j < 6.28; j += 0.07 {
        	for i := 0.0; i < 6.28; i += 0.02 {
        		var c float64 = math.Sin(i)
                var d float64 = math.Cos(j)
                var e float64 = math.Sin(A)
                var f float64 = math.Sin(j)
                var g float64 = math.Cos(A)
                var h float64 = d + 2
                var D float64 = 1 / (c * h * e + f * g + 5)
                var l float64 = math.Cos(i)
                var m float64 = math.Cos(B)
                var n float64 = math.Sin(B)
                var t float64 = c * h * g - f * e
                var x int = int(40 + 30 * D * (l * h * m - t * n))
                var y int = int(12 + 15 * D * (l * h * n + t * m))
                var o int = int(x + 80 * y)
                var N int = int(8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n))
                if (22 > y) && (y > 0) && (x > 0) && (80 > x) && (D > z[o]) {
                	z[o] = D
                	if N > 0 {
                		b[o] = ".,-~:;=!*#$@"[N]
                	} else {
                		b[o] = ".,-~:;=!*#$@"[0]
                	}
                }
        	}
        }
        CallClear()
	    for k := 0; k < 1761; k++ {
	    	if k % 80 != 0 {
	    		fmt.Printf("%c", b[k])
	    	} else {
	    		fmt.Printf("%c", 10)
	    	}
	    	A += 0.00004
	        B += 0.00002
	    }
	    time.Sleep(5)
    }
}