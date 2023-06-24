fun main (args:Array<String>){
    for (n in 1.rangeTo(100)){
        if (n%3==0 && n%5==0){
            print("Apabole, ")
            continue
        }
        if (n%3==0){
            print("Apa, ")
            continue
        }
        if (n%5==0) {
            print("Bole, ")
            continue
        }
        print(n)
        print(", ")
    }
}